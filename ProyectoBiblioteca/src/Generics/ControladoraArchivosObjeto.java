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

   public  void grabarColeccion(ArrayList<T> coleccion)
   {
       FileOutputStream fileOutputStream=null;
       ObjectOutputStream objectOutputStream=null;
       try{
           fileOutputStream=new FileOutputStream("Coleccion.data");
           objectOutputStream=new ObjectOutputStream(fileOutputStream);

           for (T objeto :coleccion)
           {
               objectOutputStream.writeObject(objeto);
           }
       }
       catch (IOException ex)
       {
           ex.printStackTrace();
       }
       catch(Exception ex){
           ex.printStackTrace();
       }
       finally {
           try{
               if(fileOutputStream!=null){
                   fileOutputStream.close();
               }
               if(objectOutputStream!=null){
                   objectOutputStream.close();
               }
           }
               catch(IOException ex)
               {
                   ex.printStackTrace();
               }
       }

   }

public ArrayList<T> leerColeccion (String archivo)
{
    ArrayList<T> coleccion=new ArrayList();

    FileInputStream fileInputStream=null;
    ObjectInputStream objectInputStream=null;

    try{
        fileInputStream=new FileInputStream(archivo);
        objectInputStream=new ObjectInputStream(fileInputStream);

        while (true)
        {
            T objeto=(T)objectInputStream.readObject();
            coleccion.add(objeto);
        }

    }
    catch (EOFException ex){
        System.out.println("Fin del archivo");
    }
    catch (ClassNotFoundException ex)
    {
        ex.printStackTrace();
    }
    catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    catch (Exception e){
        throw new RuntimeException(e);
    }


    return coleccion;
}

/// recibe un cliente y crea el archivo por el id del cliente
    public  void grabarColeccionCliente(Cliente cliente)
    {
        FileOutputStream fileOutputStream=null;
        ObjectOutputStream objectOutputStream=null;

        ArrayList<Libro> coleccion= cliente.getLibrosEnPosesion();
        try{
            fileOutputStream=new FileOutputStream("Coleccion"+cliente.getIdCliente()+".data");
            objectOutputStream=new ObjectOutputStream(fileOutputStream);

            for (Libro objeto :coleccion)
            {
                objectOutputStream.writeObject(objeto);
            }

        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        catch(Exception ex){
            ex.printStackTrace();

        }
        finally {
            try{
                if(fileOutputStream!=null){
                    fileOutputStream.close();
                }
                if(objectOutputStream!=null){
                    objectOutputStream.close();
                }
            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }

        }

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
        try (FileInputStream fis = new FileInputStream(nombreArchivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            hashMap = (HashMap<Cliente, ArrayList<Libro>>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return hashMap;
    }



}
