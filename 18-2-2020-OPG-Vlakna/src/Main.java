import java.util.ArrayList;

public class Main {

    static int zvacsiCislo = 0;

    public static void main(String[] args) throws InterruptedException {
        MojeRealShitVlakno mrsv1 = new MojeRealShitVlakno();
        MojeRealShitVlakno mrsv2 = new MojeRealShitVlakno();
        mrsv1.start();
        mrsv2.start();
        Thread.sleep(500);
        System.out.println("cislo"+zvacsiCislo);
    }
}

class MojeRealShitVlakno extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++){ Main.zvacsiCislo++ ; }

        ArrayList<Integer> cisla = new ArrayList<>();

        for (int i = 0; i < 50; i++){ cisla.add(i); }

        for (Integer x : cisla){ System.out.println(x); }
    }

}