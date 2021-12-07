package com.example;

import java.util.List;

public class Ex03 {
    public static void main(String[] args){
        String backend = "RDBMS";
        AccountService accountService;
        if("RDBMS".equals(backend)){
            String connStr = "jdbc:sqlite:///tmp/sqlite.db";
            final DBSessionService dbSessionService = new DBSessionService(connStr);
            dbSessionService.connect();
            accountService = new RdbmsAccountService(dbSessionService);
            dbSessionService.close();
        }else if("Mongodb".equals(backend)){
            accountService = new MongoDbAccountService();
        }else{
            throw new RuntimeException("Invalid backend: " + backend);
        }
        final List<BankAccount> bankAccounts = accountService.loadAccounts();
        accountService.loadAccount(1L);
    }
}
