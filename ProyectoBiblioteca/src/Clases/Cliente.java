package Clases;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Cliente extends Persona{
    private Integer idCliente;
    private String correoElectronico;
    private float saldo;

    public Cliente(String nombreYapellido, int edad, Domicilio domicilio, Integer idCliente, String correoElectronico) {
        super(nombreYapellido, edad, domicilio);
        this.idCliente = idCliente;
        this.correoElectronico = correoElectronico;
    }

    public void modificarDomicilio (Domicilio domicilio){
        super.setDomicilio(domicilio);
    }

    @Override
    public String toString() {
        return super.toString()+"\n"+
                "Datos Lector:"+"\n"+
                "idCliente: "+idCliente+"\n"+
                "Correo electronico: "+ correoElectronico+"\n";
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
        int idCliente=jsonObject.getInt("idCliente");
        String correoElectronico= jsonObject.getString("correoElectronico");
        float saldo = (float) jsonObject.getDouble("saldo");
        int edad = jsonObject.getInt("edad");
        String nombreYapellido = jsonObject.getString("nombreYapellido");
        JSONObject domicilio = jsonObject.getJSONObject("domicilio");
        String pais = domicilio.getString("pais");
        String provincia = domicilio.getString("provincia");
        String ciudad = domicilio.getString("ciudad");
        String calleYaltura = domicilio.getString("calleYaltura");

        return new Cliente(nombreYapellido,edad,new Domicilio(calleYaltura,ciudad,pais,provincia),idCliente,correoElectronico);
    }
}
