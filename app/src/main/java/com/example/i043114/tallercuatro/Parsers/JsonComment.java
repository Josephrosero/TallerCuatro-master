package com.example.i043114.tallercuatro.Parsers;

import com.example.i043114.tallercuatro.Models.ModelComment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 12/10/2017.
 */

public class JsonComment {
    public static List<ModelComment> getData(String content) throws JSONException {
        JSONArray jsonArray = new JSONArray(content);

        List<ModelComment> photosList3 = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject item = jsonArray.getJSONObject(i);



            ModelComment photos = new ModelComment();

        //    photos.setPostid(item.getInt("postId"));
            photos.setId(item.getInt("postId"));
            photos.setEmail(item.getString("email"));
            photos.setBody(item.getString("body"));


            photosList3.add(photos);
        }
        return photosList3;
    }
}
