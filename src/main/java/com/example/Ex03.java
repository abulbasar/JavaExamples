package com.example;

import com.example.exceptions.RecordNotException;
import com.example.models.BankAccount;
import com.example.services.*;

import java.io.Closeable;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class Ex03 implements Closeable {

    /*

    Install sqlite3 on ubuntu
    sudo apt install sqlite3 -y

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

    ConnectionProvider connectionProvider;
    AccountService accountService;
    final Random random = new Random();

    private ConnectionProvider getConnectionProvider(){
        if(connectionProvider == null) {
            String connStr = "jdbc:sqlite:///tmp/sqlite.db";
            connectionProvider =new ConnectionProvider(connStr);
            connectionProvider.connect();
        }
        return connectionProvider;
    }

    private AccountService getAccountService(String backend) throws SQLException {
        if(accountService == null) {
            if ("RDBMS".equals(backend)) {
                final ConnectionProvider connectionProvider = getConnectionProvider();
                accountService = new RdbmsAccountService(connectionProvider);
            } else if ("Mongodb".equals(backend)) {
                accountService = new MongoDbAccountService();
            } else {
                throw new RuntimeException("Invalid backend: " + backend);
            }
        }
        return accountService;
    }



    public void start() throws SQLException, RecordNotException {
        String backend = "RDBMS";
        AccountService accountService = getAccountService(backend);
        final BankAccount sample = Helper.createSample();
        accountService.saveAccount(sample);
        try {
            accountService.saveAccount(sample);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            System.err.println(e.getErrorCode());
        }

        for (int i = 0; i < 10; i++) {
            final BankAccount account = Helper.createSample();
            accountService.saveAccount(account);
        }
        final List<BankAccount> bankAccounts = accountService.loadAccounts();
        long accountId = 6854842105732104592L;
        try {
            final BankAccount bankAccount = accountService.loadAccount(accountId);
            throw new RuntimeException("Dummy exception");
        }catch (RecordNotException e){
            System.err.println("Account is not found: " + accountId);
        }
        System.out.println("Process is complete");
    }

    @Override
    public void close(){
        if(connectionProvider != null){
            connectionProvider.close();
        }
    }

    public Ex03(){

    }


    public static void main(String[] args) throws SQLException, RecordNotException {
        final Ex03 app = new Ex03();
        try {
            app.start();
        }finally {
            app.close();
        }

    }
}
