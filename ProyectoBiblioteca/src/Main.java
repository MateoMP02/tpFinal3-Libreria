import Clases.*;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Biblioteca biblioteca = new Biblioteca("Mi Biblioteca");
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {


        //Cargar clientes desde un JSON
        biblioteca.cargarClientesDesdeJson("clientes");

        // Muestra todos los clientes cargados
        HashMap<Integer, Cliente> clienteHashMap= biblioteca.getHashMapDeClientes();
        for (Cliente cliente : clienteHashMap.values())
        {
            System.out.println(cliente);
        }

        // Cargar libros desde un archivo JSON
        biblioteca.cargarLibrosDesdeJson("libros");

        System.out.println(biblioteca.getHashMapDeLibros());
        int opcion;

        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    Cliente nuevoCliente = crearNuevoCliente(scanner);
                    biblioteca.agregarCliente(nuevoCliente.getIdCliente(), nuevoCliente);
                    System.out.println("Cliente agregado exitosamente.");
                    break;
                case 2:
                    Libro nuevoLibro = crearNuevoLibro(scanner,biblioteca);
                    biblioteca.agregarLibro(nuevoLibro.getISBN(), nuevoLibro);
                    System.out.println("Libro agregado exitosamente.");
                    break;
                case 3:
                    System.out.print("Ingrese el ID del cliente a buscar: ");
                    int idCliente = scanner.nextInt();
                    Cliente cliente = biblioteca.buscarCliente(idCliente);
                    System.out.println(cliente != null ? cliente : "Cliente no encontrado.");
                    break;
                case 4:
                    System.out.print("Ingrese el ISBN del libro a buscar: ");
                    int isbn = scanner.nextInt();
                    Libro abuscar = biblioteca.buscarLibros(isbn);
                    if (abuscar != null) {
                        System.out.println(abuscar);
                    } else {
                        System.out.println("Libro no encontrado.");
                    }
                    break;
                case 5:
                    System.out.print("Ingrese el ID del cliente a eliminar: ");
                    int idClienteEliminar = scanner.nextInt();
                    biblioteca.eliminarCliente(idClienteEliminar);
                    System.out.println("Cliente eliminado exitosamente.");
                    break;
                case 6:
                    System.out.print("Ingrese el ISBN del libro a eliminar: ");
                    int isbnEliminar = scanner.nextInt();
                    biblioteca.eliminarLibro(isbnEliminar);
                    System.out.println("Libro eliminado exitosamente.");
                    break;
                case 7 :
                    int op;
                    System.out.println("Ingrese la opcion de busqueda");
                    System.out.println("1. Por genero");
                    System.out.println("2. Por autor");
                    System.out.println("0. Volver");
                    op = scanner.nextInt();
                    switch (op){
                        case 1:
                            ArrayList<String> generos = biblioteca.obtenerGenerosDisponibles();
                            System.out.println("Géneros disponibles:");
                            for (String genero : generos) {
                                System.out.println("- " + genero);
                            }
                            System.out.print("Ingrese el género que desea buscar: ");
                            scanner.nextLine();
                            String generoBuscado = scanner.nextLine();
                            ArrayList<Libro> librosPorGenero = biblioteca.buscarLibrosPorGenero(generoBuscado);
                            if (librosPorGenero.isEmpty()) {
                                System.out.println("No se encontraron libros para el género " + generoBuscado);
                            } else {
                                System.out.println("Libros encontrados:");
                                for (Libro libro : librosPorGenero) {
                                    System.out.println(libro);
                                }
                            }
                            break;
                        case 2:
                            System.out.print("Ingrese el autor que desea buscar: ");
                            scanner.nextLine();
                            String autorBuscado = scanner.nextLine();
                            ArrayList<Libro> librosPorAutor = biblioteca.buscarLibrosPorAutor(autorBuscado);
                            if (librosPorAutor.isEmpty()) {
                                System.out.println("No se encontraron libros del autor " + autorBuscado);
                            } else {
                                System.out.println("Libros encontrados:");
                                for (Libro libro : librosPorAutor) {
                                    System.out.println(libro);
                                }
                            }
                            break;
                        default:
                            System.out.println("Opcion invalida");
                            break;
                    }
                    break;
                case 8 :
                    System.out.println("Ingrese el ISBN del libro que quiera agregar copias");
                    int ISBN = scanner.nextInt();
                    biblioteca.agregarCopiaDeLibro(ISBN);
                    // Mostrar el número de copias del libro actualizado
                    Libro libroActualizado = biblioteca.buscarLibros(ISBN);
                    if (libroActualizado != null) {
                        System.out.println("Número de copias del libro con ISBN " + ISBN + ": " + libroActualizado.getCopias());
                    } else {
                        System.out.println("El libro con ISBN " + ISBN + " no se encontró en la biblioteca.");
                    }

                    // Guardar el estado actualizado de la biblioteca en el archivo JSON
                    biblioteca.guardarLibrosEnJSON();

                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 0);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Menú Biblioteca ---");
        System.out.println("1. Agregar nuevo cliente");
        System.out.println("2. Agregar nuevo libro");
        System.out.println("3. Buscar cliente por ID");
        System.out.println("4. Buscar libro por ISBN");
        System.out.println("5. Eliminar cliente por ID");
        System.out.println("6. Eliminar libro por ISBN");
        System.out.println("7. Buscar libros por ...");
        System.out.println("8. Agrega una copia de un libro");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static Cliente crearNuevoCliente(Scanner scanner) {
        System.out.print("Ingrese el nombre y apellido del cliente: ");
        String nombreYapellido = scanner.nextLine();
        System.out.print("Ingrese la edad del cliente: ");
        int edad = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        System.out.print("Ingrese el país: ");
        String pais = scanner.nextLine();
        System.out.print("Ingrese la provincia: ");
        String provincia = scanner.nextLine();
        System.out.print("Ingrese la ciudad: ");
        String ciudad = scanner.nextLine();
        System.out.print("Ingrese la calle y altura: ");
        String calleYaltura = scanner.nextLine();
        Domicilio domicilio = new Domicilio(calleYaltura, ciudad, pais, provincia);
        System.out.print("Ingrese el ID del cliente: ");
        int idCliente = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        System.out.print("Ingrese el correo electrónico del cliente: ");
        String correoElectronico = scanner.nextLine();
        return new Cliente(nombreYapellido, edad, domicilio, idCliente, correoElectronico);
    }



    public static Libro crearNuevoLibro(Scanner scanner, Biblioteca biblioteca) {
        int ISBN;
        boolean existeISBN;

        do {
            System.out.print("Ingrese el ISBN del libro: ");
            ISBN = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            existeISBN = biblioteca.buscarLibros(ISBN) != null;

            if (existeISBN) {
                System.out.println("El ISBN ingresado ya existe. Intente con otro.");
            }
        } while (existeISBN);

        System.out.print("Ingrese el título del libro: ");
        String titulo = scanner.nextLine();

        System.out.print("Ingrese el autor del libro: ");
        String autor = scanner.nextLine();

        System.out.print("Ingrese el género del libro: ");
        String genero = scanner.nextLine();

        double precio;
        do {
            System.out.print("Ingrese el precio del libro: ");
            precio = scanner.nextDouble();
            scanner.nextLine(); // Consumir el salto de línea
            if (precio <= 0) {
                System.out.println("El precio debe ser un número positivo. Inténtelo de nuevo.");
            }
        } while (precio <= 0);

        return new Libro(ISBN, titulo, autor, genero, precio);
    }
}
