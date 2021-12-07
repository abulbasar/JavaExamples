package com.example;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class Ex03 {

    /*

    https://www.sqlitetutorial.net/sqlite-tutorial/sqlite-describe-table/
    * Open sqlite database
    * sqlite3 /tmp/sqlite.db
    *
    * Show tables
    * .tables
    *
    * Drop table
    * drop table account
    *
    * Show schema
    * .schema account
    *
    *
    * */
    public static void main(String[] args) throws SQLException {
        String backend = "RDBMS";
        AccountService accountService;

        final Random random = new Random();

        if("RDBMS".equals(backend)){
            String connStr = "jdbc:sqlite:///tmp/sqlite.db";
            final DBSessionService dbSessionService = new DBSessionService(connStr);
            dbSessionService.connect();
            accountService = new RdbmsAccountService(dbSessionService);
            final BankAccount account = new BankAccount(random.nextLong(), "Abul", 100.0, "Active");
            accountService.saveAccount(account);
            final List<BankAccount> bankAccounts = accountService.loadAccounts();
            final BankAccount bankAccount = accountService.loadAccount(5L);
            dbSessionService.close();
        }else if("Mongodb".equals(backend)){
            accountService = new MongoDbAccountService();
        }else{
            throw new RuntimeException("Invalid backend: " + backend);
        }
    }
}
