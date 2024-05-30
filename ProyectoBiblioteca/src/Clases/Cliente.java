package Clases;

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
}
