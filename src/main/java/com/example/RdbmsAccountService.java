package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class RdbmsAccountService implements AccountService{

    private DBSessionService dbSessionService;

    public RdbmsAccountService(DBSessionService dbSessionService){
        this.dbSessionService = dbSessionService;
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
}
