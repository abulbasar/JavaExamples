package com.example.services;

import com.example.models.BankAccount;

import java.util.Random;

public class Helper {

    public final static Random random = new Random();

    public static BankAccount createSample(){
        String[] statuses = new String[]{"Active", "Inactive", "Suspended"};
        String name = "Test name " + random.nextInt();
        Double amount = Math.abs(random.nextDouble());
        String status = statuses[random.nextInt(statuses.length)];
        long id = Math.abs(random.nextLong());
        return new BankAccount(id, name, amount, status);
    }
}
