package com.example.services;

import com.example.exceptions.InsufficientBalanceException;
import com.example.exceptions.RecordNotException;
import com.example.models.BankAccount;

import java.sql.SQLException;
import java.util.List;

public class MongoDbAccountService implements AccountService{

    public MongoDbAccountService(){
        throw new RuntimeException("Class is not yet implemented");
    }

    @Override
    public BankAccount loadAccount(Long accountId) {
        return null;
    }

    @Override
    public void saveAccount(BankAccount account) {

    }

    @Override
    public void getAccountStatus(Long accountId) {

    }

    @Override
    public List<BankAccount> loadAccounts() {
        return null;
    }

    @Override
    public void withdraw(long accountId, double amount) throws SQLException, RecordNotException, InsufficientBalanceException {

    }

    @Override
    public void deposit(long accountId, double amount) throws SQLException, RecordNotException {

    }

    @Override
    public void transfer(long sourceAccountId, long targetAccountId, double amount) throws SQLException, InsufficientBalanceException, RecordNotException {

    }
}
