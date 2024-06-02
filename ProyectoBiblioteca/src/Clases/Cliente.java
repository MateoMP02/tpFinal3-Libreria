package Clases;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.util.ArrayList;

public class Cliente extends Persona{



    private Integer idCliente;
    private String correoElectronico;
    private float saldo;
    private ArrayList<Libro> librosEnPosecion;


    public Cliente(String nombreYapellido, int edad, Domicilio domicilio, Integer idCliente, String correoElectronico, float saldo) {
        super(nombreYapellido, edad, domicilio);
        this.idCliente = idCliente;
        this.correoElectronico = correoElectronico;
        this.saldo = saldo;
        this.librosEnPosecion = new ArrayList<>();
    }

    public void modificarDomicilio (Domicilio domicilio){
        super.setDomicilio(domicilio);
    }

    public boolean availableToRent(){
        boolean rta = true;
        if(librosEnPosecion.size() > 2){
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
                "libros alquilados:"+getLibrosEnPosecion()+"\n"+
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

        // Deserializar librosEnPosecion
        JSONArray librosArray = jsonObject.getJSONArray("librosEnPosecion");
        for (int i = 0; i < librosArray.length(); i++) {
            JSONObject libroJson = librosArray.getJSONObject(i);
            Libro libro = Libro.fromJson(libroJson);
            cliente.getLibrosEnPosecion().add(libro);
        }

        return cliente;
    }

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

        // Serializar librosEnPosecion
        JSONArray librosArray = new JSONArray();
        for (Libro libro : librosEnPosecion) {
            librosArray.put(libro.toJson());
        }
        jsonObject1.put("librosEnPosecion", librosArray);

        return jsonObject1;
    }

    public void restarSaldoXAlquiler(float precioAlquiler){
        saldo-=precioAlquiler;
    }

    public ArrayList<Libro> getLibrosEnPosecion() {
        return librosEnPosecion;
    }

}
