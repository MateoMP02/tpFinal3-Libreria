package Clases;

import java.awt.*;
import java.io.Serializable;

/**
 * ste código define la clase Domicilio, que representa la dirección de una persona en el sistema de gestión de bibliotecas.
 * Aquí se deja una breve descripción de cada método y un resumen del propósito del código:
 *
 * Constructor Domicilio(String, String, String, String):
 *
 * Inicializa un objeto Domicilio con los detalles proporcionados, incluyendo calle y altura, ciudad, país y provincia.
 * toString():
 *
 * Genera una representación en cadena del objeto Domicilio, incluyendo detalles como país, provincia, ciudad y calle con altura.
 * setters y getters:
 *
 * Métodos para acceder y modificar los atributos del domicilio, como país, provincia, ciudad y calle con altura.
 * Este código encapsula la información relacionada con la dirección de una persona en el sistema de gestión de la biblioteca,
 * proporcionando métodos para acceder a los detalles del domicilio y representarlo como una cadena de texto.
 */
public class Domicilio implements Serializable {
    private String pais;
    private String provincia;
    private String ciudad;
    private String calleYaltura;

    public Domicilio(String calleYaltura, String ciudad, String pais, String provincia) {
        this.calleYaltura = calleYaltura;
        this.ciudad = ciudad;
        this.pais = pais;
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return  "Pais: " + pais +"\n"+
                "Provincia: "+ provincia +"\n"+
                "Ciudad: "+ ciudad +"\n"+
                "Calle y Altura: "+ calleYaltura;

    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCalleYaltura() {
        return calleYaltura;
    }

    public void setCalleYaltura(String calleYaltura) {
        this.calleYaltura = calleYaltura;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
