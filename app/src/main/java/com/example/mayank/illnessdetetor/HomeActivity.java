package com.example.mayank.illnessdetetor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    Button Symptom,Disease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Symptom=findViewById(R.id.button2);
        Disease=findViewById(R.id.button3);
        Symptom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(HomeActivity.this,Symptoms.class);
                startActivity(intent);
            }
        });
        Disease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(HomeActivity.this,DiseaseActivity.class);
                startActivity(intent);
            }
        });
    }
}