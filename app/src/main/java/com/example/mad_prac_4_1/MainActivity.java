package com.example.mad_prac_4_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void callSecondActivity(View view) {
        Intent i = new Intent(getApplicationContext(), SecondActivity.class);
        startActivity(i);
    }

}