package sample;

public class Mena {

    private double kurz;
    private String nazov;
    private String skratka;
    private String krajina;

    Mena(double kurz, String nazov, String skratka, String krajina){
        this.kurz = kurz;
        this.nazov = nazov;
        this.skratka = skratka;
        this.krajina = krajina;
    }

    public double getKurz() {
        return kurz;
    }

    public void setKurz(double kurz) {
        this.kurz = kurz;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public String getSkratka() {
        return skratka;
    }

    public void setSkratka(String skratka) {
        this.skratka = skratka;
    }

    public String getKrajina() {
        return krajina;
    }

    public void setKrajina(String krajina) {
        this.krajina = krajina;
    }

    public double naEuro(double mnozstvo){
        return (mnozstvo/kurz);
    }

    public double zEura(double mnozstvo){
        return (mnozstvo*kurz);
    }
}
