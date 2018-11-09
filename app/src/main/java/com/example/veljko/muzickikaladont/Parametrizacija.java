package com.example.veljko.muzickikaladont;

import android.annotation.SuppressLint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class Parametrizacija extends AppCompatActivity {

    //Podaci iz prethodne forme
    private Bundle parametri;

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_parametrizacija);

        parametri = getIntent().getExtras();

        tv = (TextView) findViewById(R.id.tv);

        tv.setText(tv.getText() + "\n" + parametri.getString("imeIgrac1") +
                                    "\n" + parametri.getString("imeIgrac2") +
                                    "\n" + parametri.getString("imeIgrac3") +
                                    "\n" + parametri.getString("imeIgrac4") +
                                    "\n" + parametri.getString("imeIgrac5") +
                                    "\n" + parametri.getString("imeIgrac6"));

    }


}
