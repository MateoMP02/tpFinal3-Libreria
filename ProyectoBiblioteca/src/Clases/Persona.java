package Clases;

public abstract class Persona {
    private String nombreYapellido;
    private int edad;
    private Domicilio domicilio;

    public Persona(String nombreYapellido, int edad, Domicilio domicilio) {
        this.nombreYapellido = nombreYapellido;
        this.edad = edad;
        this.domicilio = domicilio;
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
}
