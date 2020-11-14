public class Vir implements VieZmiznut {
    private String meno;
    private int rychlostVetra;

    public Vir(String meno, int rychlost){
        this.meno = meno;
        this.rychlostVetra = rychlost;
    }



    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public int getRychlostVetra() {
        return rychlostVetra;
    }

    public void setRychlostVetra(int rychlostVetra) {
        this.rychlostVetra = rychlostVetra;
    }

    @Override
    public void zmizni() {
        this.setRychlostVetra(0);
    }

    @Override
    public String coRobis() {
        if (this.getRychlostVetra() > 5){
            return getMeno() + ": fucim";
        }
        else{
            return getMeno() + ": je klud";
        }
    }
}
