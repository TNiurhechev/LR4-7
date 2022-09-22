package ParkingSystem;

import java.time.LocalDate;
import java.util.Objects;

public class Car {
    private String model;
    private CarOwner owner;
    private String licensePlate;
    private LocalDate entryDate;
    private int paidBills;
    public Car(String model, CarOwner owner,String licensePlate){
        this.model = model;
        this.owner = owner;
        this.licensePlate = licensePlate;
        this.paidBills = 0;
        this.entryDate = LocalDate.now();
    }

    public Car(String model, CarOwner owner, String licensePlate, LocalDate entryDate, int paidBills) {
        this.model = model;
        this.owner = owner;
        this.licensePlate = licensePlate;
        this.paidBills = paidBills;
        this.entryDate = entryDate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public CarOwner getOwner() {
        return owner;
    }

    public void setOwner(CarOwner owner) {
        this.owner = owner;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public int getPaidBills() {
        return paidBills;
    }

    public void setPaidBills(int paidBills) {
        this.paidBills = paidBills;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(licensePlate, car.licensePlate);
    }

    @Override
    public String toString() {
        return "Model: " + model +
                ", " + owner.toString() +
                ", license plate: " + licensePlate +
                ", entry date: " + entryDate;
    }
}
