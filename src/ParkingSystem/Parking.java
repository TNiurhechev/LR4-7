package ParkingSystem;

import DBManagement.DBManager;
import DBManagement.ParkingDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.Iterator;
import java.util.Comparator;
import java.util.Collections;

public class Parking {
    private List<Car> cars;
    private int monthlyBill;
    private List<Archive> carsArchive;
    private LocalDate currentDate;
    {
        cars = new ArrayList<Car>();
        monthlyBill = 200;
        carsArchive = new ArrayList<Archive>();
        currentDate = LocalDate.now();
        DBManager dbManager = new DBManager("localhost:3306","root","12345678","parking");
        Connection connection = dbManager.connect();
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
    }
    public Parking(){}
    public Parking(List<Car> cars) throws SQLException {
        for(Car c:cars) {
            addCar(c);
        }
    }
    public Parking(List<Car> cars,int monthlyBill) throws SQLException {
        for(Car c:cars)
            addCar(c);
        this.monthlyBill = monthlyBill;
    }

    public void updateCars(){
        ParkingDAO p = new ParkingDAO();
        cars = p.getAll();
    }

    public void setCars(List<Car> cars) throws SQLException {
        for(Car c:cars)
            addCar(c);
    }

    public List<Car> getCars() {
        ParkingDAO p = new ParkingDAO();
        cars = p.getAll();
        return cars;
    }

    public void setMonthlyBill(int monthlyBill) {
        this.monthlyBill = monthlyBill;
    }

