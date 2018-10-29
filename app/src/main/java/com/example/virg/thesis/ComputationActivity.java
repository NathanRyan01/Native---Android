package com.example.virg.thesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Arrays;

import java.util.Arrays;

public class ComputationActivity extends MainActivity {
    EditText number;
    String text;
    int n;
    long startTime;
    long endTime;
    long MethodDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computation);
    }

    public void computeMatrix(View view) {
        startTime = (long) 0.0;
        endTime = (long) 0.0;
        MethodDuration = (long) 0.0;
        startTime = System.currentTimeMillis();
        number = findViewById(R.id.editText2);
        text = number.getText().toString();
        n = Integer.parseInt(text);
        int A[][] = new int[n][n];
        int B[][] = new int[n][n];
        int[][] C = new int[n][n];
        int i, j, k, count = 0, sum = 0;
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                A[i][j] = count;
                B[i][j] = count;
                count++;
            }
        }
        for (i = 0; i < A.length; i++) {
            for (j = 0; j < B[0].length; j++) {
                sum = 0;
                for (k = 0; k < A[0].length; k++) {
                    sum += A[i][k] * B[k][j];
                }
                C[i][j] = sum;
            }
        }
        endTime = System.currentTimeMillis();
        MethodDuration = (endTime - startTime);
        String total = String.valueOf(MethodDuration);
        Toast.makeText(ComputationActivity.this,
                total + " milliseconds to complete computation.", Toast.LENGTH_LONG).show(); }
}






