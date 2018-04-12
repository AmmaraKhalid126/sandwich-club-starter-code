package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    Sandwich sandwich;
    TextView mainName,alsoKnownAs,placeOfOrigin,descriptions,ingredients;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        createWidgets();

        //ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }


        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
//        Picasso.with(this)
//                .load(sandwich.getImage())
//                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        mainName.setText(sandwich.getMainName());
        alsoKnownAs.setText(sandwich.getAlsoKnownAs().get(1));
        placeOfOrigin.setText(sandwich.getPlaceOfOrigin());
        descriptions.setText(sandwich.getDescription());
        ingredients.setText(sandwich.getIngredients().get(1));
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(image);

    }
    private  void createWidgets()
    {
        mainName=(TextView)findViewById(R.id.main_Name);
        alsoKnownAs=(TextView)findViewById(R.id.also_known_as1);
        placeOfOrigin=(TextView)findViewById(R.id.place_Of_origin1);
        descriptions=(TextView)findViewById(R.id.description1);
        ingredients=(TextView)findViewById(R.id.ingrediets1);
        image=(ImageView)findViewById(R.id.image_iv)


    }
}
