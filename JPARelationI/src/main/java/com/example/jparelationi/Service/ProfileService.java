package com.example.jparelationi.Service;

import com.example.jparelationi.Api.ApiException;
import com.example.jparelationi.DTO.ProfileDTO;
import com.example.jparelationi.Model.Customer;
import com.example.jparelationi.Model.Profile;
import com.example.jparelationi.Repository.CustomerRepository;
import com.example.jparelationi.Repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final CustomerRepository customerRepository;

    public List<Profile> findAllProfiles() {
        return profileRepository.findAll();
    }
    public void addProfile(ProfileDTO profileDTO) {
        Customer customer = customerRepository.findCustomerById(profileDTO.getCustomer_id());

        if (customer == null) {
            throw new ApiException("Customer not found");
        }
        Profile profile = new Profile(null, profileDTO.getPhoneNumber(), profileDTO.getGender(), profileDTO.getAge(), profileDTO.getEmail(), customer);
        profileRepository.save(profile);
    }
    public void updateProfile(ProfileDTO profileDTO) {

        Profile profile = profileRepository.findProfileById(profileDTO.getCustomer_id());

        if (profile == null) {
            throw new ApiException("Profile not found");
        }
        profile.setPhoneNumber(profileDTO.getPhoneNumber());
        profile.setGender(profileDTO.getGender());
        profile.setAge(profileDTO.getAge());
        profile.setEmail(profileDTO.getEmail());

        profileRepository.save(profile);

    }

//    public void deleteProfile(Integer id) {
//        Profile profile = profileRepository.findProfileById(id);
//        if (profile == null) {
//            throw new ApiException("Profile not found");
//        }
//        profileRepository.delete(profile);
//    }

}