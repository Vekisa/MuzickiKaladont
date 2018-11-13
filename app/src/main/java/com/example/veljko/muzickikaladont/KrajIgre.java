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

        ((Button) findViewById(R.id.ponovi_igru)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(KrajIgre.this, Parametrizacija.class);
                intent.putExtras(parametri);
                v.getContext().startActivity(intent);
            }
        });

        ((Button) findViewById(R.id.glavni_meni)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(KrajIgre.this, Pocetna.class);
                intent.putExtras(parametri);
                v.getContext().startActivity(intent);
            }
        });

        tvIme = (TextView) findViewById(R.id.ime);
        tvIme.setText(parametri.get("pobednik").toString());
    }


}
