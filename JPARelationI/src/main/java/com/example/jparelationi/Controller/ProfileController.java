package com.example.jparelationi.Controller;

import com.example.jparelationi.Api.ApiResponse;
import com.example.jparelationi.DTO.ProfileDTO;
import com.example.jparelationi.Service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/get")
    public ResponseEntity getProfile() {
        return ResponseEntity.status(200).body(profileService.findAllProfiles());
    }

    @PostMapping("/add")
    public ResponseEntity addProfile(@RequestBody @Valid ProfileDTO profileDTO) {
        profileService.addProfile(profileDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Profile added"));
    }

    @PutMapping("/update")
    public ResponseEntity updateProfile(@RequestBody @Valid ProfileDTO profileDTO) {
        profileService.updateProfile(profileDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Profile updated"));
    }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity deleteProfile(@PathVariable Integer id) {
//        profileService.deleteProfile(id);
//        return ResponseEntity.status(200).body(new ApiResponse("Profile deleted"));
//    }
}