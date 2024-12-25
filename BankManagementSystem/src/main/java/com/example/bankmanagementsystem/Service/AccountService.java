package com.example.bankmanagementsystem.Service;

import com.example.bankmanagementsystem.Api.ApiException;
import com.example.bankmanagementsystem.Model.Account;
import com.example.bankmanagementsystem.Model.MyUser;
import com.example.bankmanagementsystem.Repository.AccountRepository;
import com.example.bankmanagementsystem.Repository.AuthRepository;
import com.example.bankmanagementsystem.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AuthRepository authRepository;

    public void addNewAccount(Integer user_id, Account account) {
        MyUser user = authRepository.findMyUserById(user_id);
        if (user == null) {throw new ApiException("Wrong user id");}

        account.setCustomer(user.getCustomer());
        accountRepository.save(account);
    }

    //Active a bank account

    public void activeAccount(Integer index) {

        Account account = accountRepository.findAccountById(index);
        if (account == null) {throw new ApiException("Wrong account id");}

        account.setIsActive(true);
        accountRepository.save(account);
    }

    //List user's accounts
    public List<Account> getAllMyAccounts(Integer user_id) {
        MyUser user = authRepository.findMyUserById(user_id);
        if (user == null) {throw new ApiException("Wrong user id");}

        List<Account> accounts = accountRepository.findAccountByCustomerId(user.getId());
        return accounts;
    }

    //6. Deposit and withdraw money
    public void deposit(Integer user_id, Integer index, Integer amount) {
        MyUser user = authRepository.findMyUserById(user_id);
        if (user == null) {throw new ApiException("Wrong user id");}

        Account account = accountRepository.findAccountById(index);
        if (account == null) {throw new ApiException("Wrong account id");}
        if (!account.getIsActive()){throw new ApiException("Account is not active");}

        if (!account.getCustomer().getId().equals(user.getId())) {throw new ApiException("customer doesn't own this account");}

        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    public void withdraw(Integer user_id, Integer index, Integer amount) {
        MyUser user = authRepository.findMyUserById(user_id);
        if (user == null) {throw new ApiException("Wrong user id");}

        Account account = accountRepository.findAccountById(index);
        if (account == null) {throw new ApiException("Wrong account id");}

        if (!account.getCustomer().getId().equals(user.getId())) {throw new ApiException("customer doesn't own this account");}

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
    }

    //Transfer funds between accounts
    public void transfer(Integer user_id, Integer indexFirstAccount,Integer indexSecondAccount, Integer amount) {
        MyUser user = authRepository.findMyUserById(user_id);
        if (user == null) {throw new ApiException("Wrong user id");}

        Account account = accountRepository.findAccountById(indexFirstAccount);
        if (account == null) {throw new ApiException("Wrong account id");}
        if (!account.getIsActive()){throw new ApiException("from Account is not active");}

        Account account2 = accountRepository.findAccountById(indexSecondAccount);
        if (account2 == null) {throw new ApiException("Wrong account id");}
        if (!account2.getIsActive()){throw new ApiException("to Account is not active");}

        if (!account.getCustomer().getId().equals(user.getId())) {throw new ApiException("customer doesn't own this account");}

        account.setBalance(account.getBalance() - amount);
        account2.setBalance(account2.getBalance() + amount);
        accountRepository.save(account);
        accountRepository.save(account2);

    }

    //Block bank account
    public void blockAccount(Integer index) {
        Account account = accountRepository.findAccountById(index);
        if (account == null) {throw new ApiException("Wrong account id");}
        if (!account.getIsActive()){throw new ApiException("Account is already not active");}

        account.setIsActive(false);
        accountRepository.save(account);
    }
}
