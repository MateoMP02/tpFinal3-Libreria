package Interfaces;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Este código define una interfaz llamada IJsonSerializable, que proporciona métodos para serializar y deserializar
 * objetos en formato JSON. Aquí está una descripción de los métodos:
 *
 * toJson():
 *
 * Este método convierte un objeto de tipo T a un objeto JSONObject de la biblioteca JSON.
 * Lanza una excepción JSONException si ocurre algún error durante la conversión.
 * fromJson(JSONObject jsonObject):
 *
 * Este método toma un objeto JSONObject y lo convierte de nuevo a un objeto de tipo T.
 * Lanza una excepción JSONException si ocurre algún error durante la conversión.
 * Esta interfaz proporciona una forma genérica de serializar y deserializar objetos en formato JSON,
 * lo que permite la interoperabilidad entre diferentes tipos de objetos y la representación de datos en formato JSON.
 * @param <T>
 */

public interface IJsonSerializable <T>{
    JSONObject toJson() throws JSONException;
    T fromJson(JSONObject jsonObject) throws JSONException;
}
