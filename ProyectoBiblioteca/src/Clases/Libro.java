package Clases;

import org.json.JSONObject;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Objects;

public class Libro implements Cloneable {
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
    public int hashCode() {
        return Objects.hash(ISBN);
    }

    @Override
    public String toString() {
        return "Libro {" +
                "\n\tISBN: " + ISBN +
                "\n\tTítulo: " + titulo +
                "\n\tAutor: " + autor +
                "\n\tGénero: " + genero +
                "\n\tPrecio: " + precio +
                "\n\tCantidad de copias: " + obtenerNumeroDeCopias() +
                "\n}";
    }

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("ISBN", ISBN);
            jsonObject.put("titulo", titulo);
            jsonObject.put("autor", autor);
            jsonObject.put("genero", genero);
            jsonObject.put("precio", precio);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return jsonObject;
    }

    public static Libro fromJson(JSONObject jsonObject) {
        try {
            int ISBN = jsonObject.getInt("ISBN");
            String titulo = jsonObject.getString("titulo");
            String autor = jsonObject.getString("autor");
            String genero = jsonObject.getString("genero");
            double precio = jsonObject.getDouble("precio");
            return new Libro(ISBN, titulo, autor, genero, precio);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
