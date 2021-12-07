package com.example;

import static com.example.common.Utils.println;

public class Ex02 {
    /*
    * Define a class
    * Define member variables
    * Define methods
    * Define constructor
    * Define toString method (override)
    * Define getter and setter
    * private and public access modifier
    * Throw a new exception
    * How to reach stack trace
    * */
    public static void main(String[] args){
        final BankAccount abulAccount = new BankAccount(1L, "Abul", 100.0, "Active");
        final BankAccount rahulAccount = new BankAccount(2L, "Rahul", 200.0, "Active");
        println(String.format("Abul account: %s", abulAccount));
        println(String.format("Rahul account: %s", rahulAccount));
        println("Class name: " + BankAccount.class.getName());
        abulAccount.withdraw(50.0);
        println(abulAccount);

        rahulAccount.transfer(abulAccount, 100.0);
        println(abulAccount);
        println(rahulAccount);
    }
}
