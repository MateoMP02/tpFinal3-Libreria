package Clases;

import Excepciones.*;
import Generics.GestorHashMap;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Descripción de la Clase Biblioteca
 * La clase Biblioteca representa una biblioteca y sus operaciones. Implementa la interfaz Serializable para permitir la serialización de sus datos. La clase contiene tres GestorHashMap que gestionan libros, clientes y registros de alquiler.
 *
 * Atributos Principales
 * nombreBiblioteca: El nombre de la biblioteca.
 * hashMapDeLibros: Mapa que gestiona los libros usando el ISBN como clave.
 * hashMapClientes: Mapa que gestiona los clientes usando el ID del cliente como clave.
 * hashMapAlquileres: Mapa que gestiona los registros de alquiler usando el ID del alquiler como clave.
 * Métodos Principales
 * Agregar/Buscar/Eliminar:
 *
 * agregarLibro, buscarLibros, eliminarLibro: Gestionan los libros en la biblioteca.
 * agregarCliente, buscarCliente, eliminarCliente: Gestionan los clientes en la biblioteca.
 * agregarRegistro, buscarRegistro: Gestionan los registros de alquiler.
 * Funciones de Utilidad:
 *
 * obtenerGenerosDisponibles: Devuelve una lista de todos los géneros disponibles en la biblioteca.
 * buscarLibrosPorAutor: Busca y devuelve todos los libros de un autor específico.
 * buscarLibrosPorGenero: Busca y devuelve todos los libros de un género específico.
 * Manejo de Archivos JSON:
 *
 * cargarLibrosDesdeJson, guardarLibrosEnJSON: Cargan y guardan libros desde/hacia un archivo JSON.
 * cargarClientesDesdeJson, guardarClientesEnJSON: Cargan y guardan clientes desde/hacia un archivo JSON.
 * guardarRegistroAlquilerEnJSON, cargarRegistroAlquilerDesdeJson: Cargan y guardan registros de alquiler desde/hacia un archivo JSON.
 * Operaciones de Alquiler:
 *
 * alquilarLibro: Gestiona el proceso de alquilar un libro, incluyendo validaciones como existencia del cliente, disponibilidad del libro, saldo del cliente y límite de alquileres.
 * devolverLibro: Gestiona el proceso de devolución de un libro, incluyendo verificación de posesión y posibles multas por devolución tardía.
 */

public class Biblioteca implements Serializable {

    private String nombreBiblioteca;
    private GestorHashMap<Integer, Libro> hashMapDeLibros;
    private GestorHashMap<Integer, Cliente> hashMapClientes;
    private GestorHashMap<Integer, RegistroAlquiler> hashMapAlquileres;

    public Biblioteca(String nombreBiblioteca) {
        this.nombreBiblioteca = nombreBiblioteca;
        this.hashMapClientes = new GestorHashMap<>();
        this.hashMapDeLibros = new GestorHashMap<>();
        this.hashMapAlquileres = new GestorHashMap<>();
    }

    public void agregarLibro(Integer ISBN, Libro libro) {
        hashMapDeLibros.agregar(ISBN, libro);
    }


    public void agregarCopiaDeLibro(Integer ISBN) {
        Libro libro = hashMapDeLibros.buscar(ISBN);
        if (libro != null) {
            libro.agregarCopiaLibro();
        }
    }

    public Libro buscarLibros(Integer ISBN) {
        return hashMapDeLibros.buscar(ISBN);
    }

    public void eliminarLibro(Integer ISBN) {
        hashMapDeLibros.eliminar(ISBN);
    }

    public void agregarCliente(Integer idCliente, Cliente cliente) {
        hashMapClientes.agregar(idCliente, cliente);
    }

    public Cliente buscarCliente(Integer idCliente) {
        return hashMapClientes.buscar(idCliente);
    }

    public void eliminarCliente(Integer idCliente) {
        hashMapClientes.eliminar(idCliente);
    }

    public void agregarRegistro(RegistroAlquiler registro) {
        hashMapAlquileres.agregar(registro.getIdAlquiler(), registro);
    }

    public RegistroAlquiler buscarRegistro(Integer idAlquiler) {
        return hashMapAlquileres.buscar(idAlquiler);
    }

