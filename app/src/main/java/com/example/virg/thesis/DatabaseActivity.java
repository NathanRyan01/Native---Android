package com.example.virg.thesis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DatabaseActivity extends MainActivity {

    EditText selectOne;
    EditText updateNumber;
    EditText updateText;
    EditText insertNumber;

    Button selectOneValueBtn;
    Button updateBtn;
    Button insertBtn;
    Button deleteBtn;
    Button selectJoinBtn;
    Button selectBothTablesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
    }

    public void selectOneValue(View view) {
        selectOne = findViewById(R.id.selectOneValue);
        selectOneValueBtn = findViewById(R.id.selectOneValueBtn);
        String action = "select";
        String row  = selectOne.getText().toString();
        new DatabaseResponse().execute(action, row,"dummy");
    }

    public void updatePersonTable(View view) {
        updateNumber = findViewById(R.id.updateNumberField);
        updateText = findViewById(R.id.updateTextField);
        updateBtn = findViewById(R.id.update);
        String action = "update";
        String row  = updateNumber.getText().toString();
        String value  = updateText.getText().toString();
        new DatabaseResponse().execute(action, row,value);
    }

    public void insertPersonTable(View view) {
        insertNumber = findViewById(R.id.insertNumberField);
        String action = "insert";
        String row  = insertNumber.getText().toString();
        new DatabaseResponse().execute(action, row);
    }

    public void deleteFromTables(View view) {
        deleteBtn = findViewById(R.id.delete);
        String action = "delete";
        new DatabaseRequest().execute(action);
    }

    public void selectFromBoth(View view) {
        selectJoinBtn = findViewById(R.id.selectJoin);
        String action = "both";
        new DatabaseRequest().execute(action);
    }

    public void selectFromOneTable(View view) {
        selectBothTablesBtn = findViewById(R.id.selectOneTable);
        String action = "single";
        new DatabaseRequest().execute(action);
    }
}
