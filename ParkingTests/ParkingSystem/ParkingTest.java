/*
package ParkingSystem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ParkingTest {

    private Parking parking;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        parking = new Parking();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        parking = null;
    }

    @org.junit.jupiter.api.Test
    void addCar() throws SQLException {
        parking.addCar(new Car("Mercedes-Benz E300d",new CarOwner("Udovychenko",1000),"AB3490OO"));
        parking.addCar(new Car("Audi A6 3.0",new CarOwner("Smith",300),"AB3490OO"));
        Assertions.assertEquals(1,parking.getCars().size());
        parking.addCar(new Car("BMW 520i",new CarOwner("Petrov",800),"AI4589BI"));
        Car test = new Car("Opel Ascona",new CarOwner("Zholobov",105),"BE7772TA");
        parking.addCar(test);
        Assertions.assertEquals(3,parking.getCars().size());
    }

    @org.junit.jupiter.api.Test
    void removeCar() throws SQLException {
        parking.addCar(new Car("Mercedes-Benz E300d",new CarOwner("Udovychenko",1000),"AB3490OO"));
        parking.addCar(new Car("Audi A6 3.0",new CarOwner("Smith",300),"AB3490OO"));
        parking.addCar(new Car("BMW 520i",new CarOwner("Petrov",800),"AI4589BI"));
        Car testNotInParking = new Car("Chevrolet Cruise",new CarOwner("Voloshchuk",45),"AX3250EE");
        Car testInParking = new Car("Opel Ascona",new CarOwner("Zholobov",105),"BE7772TA");
        parking.addCar(testInParking);
        parking.removeCar(testNotInParking);
        Assertions.assertEquals(3,parking.getCars().size());
        parking.removeCar(testInParking);
        Assertions.assertEquals(2,parking.getCars().size());
    }

    @org.junit.jupiter.api.Test
    void printCars() throws SQLException {
        parking.addCar(new Car("Audi A6 3.0",new CarOwner("Smith",300),"AB3490OO"));
        parking.addCar(new Car("BMW 520i",new CarOwner("Petrov",800),"AI4589BI"));
        parking.printCars();
    }

    @org.junit.jupiter.api.Test
    void billReport() throws SQLException {
        parking.addCar(new Car("Mercedes-Benz E300d",new CarOwner("Udovychenko",1000),"AB3490OO"));
        parking.addCar(new Car("BMW 520i",new CarOwner("Petrov",800),"AI4589BI"));
        Car test = new Car("Opel Ascona",new CarOwner("Zholobov",105),"BE7772TA");
        parking.addCar(test);
        parking.setCurrentDate(LocalDate.of(2022,5,12));
        Assertions.assertEquals(2,parking.billReport("name",'a').size());
    }

    @org.junit.jupiter.api.Test
    void getJournal() throws SQLException {
        parking.addCar(new Car("Mercedes-Benz E300d",new CarOwner("Udovychenko",1000),"AB3490OO"));
        parking.addCar(new Car("BMW 520i",new CarOwner("Petrov",800),"AI4589BI"));
        Car test = new Car("Opel Ascona",new CarOwner("Zholobov",105),"BE7772TA");
        parking.addCar(test);
        parking.setCurrentDate(LocalDate.of(2022,5,12));
        Assertions.assertEquals(3,
        parking.getJournal(LocalDate.of(2022,2,1),LocalDate.of(2022,6,27)).size());
    }

    @org.junit.jupiter.api.Test
    void carReport() throws SQLException {
        parking.addCar(new Car("Mercedes-Benz E300d",new CarOwner("Udovychenko",1000),"AB3490OO"));
        parking.addCar(new Car("BMW 520i",new CarOwner("Petrov",800),"AI4589BI"));
        parking.carReport(new Car("BMW 520i",new CarOwner("Petrov",800),"AI4589BI"));
    }

    @org.junit.jupiter.api.Test
    void ownerReport() throws SQLException {
        parking.addCar(new Car("Mercedes-Benz E300d",new CarOwner("Udovychenko",1000),"AB3490OO"));
        parking.addCar(new Car("BMW 520i",new CarOwner("Petrov",800),"AI4589BI"));
        parking.ownerReport(new CarOwner("Udovychenko",1000));
    }

    @org.junit.jupiter.api.Test
    void menu() {
    }

    @ParameterizedTest
    @ValueSource(ints = {2})
    public void parametrized(int count) throws SQLException {
        parking.addCar(new Car("Audi A6",new CarOwner("Eric",320),"AH0388TY"));
        parking.addCar(new Car("Kozak-2M",new CarOwner("AFU",600),"KA2363IP"));
        assertEquals(count,parking.getCars().size());
    }
    @org.junit.jupiter.api.Test
    public void exceptionTest() {
        CarOwner test = new CarOwner("Anderson",200);
        NegativeBalanceException thrown = Assertions.assertThrows(NegativeBalanceException.class,(

                )->test.setBalance(-100),"NegativeBalanceException was expected to be thrown, but nothing was thrown!");
        Assertions.assertTrue(thrown.getMessage().equals("Balance can't be negative"));
    }
}*/
