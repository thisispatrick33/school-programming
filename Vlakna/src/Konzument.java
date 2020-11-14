public class Konzument extends Thread {
    private Citanie c;
    private int suma = 0;

    Konzument(Citanie c){ this.c = c;}

    public void run(){
        int cislo;
        while (true){
            cislo = c.predaj();
            if(!interrupted()){
                suma += cislo;
            }
            else break;
        }
        System.out.println("Vysledna suma "+suma);
    }
}
