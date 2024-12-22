package com.example.jparelationi.Controller;

import com.example.jparelationi.Api.ApiResponse;
import com.example.jparelationi.Model.Customer;
import com.example.jparelationi.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/old-get")
    public ResponseEntity GetAllCustomers() {
        return ResponseEntity.status(200).body(customerService.findAllCustomers());
    }

    @GetMapping("/get")
    public ResponseEntity findCustomers(){
        return ResponseEntity.ok(customerService.findCustomers());
    }
    @PostMapping("add")
    public ResponseEntity addCustomer(@RequestBody @Valid Customer customer) {

        customerService.addCustomer(customer);
        return ResponseEntity.status(200).body(new ApiResponse("Customer added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateCustomer(@PathVariable Integer id, @RequestBody @Valid Customer customer) {
        customerService.updateCustomer(id, customer);
        return ResponseEntity.status(200).body(new ApiResponse("Customer updated"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.status(200).body(new ApiResponse("Customer deleted"));
    }
}
