package com.example;

import java.sql.*;
import java.util.ArrayList;
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
    public BankAccount loadAccount(Long accountId) throws SQLException {
        final String sql = String.format("select * from %s where id = %d", BankAccount.TABLE_NAME, accountId);
        final Connection connection = dbSessionService.getConnection();
        final Statement statement = connection.createStatement();
        final ResultSet resultSet = statement.executeQuery(sql);
        BankAccount result = null;
        while (resultSet.next()){
            result = loadAccount(resultSet);
            break;
        }
        return result;
    }

    @Override
    public void saveAccount(BankAccount account) throws SQLException {
        final String statement = String.format("insert into %s (id, name, status, amount) values (?,?,?,?)", BankAccount.TABLE_NAME);
        final Connection connection = dbSessionService.getConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setLong(1, account.getAccountId());
        preparedStatement.setString(2, account.getName());
        preparedStatement.setDouble(4, account.getAmount());
        preparedStatement.setString(3, account.getStatus());
        final int i = preparedStatement.executeUpdate();
    }

    @Override
    public void getAccountStatus(Long accountId) {

    }

    @Override
    public List<BankAccount> loadAccounts() throws SQLException {
        final String sql = String.format("select * from %s", BankAccount.TABLE_NAME);
        final Connection connection = dbSessionService.getConnection();
        final Statement statement = connection.createStatement();
        final ResultSet resultSet = statement.executeQuery(sql);
        List<BankAccount> result = new ArrayList<>();
        while (resultSet.next()){
            final BankAccount account = loadAccount(resultSet);
            result.add(account);
        }
        return result;
    }

    private BankAccount loadAccount(ResultSet resultSet) throws SQLException {
        final BankAccount account = new BankAccount();
        account.setAccountId(resultSet.getLong("id"));
        account.setName(resultSet.getString("name"));
        account.setStatus(resultSet.getString("status"));
        account.setAmount(resultSet.getDouble("amount"));
        return account;
    }


}
