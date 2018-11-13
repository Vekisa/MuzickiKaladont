public class Igrac {

    private String ime;
    private int brZivota;

    public Igrac(String ime, int brZivota){
        this.ime = ime;
        this.brZivota = brZivota;
    }

    public void setIme(String ime){
        this.ime = ime;
    }

    public String getIme(){
        return this.ime;
    }

    public void setBrZivota(int brZivota){
        this.brZivota = brZivota;
    }

    public int getBrZivota(){
        return this.brZivota;
    }




}
