package com.example.virg.thesis;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import static android.graphics.Color.rgb;

public class XMLActivity extends MainActivity {

    private ListView list = null;
    private String [] names = null;
    Button bt1;
    Button bt2;
    Button bt3;
    Button bt4;
    int value;
    long startTime;
    long endTime;
    long MethodDuration;

    private XMLWrestlerParser parser = null;
    CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml);
        list = findViewById(R.id.list);
        bt1 = findViewById(R.id.button1);
        bt2 = findViewById(R.id.button2);
        bt3 = findViewById(R.id.button3);
    }

    public void getXMLData(View v) {
        list.setBackgroundColor(rgb(0,0,0));
        switch (v.getId()) {
            case R.id.button1:
                value = 100;
                break;
            case R.id.button2:
                value = 1000;
                break;
            case R.id.button3:
                value = 10000;
                break;
        }
        startTime = (long) 0.0;
        endTime = (long) 0.0;
        MethodDuration = (long) 0.0;
        startTime = System.currentTimeMillis();

        parser = new XMLWrestlerParser(getApplicationContext(), value);
        names = parser.getNames();
        adapter = new CustomListAdapter(getApplicationContext(), names);
        list.setAdapter(adapter);


        endTime = System.currentTimeMillis();
        MethodDuration = (endTime - startTime);
        String total = String.valueOf(MethodDuration);
        Toast.makeText(XMLActivity.this,
                total + " milliseconds to complete computation.", Toast.LENGTH_LONG).show();
    }
}