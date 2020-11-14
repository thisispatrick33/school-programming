public class StrunovyNastroj extends Nastroj {
    private int pocetStrun;
    private String ladenie;

    public StrunovyNastroj(String druh, double cena, String zvuk, int pocet, int pocetStrun, String ladenie) {
        super(druh, cena, zvuk, pocet);
        this.pocetStrun = pocetStrun;
        this.ladenie = ladenie;
    }

    public int getPocetStrun() {
        return pocetStrun;
    }

    public void setPocetStrun(int pocetStrun) {
        if(pocetStrun > 0) this.pocetStrun = pocetStrun;
    }

    public String getLadenie() {
        return ladenie;
    }

    public void setLadenie(String ladenie) {
        if(ladenie.length() > 0 && Main.validateLadenie(this.pocetStrun, ladenie)){
            this.ladenie = ladenie;
        }
        else {
            System.out.println("Zle ladenie");
        }
    }

    @Override
    public String toString() {
        return "StrunovyNastroj{" + super.toString() +
                "pocetStrun=" + pocetStrun +
                ", ladenie='" + ladenie + '\'' +
                '}';
    }
}
