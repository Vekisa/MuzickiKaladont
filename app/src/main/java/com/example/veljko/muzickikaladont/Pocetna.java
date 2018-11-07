package com.example.veljko.muzickikaladont;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Pocetna extends AppCompatActivity {

    private Button novaIgra;
    private Button uputstvo;
    private Button oAplikaciji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_pocetna);

        novaIgra = (Button) findViewById(R.id.novaIgra);
        uputstvo = (Button) findViewById(R.id.uputstvo);
        oAplikaciji = (Button) findViewById(R.id.oAplikaciji);

        configureElements();
    }

    void configureElements(){

        novaIgra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NovaIgra.class);
                v.getContext().startActivity(intent);
            }
        });

        uputstvo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Uputstvo.class);
                v.getContext().startActivity(intent);
            }
        });

        oAplikaciji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), OAplikaciji.class);
                v.getContext().startActivity(intent);
            }
        });
    }

}
