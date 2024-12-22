package com.example.jparelationi.Controller;

import com.example.jparelationi.Api.ApiResponse;
import com.example.jparelationi.Model.Merchant;
import com.example.jparelationi.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    @GetMapping("/get")
    public ResponseEntity getMerchant() {
        return ResponseEntity.status(200).body(merchantService.findAllMerchant());
    }

    @PostMapping("/add")
    private ResponseEntity addMerchant(@RequestBody @Valid Merchant merchant) {
        merchantService.addMerchant(merchant);
        return ResponseEntity.status(200).body(new ApiResponse("merchant added"));
    }

    @PutMapping("/update/{id}")
    private ResponseEntity updateMerchant(@PathVariable Integer id, @RequestBody @Valid Merchant merchant) {
        merchantService.updateMerchant(id, merchant);
        return ResponseEntity.status(200).body(new ApiResponse("merchant updated"));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity deleteMerchant(@PathVariable Integer id) {
        merchantService.deleteMerchant(id);
        return ResponseEntity.status(200).body(new ApiResponse("merchant deleted"));
    }

    @PutMapping("/assign-merchant-to-customer/merchant/{merchant_id}/customer/{customer_id}")
    public ResponseEntity assignMerchantToCustomer(@PathVariable Integer merchant_id, @PathVariable Integer customer_id) {
        merchantService.assignMerchantToCustomer(merchant_id, customer_id);
        return ResponseEntity.status(200).body(new ApiResponse("merchant assigned to customer"));
    }

}
