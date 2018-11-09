package com.example.veljko.muzickikaladont;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import java.util.HashMap;
import java.util.Map;

public class Igra extends AppCompatActivity {

    private Map<String, Integer> igraci;
    private Integer brojIgraca, zaustavljanje, vreme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_igra);

        podesiIgru();
    }

    private void podesiIgru(){
        igraci = new HashMap<>(0);
        Bundle parametri = (Bundle) this.getIntent().getExtras();

        brojIgraca = (Integer) parametri.getSerializable("brojIgraca");
        zaustavljanje = (Integer) parametri.getSerializable("zaustavljanje");
        vreme = (Integer) parametri.getSerializable("vreme");
        Integer brojZivota = (Integer) parametri.getSerializable("zivot") + 1;

        for(int i = 1; i <= brojIgraca; i++){
            String igrac = "Igrac"+i;
            try{
                String ime = (String) parametri.getSerializable(igrac);
                if(ime == null || ime == "")
                    break;
                else
                    igraci.put(igrac, brojZivota);
            }catch(Exception e){
                break;
            }
        }
    }
}
