public class Nastroj implements VieZniet{
    private String druh;
    private double cena;
    private String zvuk;
    private int pocet;

    public Nastroj(String druh, double cena, String zvuk, int pocet) {
        this.druh = druh;
        this.cena = cena;
        this.zvuk = zvuk;
        this.pocet = pocet;
    }

    public String getDruh() {
        return druh;
    }

    public void setDruh(String druh) {
        if(druh.length() > 0) this.druh = druh;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        if(cena > 0) this.cena = cena;
    }

    public String getZvuk() {
        return zvuk;
    }

    public void setZvuk(String zvuk) {
        if(zvuk.length() > 0) this.zvuk = zvuk;
    }

    public int getPocet() {
        return pocet;
    }

    public void setPocet(int pocet) {
        if(pocet > 0) this.pocet = pocet;
    }

    @Override
    public String toString() {
        return "Nastroj{" +
                "druh='" + druh + '\'' +
                ", cena=" + cena +
                ", zvuk='" + zvuk + '\'' +
                ", pocet=" + pocet +
                '}';
    }


}
