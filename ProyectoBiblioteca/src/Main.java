/**
 * Este archivo Main.java es el punto de entrada de tu aplicación. Aquí se encuentra el método main que inicia
 * la ejecución del programa. Este archivo contiene la lógica principal de tu sistema de gestión de una biblioteca.
 * <p>
 * Aquí está una descripción general de lo que hace el código:
 * <p>
 * Inicialización y carga de datos: Antes de mostrar el menú principal, se cargan los datos de clientes, libros
 * y registros de alquiler desde archivos JSON existentes si los hay.
 * <p>
 * Menú principal: Se muestra un menú con varias opciones, como agregar cliente o libro, buscar cliente o libro,
 * alquilar libro, devolver libro, entre otros.
 * <p>
 * Operaciones del menú: Cada opción del menú llama a un método específico para realizar una acción determinada,
 * como agregar un nuevo cliente o libro, buscar un cliente o libro, alquilar un libro, devolver un libro, etc.
 * <p>
 * Guardado de datos: Después de que el usuario elige una opción y se realiza la operación correspondiente,
 * los datos se guardan nuevamente en archivos JSON para persistencia.
 * <p>
 * Manejo de excepciones: Se manejan diversas excepciones como ClienteNoEncontradoException, LibroNoEncontradoException, etc.,
 * para proporcionar mensajes adecuados al usuario en caso de errores.
 * <p>
 * Métodos de utilidad: Se definen varios métodos de utilidad para realizar operaciones como crear un nuevo cliente o
 * libro, solicitar información al usuario, etc.
 */

import Clases.*;
import Excepciones.*;
import Generics.ControladoraArchivosObjeto;

import java.util.*;

import static Clases.Constantes.*;


public class Main {

    private static Biblioteca biblioteca = new Biblioteca("Mi Biblioteca");
    static Scanner scanner = new Scanner(System.in);
    static HashMap<Cliente, ArrayList<Libro>> cargadoHashMap;

    public static void main(String[] args) {

        JsonUtiles.crearArchivoSiNoExiste(NOMBRE_ARCHIVO_CLIENTES);
        JsonUtiles.crearArchivoSiNoExiste(NOMBRE_ARCHIVO_LIBROS);
        JsonUtiles.crearArchivoSiNoExiste(NOMBRE_ARCHIVO_ALQUILERES);
        int opcion;

        do {

            //Cargar clientes desde un JSON
            biblioteca.cargarClientesDesdeJson(NOMBRE_ARCHIVO_CLIENTES);
            // Cargar libros desde un archivo JSON
            biblioteca.cargarLibrosDesdeJson(NOMBRE_ARCHIVO_LIBROS);

            biblioteca.cargarRegistroAlquilerDesdeJson(NOMBRE_ARCHIVO_ALQUILERES);
            cargarLibrosAlquilados();

            mostrarMenu();

            opcion = pedirNumeroAlUsuario(scanner);
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
                case 5:
                    agregarCopias();
                    break;
                case 6:
                    AlquilarLibro();
                    break;
                case 7:
                    devolverLibro();
                    break;
                case 8:
                    System.out.println("Libros en posesión ");
                    librosAlquilados();
                    break;
                case 9:
                    cargarSaldoCliente();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
            biblioteca.guardarLibrosEnJSON(NOMBRE_ARCHIVO_LIBROS);
            biblioteca.guardarClientesEnJSON(NOMBRE_ARCHIVO_CLIENTES);
            biblioteca.guardarRegistroAlquilerEnJSON(NOMBRE_ARCHIVO_ALQUILERES);
            guardarLibrosAlquilados();
        } while (opcion != 0);

        scanner.close();
    }

    private static void cargarLibrosAlquilados() {
        // Cargar el HashMap desde el archivo binario
        cargadoHashMap = ControladoraArchivosObjeto.cargarHashMap("clientesYLibros.data");
    }

