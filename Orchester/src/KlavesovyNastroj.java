public class KlavesovyNastroj extends Nastroj {
    private int pocetKlaves;

    public KlavesovyNastroj(String druh, double cena, String zvuk, int pocet, int pocetKlaves) {
        super(druh, cena, zvuk, pocet);
        this.pocetKlaves = pocetKlaves;
    }

    public int getPocetKlaves() {
        return pocetKlaves;
    }

    public void setPocetKlaves(int pocetKlaves) {
        if(pocetKlaves > 0) this.pocetKlaves = pocetKlaves;
    }

    @Override
    public String toString() {
        return "KlavesovyNastroj{" + super.toString() +
                "pocetKlaves=" + pocetKlaves +
                '}';
    }
}
