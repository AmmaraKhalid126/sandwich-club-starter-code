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
        ArrayList<ArrayList<String>> alsoKnownAs = null;
        ArrayList<String> mainName=null;
        ArrayList<String> placeOfOrigin=null;
        ArrayList<String> description=null;
        ArrayList<String> image=null;
        ArrayList<ArrayList<String>> ingredients_array=null;
        String place_of_origin,description_string,image_string;


        Sandwich s=new Sandwich();
        try{
            JSONObject obj=new JSONObject(json);
            JSONArray sandwichArray=new JSONArray("app_name");
            for(int i=0;i<sandwichArray.length();i++)
            {
                JSONObject details=sandwichArray.getJSONObject(i);
                place_of_origin=details.getString("detail_place_of_origin_label");
                description_string=details.getString("detail_description_label");
                image_string=details.getString("sandwich_picture_content_description");

                JSONObject alternateNames=details.getJSONObject("detail_also_known_as_label");
                ArrayList<String>temp=null;
                for(int j=0;j<alternateNames.length();j++)
                {
                    temp.add(alternateNames.getString("detail_also_known_as_label"));
                }
                alsoKnownAs.add(temp);
                temp=null;
                JSONObject ingredients=details.getJSONObject("detail_ingredients_label");
                for(int j=0;j<ingredients.length();j++)
                {
                    temp.add(details.getString("detail_ingredients_label"));
                }
                ingredients_array.add(temp);
                s.setAlsoKnownAs(alsoKnownAs.get(i));
                s.setDescription(description_string);
                s.setIngredients(ingredients_array.get(i));
                s.setPlaceOfOrigin(place_of_origin);
                s.setImage(image_string);

                //alsoKnownAs.add(alterneteName.get(i));
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }

       // JSONArray array= new JSONArray(R.id.sandwich_details);
//        s.getMainName()=json.getString("mainName");
//        s.getAlsoKnownAs()=json.getString("alsoKnownAs");
//        s.getPlaceOfOrigin()=json.getString("placeOfOrigin");
//        s.getDescription()=json.getString("description");
//        s.getImage()=json.getString("image");
//        s.getIngredients() =json.getString("ingredients");
        return s;
    }
}
