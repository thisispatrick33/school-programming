public class Producent extends Thread{
    private Citanie c;

    Producent(Citanie c){this.c = c;}

    public void run(){
        while (!interrupted()){
            c.nacitaj();
        }
        System.out.println("KOniec nacitania");
    }
}
