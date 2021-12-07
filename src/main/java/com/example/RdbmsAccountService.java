package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class RdbmsAccountService implements AccountService{

    private DBSessionService dbSessionService;

    public RdbmsAccountService(DBSessionService dbSessionService) throws SQLException {
        this.dbSessionService = dbSessionService;
        this.initTable();
    }

    private void initTable() throws SQLException {
        String sql = String.format("CREATE TABLE IF NOT EXISTS %s (\n", BankAccount.TABLE_NAME)
                + " id bigint PRIMARY KEY,\n"
                + " name text NOT NULL,\n"
                + " amount real,\n"
                + " status text\n"
                + ");";
        final Connection connection = dbSessionService.getConnection();
        final Statement statement = connection.createStatement();
        statement.execute(sql);
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
