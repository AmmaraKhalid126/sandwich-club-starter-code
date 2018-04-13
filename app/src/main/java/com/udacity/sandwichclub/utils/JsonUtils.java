package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json)
    {
        ArrayList<ArrayList<String>> ingredients_array = null;
        String place_of_origin, description_string, image_string;
        Sandwich sandwich = new Sandwich();
        try {
            JSONObject obj = new JSONObject(json);

            JSONObject nameObject = obj.getJSONObject("name");
            String mainName = nameObject.getString("mainName");
            JSONArray alsoKnownAs = nameObject.getJSONArray("alsoKnownAs");
            String placeOfOrigin = obj.getString("placeOfOrigin");
            String description = obj.getString("description");
            String image = obj.getString("image");
            sandwich.setPlaceOfOrigin(placeOfOrigin);
            sandwich.setMainName(mainName);

            ArrayList<String> alsoKnownAsData = new ArrayList<String>();
            if (alsoKnownAs != null) {
                for (int i=0;i<alsoKnownAs.length();i++){
                    alsoKnownAsData.add(alsoKnownAs.getString(i));
                }
            }

            sandwich.setAlsoKnownAs(alsoKnownAsData);
            sandwich.setImage(image);
            sandwich.setDescription(description);
            JSONArray ingredients=obj.getJSONArray("ingredients");
            ArrayList<String> ingredientsData = new ArrayList<String>();
            if (ingredients != null) {
                for (int i=0;i<ingredients.length();i++){
                    ingredientsData.add(ingredients.getString(i));
                }
            }




        } catch (Exception e) {
            e.printStackTrace();
        }
        return sandwich;
    }
}