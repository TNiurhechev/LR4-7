package ParkingSystem;

import java.util.Objects;

public class CarOwner {
    private String name;
    private int balance;
    public CarOwner(String name, int balance){
        this.name = name;
        this.balance = balance;
    }

    public CarOwner(String name){
        this.name = name;
        this.balance = 200;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) throws NegativeBalanceException {
        if(balance<0)
            throw new NegativeBalanceException("Balance can't be negative");
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CarOwner carOwner = (CarOwner) o;
        return Objects.equals(name, carOwner.name);
    }

    @Override
    public String toString() {
        return "Owner's name: " + name;
    }
}
