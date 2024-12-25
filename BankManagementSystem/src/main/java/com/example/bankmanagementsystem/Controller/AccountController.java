package com.example.bankmanagementsystem.Controller;

import com.example.bankmanagementsystem.Api.ApiResponse;
import com.example.bankmanagementsystem.Model.Account;
import com.example.bankmanagementsystem.Model.MyUser;
import com.example.bankmanagementsystem.Service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/add-account")
    public ResponseEntity addNewAccount(@AuthenticationPrincipal MyUser user, @RequestBody @Valid Account account) {
        accountService.addNewAccount(user.getId(), account);
        return ResponseEntity.status(200).body(new ApiResponse("Account created"));
    }

    @PutMapping("/active-account/{index}")
    public ResponseEntity activeAccount(@AuthenticationPrincipal MyUser user, @PathVariable Integer index) {
        accountService.activeAccount(index);
        return ResponseEntity.status(200).body(new ApiResponse("Account activated"));
    }

    @GetMapping("/get-all-my-accounts")
    public ResponseEntity getAllMyAccounts(@AuthenticationPrincipal MyUser user) {
        return ResponseEntity.status(200).body(accountService.getAllMyAccounts(user.getId()));
    }

    @PutMapping("/deposit/{index}/amount/{amount}")
    public ResponseEntity deposit(@AuthenticationPrincipal MyUser user, @PathVariable Integer index, @PathVariable Integer amount) {
        accountService.deposit(user.getId(), index, amount);
        return ResponseEntity.status(200).body(new ApiResponse("amount deposited"));
    }

    @PutMapping("/withdraw/{index}/amount/{amount}")
    public ResponseEntity withdraw(@AuthenticationPrincipal MyUser user, @PathVariable Integer index, @PathVariable Integer amount) {
        accountService.withdraw(user.getId(), index, amount);
        return ResponseEntity.status(200).body(new ApiResponse("amount withdrew"));
    }

    @PutMapping("/transfer/my-account/{indexFirst}/to-account/{indexSecond}/amount/{amount}")
    public ResponseEntity transfer(@AuthenticationPrincipal MyUser user, @PathVariable Integer indexFirst, @PathVariable Integer indexSecond, @PathVariable Integer amount) {
        accountService.transfer(user.getId(), indexFirst, indexSecond, amount);
        return ResponseEntity.status(200).body(new ApiResponse("amount transferred"));
    }

    @PutMapping("/block-account/{index}")
    public ResponseEntity blockAccount(@AuthenticationPrincipal MyUser user, @PathVariable Integer index) {
        accountService.blockAccount(index);
        return ResponseEntity.status(200).body(new ApiResponse("account blocked"));
    }


}
