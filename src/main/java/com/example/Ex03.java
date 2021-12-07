package com.example;

import java.io.Closeable;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class Ex03 implements Closeable {

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

    DBSessionService dbSessionService;
    AccountService accountService;

    private DBSessionService getDbSessionService(){
        if(dbSessionService == null) {
            String connStr = "jdbc:sqlite:///tmp/sqlite.db";
            dbSessionService =new DBSessionService(connStr);
            dbSessionService.connect();
        }
        return dbSessionService;
    }

    private AccountService getAccountService(String backend) throws SQLException {
        if(accountService == null) {
            if ("RDBMS".equals(backend)) {
                final DBSessionService dbSessionService = getDbSessionService();
                accountService = new RdbmsAccountService(this.dbSessionService);
            } else if ("Mongodb".equals(backend)) {
                accountService = new MongoDbAccountService();
            } else {
                throw new RuntimeException("Invalid backend: " + backend);
            }
        }
        return accountService;
    }

    public void start() throws SQLException {
        String backend = "RDBMS";
        AccountService accountService = getAccountService(backend);
        final Random random = new Random();
        final BankAccount account = new BankAccount(random.nextLong(), "Abul", 100.0, "Active");
        accountService.saveAccount(account);
        final List<BankAccount> bankAccounts = accountService.loadAccounts();
        final BankAccount bankAccount = accountService.loadAccount(5L);
        System.out.println("Process is complete");
    }

    @Override
    public void close(){
        if(dbSessionService != null){
            dbSessionService.close();
        }
    }

    public Ex03(){

    }


    public static void main(String[] args) throws SQLException {
        final Ex03 app = new Ex03();
        app.start();
        app.close();
    }
}
