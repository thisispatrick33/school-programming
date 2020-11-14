public class SlacikovyNastroj extends StrunovyNastroj {
    private String Sekcia;

    public SlacikovyNastroj(String druh, double cena, String zvuk, int pocet, int pocetStrun, String ladenie, String sekcia) {
        super(druh, cena, zvuk, pocet, pocetStrun, ladenie);
        Sekcia = sekcia;
    }

    public String getSekcia() {
        return Sekcia;
    }

    public void setSekcia(String sekcia) {
        if(sekcia.length() > 0) Sekcia = sekcia;
    }

    @Override
    public String toString() {
        return "SlacikovyNastroj{" + super.toString() +
                "Sekcia='" + Sekcia + '\'' +
                '}';
    }
}
