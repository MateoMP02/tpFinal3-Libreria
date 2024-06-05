package Generics;

import Clases.Cliente;
import Clases.Libro;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

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
