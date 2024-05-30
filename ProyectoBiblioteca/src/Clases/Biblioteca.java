package Clases;

import Generics.GestorHashMap;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

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

    public ArrayList<Libro> buscarLibrosPorGenero(String genero) {
        ArrayList<Libro> librosXgenero = new ArrayList<>();
        for (Libro libro : hashMapDeLibros.obtenerTodos().values()) {
            if (libro.getGenero().equalsIgnoreCase(genero)) {
                librosXgenero.add(libro);
            }
        }
        return librosXgenero;
    }
}
