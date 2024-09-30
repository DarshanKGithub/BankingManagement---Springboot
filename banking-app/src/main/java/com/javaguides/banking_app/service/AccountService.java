package com.javaguides.banking_app.service;

import com.javaguides.banking_app.dto.AccountDto;

import java.util.List;


public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(Long Id);
    AccountDto deposite(Long id, double amount);
    AccountDto withdraw(Long id, double amount);

    List<AccountDto> getAllAccount();

    void deleteAccount(Long id);
}
