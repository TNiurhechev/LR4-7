import DBManagement.ParkingDAO;
import ParkingSystem.Car;
import ParkingSystem.CarOwner;
import ParkingSystem.Parking;
import ParkingSystem.ParkingGUI;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        ParkingGUI parkingGUI = new ParkingGUI();
        parkingGUI.setVisible(true);
        /*Parking parking = new Parking();
        CarOwner mick = new CarOwner("Pavlov",880);
        Car chevy = new Car("Chevrolet Silverado",mick,"BE3450AB");
        Car bmw = new Car("BMW 535d",new CarOwner("Kalnichenko",550),"AA7523TX");
        Car evo = new Car("Mitsubishi Lancer Evolution 9",mick,"BE1308KC");
        Car renault = new Car("Renault Megane",new CarOwner("Dubonenko",550),"AX4890IO");
        List<Car> carList = new ArrayList<>();
        carList.add(chevy);
        carList.add(bmw);
        carList.add(evo);
        carList.add(renault);
        parking.setCars(carList);
        parking.removeCar(bmw);
        ParkingDAO p = new ParkingDAO();
        List<Car> dbList = p.getAll();
        for (Car c:
             dbList) {
            System.out.println(c.toString());
        }
        Car first = p.get(3);
        System.out.println("Second car in table: "+first.toString());
        parking.menu();*/
    }
}