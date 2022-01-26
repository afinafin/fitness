package com.example.jingchun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class Activity2 extends AppCompatActivity {

    TextView calories, intake, burn, remaining;
    int cal, intakeCal, srcIntake, currentIntake, burnCal, srcBurn, currentBurn, remainCal;
    Button buttonMeals, buttonExercise;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        setTitle("Calories - MAINTAIN");

        sp = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        sp = getApplicationContext().getSharedPreferences("UserPrefs", MODE_PRIVATE);

        calories = findViewById(R.id.CaloriesMaintain);
        cal = sp.getInt("MAINTAIN", 0);
        calories.setText(cal + "");

        intake = findViewById(R.id.intake);
        currentIntake = sp.getInt("CURRENT_MEAL", 0);
        srcIntake = sp.getInt("INTAKE",0);
        intakeCal = currentIntake + srcIntake;
        intake.setText(intakeCal + "");
        editor.putInt("CURRENT_MEAL", intakeCal);
        editor.commit();


        burn = findViewById(R.id.calorieBurn);
        currentBurn = sp.getInt("CURRENT_EXERCISE", 0);
        srcBurn = sp.getInt("BURN", 0);
        burnCal = currentBurn + srcBurn;
        burn.setText(burnCal + "");
        editor.putInt("CURRENT_EXERCISE", burnCal);
        editor.commit();

        remaining = findViewById(R.id.remainingCalorie);
        remainCal = cal - intakeCal + burnCal;
        remaining.setText(remainCal + "");



        buttonMeals = findViewById(R.id.addMeal);
        buttonMeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity2.this, UpdateMeals.class);
                startActivity(intent);
            }
        });

        buttonExercise = findViewById(R.id.addExercise);
        buttonExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity2.this, UpdateExercise.class);
                startActivity(intent);
            }
        });


    }

}

