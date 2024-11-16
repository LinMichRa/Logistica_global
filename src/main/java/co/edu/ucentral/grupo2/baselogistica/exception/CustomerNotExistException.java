package co.edu.ucentral.grupo2.baselogistica.exception;

public class CustomerNotExistException extends RuntimeException {


    public CustomerNotExistException() {
        super("El usuario ingresado no existe.");
    }
}
