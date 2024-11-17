package co.edu.ucentral.grupo2.baselogistica.exception;

public class EmailValidationException extends RuntimeException {

    public EmailValidationException(){
        super("El email no tiene el formato requerido.");
    }
}