package com.example.services;

import com.example.exceptions.InsufficientBalanceException;
import com.example.exceptions.RecordNotException;
import com.example.models.BankAccount;

import java.sql.SQLException;
import java.util.List;

public interface AccountService {
    BankAccount loadAccount(Long accountId) throws SQLException, RecordNotException;
    void saveAccount(BankAccount account) throws SQLException;
    void getAccountStatus(Long accountId);
    List<BankAccount> loadAccounts() throws SQLException;
    void withdraw(long accountId, double amount) throws SQLException, RecordNotException, InsufficientBalanceException;
    void deposit(long accountId, double amount) throws SQLException, RecordNotException;
    void transfer(long sourceAccountId, long targetAccountId, double amount) throws SQLException, InsufficientBalanceException, RecordNotException;
    boolean isActive(long accountId) throws SQLException, RecordNotException;
}
