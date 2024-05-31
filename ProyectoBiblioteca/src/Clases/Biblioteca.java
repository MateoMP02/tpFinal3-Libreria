package Clases;

import Generics.GestorHashMap;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Biblioteca implements Serializable {

    private String nombreBiblioteca;
    private GestorHashMap<Integer, Libro> hashMapDeLibros;
    private GestorHashMap<Integer, Cliente> hashMapClientes;

    public Biblioteca(String nombreBiblioteca) {
        this.nombreBiblioteca = nombreBiblioteca;
        this.hashMapClientes = new GestorHashMap<>();
        this.hashMapDeLibros = new GestorHashMap<>();
    }

    public void agregarLibro(Integer ISBN, Libro libro) {
        hashMapDeLibros.agregar(ISBN, libro);
        guardarLibrosEnJSON(); // Llamar a la función para guardar en JSON
    }

    public HashMap<Integer, Cliente> getHashMapDeClientes() {
        return hashMapClientes.obtenerTodos();
    }

    public void agregarCopiaDeLibro(Integer ISBN) {
        Libro libro = hashMapDeLibros.buscar(ISBN);
        if (libro != null) {
            libro.agregarCopiaLibro();
            guardarLibrosEnJSON();
        }
    }

    public void guardarLibrosEnJSON() {
        JSONArray jsonArray = new JSONArray();
        for (Libro libro : hashMapDeLibros.obtenerTodos().values()) {
            JSONObject jsonLibro = libro.toJson();
            jsonArray.put(jsonLibro);
        }
        JsonUtiles.grabar(jsonArray, "libros");
    }



    public Libro buscarLibros(Integer ISBN) {
        return hashMapDeLibros.buscar(ISBN);
    }

    public void eliminarLibro(Integer ISBN) {
        hashMapDeLibros.eliminar(ISBN);
        guardarLibrosEnJSON(); // Asegurarse de actualizar el archivo JSON
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

    public HashMap<Integer, Libro> getHashMapDeLibros() {
        return hashMapDeLibros.obtenerTodos();
    }

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

    // Function to get all unique genres available in the library
    public ArrayList<String> obtenerGenerosDisponibles() {
        HashSet<String> generos = new HashSet<>();
        for (Libro libro : hashMapDeLibros.obtenerTodos().values()) {
            generos.add(libro.getGenero());
        }
        return new ArrayList<>(generos);
    }

    // Function to search books by author
    public ArrayList<Libro> buscarLibrosPorAutor(String autor) {
        ArrayList<Libro> librosPorAutor = new ArrayList<>();
        for (Libro libro : hashMapDeLibros.obtenerTodos().values()) {
            if (libro.getAutor().equalsIgnoreCase(autor)) {
                librosPorAutor.add(libro);
            }
        }
        return librosPorAutor;
    }


    public ArrayList<Libro> buscarLibrosPorGenero(String genero) {
        ArrayList<Libro> librosXgenero = new ArrayList<>();
        for (Libro libro : hashMapDeLibros.obtenerTodos().values()) {
            if (libro.getGenero().equalsIgnoreCase(genero)) {
                librosXgenero.add(libro);
            }
        }
        return librosXgenero;
    }

    public void cargarClientesDesdeJson(String archivoJson) { //agrega al hashMap todos los clientes que se encuentran en el archivo JSON
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

    public void cargarClientesToJson (String archivoJson) //Carga todos los clientes que contiene el hashMap al archivo JSON
    {
        try {
            JSONArray jsonArray = new JSONArray();
            for (Cliente cliente: hashMapClientes.obtenerTodos().values())
            {
                jsonArray.put(cliente.toJson());
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("clientes",jsonArray);
            JsonUtiles.grabar(jsonObject,archivoJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
