package com.example.services;

import com.example.models.BankAccount;
import org.sqlite.util.StringUtils;

import java.util.Random;

public class Helper {

    public final static Random random = new Random();

    // Anonymous class
    public final static IdGenerator generator = new IdGenerator() {
        @Override
        public long generateId() {
            return Math.abs(Integer.valueOf(random.nextInt()).longValue());
        }
    };
    // Lambda expression with return
    public final static IdGenerator idGeneratorUsingLambda = () -> {
        return Math.abs(Integer.valueOf(random.nextInt()).longValue());
    };

    // Lambda expression without return
    // This is applicable when the body of the lambda is a single statement as expression
    public final static IdGenerator idGeneratorUsingLambdaV2 = ()
            -> Math.abs(Integer.valueOf(random.nextInt()).longValue());

    public static BankAccount createSample(){
        String[] statuses = new String[]{"Active", "Inactive", "Suspended"};
        String name = "Test name " + random.nextInt();
        Double amount = Math.abs(random.nextDouble());
        String status = statuses[random.nextInt(statuses.length)];
        long id = idGeneratorUsingLambdaV2.generateId();
        return new BankAccount(id, name, amount, status);
    }

    public static String randomString(int lower, int upper){
        int length = random.nextInt(upper-lower) + lower;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int base =  random.nextBoolean()?65:97;
            final char c = (char) (random.nextInt(26) + base);
            sb.append(c);
        }
        return sb.toString();
    }
}
