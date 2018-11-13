package com.example.veljko.muzickikaladont;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.CountDownTimer;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Igra extends AppCompatActivity {

    private Map<String, Pair<String, Integer>> igraci;
    private int brojIgraca, zaustavljanje, vreme, trenutniIgrac;
    private Bundle parametri;
    private Random random;
    CountDownTimer odbrojavanje, odbrojavanjePevanja,
            odbrojavanjePocetka, odbrojavanjeStop, odbrojavanjeSrce;
    private TextView brojSekundi, sadaIgra;
    private ImageView stopSlika, slomljenoSrceSlika;
    private TableLayout igraciTBL;
    private boolean kraj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_igra);
        kraj = false;
        parametri = new Bundle((Bundle) this.getIntent().getExtras());
        random = new Random();
        odbrojavanje = null;
        brojSekundi = (TextView) findViewById(R.id.mojtext);
        sadaIgra = (TextView) findViewById(R.id.igracime);
        stopSlika = (ImageView) findViewById(R.id.slikaStop);
        slomljenoSrceSlika = (ImageView) findViewById(R.id.slikaSlomljenoSrce);
        igraciTBL = (TableLayout) findViewById(R.id.igraciTBL);

        podesiIgru();
        podesiTabelu();
        osluskivaci();
        zapocniIgru();

    }

    private void osluskivaci(){
        ((Button) findViewById(R.id.dadugme)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(odbrojavanje!=null){
                    odbrojavanje.cancel();
                    odbrojavanje = null;
                }

                pritisnutoDA();
            }
        });

        ((Button) findViewById(R.id.nedugme)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(odbrojavanje!=null){
                    odbrojavanje.cancel();
                    odbrojavanje = null;
                }

                pritisnutoNE();
            }
        });

    }

    private void krajIgre(){
        Intent intent = new Intent(Igra.this, KrajIgre.class);
        for (Map.Entry<String, Pair<String,Integer>> entry : igraci.entrySet())
        {
            if(entry.getValue().second > 0){
                parametri.putString("pobednik",entry.getValue().first);
            }
        }
        intent.putExtras(parametri);
        startActivity(intent);
    }

    private void odbrojavaj(int sekunde) {
        brojSekundi.setText("");
        brojSekundi.setVisibility(View.VISIBLE);
    odbrojavanje = new CountDownTimer(sekunde * 1000 + 1000, 1000) {
        public void onTick(long millisUntilFinished) {
            brojSekundi.setText(String.valueOf(millisUntilFinished / 1000));
        }
        public void onFinish() {

            if(odbrojavanje != null){
                odbrojavanje.cancel();
                odbrojavanje = null;
            }

            sakrijDugmad();
            gubitakSrca();
        }
    };
    odbrojavanje.start();
    }

    private void odbrojavajZaPocetak() {
        brojSekundi.setText("");
        brojSekundi.setVisibility(View.VISIBLE);
        if(!kraj){
            odbrojavanjePocetka = new CountDownTimer(6000, 1000) {
                public void onTick(long millisUntilFinished) {
                    brojSekundi.setText(String.valueOf(millisUntilFinished / 1000));
                }
                public void onFinish() {
                    if(odbrojavanjePocetka != null){
                        odbrojavanjePocetka.cancel();
                        odbrojavanjePocetka = null;
                    }
                    ((TextView) findViewById(R.id.napotezuje)).setText("Na potezu je igrač ");
                    potezIgraca();
                }
            };
            odbrojavanjePocetka.start();
        }
    }

    private void odbrojavajPevanje(int sekunde) {
        brojSekundi.setVisibility(View.VISIBLE);
        odbrojavanjePevanja = new CountDownTimer(sekunde * 1000 + 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                brojSekundi.setText("PEVAJ!");
                System.out.println(millisUntilFinished/1000);
            }
            public void onFinish() {
                stopirajPotez();
            }
        };
        odbrojavanjePevanja.start();
    }


    private void odabirSledecegIgraca() {
        ArrayList<Integer> lista = new ArrayList<Integer>();
        int i = 0;
        for(String k : igraci.keySet()){
            if(igraci.get(k).second != 0){
                i++;
                if(!k.equals("Igrac " + trenutniIgrac))
                    lista.add(Integer.valueOf(k.substring(6)));
            }
        }

        if(lista.size() == 0 || i < 2){
            kraj = true;
            krajIgre();
        }
        else{
            Collections.shuffle(lista);
            int item = random.nextInt(lista.size());
            trenutniIgrac = lista.get(item);
        }
    }

    private void potezBeskonacnoText(){
        brojSekundi.setTextSize(60);
        brojSekundi.setText("TEBE CEKAMO!");
        sakrijDugmad();
    }

    private void odbrojavajText(){
        brojSekundi.setTextSize(105);
        prikaziDugmad();
    }

    private void stopirajPotez() {
        odbrojavanjeStop = new CountDownTimer(1500, 1000) {
            public void onTick(long millisUntilFinished) {
                brojSekundi.setVisibility(View.INVISIBLE);
                stopSlika.setVisibility(View.VISIBLE);
            }
            public void onFinish() {
                brojSekundi.setVisibility(View.GONE);
                stopSlika.setVisibility(View.INVISIBLE);

                if(odbrojavanjePevanja != null){
                    odbrojavanjePevanja.cancel();
                    odbrojavanjePevanja = null;
                }

                if(odbrojavanjeStop != null){
                    odbrojavanjeStop.cancel();
                    odbrojavanjeStop = null;
                }

                sledeciIgrac();
            }
        };
        odbrojavanjeStop.start();
    }

    private void slomljenoSrce(){
        brojSekundi.setVisibility(View.GONE);
        brojSekundi.setText("PEVAJ!");
        odbrojavanjeSrce = new CountDownTimer(1500, 1000) {
            public void onTick(long millisUntilFinished) {
                brojSekundi.setVisibility(View.INVISIBLE);
                slomljenoSrceSlika.setVisibility(View.VISIBLE);
            }
            public void onFinish() {
                brojSekundi.setVisibility(View.GONE);
                slomljenoSrceSlika.setVisibility(View.INVISIBLE);

                if(odbrojavanje != null){
                    odbrojavanje.cancel();
                    odbrojavanje = null;
                }

                if(odbrojavanjeSrce != null){
                    odbrojavanjeSrce.cancel();
                    odbrojavanjeSrce = null;
                }

                zapocniIgru();
            }
        };
        odbrojavanjeSrce.start();
    }

    private void prikaziDugmad(){
        ((Button) findViewById(R.id.dadugme)).setVisibility(View.VISIBLE);
        ((Button) findViewById(R.id.nedugme)).setVisibility(View.VISIBLE);
    }

    private void sakrijDugmad(){
        ((Button) findViewById(R.id.dadugme)).setVisibility(View.INVISIBLE);
        ((Button) findViewById(R.id.nedugme)).setVisibility(View.INVISIBLE);
    }

    private void sledeciIgrac(){
        odabirSledecegIgraca();
        sadaIgraIgrac();
        if(vreme != 5){
            int trajanjePoteza = 0;
            switch(vreme){
                case 0: trajanjePoteza = 10; break;
                case 1: trajanjePoteza = 20; break;
                case 2: trajanjePoteza = 30; break;
                case 3: trajanjePoteza = 45; break;
                case 4: trajanjePoteza = 60; break;
            }
            odbrojavajText();
            odbrojavaj(trajanjePoteza);

        } else {
            potezBeskonacnoText();
            odbrojavajText();
        }
    }

    private void pritisnutoDA(){
        sakrijDugmad();
        potezIgraca();
    }

    private void gubitakSrca(){
        String igrac = "Igrac " + trenutniIgrac;
        int zivoti = igraci.get(igrac).second;
        zivoti--;
        igraci.put(igrac, new Pair(igraci.get(igrac).first, zivoti));

        osveziTabelu();
        slomljenoSrce();
    }

    private void pritisnutoNE(){
        sakrijDugmad();
        gubitakSrca();
    }

    private void sadaIgraIgrac(){
        sadaIgra.setText(igraci.get("Igrac "+trenutniIgrac).first);
    }

    private void zapocniIgru(){
        ((TextView) findViewById(R.id.napotezuje)).setText("Novu igru počinje ");
        odabirSledecegIgraca();
        sadaIgraIgrac();
        odbrojavajZaPocetak();
    }

    private void potezIgraca(){
        if(zaustavljanje == 0){
            //int sekundi = random.nextInt(10) + 15;
            int sekundi = random.nextInt(4) + 6;
            odbrojavajPevanje(sekundi);
        } else {
            //TODO: show STOP dugme, igrac pritiska dugme STOP, na click se dugme sakrije i pozove sledeciIgrac();
            //privremeno!
            brojSekundi.setText("PEVAJ!");
            int sekundi = random.nextInt(10) + 15;
            odbrojavajPevanje(sekundi);
        }
    }

    private void podesiIgru(){
        igraci = new HashMap<>(0);

        trenutniIgrac = 0;

        brojIgraca = (int) parametri.getSerializable("brojIgraca");
        zaustavljanje = (int) parametri.getSerializable("zaustavljanje");
        vreme = (int) parametri.getSerializable("vreme");
        int brojZivota = (int) parametri.getSerializable("zivot") + 1;

        for(int i = 1; i <= brojIgraca; i++){
            String igrac = "Igrac "+i;
            try{
                String ime = (String) parametri.getSerializable(igrac);
                if(ime == null || ime == "")
                    break;
                else
                    igraci.put(igrac, new Pair(ime, new Integer(brojZivota)));
            }catch(Exception e){
                break;
            }
        }
    }

    private void podesiTabelu(){

        TextView tv;
        LinearLayout ll;
        TableRow tr;
        String igrac;
        Pair p;
        String ime;
        Integer zivoti;

        for(int i = 1; i <= brojIgraca; i++){
            tv = new TextView(this);
            ll = new LinearLayout(this);
            tr = new TableRow(this);
            tv.setGravity(Gravity.CENTER);
            ll.setGravity(Gravity.CENTER);

            igrac = "Igrac " + i;
            p = igraci.get(igrac);
            ime = (String) p.first;
            zivoti = (Integer) p.second;

            tv.setText(ime);
            popuniZivotima(ll,zivoti,tr);

            tr.addView(tv);
            tr.addView(ll);
            igraciTBL.addView(tr);
        }
    }

    private void popuniZivotima(LinearLayout zivotiL, int zivotiBr, TableRow tblR){

        ImageView imv;
        for(int i = 0; i < zivotiBr; i++){
            imv = new ImageView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(35, 35);
            imv.setLayoutParams(layoutParams);
            imv.setImageResource(R.drawable.srce);
            zivotiL.addView(imv);
        }

        if(zivotiBr == 0){
            tblR.setBackgroundResource(R.color.siva);
        }
    }

    private void osveziTabelu(){

            int childCount = igraciTBL.getChildCount();

            if (childCount > 1) {
                igraciTBL.removeViews(1, childCount - 1);
            }

            podesiTabelu();
    }

}
