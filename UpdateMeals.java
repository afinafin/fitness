package com.example.jingchun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Spinner;

import java.lang.reflect.Array;

public  class UpdateMeals extends AppCompatActivity  {

    String[] mealsArray = {"Select your meal", "Nasi Lemak", "Mee Goreng"};

    //initialize just to check working
    int mealCal=490;
    Spinner mealsInput;
    Button mealSaveBtn, mealCancelBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_meals);
        setTitle("UPDATE MEALS");

        mealSaveBtn = findViewById(R.id.saveMeal);
        mealCancelBtn = findViewById(R.id.cancelMeal);

//
//        mealsInput.setOnItemSelectedListener(this);

        mealsInput = (Spinner) findViewById(R.id.mealSpinner);

        ArrayAdapter mealSpinner = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, mealsArray);
//
        mealSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
        mealsInput.setAdapter(mealSpinner);

        mealSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sp = getApplicationContext().getSharedPreferences("UserPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();

                editor.putInt("INTAKE", mealCal);
                editor.commit();

                Intent intent = new Intent(UpdateMeals.this, Activity2.class);
                startActivity(intent);
            }
        });

        mealCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateMeals.this, Activity2.class);
                startActivity(intent);
            }

        });

    }


//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        if (mealsArray[i] == "Nasi Lemak")
//        {
//            mealCal = 490;
//        }
//        else if (mealsArray[i] == "Mee Goreng"){
//            mealCal = 400;
//        }
//        else
//            mealCal=0;
//
//        Toast.makeText(getApplicationContext(), mealsArray[i], Toast.LENGTH_SHORT).show();
//
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//
//    }

}

//AutoCompleteTextView using drop down

//        String[] meals = getResources().getStringArray(R.array.meals);
//        // get reference to the string array that we just created
//
//        // create an array adapter and pass the required parameter
//        // in our case pass the context, drop down layout , and array.
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, meals);
//        // get reference to the autocomplete text view
//        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
//        // set adapter to the autocomplete tv to the arrayAdapter
////        mealsInput.setOnItemSelectedListener(this);
//        textView.setAdapter(arrayAdapter);