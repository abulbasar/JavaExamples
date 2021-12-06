package com.example;



import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.example.common.Utils.*;

public class Ex01 {

    public static void main(String[] args){
        Instant now = Instant.now();
        println("Current time: ", now.toString());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
        println("Current date: ", dateFormat.format(Date.from(now)));

        //println("Current date: ", new SimpleDateFormat("dd/MMM/yyyy").format(Date.from(Instant.now())));

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

        Map<Integer, Integer> frequencyOfLastDigitsV2 = primeNumberService.findFrequencyOfLastDigitsV2(10, 1000);
        for (Map.Entry<Integer, Integer> pair : frequencyOfLastDigitsV2.entrySet()) {
            println(String.format("(V2) Digit: %d, frequency: %d", pair.getKey(), pair.getValue()));
        }

        Set<Integer> lastDigits = primeNumberService.findLastDigits(10, 1000);
        System.out.print("Last digits: ");
        for (Integer lastDigit : lastDigits) {
            System.out.print(lastDigit);
            System.out.print(" ");
        }
        println();


        System.exit(10);
    }

}
