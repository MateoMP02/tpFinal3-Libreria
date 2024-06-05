package Interfaces;

import org.json.JSONException;
import org.json.JSONObject;

public interface IJsonSerializable <T>{
    JSONObject toJson() throws JSONException;
    T fromJson(JSONObject jsonObject) throws JSONException;
}