    private static void librosAlquilados() {
        // Mostrar el contenido del HashMap cargado
        Iterator<Map.Entry<Cliente, ArrayList<Libro>>> it = cargadoHashMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Cliente, ArrayList<Libro>> entradaMapa = it.next();
            Cliente cliente = entradaMapa.getKey();
            ArrayList<Libro> libros = entradaMapa.getValue();
            System.out.println("Cliente: " + cliente.getNombreYapellido());
            System.out.println("Libros Alquilados:");
            for (Libro libro : libros) {
                System.out.println(libro.getTitulo());
            }
            System.out.println();
        }
    }

    private static void guardarLibrosAlquilados() {
        HashMap<Cliente, ArrayList<Libro>> hashMap = new HashMap<>();

        // Suponiendo que biblioteca.getHashMapDeClientes() devuelve un HashMap<Integer, Cliente>
        for (Cliente cliente : biblioteca.getHashMapDeClientes().values()) {
            if (!cliente.getLibrosEnPosesion().isEmpty()) {
                hashMap.put(cliente, cliente.getLibrosEnPosesion());
            }
        }

        // Guardar el HashMap en un archivo binario
        ControladoraArchivosObjeto.guardarHashMap(hashMap, "clientesYLibros.data");
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Menú Biblioteca ---");
        System.out.println("1. Agregar nuevo cliente / libro");
        System.out.println("2. Buscar Cliente por ...");
        System.out.println("3. Bajar cliente/libro");
        System.out.println("4. Buscar libro por ...");
        System.out.println("5. Agregar copia de libro");
        System.out.println("6. Alquilar Libro");
        System.out.println("7. Devolver Libro");
        System.out.println("8. Libros en préstamo");
        System.out.println("9. Cargar Saldo cliente");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void crear() {
        System.out.println("1. Crear cliente");
        System.out.println("2. Crear libro");
        System.out.println("0. Volver");
        int opcion = pedirNumeroAlUsuario(scanner);
        scanner.nextLine();  // Consumir el salto de línea

        switch (opcion) {
            case 1:
                Cliente nuevoCliente = crearNuevoCliente(scanner, biblioteca);
                biblioteca.agregarCliente(nuevoCliente.getIdCliente(), nuevoCliente);
                System.out.println("Cliente agregado exitosamente.");
                break;
            case 2:
                Libro nuevoLibro = crearNuevoLibro(scanner, biblioteca);
                biblioteca.agregarLibro(nuevoLibro.getISBN(), nuevoLibro);
                biblioteca.guardarLibrosEnJSON(NOMBRE_ARCHIVO_LIBROS);
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

    private static void AlquilarLibro() {
        System.out.println("Ingrese el ISBN del libro que desea alquilar");
        int ISBN = scanner.nextInt();
        Libro libro = biblioteca.buscarLibros(ISBN);

        System.out.println("Ingrese su id");
        int idCliente = scanner.nextInt();
        Cliente cliente = biblioteca.buscarCliente(idCliente);

        System.out.println("Dias a alquilar: ");
        int diasDeAlquiler = scanner.nextInt();
        try {
            biblioteca.alquilarLibro(libro, cliente, diasDeAlquiler);
            System.out.println("Alquiler exitoso");
        } catch (ClienteNoEncontradoException | LibroNoEncontradoException | CopiasInsuficientesException |
                 SaldoInsuficienteException |
                 LimiteAlquilerException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void devolverLibro() {
        System.out.println("Ingrese el ISBN del libro a devolver");
        int ISBN = scanner.nextInt();
        Libro libroEncontrado = biblioteca.buscarLibros(ISBN);

        System.out.println("Ingrese el id del cliente");
        int idCliente = scanner.nextInt();
        Cliente clienteEncontrado = biblioteca.buscarCliente(idCliente);
        try {
            biblioteca.devolverLibro(libroEncontrado, clienteEncontrado);
            System.out.println("Libro devuelto correctamente");
        } catch (ClienteNoEncontradoException | LibroNoEncontradoException | LibroNoAlquiladoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void agregarCopias() {
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
    }

    private static void busquedaCliente() {

        System.out.println("1. Por Id");
        System.out.println("2. Por Nombre y Apellido");
        int op = pedirNumeroAlUsuario(scanner);
        Cliente cliente;
        switch (op){
            case 1:
                System.out.print("Ingrese el ID del cliente a buscar: ");
                int idCliente = scanner.nextInt();
                cliente = biblioteca.buscarCliente(idCliente);
                System.out.println(cliente != null ? cliente : "Cliente no encontrado.");
                break;
            case 2:
                System.out.println("Ingrese nombre y apellido a buscar");
                String nombre = scanner.nextLine();
                cliente = buscarClientePorNombreApellido(nombre);
                if(cliente != null){
                    System.out.println("Datos del cliente: "+ cliente);
                }else {
                    System.out.println("Cliente no encontrado. Verifique en la base de datos");
                }
                break;
            case 0:
                System.out.println("Volviendo");
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }
    }

    private static Cliente buscarClientePorNombreApellido(String nombreYapellido) {
        for (Cliente cliente : biblioteca.getHashMapDeClientes().values()) {
            if (cliente.getNombreYapellido().equalsIgnoreCase(nombreYapellido)) {
                return cliente;
            }
        }
        return null; // Retorna null si no se encuentra ningún cliente con el nombre y apellido dado
    }

    private static void busquedaLibros() {
        int op;
        System.out.println("Ingrese la opcion de busqueda");
        System.out.println("1. Por genero");
        System.out.println("2. Por autor");
        System.out.println("3. Por ISBN");
        System.out.println("0. Volver");
        op = pedirNumeroAlUsuario(scanner);
        switch (op) {
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

    private static void baja() {
        System.out.println("1. Bajar Cliente");
        System.out.println("2. Bajar Libro");
        int op = pedirNumeroAlUsuario(scanner);
        switch (op) {
            case 1:
                System.out.print("Ingrese el ID del cliente a eliminar: ");
                int idClienteEliminar = scanner.nextInt();
                biblioteca.eliminarCliente(idClienteEliminar);
                System.out.println("Cliente eliminado exitosamente.");
                break;
            case 2:
                System.out.print("Ingrese el ISBN del libro a eliminar: ");
                int isbnEliminar = scanner.nextInt();
                biblioteca.eliminarLibro(isbnEliminar);
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


    private static Cliente crearNuevoCliente(Scanner scanner, Biblioteca biblioteca) {
        String nombreYapellido = solicitarCampoNoVacio(scanner, "Ingrese el nombre y apellido del cliente: ");
        int edad = solicitarEdad(scanner);
        String pais = solicitarCampoNoVacio(scanner, "Ingrese el país: ");
        String provincia = solicitarCampoNoVacio(scanner, "Ingrese la provincia: ");
        String ciudad = solicitarCampoNoVacio(scanner, "Ingrese la ciudad: ");
        String calleYaltura = solicitarCampoNoVacio(scanner, "Ingrese la calle y altura: ");
        Domicilio domicilio = new Domicilio(calleYaltura, ciudad, pais, provincia);

        int idCliente = solicitarID(scanner, biblioteca);
        String correoElectronico = solicitarCorreoElectronico(scanner);
        float saldo = solicitarSaldo(scanner);

        return new Cliente(nombreYapellido, edad, domicilio, idCliente, correoElectronico, saldo);
    }

    private static int solicitarEdad(Scanner scanner) {
        int edad;
        while (true) {
            System.out.print("Ingrese la edad del cliente: ");
            if (scanner.hasNextInt()) {
                edad = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea
                if (edad > 0) {
                    break;
                } else {
                    System.out.println("La edad debe ser un número positivo. Inténtelo de nuevo.");
                }
            } else {
                System.out.println("La edad debe ser un número. Inténtelo de nuevo.");
                scanner.nextLine(); // Consumir el salto de línea incorrecto
            }
        }
        return edad;
    }

    private static int solicitarID(Scanner scanner, Biblioteca biblioteca) {
        int idCliente;
        boolean existeID;
        while (true) {
            System.out.print("Ingrese el ID del cliente: ");
            if (scanner.hasNextInt()) {
                idCliente = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea
                existeID = biblioteca.buscarCliente(idCliente) != null;
                if (!existeID) {
                    break;
                } else {
                    System.out.println("El ID ya existe. Vuelva a ingresar.");
                }
            } else {
                System.out.println("El ID debe ser un número. Inténtelo de nuevo.");
                scanner.nextLine(); // Consumir el salto de línea incorrecto
            }
        }
        return idCliente;
    }

    private static String solicitarCorreoElectronico(Scanner scanner) {
        String correoElectronico;
        while (true) {
            System.out.print("Ingrese el correo electrónico del cliente: ");
            correoElectronico = scanner.nextLine();
            if (validarCorreo(correoElectronico)) {
                break;
            } else {
                System.out.println("Formato de correo electrónico incorrecto. Vuelva a ingresar.");
            }
        }
        return correoElectronico;
    }

    private static float solicitarSaldo(Scanner scanner) {
        float saldo;
        while (true) {
            System.out.print("Ingrese el saldo del cliente: ");
            if (scanner.hasNextFloat()) {
                saldo = scanner.nextFloat();
                scanner.nextLine(); // Consumir el salto de línea
                if (saldo >= 0) {
                    break;
                } else {
                    System.out.println("El saldo debe ser un número no negativo. Inténtelo de nuevo.");
                }
            } else {
                System.out.println("El saldo debe ser un número. Inténtelo de nuevo.");
                scanner.nextLine(); // Consumir el salto de línea incorrecto
            }
        }
        return saldo;
    }

    private static String solicitarCampoNoVacio(Scanner scanner, String mensaje) {
        String campo;
        do {
            System.out.print(mensaje);
            campo = scanner.nextLine();
            if (campo.isEmpty()) {
                System.out.println("Este campo no puede estar vacío. Inténtelo de nuevo.");
            }
        } while (campo.isEmpty());
        return campo;
    }

    private static boolean validarCorreo(String correoElectronico) {
        return correoElectronico.contains("@") && correoElectronico.contains(".com") &&
                (correoElectronico.contains("gmail") || correoElectronico.contains("outlook") || correoElectronico.contains("hotmail"));
    }

    public static int pedirNumeroAlUsuario(Scanner scanner) {
        int numero;

        while (true) {
            // Verificamos si la entrada es un número entero
            if (scanner.hasNextInt()) {
                numero = scanner.nextInt();
                break; // Salimos del bucle si el usuario ingresó un número válido
            } else {
                // Si la entrada no es un número, mostramos un mensaje de error y limpiamos la entrada
                System.out.println("Entrada no válida. Intente nuevamente.");
                scanner.next(); // Limpiamos la entrada no válida
            }
        }

        return numero;
    }

    public static Libro crearNuevoLibro(Scanner scanner, Biblioteca biblioteca) {
        int ISBN;
        boolean existeISBN;

        do {
            ISBN = solicitarISBN(scanner);
            existeISBN = biblioteca.buscarLibros(ISBN) != null;

            if (existeISBN) {
                System.out.println("El ISBN ingresado ya existe. Intente con otro.");
            }
        } while (existeISBN);

        String titulo = solicitarCampoNoVacio(scanner, "Ingrese el título del libro: ");
        String autor = solicitarCampoNoVacio(scanner, "Ingrese el autor del libro: ");
        String genero = solicitarCampoNoVacio(scanner, "Ingrese el género del libro: ");
        double precio = solicitarPrecio(scanner);

        return new Libro(ISBN, titulo, autor, genero, precio);
    }

    private static int solicitarISBN(Scanner scanner) {
        int ISBN;
        while (true) {
            System.out.print("Ingrese el ISBN del libro: ");
            if (scanner.hasNextInt()) {
                ISBN = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea
                break;
            } else {
                System.out.println("El ISBN debe ser un número. Inténtelo de nuevo.");
                scanner.nextLine(); // Consumir el salto de línea incorrecto
            }
        }
        return ISBN;
    }


    private static double solicitarPrecio(Scanner scanner) {
        double precio;
        while (true) {
            System.out.print("Ingrese el precio del libro: ");
            if (scanner.hasNextDouble()) {
                precio = scanner.nextDouble();
                scanner.nextLine(); // Consumir el salto de línea
                if (precio > 0) {
                    break;
                } else {
                    System.out.println("El precio debe ser un número positivo. Inténtelo de nuevo.");
                }
            } else {
                System.out.println("El precio debe ser un número. Inténtelo de nuevo.");
                scanner.nextLine(); // Consumir el salto de línea incorrecto
            }
        }
        return precio;
    }


    private static void cargarSaldoCliente(){

        System.out.println("Ingrese el iD del cliente a cargar el saldo: ");
        int id = pedirNumeroAlUsuario(scanner);
        Cliente cliente = biblioteca.buscarCliente(id);
        if(cliente != null){
            System.out.println("Saldo actual: "+cliente.getSaldo());
            System.out.println("Ingrese el monto a cargar: ");
            float newSaldo = pedirFloatAlUsuario(scanner);
            cliente.cargarSaldo(newSaldo);
            System.out.println("Saldo cargado exitosamente. ");
        }else{
            System.out.println("Cliente no encontrado");
        }
    }

    public static float pedirFloatAlUsuario(Scanner scanner) {
        float numero;

        while (true) {
            // Verificamos si la entrada es un número entero
            if (scanner.hasNextFloat()) {
                numero = scanner.nextFloat();
                break; // Salimos del bucle si el usuario ingresó un número válido
            } else {
                // Si la entrada no es un número, mostramos un mensaje de error y limpiamos la entrada
                System.out.println("Entrada no válida. Intente nuevamente.");
                scanner.next(); // Limpiamos la entrada no válida
            }
        }
        return numero;
    }
}