    public int getMonthlyBill() {
        return monthlyBill;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void addCar(Car car) throws SQLException {
        for(Car c:cars){
            if(c.equals(car)) {
                System.out.println("Car with a such registration number is already in the parking!");
                return;
            }
        }
            car.setEntryDate(LocalDate.now());
            cars.add(car);
            ParkingDAO p = new ParkingDAO();
            p.save(car);
            updateCars();
    }
    public void removeCar(Car car) throws SQLException{
        cars.remove(car);
        ParkingDAO p = new ParkingDAO();
        p.delete(car);
        updateCars();
        carsArchive.add(new Archive(car.getModel(),car.getLicensePlate(),car.getOwner().getName(),
        car.getEntryDate(),LocalDate.now()));
    }
    public void printCars(){
        System.out.println("Cars currently in the parking lot:");
        updateCars();
        for(Car car:cars)
            System.out.println(car.toString());
    }
    public List<Bill> billReport(String field, char sortMode){
        List<Bill> bills = new ArrayList<Bill>();
        updateCars();
        Iterator iterator = cars.iterator();
        while (iterator.hasNext()){
            Car c = (Car) iterator.next();
            Period period = Period.between(this.getCurrentDate(), c.getEntryDate());
            int unpaidMonths = Math.abs(period.getMonths());
            if (c.getOwner().getBalance() < this.getMonthlyBill() * unpaidMonths) {
                System.out.println("Car with license plate " + c.getLicensePlate() + " which belongs to " +
                c.getOwner().getName() + " will now be kicked off\nparking due to owner's inability to pay for the parking!");
                carsArchive.add(new Archive(c.getModel(),c.getLicensePlate(),c.getOwner().getName(),
                c.getEntryDate(),LocalDate.now()));
                iterator.remove();
            } else {
                Bill b = new Bill(c.getModel(), c.getLicensePlate(), c.getOwner().getName(), this.getMonthlyBill() * unpaidMonths);
                bills.add(b);
                int newBalance = c.getOwner().getBalance() - this.getMonthlyBill() * unpaidMonths;
                try {
                    c.getOwner().setBalance(newBalance);
                } catch (NegativeBalanceException e) {
                    e.getMessage();
                }
            }
        }
        switch(field){
            case "owner":
                Collections.sort(bills, new Comparator<Bill>() {
                    @Override
                    public int compare(Bill o1, Bill o2) {
                        return o1.getOwnerName().compareTo(o2.getOwnerName());
                    }
                });
                if(sortMode=='r')
                    Collections.reverse(bills);
                break;
            case "plate":
                Collections.sort(bills, new Comparator<Bill>() {
                    @Override
                    public int compare(Bill o1, Bill o2) {
                        return o1.getLicensePlate().compareTo(o2.getLicensePlate());
                    }
                });
                if(sortMode=='r')
                    Collections.reverse(bills);
                break;
            default:
                break;
        }
        return bills;
    }
    public List<String> getJournal(LocalDate from, LocalDate to){
        List<String> journal = new ArrayList<String>();
        updateCars();
        for(Car c:cars){
            if(c.getEntryDate().isBefore(to)&&!c.getEntryDate().isAfter(to)){
                String report = c.getModel()+" with the license plate "+c.getLicensePlate()+
                " entered the parking on "+c.getEntryDate();
                journal.add(report);
            }
        }
        for(Archive a:carsArchive){
            if((a.getEntryDate().isAfter(from)&&a.getEntryDate().isBefore(to))){
                String report = a.getCarModel()+" with the license plate "+a.getCarLicensePlate()+
                " entered the parking on "+a.getEntryDate();
                journal.add(report);
            }
            if(a.getExitDate().isAfter(from)&&a.getExitDate().isBefore(to)){
                String report = a.getCarModel()+" with the license plate "+a.getCarLicensePlate()+
                " left the parking on "+a.getExitDate();
                journal.add(report);
            }
        }
        return journal;
    }
    public List<String> carReportGUI(Car car){
        List<String> reports = new ArrayList<>();
        updateCars();
        for(Car c:cars){
            if(c.equals(car)){
                reports.add("Car "+car.getModel()+" with the license plate "+car.getLicensePlate()
                        +" is in parking since "+car.getEntryDate());
            }
        }
        for(Archive a:carsArchive){
            if(a.getCarLicensePlate().equals(car.getLicensePlate())){
                reports.add("Car "+car.getModel()+" with the license plate "+car.getLicensePlate()
                        +" is entered parking  on "+a.getEntryDate()+" and left on "+a.getExitDate());
            }
        }
        return reports;
    }
    public void carReport(Car car){
        updateCars();
        for(Car c:cars){
            if(c.equals(car)){
                System.out.println("Car "+car.getModel()+" with the license plate "+car.getLicensePlate()
                +" is in parking since "+car.getEntryDate());
            }
        }
        for(Archive a:carsArchive){
            if(a.getCarLicensePlate().equals(car.getLicensePlate())){
                System.out.println("Car "+car.getModel()+" with the license plate "+car.getLicensePlate()
                +" is entered parking  on "+a.getEntryDate()+" and left on "+a.getExitDate());
            }
        }
    }
    public List<String> ownerReportGUI(CarOwner owner){
        List<String> reports = new ArrayList<>();
        updateCars();
        for(Car c:cars){
            if(c.getOwner().equals(owner)){
                reports.add("Currently in parking: "+c.toString());
            }
        }
        for(Archive a:carsArchive){
            if(a.getCarOwnerName().equals(owner.getName())){
                reports.add("From archive: "+a.toString());
            }
        }
        return reports;
    }
    public void ownerReport(CarOwner owner){
        System.out.println("All cars belonging to "+owner.getName()+":");
        updateCars();
        for(Car c:cars){
            if(c.getOwner().equals(owner)){
                System.out.println("Currently in parking: "+c.toString());
            }
        }
        for(Archive a:carsArchive){
            if(a.getCarOwnerName().equals(owner.getName())){
                System.out.println("From archive: "+a.toString());
            }
        }
    }

    public void menu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        char choice = 'z';
        System.out.println("----------Welcome to your parking management app!----------");
        while(choice!='x') {
            System.out.println("\tFeel free to exit any moment - just press \'x\'");
            System.out.println("\tOther options:");
            System.out.println("\tTo add a car to the parking, press \'a\'");
            System.out.println("\tTo get a report on your bills, press \'b\'");
            System.out.println("\tTo get a report on a specific car, press \'c\'");
            System.out.println("\tTo change current date, press \'d\'");
            System.out.println("\tTo get an in-out journal, press \'j\'");
            System.out.println("\tTo get a report on a specific owner, press \'o\'");
            System.out.println("\tTo get print all cars currently in the parking, press \'p\'");
            System.out.println("\tTo remove a car from parking, press \'r\'");
            System.out.print("\tEnter your option: ");
            String ch = scanner.next();
            choice = ch.charAt(0);
            switch (choice){
                case 'a':
                    System.out.print("Enter car's license plate number: ");
                    String licensePlate = scanner.next();
                    System.out.print("Enter car owner's name: ");
                    String owner = scanner.next();
                    System.out.print("Enter car owner's balance: ");
                    int balance = scanner.nextInt();
                    System.out.print("Enter car's model: ");
                    String model = scanner.next();
                    Car c = new Car(model,new CarOwner(owner,balance),licensePlate);
                    this.addCar(c);
                    break;
                case 'b':
                    System.out.print("Enter a field for sort(owner/license plate): ");
                    String field = scanner.next();
                    System.out.print("Enter sorting mode(\'r\' for reverse): ");
                    String m = scanner.next();
                    char mode = m.charAt(0);
                    List<Bill> bills = this.billReport(field,mode);
                    for(Bill b:bills)
                        System.out.println(b.toString());
                    break;
                case 'c':
                    System.out.print("Enter car's model: ");
                    String cModel = scanner.next();
                    System.out.print("Enter car's license plate number: ");
                    String cLicensePlate = scanner.next();
                    System.out.print("Enter car owner's name: ");
                    String cOwner = scanner.next();
                    System.out.print("Enter car owner's balance: ");
                    int cBalance = scanner.nextInt();
                    Car car = new Car(cModel,new CarOwner(cOwner,cBalance),cLicensePlate);
                    this.carReport(car);
                    break;
                case 'd':
                    System.out.print("Enter new date.Start with days: ");
                    int dd = scanner.nextInt();
                    System.out.print("Continue with months: ");
                    int mm = scanner.nextInt();
                    System.out.print("Finally, enter year: ");
                    int yy = scanner.nextInt();
                    this.setCurrentDate(LocalDate.of(yy,mm,dd));
                    System.out.println("New date set!");
                    break;
                case 'j':
                    System.out.print("Enter the first date.Start with days: ");
                    int dd1 = scanner.nextInt();
                    System.out.print("Continue with months: ");
                    int mm1 = scanner.nextInt();
                    System.out.print("Finally, enter year: ");
                    int yy1 = scanner.nextInt();
                    LocalDate from = LocalDate.of(yy1,mm1,dd1);
                    System.out.print("Now enter the second date.Start with days: ");
                    int dd2 = scanner.nextInt();
                    System.out.print("Continue with months: ");
                    int mm2 = scanner.nextInt();
                    System.out.print("Finally, enter year: ");
                    int yy2 = scanner.nextInt();
                    LocalDate to = LocalDate.of(yy2,mm2,dd2);
                    List<String> journal = this.getJournal(from,to);
                    for(String s:journal)
                        System.out.println(s);
                    break;
                case 'o':
                    System.out.print("Enter owner's name: ");
                    String name = scanner.next();
                    this.ownerReport(new CarOwner(name));
                    break;
                case 'p':
                    this.printCars();
                    break;
                case 'r':
                    System.out.print("Enter car's license plate number: ");
                    String dLicensePlate = scanner.next();
                    System.out.print("Enter car owner's name: ");
                    String dOwner = scanner.next();
                    System.out.print("Enter car owner's balance: ");
                    int dBalance = scanner.nextInt();
                    System.out.print("Enter car's model: ");
                    String dModel = scanner.next();
                    Car del = new Car(dModel,new CarOwner(dOwner,dBalance),dLicensePlate);
                    this.removeCar(del);
                    break;
                case 'x':
                    System.out.println("\t\t\tThanks for using this application! Have a nice day!\n" +
                    "\t\tCopyright © 2022-present, Tymur Niurhechev. All rights reserved");
                    return;
                default:
                    System.out.println("Invalid argument!");
                    return;
            }
        }
    }
}