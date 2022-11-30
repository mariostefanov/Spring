package com.example.spring_data_intro.services.impl;

import com.example.spring_data_intro.models.Account;
import com.example.spring_data_intro.repositories.AccountRepository;
import com.example.spring_data_intro.repositories.UserRepository;
import com.example.spring_data_intro.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final UserRepository userRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal money, Long id) {
        Optional<Account> optAccount = accountRepository.findAccountById(id);

        if (optAccount.isPresent()) {
            BigDecimal balance = optAccount.get().getBalance();
            if (balance.compareTo(money) == 0) {
                optAccount.get().setBalance(balance.subtract(money));
            } else
                throw new IllegalStateException("Balance is not enough for this operation!");
        } else
            throw new IllegalStateException("Account does not exist!");
    }


}
