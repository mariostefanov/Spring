package com.example.spring_data_intro.services.impl;

import com.example.spring_data_intro.repositories.AccountRepository;
import com.example.spring_data_intro.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal balance, Long id) {

    }

    @Override
    public void transferMoney(BigDecimal balance, Long id) {

    }
}