    public HashMap<Integer, Libro> getHashMapDeLibros() {
        return hashMapDeLibros.obtenerTodos();
    }

    public HashMap<Integer, Cliente> getHashMapDeClientes() {
        return hashMapClientes.obtenerTodos();
    }

    public HashMap<Integer, RegistroAlquiler> getHashMapAlquileres() {
        return hashMapAlquileres.obtenerTodos();
    }


    // Devuelve todos los tipos de generos que posee la libreria
    public ArrayList<String> obtenerGenerosDisponibles() {
        HashSet<String> generos = new HashSet<>();
        for (Libro libro : hashMapDeLibros.obtenerTodos().values()) {
            generos.add(libro.getGenero());
        }
        return new ArrayList<>(generos);
    }

    // Devuelve todos los libros de un autor
    public ArrayList<Libro> buscarLibrosPorAutor(String autor) {
        ArrayList<Libro> librosPorAutor = new ArrayList<>();
        for (Libro libro : hashMapDeLibros.obtenerTodos().values()) {
            if (libro.getAutor().equalsIgnoreCase(autor)) {
                librosPorAutor.add(libro);
            }
        }
        return librosPorAutor;
    }

    // Devuelve todos los libros de un genero enviado por parametro
    public ArrayList<Libro> buscarLibrosPorGenero(String genero) {
        ArrayList<Libro> librosXgenero = new ArrayList<>();
        for (Libro libro : hashMapDeLibros.obtenerTodos().values()) {
            if (libro.getGenero().equalsIgnoreCase(genero)) {
                librosXgenero.add(libro);
            }
        }
        return librosXgenero;
    }

