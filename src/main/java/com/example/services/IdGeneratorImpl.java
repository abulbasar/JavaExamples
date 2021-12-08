package com.example.services;

import static com.example.services.Helper.random;

public class IdGeneratorImpl implements IdGenerator{
    @Override
    public long generateId() {
        return Math.abs(Integer.valueOf(random.nextInt()).longValue());
    }
}
