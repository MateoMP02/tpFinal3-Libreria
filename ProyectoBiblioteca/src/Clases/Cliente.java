package Clases;

import Interfaces.IJsonSerializable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Este código defines la clase Cliente, que representa a un cliente en un sistema de gestión de una biblioteca.
 * Aquí se deja un breve comentario sobre cada función y un mini texto que resume el propósito del código:
 *
 * Constructor Cliente(String, int, Domicilio, Integer, String, float):
 *
 * Este constructor inicializa un objeto Cliente con los detalles proporcionados, incluyendo nombre, edad, domicilio,
 * ID, correo electrónico y saldo.
 * Constructor Cliente():
 *
 * Un constructor sin argumentos que inicializa un objeto Cliente con valores predeterminados.
 * setLibrosEnPosesion(ArrayList<Libro>):
 *
 * Establece la lista de libros en posesión del cliente.
 * modificarDomicilio(Domicilio):
 *
 * Permite modificar el domicilio del cliente.
 * availableToRent():
 *
 * Devuelve true si el cliente puede alquilar más libros (menos de 3 libros en posesión), de lo contrario, devuelve false.
 * toString():
 *
 * Genera una representación en cadena del objeto Cliente, incluyendo detalles como nombre, ID, correo electrónico,
 * libros alquilados y saldo.
 * getters y setters:
 *
 * Métodos para acceder y modificar los atributos del cliente, como ID, correo electrónico, saldo, etc.
 * equals(Object):
 *
 * Sobrescrito para comparar dos objetos Cliente en función de su ID.
 * fromJson(JSONObject):
 *
 * Convierte un objeto JSON en un objeto Cliente.
 * toJson():
 *
 * Convierte un objeto Cliente en un objeto JSON.
 * restarSaldoXAlquiler(float):
 *
 * Resta el precio de un libro alquilado del saldo total del cliente.
 * getLibrosEnPosesion():
 *
 * Devuelve la lista de libros en posesión del cliente.
 * Este código encapsula la información y el comportamiento relacionado con los clientes de la biblioteca, proporcionando
 * métodos para interactuar con ellos, incluyendo la capacidad de alquilar libros, gestionar su saldo y realizar
 * conversiones entre objetos Cliente y JSON.
 */

public class Cliente extends Persona implements IJsonSerializable<Cliente> {



    private Integer idCliente;
    private String correoElectronico;
    private float saldo;
    private ArrayList<Libro> librosEnPosesion;



    public Cliente(String nombreYapellido, int edad, Domicilio domicilio, Integer idCliente, String correoElectronico, float saldo) {
        super(nombreYapellido, edad, domicilio);
        this.idCliente = idCliente;
        this.correoElectronico = correoElectronico;
        this.saldo = saldo;
        this.librosEnPosesion = new ArrayList<>();
    }

    public Cliente() {
        this.idCliente = 0;
        this.correoElectronico = "";
        this.saldo = 0;
        this.librosEnPosesion = new ArrayList<>();

    }

    //Devuelve true si el cliente posee menos de 3 libros alquilados, false si posee 3 o mas
    public boolean availableToRent(){
        boolean rta = true;
        if(librosEnPosesion.size() > 2){
            rta = false;
        }
        return rta;
    }

    @Override
    public String toString() {
        return super.toString()+"\n"+
                "Datos Lector:"+"\n"+
                "idCliente: "+idCliente+"\n"+
                "Correo electronico: "+ correoElectronico+"\n"+
                "libros alquilados:\n"+getLibrosEnPosesion()+"\n"+
                "Saldo: "+saldo+"\n";
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public float getSaldo() {
        return saldo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(idCliente, cliente.idCliente);
    }

    @Override
    public int hashCode() {
        return 1;
    }

    //Traduce el archivo JSON y devuelve un objeto Cliente
    public Cliente fromJson(JSONObject jsonObject) throws JSONException {
        int idCliente = jsonObject.getInt("idCliente");
        String correoElectronico = jsonObject.getString("correoElectronico");
        float saldo = (float) jsonObject.getDouble("saldo");
        int edad = jsonObject.getInt("edad");
        String nombreYapellido = jsonObject.getString("nombreYapellido");

        JSONObject domicilioJson = jsonObject.getJSONObject("domicilio");
        String pais = domicilioJson.getString("pais");
        String provincia = domicilioJson.getString("provincia");
        String ciudad = domicilioJson.getString("ciudad");
        String calleYaltura = domicilioJson.getString("calleYaltura");
        Domicilio domicilio = new Domicilio(calleYaltura, ciudad, pais, provincia);

        Cliente cliente = new Cliente(nombreYapellido, edad, domicilio, idCliente, correoElectronico, saldo);

        // Deserializar librosEnPosesion
        JSONArray librosArray = jsonObject.getJSONArray("librosEnPosesion");
        Libro aux = new Libro();
        for (int i = 0; i < librosArray.length(); i++) {
            JSONObject libroJson = librosArray.getJSONObject(i);
            Libro libro = aux.fromJson(libroJson);
            cliente.getLibrosEnPosesion().add(libro);
        }

        return cliente;
    }

    //Crea un jsonObject a partir de un Cliente y lo devuelve
    public JSONObject toJson() throws JSONException {
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("idCliente", getIdCliente());
        jsonObject1.put("correoElectronico", getCorreoElectronico());
        jsonObject1.put("saldo", getSaldo());
        jsonObject1.put("edad", getEdad());
        jsonObject1.put("nombreYapellido", getNombreYapellido());

        JSONObject domicilio = new JSONObject();
        domicilio.put("pais", getDomicilio().getPais());
        domicilio.put("provincia", getDomicilio().getProvincia());
        domicilio.put("ciudad", getDomicilio().getCiudad());
        domicilio.put("calleYaltura", getDomicilio().getCalleYaltura());
        jsonObject1.put("domicilio", domicilio);

        // Serializar librosEnPosesion
        JSONArray librosArray = new JSONArray();
        for (Libro libro : librosEnPosesion) {
            librosArray.put(libro.toJson());
        }
        jsonObject1.put("librosEnPosesion", librosArray);

        return jsonObject1;
    }

    //Resta el precio del libro alquilado al saldo total del cliente
    public void restarSaldoXAlquiler(float precioAlquiler){
        saldo-=precioAlquiler;
    }

    public ArrayList<Libro> getLibrosEnPosesion() {
        return librosEnPosesion;
    }

    public void cargarSaldo(float newSaldo){
        saldo+=newSaldo;
    }

}
