package Generics;

import Clases.Cliente;
import Clases.Libro;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Este código define una clase genérica llamada ControladoraArchivosObjeto que se utiliza para guardar
 * y cargar objetos en archivos utilizando serialización de objetos en Java. Aquí está una descripción de los métodos:
 *
 * Constructor ControladoraArchivosObjeto():
 *
 * Un constructor vacío para la clase.
 * guardarHashMap(HashMap<Cliente, ArrayList<Libro>> hashMap, String nombreArchivo):
 *
 * Este método estático guarda un HashMap en un archivo. Toma como argumentos el HashMap a guardar y
 * el nombre del archivo en el que se guardará.
 * Utiliza un flujo de salida de objetos para escribir el HashMap en el archivo.
 * cargarHashMap(String nombreArchivo):
 *
 * Este método estático carga un HashMap desde un archivo. Toma el nombre del archivo como argumento y devuelve el HashMap cargado.
 * Utiliza un flujo de entrada de objetos para leer el HashMap del archivo.
 * Si el archivo no existe, se imprime un mensaje y se crea un nuevo HashMap vacío.
 * El uso de una clase genérica permite que esta clase se utilice para guardar y cargar diferentes tipos de HashMaps.
 * En este caso, se espera que el HashMap contenga claves de tipo Cliente y valores de tipo ArrayList<Libro>.
 * @param <T>
 */

public class ControladoraArchivosObjeto  <T> implements Serializable {

    private T variable;

    public ControladoraArchivosObjeto() {

    }

    public static void guardarHashMap(HashMap<Cliente, ArrayList<Libro>> hashMap, String nombreArchivo) {
        try (FileOutputStream fos = new FileOutputStream(nombreArchivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(hashMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<Cliente, ArrayList<Libro>> cargarHashMap(String nombreArchivo) {
        HashMap<Cliente, ArrayList<Libro>> hashMap = null;
        File archivo = new File(nombreArchivo);
        if (archivo.exists()) {
            try (FileInputStream fis = new FileInputStream(nombreArchivo);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                hashMap = (HashMap<Cliente, ArrayList<Libro>>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("El archivo " + nombreArchivo + " no existe.");
            hashMap = new HashMap<>();
        }
        return hashMap;
    }



}
