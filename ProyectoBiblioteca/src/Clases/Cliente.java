package Clases;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;

public class Cliente extends Persona{



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

    public void setLibrosEnPosesion(ArrayList<Libro> librosEnPosesion) {
        this.librosEnPosesion = librosEnPosesion;
    }

    public void modificarDomicilio (Domicilio domicilio){
        super.setDomicilio(domicilio);
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
    public static Cliente fromJson(JSONObject jsonObject) throws JSONException {
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
        for (int i = 0; i < librosArray.length(); i++) {
            JSONObject libroJson = librosArray.getJSONObject(i);
            Libro libro = Libro.fromJson(libroJson);
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

}
