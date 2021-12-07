package com.example;

public class BankAccount implements Comparable<BankAccount>{

    // Member variables
    // private, public: access modifier

    private Long accountId;
    private String name;
    private Double amount;
    private String status;

    public BankAccount(Long accountId, String name, Double amount, String status){
        this.setAmount(amount);
        this.accountId = accountId;
        this.name = name;
        this.status = status;
    }

    public Double getAmount(){
        return this.amount;
    }

    private void setAmount(Double value){
        if(value < 0){
            throw new RuntimeException("Negative amount is not allowed");
        }
        this.amount = value;
    }

    // Methods
    public void withdraw(Double amount){
        if(amount > this.amount){
            throw new RuntimeException("Insufficient balance");
        }
        final double balance = this.amount - amount;
        this.setAmount(balance);
    }

    public void deposit(Double amount){
        setAmount(this.amount + amount);
    }

    public void transfer(BankAccount target, Double amount){
        if(!this.isActive()){
            throw new RuntimeException("source account is not active");
        }
        if(!target.isActive()){
            throw new RuntimeException("target account is not active");
        }
        this.withdraw(amount);
        target.deposit(amount);
    }

    public boolean isActive(){
        return "Active".equals(status); // status.equals("Active")
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString(){
        return String.format("Account(id=%d, name: %s, balance: %.2f)", accountId, name, amount);
    }


    @Override
    public int compareTo(BankAccount other) {
        return - this.accountId.compareTo(other.getAccountId());
    }
}
