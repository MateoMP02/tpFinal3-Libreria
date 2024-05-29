import Clases.*;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Biblioteca biblioteca = new Biblioteca("Mi Biblioteca");
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        generarListaDeLibros(biblioteca);
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


    public static void generarListaDeLibros(Biblioteca biblioteca) {
        String[] titulos = {
                "El Quijote", "Cien Años de Soledad", "Don Juan Tenorio", "La Odisea",
                "Hamlet", "El Principito", "Moby Dick", "Orgullo y Prejuicio",
                "Crimen y Castigo", "1984", "Drácula", "Frankenstein"
        };
        String[] autores = {
                "Miguel de Cervantes", "Gabriel Garcia Marquez", "José Zorrilla", "Homero",
                "William Shakespeare", "Antoine de Saint-Exupéry", "Herman Melville", "Jane Austen",
                "Fyodor Dostoevsky", "George Orwell", "Bram Stoker", "Mary Shelley"
        };
        String[] generos = {
                "Aventura", "Aventura", "Aventura", "Ciencia Ficción", "Ciencia Ficción",
                "Ciencia Ficción", "Drama", "Drama", "Drama", "Terror", "Terror", "Terror"
        };
        double[] precios = {19.99, 25.99, 14.99, 29.99, 9.99, 17.99, 21.99, 13.99, 18.99, 16.99, 22.99, 20.99};

        for (int i = 0; i < titulos.length; i++) {
            int isbn = 1000000 + i + 1;  // Incremental ISBN
            Libro libro = new Libro(isbn, titulos[i], autores[i], generos[i], precios[i]);
            biblioteca.agregarLibro(isbn, libro);
        }
    }
}
