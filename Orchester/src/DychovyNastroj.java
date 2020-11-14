public class DychovyNastroj extends Nastroj {
    private int pocet_dier;
    private String ladenie;

    public DychovyNastroj(String druh, double cena, String zvuk, int pocet, int pocet_dier, String ladenie) {
        super(druh, cena, zvuk, pocet);
        this.pocet_dier = pocet_dier;
        this.ladenie = ladenie;
    }

    public int getPocet_dier() {
        return pocet_dier;
    }

    public void setPocet_dier(int pocet_dier) {
        if(pocet_dier > 0) this.pocet_dier = pocet_dier;
    }

    public String getLadenie() {
        return ladenie;
    }

    public void setLadenie(String ladenie) {
        if(ladenie.length() > 0 && Main.validateLadenie(1, ladenie)){
            this.ladenie = ladenie;
        }
        else {
            System.out.println("Zle ladenie");
        }
    }

    @Override
    public String toString() {
        return "DychovyNastroj{" + super.toString() +
                "pocet_dier=" + pocet_dier +
                ", ladenie='" + ladenie + '\'' +
                '}';
    }
}
