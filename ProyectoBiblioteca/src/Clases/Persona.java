package Clases;

import java.io.Serializable;

public abstract class Persona implements Serializable {
    private String nombreYapellido;
    private int edad;
    private Domicilio domicilio;

    public Persona(String nombreYapellido, int edad, Domicilio domicilio) {
        this.nombreYapellido = nombreYapellido;
        this.edad = edad;
        this.domicilio = domicilio;
    }

    public Persona() {
        this.nombreYapellido = "";
        this.edad = 0;
        this.domicilio = null;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    @Override
    public String toString() {
        return "Datos Personales:\n" +
                "Nombre  y apellido:" + nombreYapellido +"\n"+
                "Edad: "+edad +"\n"+
                "Domicilio: "+ domicilio;
    }

    public String getNombreYapellido() {
        return nombreYapellido;
    }

    public void setNombreYapellido(String nombreYapellido) {
        this.nombreYapellido = nombreYapellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }
}
