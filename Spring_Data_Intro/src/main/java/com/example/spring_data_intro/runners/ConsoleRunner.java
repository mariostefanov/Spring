package com.example.spring_data_intro.runners;

import com.example.spring_data_intro.models.Account;
import com.example.spring_data_intro.models.User;
import com.example.spring_data_intro.services.AccountService;
import com.example.spring_data_intro.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final AccountService accountService;
    private final UserService userService;

    public ConsoleRunner(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User("Pesho", 20);

        Account account = new Account(new BigDecimal("25000"));

        account.setUser(user);

        user.setAccounts(new HashSet<>() {{
            add(account);
        }});

        userService.registerUser(user);

        //accountService.withdrawMoney(new BigDecimal("20000"),account.getId());
        //accountService.withdrawMoney(new BigDecimal("30000"),account.getId());
    }
}
