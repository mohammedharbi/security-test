package com.example.jparelationi.Controller;

import com.example.jparelationi.Api.ApiResponse;
import com.example.jparelationi.Model.Branch;
import com.example.jparelationi.Repository.BranchRepository;
import com.example.jparelationi.Service.BranchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/branch")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;

    @GetMapping("/old-get")
    public ResponseEntity getBranch() {
        return ResponseEntity.ok(branchService.getAllBranch());
    }

    @GetMapping("/get")
    public ResponseEntity getAllBranches(){
        return ResponseEntity.ok(branchService.getAllBranches());
    }

    //flow 1
//    @PostMapping("/add/{merchant_id}")
//    public ResponseEntity addBranch(@PathVariable Integer merchant_id, @RequestBody @Valid Branch branch) {
//        branchService.addBranch(merchant_id,branch);
//        return ResponseEntity.status(200).body(new ApiResponse("branch added"));
//    }

    @PostMapping("/add")
    public ResponseEntity addBranch(@Valid @RequestBody Branch branch) {
        branchService.addBranch(branch);
        return ResponseEntity.status(200).body(new ApiResponse("branch added"));
    }

    @PutMapping("/assign/merchant_id/{merchantId}/branch_id/{branchId}")
    public ResponseEntity assignMerchantToBranch(@PathVariable Integer merchantId, @PathVariable Integer branchId) {
        branchService.assignMerchantToBranch(merchantId, branchId);
        return ResponseEntity.status(200).body(new ApiResponse("branch assigned"));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateBranch(@PathVariable Integer id, @RequestBody Branch branch) {
        branchService.updateBranch(id, branch);
        return ResponseEntity.status(200).body(new ApiResponse("branch updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBranch(@PathVariable Integer id) {
        branchService.deleteBranch(id);
        return ResponseEntity.status(200).body(new ApiResponse("branch deleted"));
    }

    @GetMapping("/get-by-number/{number}")
    public ResponseEntity getBranchByNumber(@PathVariable Integer number) {
        return ResponseEntity.ok(branchService.getBranchByNumber(number));
    }
}