package com.javaguides.banking_app.service.impl;

import com.javaguides.banking_app.dto.AccountDto;
import com.javaguides.banking_app.entity.Account;
import com.javaguides.banking_app.mapper.AccountMapper;
import com.javaguides.banking_app.repository.AccountRepository;
import com.javaguides.banking_app.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapTOAccountDto(savedAccount);

    }

    @Override
    public AccountDto getAccountById(Long Id) {
     Account account =  accountRepository.findById(Id).orElseThrow(()-> new RuntimeException("Account Doesn't Exist"));

        return AccountMapper.mapTOAccountDto(account);
    }

    @Override
    public AccountDto deposite(Long id, double amount) {
        Account account =  accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account Doesn't Exist"));

        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapTOAccountDto(savedAccount);

    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account =  accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account Doesn't Exist"));

        if(account.getBalance() < amount){
            throw new RuntimeException("Insufficient amount");
        }

        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount =  accountRepository.save(account);

        return AccountMapper.mapTOAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccount() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account) -> AccountMapper.mapTOAccountDto(account)).collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account =  accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account Doesn't Exist"));

        accountRepository.deleteById(id);

    }
}
