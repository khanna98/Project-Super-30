package com.example.mayank.illnessdetetor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DiseaseActivity extends AppCompatActivity {
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> hashMap;
    DBConnectivity db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diseases);

        db = new DBConnectivity(getApplicationContext());
        try {
            db.createDatabase();
            db.openDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        expandableListView = findViewById(R.id.exp_list);
        ListData();
        expandableListAdapter = new ExpandableListAdapter(this, expandableListTitle, hashMap);
        expandableListView.setAdapter(expandableListAdapter);
    }

    private void ListData() {
        expandableListTitle = new ArrayList<>();
        hashMap = new HashMap<>();
        expandableListTitle = db.getDiseases();
        List<String> Disease = new ArrayList<>();

        for (int counter = 0; counter < expandableListTitle.size(); counter++) {
            Disease = db.getSymptomsFromDisease(expandableListTitle.get(counter));
            hashMap.put(expandableListTitle.get(counter), Disease);
        }
    }
}