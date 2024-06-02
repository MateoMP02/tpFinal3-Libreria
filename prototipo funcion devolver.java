public void devolverLibro(Libro libro, Cliente cliente) throws ClienteNoEncontradoException, LibroNoEncontradoException, LibroNoAlquiladoException {
    // Verificar si el cliente existe
    Cliente clienteEncontrado = buscarCliente(cliente.getIdCliente());
    if (clienteEncontrado == null) {
        throw new ClienteNoEncontradoException("Cliente no encontrado");
    }

    // Verificar si el libro existe
    Libro libroEncontrado = buscarLibros(libro.getISBN());
    if (libroEncontrado == null) {
        throw new LibroNoEncontradoException("Libro no encontrado");
    }

    // Verificar si el cliente tiene el libro en posesión
    if (!clienteEncontrado.getLibrosEnPosecion().contains(libro)) {
        throw new LibroNoAlquiladoException("El cliente no tiene este libro alquilado");
    }

    // Devolver el libro
    clienteEncontrado.getLibrosEnPosecion().remove(libro);
    libro.agregarCopiaLibro();  // Aumentar la cantidad de copias disponibles
}

package Excepciones;

public class LibroNoAlquiladoException extends Exception {
    public LibroNoAlquiladoException(String mensaje) {
        super(mensaje);
    }
}
