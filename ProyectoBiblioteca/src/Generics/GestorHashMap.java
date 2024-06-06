package Generics;
import java.util.HashMap;

/**
 *
 Este código define una clase genérica llamada GestorHashMap, que actúa como un gestor para una estructura de datos HashMap.
 Aquí una breve descripción de cada método:

 Constructor GestorHashMap():

 Inicializa un nuevo HashMap vacío.
 agregar(Integer clave, T valor):

 Agrega un par clave-valor al HashMap.
 buscar(Integer clave):

 Busca y devuelve el valor asociado con la clave especificada en el HashMap.
 actualizar(Integer clave, T nuevoValor):

 Reemplaza el valor asociado con la clave especificada en el HashMap por un nuevo valor.
 eliminar(Integer clave):

 Elimina la entrada asociada con la clave especificada del HashMap.
 obtenerTodos():

 Devuelve el HashMap completo.
 Esta clase proporciona métodos básicos para agregar, buscar, actualizar y eliminar elementos en un HashMap,
 así como para obtener todos los elementos almacenados en él. El uso de tipos genéricos permite que la clase
 sea utilizada con cualquier tipo de datos para las claves y los valores.
 */


public class GestorHashMap<Integer,T> {
    private HashMap<Integer, T> mapa;

    public GestorHashMap() {
        this.mapa = new HashMap<>();
    }

    public void agregar(Integer clave, T valor) {
        mapa.put(clave, valor);
    }

    public T buscar(Integer clave) {
        return mapa.get(clave);
    }

    public void actualizar(Integer clave, T nuevoValor) {
        mapa.put(clave, nuevoValor);
    }

    public void eliminar(Integer clave) {
        mapa.remove(clave);
    }

    public HashMap<Integer, T> obtenerTodos() {
        return mapa;
    }
}
