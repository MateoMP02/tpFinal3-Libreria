package Clases;

import java.io.Serializable;

/**
 * Este código define una clase abstracta Persona, que sirve como base para representar personas en el sistema de
 * gestión de bibliotecas. Aquí está una breve descripción de cada método y un resumen del propósito del código:
 *
 * Constructor Persona(String, int, Domicilio):
 *
 * Inicializa un objeto Persona con el nombre y apellido, edad y domicilio proporcionados.
 * Constructor Persona():
 *
 * Constructor por defecto que inicializa los atributos de la persona con valores predeterminados.
 * setDomicilio(Domicilio):
 *
 * Establece el domicilio de la persona con el domicilio proporcionado.
 * toString():
 *
 * Genera una representación en cadena de los datos personales, incluyendo nombre y apellido, edad y domicilio.
 * setters y getters:
 *
 * Métodos para acceder y modificar los atributos de la persona, como nombre y apellido, edad y domicilio.
 * Esta clase abstracta encapsula la información básica de una persona en el sistema de gestión de la biblioteca,
 * permitiendo la extensión para diferentes tipos de personas, como clientes y empleados.
 */

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
