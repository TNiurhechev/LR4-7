package ParkingSystem;

public class Bill {
    private String model;
    private String licensePlate;
    private String ownerName;
    private int bill;
    public Bill(String model,String licensePlate,String owner,int bill){
        this.model = model;
        this.licensePlate = licensePlate;
        this.ownerName = owner;
        this.bill = bill;
    }

    public String getModel() {
        return model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public int getBill() {
        return bill;
    }

    @Override
    public String toString() {
        return ownerName+" is set to pay "+bill+" UAH for the parking of "+model+" with the license plate "+licensePlate;
    }
}
