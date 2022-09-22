package DBManagement;

import ParkingSystem.Car;
import ParkingSystem.CarOwner;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingDAO implements DAO{

    private Connection getConnection(){
        DBManager dbManager = new DBManager("localhost:3306","root","12345678","parking");
        return dbManager.connect();
    }

    @Override
    public Car get(long id) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE if NOT EXISTS Parking("
                    + "Id INTEGER PRIMARY KEY auto_increment, "
                    + "Model VARCHAR(100), OwnerName VARCHAR(100),"
                    + "Balance INTEGER, LicensePlate VARCHAR(100), "
                    + "EntryDate DATE, PaidBills INTEGER);");
            ResultSet parkingSet = statement.executeQuery("SELECT * FROM Parking;");

            while(parkingSet.next()) {
                int carId = parkingSet.getInt("Id");
                String model = parkingSet.getString("Model");
                String owner = parkingSet.getString("OwnerName");
                int balance = parkingSet.getInt("Balance");
                String licensePlate = parkingSet.getString("LicensePlate");
                LocalDate entryDate = parkingSet.getDate("EntryDate").toLocalDate();
                int paidBills = parkingSet.getInt("PaidBills");
                Car c = new Car(model,new CarOwner(owner,balance),licensePlate,entryDate,paidBills);
                if(carId==id)
                    return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Car> getAll() {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE if NOT EXISTS Parking("
                    + "Id INTEGER PRIMARY KEY auto_increment, "
                    + "Model VARCHAR(100), OwnerName VARCHAR(100),"
                    + "Balance INTEGER, LicensePlate VARCHAR(100), "
                    + "EntryDate DATE, PaidBills INTEGER);");
            ResultSet parkingSet = statement.executeQuery("SELECT * FROM Parking;");
            List<Car> cars = new ArrayList<Car>();
            while(parkingSet.next()) {
                String model = parkingSet.getString("Model");
                String owner = parkingSet.getString("OwnerName");
                int balance = parkingSet.getInt("Balance");
                String licensePlate = parkingSet.getString("LicensePlate");
                LocalDate entryDate = parkingSet.getDate("EntryDate").toLocalDate();
                int paidBills = parkingSet.getInt("PaidBills");
                cars.add(new Car(model,new CarOwner(owner,balance),licensePlate,entryDate,paidBills));
            }
            return cars;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Object o) throws SQLException {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE if NOT EXISTS Parking("
                    + "Id INTEGER PRIMARY KEY auto_increment, "
                    + "Model VARCHAR(100), OwnerName VARCHAR(100),"
                    + "Balance INTEGER, LicensePlate VARCHAR(100), "
                    + "EntryDate DATE, PaidBills INTEGER);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Car c = (Car)o;
        java.sql.Date date = Date.valueOf(c.getEntryDate());
        PreparedStatement insertCar = connection.prepareStatement("INSERT INTO " +
        "Parking(Model,OwnerName,Balance,LicensePlate,EntryDate,PaidBills) VALUES (?,?,?,?,?,?)");
        insertCar.setString(1,c.getModel());
        insertCar.setString(2,c.getOwner().getName());
        insertCar.setInt(3,c.getOwner().getBalance());
        insertCar.setString(4,c.getLicensePlate());
        insertCar.setDate(5,date);
        insertCar.setInt(6,c.getPaidBills());
        insertCar.executeUpdate();
    }

    @Override
    public void update(Object o, String[] params) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE if NOT EXISTS Parking("
                    + "Id INTEGER PRIMARY KEY auto_increment, "
                    + "Model VARCHAR(100), OwnerName VARCHAR(100),"
                    + "Balance INTEGER, LicensePlate VARCHAR(100), "
                    + "EntryDate DATE, PaidBills INTEGER);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Car c = (Car)o;
        //TODO
    }

    @Override
    public void delete(Object o) throws SQLException {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE if NOT EXISTS Parking("
                    + "Id INTEGER PRIMARY KEY auto_increment, "
                    + "Model VARCHAR(100), OwnerName VARCHAR(100),"
                    + "Balance INTEGER, LicensePlate VARCHAR(100), "
                    + "EntryDate DATE, PaidBills INTEGER);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Car c = (Car)o;
        PreparedStatement deleteCar = connection.prepareStatement("DELETE FROM Parking" +
                " WHERE LicensePlate=?");
        deleteCar.setString(1,c.getLicensePlate());
        deleteCar.executeUpdate();
    }
}
