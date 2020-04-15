public class BankovyUcet {
    private double sumaNaUcte;

    public BankovyUcet(int suma){
        sumaNaUcte = suma;
    }
    public void prijem(double suma){
        sumaNaUcte += suma;
    }
    public void vydaj(double suma){
        if (suma < sumaNaUcte){
            sumaNaUcte -= suma;
            System.out.println("Transakcia prebehla v poriadku.");
        }
        else{
            System.out.println("Nedostatok prostriedkov");
        }
    }

    public double stavNaUcte(){
        return sumaNaUcte;
    }
}
