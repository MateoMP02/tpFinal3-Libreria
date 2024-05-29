package Generics;
import java.util.HashMap;

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
