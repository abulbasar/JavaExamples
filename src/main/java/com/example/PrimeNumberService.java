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
}
