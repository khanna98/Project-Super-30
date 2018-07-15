package com.example.mayank.illnessdetetor;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class Symptoms extends AppCompatActivity {
    ListView listView;
    Button Show_Deseases;
    DBConnectivity db;
    ArrayList<String> listContent = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);

        db = new DBConnectivity(getApplicationContext());
        try {
            db.createDatabase();
            db.openDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        listContent = db.getSymptoms();
        Show_Deseases = findViewById(R.id.buttonB);
        listView = findViewById(R.id.sym_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, listContent);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);
        Show_Deseases.setOnClickListener(new Button.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 String checked_symptoms = "";
                                                 int cntChoice = listView.getCount();
                                                 ArrayList<String> symptoms_ar_list = new ArrayList<>();
                                                 SparseBooleanArray pos_Array = listView.getCheckedItemPositions();
                                                 for (int i = 0; i < cntChoice; i++) {
                                                     if (pos_Array.get(i)) {

                                                         symptoms_ar_list.add(listView.getItemAtPosition(i).toString());
                                                     }
                                                 }

                                                 Set<String> disease = db.getDiseaseFromSymptoms(symptoms_ar_list);
                                                 openDialog(disease);
                                             }
                                         }
        );
    }

    private void openDialog(Set<String> diseases) {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogue_view = inflater.inflate(R.layout.dialogue_box, null);
        dialogBuilder.setView(dialogue_view);


        dialogBuilder.setTitle("Possible diseases : \n");
        String end_result = "";

        for (String diseas : diseases) {
            end_result = end_result + diseas + "\n";
        }

        dialogBuilder.setMessage(end_result);

        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        AlertDialog box = dialogBuilder.create();
        box.show();
    }
}