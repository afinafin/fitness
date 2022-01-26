package com.example.jingchun;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText InputName;
    EditText InputAge;
    EditText InputHeight;
    EditText InputWeight;
    String[] gender = {"", "Male", "Female"};
    Spinner InputGender;
    int genderValue;

    Button buttonSave, buttonNext;

    String userName;
    int userAge;
    Float userHeight;
    Float userWeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Home");
        InputName = findViewById(R.id.UserName);
        InputGender = findViewById(R.id.genderSpinner);
        InputAge = findViewById(R.id.UserAge);
        InputHeight = findViewById(R.id.UserHeight);
        InputWeight = findViewById(R.id.UserWeight);
        buttonSave = findViewById(R.id.UserSave);
        buttonNext = findViewById(R.id.Activity2);

        InputGender.setOnItemSelectedListener(this);

        //style spinner
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, gender);

        //dropdown
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //attach adapter to spinner
        InputGender.setAdapter(adapter);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((InputName.getText().toString().equals("")) ||
                        (InputAge.getText().toString().equals("")) ||
                        (InputHeight.getText().toString().equals("")) ||
                        (InputWeight.getText().toString().equals(""))) {
                    Toast.makeText(MainActivity.this, "Please make sure you have selected and entered all inputs", Toast.LENGTH_SHORT).show();
                } else {
                    userName = InputName.getText().toString();
                    userAge = Integer.parseInt(InputAge.getText().toString());
                    userHeight = Float.parseFloat(InputHeight.getText().toString());
                    userWeight = Float.parseFloat(InputWeight.getText().toString());

                    //call function to save data
                    saveData(userName,userAge,userHeight,userWeight,genderValue);
                }

            }
        });


        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if (gender[i] == "Male") {
            genderValue = 1;
        }
        else if (gender[i] == "Female") {
            genderValue = 0;
        }
        else
            genderValue = 999;
        Toast.makeText(getApplicationContext(), gender[i], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

   // https://gist.github.com/codinginflow/b4f4c0cb30dbc135129c89fa13c184a1
   public void saveData(String name, int age, Float height, Float weight, int gender)
   {

       int caloriesMaintain = calculateCaloriesMaintain(age,height,weight,gender);

       SharedPreferences sp = getSharedPreferences("UserPrefs", MODE_PRIVATE);
       SharedPreferences.Editor editor = sp.edit();

       editor.clear();

        editor.putString("NAME", name);
        editor.putFloat("AGE", age);
        editor.putFloat("HEIGHT", height);
        editor.putFloat("WEIGHT", weight);
        editor.putInt("GENDER", gender);
        editor.putInt("CURRENT_MEAL", 0);
        editor.putInt("CURRENT_EXERCISE", 0);



       editor.putInt("MAINTAIN",caloriesMaintain);
       editor.commit();

       //display message about information is saved
       Toast.makeText(MainActivity.this, "Information Saved:", Toast.LENGTH_SHORT).show();

   }

    //function to calculate calories maintain weight
    public int calculateCaloriesMaintain (int age, Float height, Float weight, int gender){

        int result = 0;
        // for male
        if (gender==1){
            result = (int) ((10*weight + 6.25*height - 5*age -161)*1.7);
        }
        //for female
        else if (gender==0){
            result = (int) ((10*weight + 6.25*height - 5*age +5)*1.7);
        }

        return result;
    }
}
