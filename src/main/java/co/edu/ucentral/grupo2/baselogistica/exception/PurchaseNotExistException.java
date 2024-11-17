package co.edu.ucentral.grupo2.baselogistica.exception;

public class PurchaseNotExistException extends RuntimeException {

    public PurchaseNotExistException() {
        super("La factura no existe.");
    }
}
