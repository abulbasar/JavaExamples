package com.example.common;

public class Utils {
    public static void println(Object... objects){
        for (Object object : objects) {
            System.out.print(object);
        }
        System.out.println();
    }
}
