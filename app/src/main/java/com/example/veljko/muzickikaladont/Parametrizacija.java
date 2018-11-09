package com.example.veljko.muzickikaladont;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class Parametrizacija extends AppCompatActivity {

    private RadioButton radio_15, radio_30, radio_45, radio_60, radio_90, radio_beskonacno,
    zivot_1, zivot_2, zivot_3;

    private ArrayList<RadioButton> vremena, zivoti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_parametrizacija);

        ((Button) findViewById(R.id.zapocni)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Parametrizacija.this, Igra.class);
                zabeleziPodesavanja(intent);
                v.getContext().startActivity(intent);
            }
        });

        vremenaUGrupu();
        zivotiUGrupu();
    }

    private void vremenaUGrupu(){

        radio_15 = (RadioButton)findViewById(R.id.radio_15);
        radio_30 = (RadioButton)findViewById(R.id.radio_30);
        radio_45 = (RadioButton)findViewById(R.id.radio_45);
        radio_60 = (RadioButton)findViewById(R.id.radio_60);
        radio_90 = (RadioButton)findViewById(R.id.radio_90);
        radio_beskonacno = (RadioButton)findViewById(R.id.radio_beskonacno);

        vremena =  new ArrayList<>();
        vremena.add(radio_15);
        vremena.add(radio_30);
        vremena.add(radio_45);
        vremena.add(radio_60);
        vremena.add(radio_90);
        vremena.add(radio_beskonacno);


        radio_15.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                radio_30.setChecked(false);
                radio_45.setChecked(false);
                radio_60.setChecked(false);
                radio_90.setChecked(false);
                radio_beskonacno.setChecked(false);
            }
        });

        radio_30.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                radio_15.setChecked(false);
                radio_45.setChecked(false);
                radio_60.setChecked(false);
                radio_90.setChecked(false);
                radio_beskonacno.setChecked(false);
            }
        });

        radio_45.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                radio_15.setChecked(false);
                radio_30.setChecked(false);
                radio_60.setChecked(false);
                radio_90.setChecked(false);
                radio_beskonacno.setChecked(false);
            }
        });

        radio_60.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                radio_15.setChecked(false);
                radio_30.setChecked(false);
                radio_45.setChecked(false);
                radio_90.setChecked(false);
                radio_beskonacno.setChecked(false);
            }
        });

        radio_90.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                radio_15.setChecked(false);
                radio_30.setChecked(false);
                radio_45.setChecked(false);
                radio_60.setChecked(false);
                radio_beskonacno.setChecked(false);
            }
        });

        radio_beskonacno.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                radio_15.setChecked(false);
                radio_30.setChecked(false);
                radio_45.setChecked(false);
                radio_60.setChecked(false);
                radio_90.setChecked(false);
            }
        });
    }

    private void zivotiUGrupu(){

        zivot_1 = (RadioButton)findViewById(R.id.zivot_1);
        zivot_2 = (RadioButton)findViewById(R.id.zivot_2);
        zivot_3 = (RadioButton)findViewById(R.id.zivot_3);

        zivoti = new ArrayList<>();
        zivoti.add(zivot_1);
        zivoti.add(zivot_2);
        zivoti.add(zivot_3);

        zivot_1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                zivot_2.setChecked(false);
                zivot_3.setChecked(false);

            }
        });

        zivot_2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                zivot_1.setChecked(false);
                zivot_3.setChecked(false);

            }
        });

        zivot_3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                zivot_1.setChecked(false);
                zivot_2.setChecked(false);

            }
        });
    }

    private void zabeleziPodesavanja(Intent intent){
        Bundle parametri = (Bundle) this.getIntent().getExtras();
        int zaustavljanje = 0, vreme = 0, zivot = 0;

        if(((RadioButton)findViewById(R.id.radio_manuelno)).isChecked())
            zaustavljanje = 1;

        for(int i = 0; i < vremena.size(); i++){
            if(vremena.get(i).isChecked()){
                vreme = i;
                break;
            }
        }

        for(int i = 0; i < zivoti.size(); i++){
            if(zivoti.get(i).isChecked()){
                zivot = i;
                break;
            }
        }

        parametri.putInt("zaustavljanje", zaustavljanje);
        parametri.putInt("vreme", vreme);
        parametri.putInt("zivot", zivot);

        intent.putExtras(parametri);
    }
}
