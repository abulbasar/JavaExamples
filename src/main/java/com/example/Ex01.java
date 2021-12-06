package com.example;

import com.example.common.Utils;

import java.util.List;

import static com.example.common.Utils.*;

public class Ex01 {

    public static void main(String[] args){
        System.out.println("Hello World");
        final PrimeNumberService primeNumberService = new PrimeNumberService();
        int n = 10;
        boolean prime = primeNumberService.isPrime(n);
        System.out.println(String.format("Number %d is prime? %s", n, prime));

        long startTime = System.currentTimeMillis(); // return unix timestamp
        List<Integer> primes = primeNumberService.findNPrimes(10);
        long duration = System.currentTimeMillis() - startTime;
        for (Integer value : primes) {
            println(value);
        }
        println("My prime function took ", duration, "ms");

        int[] frequencyOfLastDigits = primeNumberService.findFrequencyOfLastDigits(10, 1000);
        for (int i = 0; i < frequencyOfLastDigits.length; i++) {
            println(String.format("Digit %d, frequency: %d", i, frequencyOfLastDigits[i]));
        }

        System.exit(10);
    }

}
