package Generics;

import Clases.Cliente;
import Clases.Libro;

import java.io.*;
import java.util.ArrayList;

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

public ArrayList<T> leerColeccion ( )
{
    ArrayList<T> coleccion=new ArrayList();

    FileInputStream fileInputStream=null;
    ObjectInputStream objectInputStream=null;

    try{
        fileInputStream=new FileInputStream("Coleccion.data");
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



}
