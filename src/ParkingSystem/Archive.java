package ParkingSystem;

import java.time.LocalDate;

public class Archive {
    private String carModel;
    private String carLicensePlate;
    private String carOwnerName;
    private LocalDate entryDate;
    private LocalDate exitDate;
    public Archive(String carModel, String carLicensePlate, String carOwnerName, LocalDate entryDate, LocalDate exitDate){
        this.carModel = carModel;
        this.carLicensePlate = carLicensePlate;
        this.carOwnerName = carOwnerName;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getCarLicensePlate() {
        return carLicensePlate;
    }

    public String getCarOwnerName() {
        return carOwnerName;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public LocalDate getExitDate() {
        return exitDate;
    }

    @Override
    public String toString() {
        return "Car model: " + carModel +
                ", license plate: " + carLicensePlate +
                ", owner's name: " + carOwnerName +
                ", entry date: " + entryDate +
                ", exit date: " + exitDate;
    }
}
