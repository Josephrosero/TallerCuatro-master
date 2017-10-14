package com.example.i043114.tallercuatro.Parsers;

import com.example.i043114.tallercuatro.Models.ModelPost;
import com.example.i043114.tallercuatro.Models.ModelUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 12/10/2017.
 */

public class JsonPost {

    public static List<ModelPost> getData(String content) throws JSONException {
        JSONArray jsonArray = new JSONArray(content);

        List<ModelPost> photosList2 = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject item = jsonArray.getJSONObject(i);

             ModelPost photos = new ModelPost();

            //  photos.setUserid(item.getInt("userId"));
            photos.setId(item.getInt("id"));
            photos.setTitle(item.getString("title"));
            photos.setBody(item.getString("body"));

            photosList2.add(photos);
        }
        return photosList2;
    }
}
