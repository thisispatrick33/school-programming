public class Lod implements VieZniet{
    private int dlzka;

    public Lod(int dlzka) {
        this.dlzka = dlzka;
    }

    public int getDlzka() {
        return dlzka;
    }

    public void setDlzka(int dlzka) {
        this.dlzka = dlzka;
    }

    @Override
    public String toString() {
        return "Lod{" +
                "dlzka=" + dlzka +
                '}';
    }

    @Override
    public String getZvuk() {
        return "tuuuut";
    }

    @Override
    public int getPocet() {
        return 0;
    }

    @Override
    public double getCena() {
        return 0;
    }

    @Override
    public String getDruh() {
        return "Lod";
    }
}
