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