    // Carga los libros del archivo JSON al hashmap
    public void cargarLibrosDesdeJson(String archivoJson) {
        String contenido = JsonUtiles.leer(archivoJson);
        Libro aux = new Libro();
        try {
            JSONArray jsonArray = new JSONArray(contenido);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Libro libro = aux.fromJson(jsonObject);
                this.agregarLibro(libro.getISBN(), libro);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Carga al archivo JSON todos los libros del hashmap
    public void guardarLibrosEnJSON(String archivoJSON) {
        JSONArray jsonArray = new JSONArray();
        for (Libro libro : hashMapDeLibros.obtenerTodos().values()) {
            JSONObject jsonLibro = libro.toJson();
            jsonArray.put(jsonLibro);
        }
        JsonUtiles.grabar(jsonArray, archivoJSON);
    }

    // Agrega al hashmap todos los clientes del archivo JSON
    public void cargarClientesDesdeJson(String archivoJson) {
        String contenido = JsonUtiles.leer(archivoJson);
        Cliente aux = new Cliente();
        try {
            JSONObject jsonObject = new JSONObject(contenido);
            JSONArray jsonArray = jsonObject.getJSONArray("clientes");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject clienteObj = jsonArray.getJSONObject(i);
                Cliente cliente = aux.fromJson(clienteObj);
                this.agregarCliente(cliente.getIdCliente(), cliente);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Carga todos los clientes que contiene el hashMap clientes al archivo JSON
    public void guardarClientesEnJSON(String archivoJson) {
        try {
            JSONArray jsonArray = new JSONArray();
            for (Cliente cliente : hashMapClientes.obtenerTodos().values()) {
                jsonArray.put(cliente.toJson());
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("clientes", jsonArray);
            JsonUtiles.grabar(jsonObject, archivoJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Agrega al archivo JSON todos los elementos del hashmap de registro de alquileres
    public void guardarRegistroAlquilerEnJSON(String archivoJson) {
        JSONArray jsonArray = new JSONArray();
        for (RegistroAlquiler registroAlquiler : hashMapAlquileres.obtenerTodos().values()) {
            try {
                jsonArray.put(registroAlquiler.toJson());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        JsonUtiles.grabar(jsonArray, archivoJson);
    }

    // Agrega todos los registros de alquiler del archivo JSON al hashmap alquileres
    public void cargarRegistroAlquilerDesdeJson(String archivoJson) {
        String contenido = JsonUtiles.leer(archivoJson);
        RegistroAlquiler aux = new RegistroAlquiler();
        try {
            JSONArray jsonArray = new JSONArray(contenido);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                RegistroAlquiler registro = aux.fromJson(jsonObject);
                agregarRegistro(registro);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    // Genera un id que no existe en el hashmap alquileres
    public int generarIdRegistroAlquiler() {
        int mayor = -1;
        if (hashMapAlquileres.obtenerTodos() != null) {
            for (RegistroAlquiler alquiler : hashMapAlquileres.obtenerTodos().values()) {
                if (mayor < alquiler.getIdAlquiler()) {
                    mayor = alquiler.getIdAlquiler();
                }
            }
        }
        return mayor + 1;
    }


    public void alquilarLibro(Libro libro, Cliente cliente, int diasDeAlquiler) throws ClienteNoEncontradoException, LibroNoEncontradoException, CopiasInsuficientesException, SaldoInsuficienteException, LimiteAlquilerException {

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = localDateTime.plusDays(diasDeAlquiler);


        if (cliente == null) {
            throw new ClienteNoEncontradoException("Cliente no encontrado");
        }

        if (libro == null) {
            throw new LibroNoEncontradoException("Libro no encontrado");
        }

        if (libro.getCopias() < 1) {
            throw new CopiasInsuficientesException("No disponible por falta de copias");
        }

        long daysBetween = ChronoUnit.DAYS.between(localDateTime, localDateTime1);
        float precioAlquiler = (float) (daysBetween * libro.getPrecio());

        if (cliente.getSaldo() < precioAlquiler) {
            throw new SaldoInsuficienteException("Saldo insuficiente para el alquiler");
        }

        if (!cliente.availableToRent()) {
            throw new LimiteAlquilerException("El cliente cuenta con más de 2 libros alquilados: " + cliente.getLibrosEnPosesion());
        }

        // Si llego hasta acá alquilo
        libro.restarUnaCopia();
        cliente.restarSaldoXAlquiler(precioAlquiler);
        cliente.getLibrosEnPosesion().add(libro);
        String fecha1 = localDateTime.toString(); //Fecha de alquiler
        String fecha2 = localDateTime1.toString(); //Fecha de devolucion esperada
        // Agrega el registro del alquiler
        int id = generarIdRegistroAlquiler();
        agregarRegistro(new RegistroAlquiler(id, libro, cliente, fecha1, fecha2));
    }

    public void devolverLibro(Libro libro, Cliente cliente) throws ClienteNoEncontradoException, LibroNoEncontradoException, LibroNoAlquiladoException {

        if (cliente == null) {
            throw new ClienteNoEncontradoException("Cliente no encontrado");
        }


        if (libro == null) {
            throw new LibroNoEncontradoException("Libro no encontrado");
        }

        // Verificar si el cliente tiene el libro en posesión
        if (!cliente.getLibrosEnPosesion().contains(libro)) {
            throw new LibroNoAlquiladoException("El cliente no tiene este libro alquilado");
        }

        // Devolver el libro
        cliente.getLibrosEnPosesion().remove(libro);
        libro.agregarCopiaLibro();
        System.out.println("LLegue hasta aca");
        // Aumentar la cantidad de copias disponibles
        RegistroAlquiler buscado = null;
        LocalDateTime fecha1 = null;
        LocalDateTime fecha2 = null;
        long daysBefore = 0;
        for (RegistroAlquiler registroAlquiler : hashMapAlquileres.obtenerTodos().values()) {
            if (registroAlquiler.getCliente().getNombreYapellido().equalsIgnoreCase(cliente.getNombreYapellido())) {
                buscado = registroAlquiler;
                fecha1 = LocalDateTime.now();
                LocalDateTime fechaEspecifica = LocalDateTime.of(2024, 6, 06, 14, 30, 0);

                fecha2 = LocalDateTime.parse(buscado.getFechaDeDevolucionEsperada());
                daysBefore = ChronoUnit.DAYS.between(fecha2, fechaEspecifica);


            }
        }

        if (daysBefore == 1) {
            System.out.println("WARNING LA PROXIMA TE VIOLO");
        } else if (daysBefore > 1) {
            System.out.println("Paga la multa");
            System.out.println("Dias pasados : " + daysBefore);
            float multa = (float) ((float) daysBefore * libro.getPrecio());
            System.out.println("Multa es de : " + multa);
        } else {
            System.out.println("Devuelto en tiempo y forma");
        }

    }

}
