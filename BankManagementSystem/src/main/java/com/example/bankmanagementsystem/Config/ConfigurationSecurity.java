package com.example.bankmanagementsystem.Config;

import com.example.bankmanagementsystem.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigurationSecurity {

    private final MyUserDetailsService myUserDetailsService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/customer/add-customer","/api/v1/employee/add-employee").permitAll()
                .requestMatchers("/api/v1/account/add-account","/api/v1/customer/update-customer", "/api/v1/customer/delete-customer","/api/v1/account/get-all-my-accounts","/api/v1/account/deposit/","/api/v1/account/withdraw/", "/api/v1/account/transfer/my-account/").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/employee/update-employee", "/api/v1/employee/delete-employee", "/api/v1/account/active-account/**").hasAuthority("EMPLOYEE")
                .requestMatchers("/api/v1/account/block-account/","/api/v1/user/get-all-users").hasAnyAuthority("EMPLOYEE","ADMIN")
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutUrl("/api/v1/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();

    }
}
