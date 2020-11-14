import java.io.BufferedReader;
import java.io.FileReader;

public class Citanie {
    public String menoSuboru;
    private int hodnota;
    private boolean precitane = false;
    private boolean koniecsuboru = false;
    private BufferedReader in;


    Citanie(String cesta){
        System.out.println(cesta);
        menoSuboru = cesta;
        try {
            in = new BufferedReader(new FileReader(menoSuboru));
        } catch (Exception e) {
            System.out.println(e);
        }
    }



    synchronized public void nacitaj(){
        while(precitane){
            try {
                wait();
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        String riadok;
        try {
            if((riadok = in.readLine())!=null){
                hodnota = Integer.parseInt(riadok);
            }
            else {
                koniecsuboru = true;
                Thread.currentThread().interrupt();
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        precitane = true;
        notifyAll();
    }

    synchronized public int predaj(){
        while (!precitane){
            try {
                wait();
            }catch (Exception e){
                System.out.println(e);
            }
        }
        precitane = false;
        if(!koniecsuboru){
            System.out.println("predane " + hodnota);
            notifyAll();
            return hodnota;
        }
        else {
            Thread.currentThread().interrupt();
            return 0;
        }
    }

    protected void finalize(){
        try {
            in.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
