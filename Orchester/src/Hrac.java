public class Hrac {
    private String meno;
    private String priezvisko;
    private String nastroj;
    private double sadzba;

    public Hrac(String meno, String priezvisko, String nastroj, double sadzba){
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.nastroj = nastroj;
        this.sadzba = sadzba;
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        if(meno.length() > 0) this.meno = meno;
    }

    public String getPriezvisko() {
        return priezvisko;
    }

    public void setPriezvisko(String priezvisko) {
        if(priezvisko.length() > 0) this.priezvisko = priezvisko;
    }

    public String getNastroj() {
        return nastroj;
    }

    public void setNastroj(String nastroj) {
        if(nastroj.length() > 0) this.nastroj = nastroj;
    }

    public double getSadzba() {
        return sadzba;
    }

    public void setSadzba(double sadzba) {
        if(sadzba > 0) this.sadzba = sadzba;
    }

    @Override
    public String toString() {
        return "Hrac{" +
                "meno='" + meno + '\'' +
                ", priezvisko='" + priezvisko + '\'' +
                ", nastroj='" + nastroj + '\'' +
                ", sadzba=" + sadzba +
                '}';
    }
}
