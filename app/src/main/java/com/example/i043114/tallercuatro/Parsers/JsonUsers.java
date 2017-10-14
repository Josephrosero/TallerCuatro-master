package com.example.i043114.tallercuatro.Parsers;

import com.example.i043114.tallercuatro.Models.ModelUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 12/10/2017.
 */

public class JsonUsers {

    public static List<ModelUser> getData(String content) throws JSONException {
        JSONArray jsonArray = new JSONArray(content);

        List<ModelUser> photosList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject item = jsonArray.getJSONObject(i);


            JSONObject addresItem = item.getJSONObject("address");
            JSONObject companyItem = item.getJSONObject("company");

            ModelUser photos = new ModelUser();

            photos.setId(item.getInt("id"));
            photos.setName(item.getString("name"));
            photos.setUsername(item.getString("username"));
            photos.setCompany1(addresItem.getString("city"));

            photos.setAdress(companyItem.getString("name"));
            photosList.add(photos);
        }
        return photosList;
    }
}
