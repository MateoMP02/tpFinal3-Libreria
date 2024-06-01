import Clases.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static Clases.Constantes.NOMBRE_ARCHIVO_CLIENTES;
import static Clases.Constantes.NOMBRE_ARCHIVO_LIBROS;

public class Main {

    private static Biblioteca biblioteca = new Biblioteca("Mi Biblioteca");
    static Scanner scanner = new Scanner(System.in);



    public static void main(String[] args) {


        //Cargar clientes desde un JSON
        biblioteca.cargarClientesDesdeJson(NOMBRE_ARCHIVO_CLIENTES);

        /*// Muestra todos los clientes cargados
        HashMap<Integer, Cliente> clienteHashMap= biblioteca.getHashMapDeClientes();

        for (Cliente cliente : clienteHashMap.values())
        {
            System.out.println(cliente);
        }*/

        // Cargar libros desde un archivo JSON
        biblioteca.cargarLibrosDesdeJson(NOMBRE_ARCHIVO_LIBROS);

        Libro libro=biblioteca.buscarLibros(284756472);

        Cliente cliente=biblioteca.buscarCliente(789456);

        biblioteca.agregarRegistro(new RegistroAlquiler(123,libro,cliente));
        try {
            biblioteca.cargarRegistroAlquileresToJson("alquileres");
            biblioteca.cargarRegistroAlquilerDesdeJson("alquileres");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        System.out.println(biblioteca.getHashMapAlquileres().obtenerTodos());

        /*System.out.println(biblioteca.getHashMapDeLibros());*/
        int opcion;

        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    crear();
                    break;
                case 2:
                    busquedaCliente();
                    break;
                case 3:
                    baja();
                    break;
                case 4:
                    busquedaLibros();
                    break;
                case 5 :
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
                    //biblioteca.cargarLibrosToJson(NOMBRE_ARCHIVO_LIBROS);
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
        System.out.println("1. Agregar nuevo cliente / libro");
        System.out.println("2. Buscar Cliente por ...");
        System.out.println("3. Bajar cliente/libro");
        System.out.println("4. Buscar libro por ...");
        System.out.println("5. Agregar copia de libro");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void crear() {
        System.out.println("1. Crear cliente");
        System.out.println("2. Crear libro");
        System.out.println("0. Volver");
        int opcion = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea

        switch (opcion) {
            case 1:
                Cliente nuevoCliente = crearNuevoCliente(scanner);
                biblioteca.agregarCliente(nuevoCliente.getIdCliente(), nuevoCliente);
                biblioteca.cargarClientesToJson(NOMBRE_ARCHIVO_CLIENTES);
                System.out.println("Cliente agregado exitosamente.");
                break;
            case 2:
                Libro nuevoLibro = crearNuevoLibro(scanner, biblioteca);
                biblioteca.agregarLibro(nuevoLibro.getISBN(), nuevoLibro);
                biblioteca.cargarLibrosToJson(NOMBRE_ARCHIVO_LIBROS);
                System.out.println("Libro agregado exitosamente.");
                break;
            case 0:
                System.out.println("Volviendo al menu principal");
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }
    }

    private static void busquedaCliente(){
        System.out.print("Ingrese el ID del cliente a buscar: ");
        int idCliente = scanner.nextInt();
        Cliente cliente = biblioteca.buscarCliente(idCliente);
        System.out.println(cliente != null ? cliente : "Cliente no encontrado.");
    }

    private static void busquedaLibros(){
        int op;
        System.out.println("Ingrese la opcion de busqueda");
        System.out.println("1. Por genero");
        System.out.println("2. Por autor");
        System.out.println("3. Por ISBN");
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
            case 3:
                System.out.print("Ingrese el ISBN del libro a buscar: ");
                int isbn = scanner.nextInt();
                Libro abuscar = biblioteca.buscarLibros(isbn);
                if (abuscar != null) {
                    System.out.println(abuscar);
                } else {
                    System.out.println("Libro no encontrado.");
                }
                break;
            case 0:
                System.out.println("Volviendo al menu principal");
                break;
            default:
                System.out.println("Opcion invalida");
                break;
        }
    }

    private static void baja (){
        System.out.println("1. Bajar Libro");
        System.out.println("2. Bajar Cliente");
        int op = scanner.nextInt();
        switch (op){
            case 1:
                System.out.print("Ingrese el ID del cliente a eliminar: ");
                int idClienteEliminar = scanner.nextInt();
                biblioteca.eliminarCliente(idClienteEliminar);
                biblioteca.cargarClientesToJson(NOMBRE_ARCHIVO_CLIENTES);
                System.out.println("Cliente eliminado exitosamente.");
                break;
            case 2:
                System.out.print("Ingrese el ISBN del libro a eliminar: ");
                int isbnEliminar = scanner.nextInt();
                biblioteca.eliminarLibro(isbnEliminar);
                biblioteca.cargarLibrosToJson(NOMBRE_ARCHIVO_LIBROS);
                System.out.println("Libro eliminado exitosamente.");
                break;
            case 0:
                System.out.println("Volviendo al menu principal");
                break;
            default:
                System.out.println("Opcion incorrecta");
                break;
        }
    }


    private static Cliente crearNuevoCliente(Scanner scanner) {
        boolean existeID, comprobarFormatoMail = false;

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
        int idCliente;
        do {
            idCliente = scanner.nextInt();
            existeID = biblioteca.buscarCliente(idCliente) != null;

            if (existeID) {
                System.out.println("El id ya existe, vuelva a ingresar");
            }
        } while (existeID);
        scanner.nextLine(); // Consumir el salto de línea
        String correoElectronico;
        do {
            System.out.print("Ingrese el correo electrónico del cliente: ");
            correoElectronico = scanner.nextLine();

            if (validarCorreo(correoElectronico)) //comprueba que el formato sea correcto
            {
                comprobarFormatoMail = true;
            }
            if (!comprobarFormatoMail)
            {
                System.out.println("Formato de correo electronico incorrecto, vuelva a ingresar");
            }
        } while (!comprobarFormatoMail);

        System.out.println("Ingrese el saldo del cliente: ");
        float saldo = scanner.nextFloat();

        return new Cliente(nombreYapellido, edad, domicilio, idCliente, correoElectronico, saldo);
    }

    public static boolean validarCorreo (String correoElectronico) //comprueba que el formato del correo sea el correcto
    {
        return correoElectronico.contains("@") && correoElectronico.contains(".com") && (correoElectronico.contains("gmail") || correoElectronico.contains("outlook") || correoElectronico.contains("hotmail"));
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
