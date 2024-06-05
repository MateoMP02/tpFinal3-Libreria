package Clases;

import Interfaces.IJsonSerializable;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;

/**
 * Este código define la clase RegistroAlquiler, que representa el registro de un alquiler de un libro por
 * parte de un cliente en la biblioteca. Aquí hay un comentario breve sobre cada método y un resumen del
 * propósito del código:
 *
 * Constructor RegistroAlquiler(int, Libro, Cliente):
 *
 * Inicializa un objeto RegistroAlquiler con el ID de alquiler, el libro alquilado, el cliente y establece
 * la fecha de alquiler como la fecha actual.
 * Constructor RegistroAlquiler(int, Libro, Cliente, String, String):
 *
 * Inicializa un objeto RegistroAlquiler con el ID de alquiler, el libro alquilado, el cliente y establece
 * las fechas de alquiler y devolución esperada según los parámetros proporcionados.
 * getFechaDeDevolucionEsperada():
 *
 * Devuelve la fecha de devolución esperada del libro alquilado.
 * getLibroAlquilado():
 *
 * Devuelve el libro alquilado.
 * getCliente():
 *
 * Devuelve el cliente que realizó el alquiler.
 * getFechaAlquiler():
 *
 * Devuelve la fecha en que se realizó el alquiler.
 * toString():
 *
 * Genera una representación en cadena del registro de alquiler, mostrando el ID del alquiler,
 * el título y ISBN del libro alquilado, el ID y nombre del cliente, y las fechas de alquiler y devolución esperada.
 * toJson():
 *
 * Crea un objeto JSONObject a partir del registro de alquiler y lo devuelve.
 * fromJson(JSONObject):
 *
 * Traduce un objeto JSON al registro de alquiler correspondiente y lo devuelve.
 * equals(Object):
 *
 * Compara si dos registros de alquiler son iguales basándose en su ID de alquiler.
 * getIdAlquiler():
 * Devuelve el ID del alquiler.
 * hashCode():
 * Devuelve el hash code del objeto.
 * Esta clase encapsula la información sobre un alquiler de libro realizado por un cliente en la biblioteca y
 * proporciona métodos para representar esta información en formato JSON y para comparar registros de alquiler.
 */

public class RegistroAlquiler implements IJsonSerializable<RegistroAlquiler> {

    LocalDateTime now = LocalDateTime.now();

    private int idAlquiler; // crear funcion automatica en biblioteca
    private Libro libroAlquilado;
    private Cliente cliente;
    private String fechaAlquiler;
    private String fechaDeDevolucionEsperada;


    //Constructor sin fecha
    public RegistroAlquiler(int idAlquiler, Libro libroAlquilado, Cliente cliente) {
        this.idAlquiler = idAlquiler;
        this.libroAlquilado = libroAlquilado;
        this.fechaAlquiler = now.toString();
        this.cliente = cliente;
    }

    //Constructor sin alquiler
    public RegistroAlquiler(int idAlquiler, Libro libroAlquilado, Cliente cliente, String fechaAlquiler,String fechaDeDevolucionEsperada) {
        this.idAlquiler = idAlquiler;
        this.libroAlquilado = libroAlquilado;
        this.cliente = cliente;
        this.fechaAlquiler = fechaAlquiler;
        this.fechaDeDevolucionEsperada = fechaDeDevolucionEsperada;
    }

    public RegistroAlquiler(){

    }

    public String getFechaDeDevolucionEsperada() {
        return fechaDeDevolucionEsperada;
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
                "\n\nFecha devolucion=" + fechaDeDevolucionEsperada +
                "\n}";
    }

    //Crea un jsonObject a partir de un registroAlquiler y lo devuelve
    public JSONObject toJson() throws JSONException {
        JSONObject jsonLibro = getLibroAlquilado().toJson();
        JSONObject jsonCliente = getCliente().toJson();
        String fechaAlquiler = getFechaAlquiler();
        String fechaDeDevolucionEsperada = getFechaDeDevolucionEsperada();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idAlquiler", idAlquiler);
        jsonObject.put("libroAlquilado", jsonLibro);
        jsonObject.put("cliente", jsonCliente);
        jsonObject.put("fechaAlquiler", fechaAlquiler);
        jsonObject.put("fechaDeDevolucionEsperada", fechaDeDevolucionEsperada);
        return jsonObject;
    }

    //Traduce el archivo JSON y devuelve un objeto RegistroAlquiler
    public RegistroAlquiler fromJson(JSONObject jsonObject) throws JSONException {
        int idAlquiler = jsonObject.getInt("idAlquiler");
        Libro aux = new Libro();
        Libro libro = aux.fromJson(jsonObject.getJSONObject("libroAlquilado"));
        Cliente auxCliente = new Cliente();
        Cliente cliente = auxCliente.fromJson(jsonObject.getJSONObject("cliente"));
        String fechaAlquiler = jsonObject.getString("fechaAlquiler");
        String fechaDeDevolucionEsperada = jsonObject.getString("fechaDeDevolucionEsperada");

        return (new RegistroAlquiler(idAlquiler, libro, cliente, fechaAlquiler, fechaDeDevolucionEsperada));
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