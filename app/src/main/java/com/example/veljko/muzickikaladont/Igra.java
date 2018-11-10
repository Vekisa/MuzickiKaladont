package com.example.veljko.muzickikaladont;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Igra extends AppCompatActivity {

    private Map<String, Integer> igraci;
    private int brojIgraca, zaustavljanje, vreme, trenutniIgrac;
    private Bundle parametri;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_igra);
        parametri = new Bundle((Bundle) this.getIntent().getExtras());
        random = new Random();

        podesiIgru();
        zapocniIgru();
    }

    private void krajIgre(){
        //TODO: pobednik je trenutniIgrac, ponuditi izbor revansa ili odlazak na glavni meni
    }

    private void odabirSledecegIgraca() {
        int i = 1;
        ArrayList<Integer> lista = new ArrayList<Integer>();

        for(String k : igraci.keySet()){
            if(igraci.get(k) != 0 & igraci.get(k) != trenutniIgrac)
                lista.add(i);

            i++;
        }

        if(lista.size() == 0)
            krajIgre();
        else{
            Collections.shuffle(lista);
            int item = random.nextInt(lista.size());
            trenutniIgrac = lista.get(item);
        }
    }

    private void sledeciIgrac(){
        odabirSledecegIgraca();
        //TODO: Da li ste spremni da zapevate? Prikazuje se DA! i NE! dugme

        if(vreme != 5){
            int trajanjePoteza = 0;
            switch(vreme){
                case 0: trajanjePoteza = 15; break;
                case 1: trajanjePoteza = 30; break;
                case 2: trajanjePoteza = 45; break;
                case 3: trajanjePoteza = 60; break;
                case 4: trajanjePoteza = 90; break;
            }
            //TODO: odbrojavanje trajanjePoteza, prekida se kada korisnik klikne dugme
        } else {
            //TODO: beskonacno trajanje poteza, ceka se pritisak dugmeta
        }
    }

    private void pritisnutoDA(){
        potezIgraca();
    }

    private void pritisnutoNE(){
        String igrac = "Igrac" + trenutniIgrac;
        int zivoti = igraci.get(igrac);
        zivoti--;
        igraci.put(igrac, zivoti);
        //TODO: oduzimanje srca

        if(zivoti == 0){
        //TODO: ako je broj zivota 0 zatamni igraca u listi
        }

        zapocniIgru();
    }

    private void zapocniIgru(){
        odabirSledecegIgraca();
        //TODO: ovde ide prikaz ko zapocinje novu igru i odbrojava 5 sekundi do pocetka igre
        potezIgraca();
    }

    private void potezIgraca(){
        if(zaustavljanje == 0){
            int sekundi = random.nextInt();
            //TODO: automatsko odbrojavanje sekundi poteza igraca
            sledeciIgrac();

        } else {
            //TODO: show STOP dugme, igrac pritiska dugme STOP, na click se dugme sakrije i pozove sledeciIgrac();
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
            String igrac = "Igrac"+i;
            try{
                String ime = (String) parametri.getSerializable(igrac);
                if(ime == null || ime == "")
                    break;
                else
                    igraci.put(igrac, new Integer(brojZivota));
            }catch(Exception e){
                break;
            }
        }
    }
}
