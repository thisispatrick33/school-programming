public class Klient {
    private int user_id;
    private String meno;
    private String priezvisko;
    private String adresa;
    private String COP;

    /* Konstruktor */

    Klient(int user_id, String meno, String priezvisko, String adresa, String COP){
        this.user_id = user_id;
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.adresa = adresa;
        this.COP = COP;
    }

    /* Getter */

    public int getUser_id() {
        return user_id;
    }

    public String getMeno() {
        return meno;
    }

    public String getPriezvisko() {
        return priezvisko;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getCOP() {
        return COP;
    }

    /* Setter */

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public void setPriezvisko(String priezvisko) {
        this.priezvisko = priezvisko;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setCOP(String COP) {
        this.COP = COP;
    }

}
