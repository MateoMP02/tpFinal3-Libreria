package Clases;

import org.json.JSONObject;
import org.json.JSONException;

import java.util.Objects;

public class Libro {
    private Integer ISBN;
    private String titulo;
    private String autor;
    private String genero;
    private double precio;
    private int copias; // Nuevo atributo para llevar el registro de las copias

    public Libro(Integer ISBN, String titulo, String autor, String genero, double precio) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.precio = precio;
        this.copias = 1; // Se establece la cantidad inicial de copias en 1
    }

    public Libro(Integer ISBN, String titulo, String autor, String genero, double precio,int copias) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.precio = precio;
        this.copias = copias; // Se establece la cantidad inicial de copias en 1
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

    public int getCopias() {
        return copias;
    }

    public void agregarCopiaLibro() {
        copias++; // Incrementa la cantidad de copias
    }

    public void restarUnaCopia(){
        copias--;
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
        return 1;
    }

    @Override
    public String toString() {
        return "Libro {" +
                "\n\tISBN: " + ISBN +
                "\n\tTítulo: " + titulo +
                "\n\tAutor: " + autor +
                "\n\tGénero: " + genero +
                "\n\tPrecio: " + precio +
                "\n\tCantidad de copias: " + copias +
                "\n}";
    }
    //Crea un jsonObject y lo devuelve a partir de un objeto Libro
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("ISBN", ISBN);
            jsonObject.put("titulo", titulo);
            jsonObject.put("autor", autor);
            jsonObject.put("genero", genero);
            jsonObject.put("precio", precio);
            jsonObject.put("copias",copias);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return jsonObject;
    }
    //Traduce el archivo JSON y devuelve un objeto Libro
    public static Libro fromJson(JSONObject jsonObject) {
        try {
            int ISBN = jsonObject.getInt("ISBN");
            String titulo = jsonObject.getString("titulo");
            String autor = jsonObject.getString("autor");
            String genero = jsonObject.getString("genero");
            double precio = jsonObject.getDouble("precio");
            int copias = jsonObject.getInt("copias");
            return new Libro(ISBN, titulo, autor, genero, precio,copias);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

}
