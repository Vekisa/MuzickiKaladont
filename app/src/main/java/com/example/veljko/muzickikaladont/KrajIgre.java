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

public class KrajIgre extends AppCompatActivity {

    Bundle parametri;
    TextView tvIme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_kraj_igre);

        parametri = new Bundle((Bundle) this.getIntent().getExtras());
        tvIme = (TextView) findViewById(R.id.ime);

        tvIme.setText(parametri.get("pobednik").toString());


    }


}
