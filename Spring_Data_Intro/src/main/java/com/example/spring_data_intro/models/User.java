package com.example.spring_data_intro.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true,nullable = false)
    private String username;

    private int age;

    @OneToMany(targetEntity = Account.class,mappedBy = "users")
    private Set<Account> accounts;

    public User() {
        this.accounts = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public User setId(long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public int getAge() {
        return age;
    }

    public User setAge(int age) {
        this.age = age;
        return this;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public User setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
        return this;
    }
}
