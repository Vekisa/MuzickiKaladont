package com.example.veljko.muzickikaladont.Model;

public class Podesavanja {
    private int zaustavljanje;
    private int vreme;
    private int zivot;
    private static String PUTANJA = "podesavanja.txt";

    public Podesavanja() {
        this.zaustavljanje = 0;
        this.vreme = 5;
        this.zivot = 0;
    }

    public Podesavanja(int zaustavljanje, int vreme, int zivot) {
        this.zaustavljanje = zaustavljanje;
        this.vreme = vreme;
        this.zivot = zivot;
    }

    public int getZaustavljanje() {
        return zaustavljanje;
    }

    public void setZaustavljanje(int zaustavljanje) {
        this.zaustavljanje = zaustavljanje;
    }

    public int getVreme() {
        return vreme;
    }

    public void setVreme(int vreme) {
        this.vreme = vreme;
    }

    public int getZivot() {
        return zivot;
    }

    public void setZivot(int zivot) {
        this.zivot = zivot;
    }
}
