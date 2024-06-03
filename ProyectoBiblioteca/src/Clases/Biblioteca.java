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
        try {
            JSONArray jsonArray = new JSONArray(contenido);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Libro libro = Libro.fromJson(jsonObject);
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
        try {
            JSONObject jsonObject = new JSONObject(contenido);
            JSONArray jsonArray = jsonObject.getJSONArray("clientes");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject clienteObj = jsonArray.getJSONObject(i);
                Cliente cliente = Cliente.fromJson(clienteObj);
                this.agregarCliente(cliente.getIdCliente(), cliente);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    // Carga todos los clientes que contiene el hashMap clientes al archivo JSON
    public void guardarClientesEnJSON(String archivoJson)
    {
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
            int idAlquiler = registroAlquiler.getIdAlquiler();

            try {
                JSONObject jsonLibro = registroAlquiler.getLibroAlquilado().toJson();
                JSONObject jsonCliente = registroAlquiler.getCliente().toJson();
                String fechaAlquiler = registroAlquiler.getFechaAlquiler();

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("idAlquiler", idAlquiler);
                jsonObject.put("libroAlquilado", jsonLibro);
                jsonObject.put("cliente", jsonCliente);
                jsonObject.put("fechaAlquiler", fechaAlquiler);
                jsonArray.put(jsonObject);
            }catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        JsonUtiles.grabar(jsonArray, archivoJson);
    }
    // Agrega todos los registros de alquiler del archivo JSON al hashmap alquileres
    public void cargarRegistroAlquilerDesdeJson(String archivoJson) {
        String contenido = JsonUtiles.leer(archivoJson);
        try {
            JSONArray jsonArray = new JSONArray(contenido);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int idAlquiler = jsonObject.getInt("idAlquiler");
                Libro libro = Libro.fromJson(jsonObject.getJSONObject("libroAlquilado"));
                Cliente cliente = Cliente.fromJson(jsonObject.getJSONObject("cliente"));
                String fechaAlquiler = jsonObject.getString("fechaAlquiler");
                this.agregarRegistro(new RegistroAlquiler(idAlquiler, libro, cliente, fechaAlquiler));
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
                if(mayor < alquiler.getIdAlquiler())
                {
                    mayor = alquiler.getIdAlquiler();
                }
            }
        }
        return mayor+1;
    }


    public void alquilarLibro(Libro libro, Cliente cliente, int diasDeAlquiler) throws ClienteNoEncontradoException, LibroNoEncontradoException, CopiasInsuficientesException, SaldoInsuficienteException, LimiteAlquilerException {

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = localDateTime.plusDays(diasDeAlquiler);

        cliente = buscarCliente(cliente.getIdCliente());
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

        // Agrega el registro del alquiler
        int id = generarIdRegistroAlquiler();
        agregarRegistro(new RegistroAlquiler(id,libro,cliente));
    }

}
