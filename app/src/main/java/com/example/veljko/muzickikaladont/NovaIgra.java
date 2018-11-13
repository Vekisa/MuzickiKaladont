package com.example.veljko.muzickikaladont;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.HashMap;

public class NovaIgra extends AppCompatActivity {

    //Labele koje su hide-ovane
    private LinearLayout igrac3;
    private LinearLayout igrac4;
    private LinearLayout igrac5;
    private LinearLayout igrac6;

    //EditTextovi za unos imena
    private EditText ime1;
    private EditText ime2;
    private EditText ime3;
    private EditText ime4;
    private EditText ime5;
    private EditText ime6;


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

        ime1 = (EditText) findViewById(R.id.ET1);
        ime2 = (EditText) findViewById(R.id.ET2);
        ime3 = (EditText) findViewById(R.id.ET3);
        ime4 = (EditText) findViewById(R.id.ET4);
        ime5 = (EditText) findViewById(R.id.ET5);
        ime6 = (EditText) findViewById(R.id.ET6);

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

    void configureElements() {

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (brojac) {
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
                switch (brojac) {
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

                Intent intent = new Intent(NovaIgra.this, Parametrizacija.class);
                if (zabeleziImena(intent)) {
                    v.getContext().startActivity(intent);
                }
            }
        });
    }

    boolean zabeleziImena(Intent intent) {
        Bundle parametri = new Bundle();
        HashMap<String,Integer> imena = new HashMap<String,Integer>();

        parametri.putInt("brojIgraca", brojac);

        parametri.putString("Igrac 1", ime1.getText().toString());
        parametri.putString("Igrac 2", ime2.getText().toString());
        parametri.putString("Igrac 3", ime3.getText().toString());
        parametri.putString("Igrac 4", ime4.getText().toString());
        parametri.putString("Igrac 5", ime5.getText().toString());
        parametri.putString("Igrac 6", ime6.getText().toString());

        switch (brojac) {
            case 2:
                if (ime1.getText().toString().equals("") || ime2.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Unesite imena svih igraca!", Toast.LENGTH_SHORT);
                    toast.setMargin(50, 50);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
                    toast.show();
                    return false;
                }
                imena.put(ime1.getText().toString(),1);
                imena.put(ime2.getText().toString(),2);
                break;
            case 3:
                if (ime1.getText().toString().equals("") || ime2.getText().toString().equals("") ||
                        ime3.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Unesite imena svih igraca!", Toast.LENGTH_SHORT);
                    toast.setMargin(50, 50);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
                    toast.show();
                    return false;
                }
                imena.put(ime1.getText().toString(),1);
                imena.put(ime2.getText().toString(),2);
                imena.put(ime3.getText().toString(),3);
                break;
            case 4:
                if (ime1.getText().toString().equals("") || ime2.getText().toString().equals("") ||
                        ime3.getText().toString().equals("") || ime4.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Unesite imena svih igraca!", Toast.LENGTH_SHORT);
                    toast.setMargin(50, 50);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
                    toast.show();
                    return false;
                }
                imena.put(ime1.getText().toString(),1);
                imena.put(ime2.getText().toString(),2);
                imena.put(ime3.getText().toString(),3);
                imena.put(ime4.getText().toString(),4);
                break;
            case 5:
                if (ime1.getText().toString().equals("") || ime2.getText().toString().equals("") ||
                        ime3.getText().toString().equals("") || ime4.getText().toString().equals("") ||
                        ime5.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Unesite imena svih igraca!", Toast.LENGTH_SHORT);
                    toast.setMargin(50, 50);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
                    toast.show();
                    return false;
                }
                imena.put(ime1.getText().toString(),1);
                imena.put(ime2.getText().toString(),2);
                imena.put(ime3.getText().toString(),3);
                imena.put(ime4.getText().toString(),4);
                imena.put(ime5.getText().toString(),5);
                break;
            case 6:
                if (ime1.getText().toString().equals("") || ime2.getText().toString().equals("") ||
                        ime3.getText().toString().equals("") || ime4.getText().toString().equals("") ||
                        ime5.getText().toString().equals("") || ime6.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Unesite imena svih igraca!", Toast.LENGTH_SHORT);
                    toast.setMargin(50, 50);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
                    toast.show();
                    return false;
                }
                imena.put(ime1.getText().toString(),1);
                imena.put(ime2.getText().toString(),2);
                imena.put(ime3.getText().toString(),3);
                imena.put(ime4.getText().toString(),4);
                imena.put(ime5.getText().toString(),5);
                imena.put(ime6.getText().toString(),6);
                break;
        }

        if(imena.size() != brojac) {
            Toast toast = Toast.makeText(getApplicationContext(), "Imena se moraju razlikovati!", Toast.LENGTH_SHORT);
            toast.setMargin(50, 50);
            toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
            toast.show();
            return false;
        }

        intent.putExtras(parametri);
        return true;
    }

}
