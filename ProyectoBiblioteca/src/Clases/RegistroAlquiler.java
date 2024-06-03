package Clases;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

public class RegistroAlquiler {

    LocalDateTime now = LocalDateTime.now();

    private int idAlquiler; // crear funcion automatica en biblioteca
    private Libro libroAlquilado;
    private Cliente cliente;
    private String fechaAlquiler;
    private String fechaDeDevolucionEsperada;
    private int diasAlquilados;

    //Constructor sin fecha
    public RegistroAlquiler(int idAlquiler, Libro libroAlquilado, Cliente cliente) {
        this.idAlquiler = idAlquiler;
        this.libroAlquilado = libroAlquilado;
        this.fechaAlquiler = now.toString();
        this.cliente = cliente;
    }

    //Constructor sin alquiler
    public RegistroAlquiler(int idAlquiler, Libro libroAlquilado, Cliente cliente, String fechaAlquiler,String fechaDeDevolucionEsperada, int diasAlquilados) {
        this.idAlquiler = idAlquiler;
        this.libroAlquilado = libroAlquilado;
        this.cliente = cliente;
        this.fechaAlquiler = fechaAlquiler;
        this.fechaDeDevolucionEsperada = fechaDeDevolucionEsperada;
        this.diasAlquilados = diasAlquilados;
    }

    public String getFechaDeDevolucionEsperada() {
        return fechaDeDevolucionEsperada;
    }

    public Libro getLibroAlquilado() {
        return libroAlquilado;
    }

    public int getDiasAlquilados() {
        return diasAlquilados;
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
                "\n\nFecha devolucion=" + fechaDeDevolucionEsperada +
                "\n\nDias de prestamo=" + diasAlquilados +
                "\n}";
    }



    public boolean equals(Object o) {
        boolean rta = false;
        if(o !=null){
            if(o instanceof RegistroAlquiler){
                RegistroAlquiler aComparar = (RegistroAlquiler) o;
                if(((RegistroAlquiler) o).getIdAlquiler() == aComparar.getIdAlquiler()){
                    rta = true;
                }
            }
        }
        return rta;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    public int getIdAlquiler() {
        return idAlquiler;
    }


}