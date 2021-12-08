package com.example;

public class Calculator {
    public static int add(int i, int j){
        return i+j;
    }
    public static int subtract(int i, int j){
        return i-j;
    }
    public static int multiply(int i, int j){
        return i*j;
    }
    public static float divide(int i, int j){
        return i * 1.0f/j;
    }
    public static double pow(int i, int j){
        return Math.pow(i, j);
    }
}
