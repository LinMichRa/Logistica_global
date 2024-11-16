package co.edu.ucentral.grupo2.baselogistica.exception;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException() {
        super("No tiene los permisos necesarios.");
    }
}
