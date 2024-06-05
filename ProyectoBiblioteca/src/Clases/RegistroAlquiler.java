package Clases;

import Interfaces.IJsonSerializable;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;

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