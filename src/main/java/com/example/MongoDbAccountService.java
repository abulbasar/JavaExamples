package com.example;

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
}
