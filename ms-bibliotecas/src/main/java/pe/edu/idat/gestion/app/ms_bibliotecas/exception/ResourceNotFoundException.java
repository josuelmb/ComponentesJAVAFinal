package pe.edu.idat.gestion.app.ms_bibliotecas.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}