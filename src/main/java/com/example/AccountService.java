package com.example;

import java.sql.SQLException;
import java.util.List;

public interface AccountService {
    BankAccount loadAccount(Long accountId) throws SQLException;
    void saveAccount(BankAccount account) throws SQLException;
    void getAccountStatus(Long accountId);
    List<BankAccount> loadAccounts() throws SQLException;
}
