public class RytmickyNastroj extends Nastroj{
    private int pocet_zvukov;

    public RytmickyNastroj(String druh, double cena, String zvuk, int pocet, int pocet_zvukov) {
        super(druh, cena, zvuk, pocet);
        this.pocet_zvukov = pocet_zvukov;
    }

    public int getPocet_zvukov() {
        return pocet_zvukov;
    }

    public void setPocet_zvukov(int pocet_zvukov) {
        if((pocet_zvukov) > 0) this.pocet_zvukov = pocet_zvukov;
    }

    @Override
    public String toString() {
        return "RytmickyNastroj{" + super.toString() +
                "pocet_zvukov=" + pocet_zvukov +
                '}';
    }
}
