package ParkingSystem;

public class NegativeBalanceException extends Exception{
    String message;
    public NegativeBalanceException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}