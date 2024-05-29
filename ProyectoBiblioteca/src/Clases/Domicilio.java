package Clases;

public class Domicilio {
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

    public String getPais() {
        return pais;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getCalleYaltura() {
        return calleYaltura;
    }

    @Override
    public String toString() {
        return  "Pais'" + pais +"\n"+
                "Provincia: "+ provincia +"\n"+
                "Ciudad: "+ ciudad +"\n"+
                "Calle y Altura: "+ calleYaltura;

    }
}
