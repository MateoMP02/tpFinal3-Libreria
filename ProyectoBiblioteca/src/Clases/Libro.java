package Clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import org.json.JSONException;
import org.json.JSONObject;

public class Libro implements Serializable {
    private Integer ISBN;
    private String titulo;
    private String autor;
    private String genero;
    private double precio;
    private final ArrayList<Libro> copias;

    public Libro(Integer ISBN, String titulo, String autor, String genero, double precio) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.precio = precio;
        this.copias = new ArrayList<>();
    }

    // Getters y Setters
    public Integer getISBN() {
        return ISBN;
    }

    public void setISBN(Integer ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void agregarCopiaLibro() {
        try {
            Libro copia = (Libro) this.clone();
            copias.add(copia);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public int obtenerNumeroDeCopias() {
        return copias.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return Objects.equals(ISBN, libro.ISBN);
    }

    @Override
    public String toString() {
        return "Libro {" +
                "\n\tISBN: " + ISBN +
                "\n\tTítulo: " + titulo +
                "\n\tAutor: " + autor +
                "\n\tGénero: " + genero +
                "\n\tPrecio: " + precio +
                "\n}";
    }



    @Override
    public int hashCode() {
        return Objects.hash(ISBN);
    }

    // Método para convertir el objeto a un JSONObject
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("ISBN", ISBN);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        try {
            jsonObject.put("titulo", titulo);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        try {
            jsonObject.put("autor", autor);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        try {
            jsonObject.put("genero", genero);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        try {
            jsonObject.put("precio", precio);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return jsonObject;
    }

    // Método estático para crear un objeto Libro desde un JSONObject
    public static Libro fromJson(JSONObject jsonObject) {
        int ISBN = 0;
        try {
            ISBN = jsonObject.getInt("ISBN");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        String titulo = null;
        try {
            titulo = jsonObject.getString("titulo");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        String autor = null;
        try {
            autor = jsonObject.getString("autor");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        String genero = null;
        try {
            genero = jsonObject.getString("genero");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        double precio = 0;
        try {
            precio = jsonObject.getDouble("precio");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return new Libro(ISBN, titulo, autor, genero, precio);
    }
}
