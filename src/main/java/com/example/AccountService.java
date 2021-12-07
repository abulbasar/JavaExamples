package com.example;

import java.util.List;

public interface AccountService {
    BankAccount loadAccount(Long accountId);
    void saveAccount(BankAccount account);
    void getAccountStatus(Long accountId);
    List<BankAccount> loadAccounts();
}
