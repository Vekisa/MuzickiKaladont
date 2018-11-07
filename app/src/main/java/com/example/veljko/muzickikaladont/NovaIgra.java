package com.example.veljko.muzickikaladont;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class NovaIgra extends AppCompatActivity {

    //Labele koje su hide-ovane
    private LinearLayout igrac3;
    private LinearLayout igrac4;
    private LinearLayout igrac5;
    private LinearLayout igrac6;

    private ImageButton btnAdd;
    private ImageButton btnRemove;
    private Button btnNext;
    private int brojac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_nova_igra);

        //Inicijalizacija, uvek ce biti broj trenutno vidljivih labela za unos imena
        brojac = 2;

        igrac3 = (LinearLayout) findViewById(R.id.LI3);
        igrac4 = (LinearLayout) findViewById(R.id.LI4);
        igrac5 = (LinearLayout) findViewById(R.id.LI5);
        igrac6 = (LinearLayout) findViewById(R.id.LI6);

        igrac3.setVisibility(View.GONE);
        igrac4.setVisibility(View.GONE);
        igrac5.setVisibility(View.GONE);
        igrac6.setVisibility(View.GONE);


        btnAdd = (ImageButton) findViewById(R.id.btnAdd);
        btnRemove = (ImageButton) findViewById(R.id.btnRemove);
        btnNext = (Button) findViewById(R.id.dalje);
        btnRemove.setVisibility(View.GONE);

        configureElements();
    }

    void configureElements(){

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(brojac){
                    case 2:
                        igrac3.setVisibility(View.VISIBLE);
                        btnRemove.setVisibility(View.VISIBLE);
                        ++brojac;
                        break;
                    case 3:
                        igrac4.setVisibility(View.VISIBLE);
                        ++brojac;
                        break;
                    case 4:
                        igrac5.setVisibility(View.VISIBLE);
                        ++brojac;
                        break;
                    case 5:
                        igrac6.setVisibility(View.VISIBLE);
                        ++brojac;
                        btnAdd.setVisibility(View.GONE);

                }
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (brojac){
                    case 6:
                        igrac6.setVisibility(View.GONE);
                        --brojac;
                        btnAdd.setVisibility(View.VISIBLE);
                        break;
                    case 5:
                        igrac5.setVisibility(View.GONE);
                        --brojac;
                        break;
                    case 4:
                        igrac4.setVisibility(View.GONE);
                        --brojac;
                        break;
                    case 3:
                        igrac3.setVisibility(View.GONE);
                        --brojac;
                        btnRemove.setVisibility(View.GONE);
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zabeleziImena();
                Intent intent = new Intent(v.getContext(), Parametrizacija.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    void zabeleziImena(){
        //Ovde se treba pobrinuti o imenima igraca i broju igraca
    }
}
