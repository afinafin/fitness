package com.example.jingchun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UpdateExercise extends AppCompatActivity {

    //initialize just to check calculation working or not
    int burnCal=89;
    Button exerciseSaveBtn, exerciseCancelBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_exercise);
        setTitle("UPDATE MEALS");

        exerciseSaveBtn = findViewById(R.id.saveExercise);
        exerciseCancelBtn = findViewById(R.id.cancelExercise);

        exerciseSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sp = getApplicationContext().getSharedPreferences("UserPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();

                editor.putInt("BURN", burnCal);
                editor.commit();

                Intent intent = new Intent(UpdateExercise.this, Activity2.class);
                startActivity(intent);
            }
        });

        exerciseCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateExercise.this, Activity2.class);
                startActivity(intent);
            }

        });
    }
}