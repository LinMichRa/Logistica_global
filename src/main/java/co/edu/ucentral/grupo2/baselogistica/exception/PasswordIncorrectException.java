package co.edu.ucentral.grupo2.baselogistica.exception;

public class PasswordIncorrectException extends RuntimeException {

    public PasswordIncorrectException() {
        super("La contraseña es inválida.");
    }
}