package Clases;

import java.time.LocalDateTime;
import java.util.Objects;

public class RegistroAlquiler {

    LocalDateTime now = LocalDateTime.now();

    private int idAlquiler; // crear funcion automatica en biblioteca
    private Libro libroAlquilado;
    private Cliente cliente;
    private String fechaAlquiler;

    //Constructor sin fecha
    public RegistroAlquiler(int idAlquiler, Libro libroAlquilado, Cliente cliente) {
        this.idAlquiler = idAlquiler;
        this.libroAlquilado = libroAlquilado;
        this.fechaAlquiler = now.toString();
        this.cliente = cliente;
    }

    //Constructor sin alquiler
    public RegistroAlquiler(int idAlquiler, Libro libroAlquilado, Cliente cliente, String fechaAlquiler) {
        this.idAlquiler = idAlquiler;
        this.libroAlquilado = libroAlquilado;
        this.cliente = cliente;
        this.fechaAlquiler = fechaAlquiler;
    }

    public Libro getLibroAlquilado() {
        return libroAlquilado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getFechaAlquiler() {
        return fechaAlquiler;
    }

    //Muestra id y fecha de alquiler.Muestra titulo y ISBN de libro. Muestra nombre y apellido e ID del cliente
    @Override
    public String toString() {
        return "RegistroAlquiler {" +
                "\nidAlquiler=" + idAlquiler +
                "\nLibro{"+
                "\nTitulo= " + libroAlquilado.getTitulo() +
                "\nISBN= "+ libroAlquilado.getISBN()+
                "\n}"+
                "\nCliente{"+
                "\nID del cliente= " + cliente.getIdCliente() +
                "\nNombre y apellido del cliente= "+ cliente.getNombreYapellido()+
                "\n}"+
                "\n\nfechaAlquiler=" + fechaAlquiler +
                "\n}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistroAlquiler that = (RegistroAlquiler) o;
        return idAlquiler == that.idAlquiler;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    public int getIdAlquiler() {
        return idAlquiler;
    }
}