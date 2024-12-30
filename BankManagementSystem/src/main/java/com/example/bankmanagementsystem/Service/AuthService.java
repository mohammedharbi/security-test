package com.example.bankmanagementsystem.Service;

import com.example.bankmanagementsystem.Api.ApiException;
import com.example.bankmanagementsystem.DTO.CustomerUserDTOIN;
import com.example.bankmanagementsystem.DTO.EmployeeUserDTOIN;
import com.example.bankmanagementsystem.Model.Customer;
import com.example.bankmanagementsystem.Model.Employee;
import com.example.bankmanagementsystem.Model.MyUser;
import com.example.bankmanagementsystem.Repository.AuthRepository;
import com.example.bankmanagementsystem.Repository.CustomerRepository;
import com.example.bankmanagementsystem.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;


    public List<MyUser> getAllUsers() {
        return authRepository.findAll();
    }

    public void registerCustomer(CustomerUserDTOIN customerUserDTOIN) {

        //create a user
        MyUser user = new MyUser();
        user.setUsername(customerUserDTOIN.getUsername());
        user.setPassword(customerUserDTOIN.getPassword());
        user.setEmail(customerUserDTOIN.getEmail());
        user.setName(customerUserDTOIN.getName());
        user.setRole("CUSTOMER");
        String hashedPassword = new BCryptPasswordEncoder().encode(customerUserDTOIN.getPassword());
        user.setPassword(hashedPassword);

        //create a customer
        Customer customer = new Customer();
        customer.setPhoneNumber(customerUserDTOIN.getPhoneNumber());

        //set
        user.setCustomer(customer);
        customer.setUser(user);


        customerRepository.save(customer);
    }

    public void updateCustomer(Integer user_id, CustomerUserDTOIN customerUserDTOIN) {
        MyUser user = authRepository.findMyUserById(user_id);
        if (user == null) {throw new ApiException("Wrong user id");}

        Customer customer = customerRepository.findCustomerById(user_id);
        if (customer == null) {throw new ApiException("Wrong customer id");}

        user.setName(customerUserDTOIN.getName());
        user.setEmail(customerUserDTOIN.getEmail());
        customer.setPhoneNumber( customerUserDTOIN.getPhoneNumber());

        customerRepository.save(customer);
        authRepository.save(user);
    }

    public void deleteCustomer(Integer user_id) {
        MyUser user = authRepository.findMyUserById(user_id);
        if (user == null) {throw new ApiException("Wrong user id");}
        Customer customer = customerRepository.findCustomerById(user_id);
        if (customer == null) {throw new ApiException("Wrong customer id");}

        customer.setUser(null);
        user.setCustomer(null);
        customerRepository.delete(customer);
        authRepository.delete(user);
    }

    public void registerEmployee(EmployeeUserDTOIN employeeUserDTOIN) {

        //create a user
        MyUser user = new MyUser();

        user.setUsername(employeeUserDTOIN.getUsername());
        user.setPassword(employeeUserDTOIN.getPassword());
        user.setEmail(employeeUserDTOIN.getEmail());
        user.setName(employeeUserDTOIN.getName());
        user.setRole("EMPLOYEE");
        String hashedPassword = new BCryptPasswordEncoder().encode(employeeUserDTOIN.getPassword());
        user.setPassword(hashedPassword);

        //create a Employee
        Employee employee = new Employee();
        employee.setPosition(employeeUserDTOIN.getPosition());
        employee.setSalary(employeeUserDTOIN.getSalary());

        //set
        user.setEmployee(employee);
        employee.setUser(user);


        employeeRepository.save(employee);
    }

    public void updateEmployee(Integer user_id, EmployeeUserDTOIN employeeUserDTOIN) {
        MyUser user = authRepository.findMyUserById(user_id);
        if (user == null) {throw new ApiException("Wrong user id");}

        Employee employee = employeeRepository.findEmployeeById(user_id);
        if (employee == null) {throw new ApiException("Wrong employee id");}

        user.setName(employeeUserDTOIN.getName());
        user.setEmail(employeeUserDTOIN.getEmail());
        employee.setPosition(employeeUserDTOIN.getPosition());
        employee.setSalary(employeeUserDTOIN.getSalary());

        employeeRepository.save(employee);
        authRepository.save(user);
    }

    public void deleteEmployee(Integer user_id) {
        MyUser user = authRepository.findMyUserById(user_id);
        if (user == null) {throw new ApiException("Wrong user id");}

        Employee employee = employeeRepository.findEmployeeById(user_id);
        if (employee == null) {throw new ApiException("Wrong employee id");}

        employee.setUser(null);
        user.setCustomer(null);

        employeeRepository.delete(employee);
        authRepository.delete(user);
    }
}