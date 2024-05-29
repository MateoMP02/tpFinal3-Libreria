package Clases;

import java.util.ArrayList;
import java.util.Objects;

public class Libro {
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
}
