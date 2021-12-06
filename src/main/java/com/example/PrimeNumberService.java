package com.example;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumberService {

    public boolean isPrime(int n){
        boolean result = true;
        for(int i=2;i<n;++i){
            if(n%i == 0){
                result = false;
                break;
            }
        }
        return result;
    }

    /*
    This function checks whether a number is a prime
    @param n: natural integer
    @returns: boolean whether n is prime
     */
    public boolean isPrimeV2(int n){
        boolean result = true;
        int i = 2;
        while (i<n){
            if(n%i == 0){
                result = false;
                break;
            }
            ++i; // i = i+1;
        }
        return result;
    }

    public List<Integer> findNPrimes(int n){
        final List<Integer> result = new ArrayList<>();
        int i = 2;
        while(result.size() < n){
            if(isPrime(i)){
                result.add(i);
            }
            ++i;
        }
        return result;
    }

    public List<Integer> findPrimes(int lower, int upper){
        final List<Integer> result = new ArrayList<>();
        int i = lower;
        while(i<upper){
            if(isPrime(i)){
                result.add(i);
            }
            ++i;
        }
        return result;
    }

    public List<Integer> findPrimesByCount(int count){
        return findPrimesByCount(2, count);
    }
    public List<Integer> findPrimesByCount(int lower, int count){
        final List<Integer> result = new ArrayList<>();
        int i = lower;
        while(result.size()<count){
            if(isPrime(i)){
                result.add(i);
            }
            ++i;
        }
        return result;
    }

    public int[] findFrequencyOfLastDigits(int lower, int count){
        final int[] result = new int[10];
        List<Integer> primes = findPrimesByCount(lower, count);
        for (Integer prime : primes) {
            int lastDigit = prime % 10;
            result[lastDigit]++;
        }
        return result;
    }

}
