package Excepciones;

public class LibroNoAlquiladoException extends Exception {
    public LibroNoAlquiladoException(String mensaje) {
        super(mensaje);
    }
}