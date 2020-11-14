public class Vyr implements VieZmiznut{
    private String druh;
    private int vek;
    private double energia;

    public Vyr(String druh, int vek, double energia){
        this.druh = druh;
        this.vek = vek;
        this.energia = energia;
    }

    public String getDruh() {
        return druh;
    }

    public void setDruh(String druh) {
        this.druh = druh;
    }

    public int getVek() {
        return vek;
    }

    public void setVek(int vek) {
        this.vek = vek;
    }

    public double getEnergia() {
        return energia;
    }

    public void setEnergia(double energia) {
        this.energia = energia;
    }


    @Override
    public void zmizni() {
        this.setEnergia(200);
    }

    @Override
    public String coRobis() {
        if (this.getEnergia()> 20){
            return getDruh() + ": letim";
        }
        else{
            return getDruh() + ": sedim na konari";
        }
    }
}
