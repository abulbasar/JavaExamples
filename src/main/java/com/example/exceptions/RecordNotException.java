package com.example.exceptions;

public class RecordNotException extends Exception{
    private long id;
    public RecordNotException(long id){
        super(String.format("Record is not found for id: %d", id));
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
