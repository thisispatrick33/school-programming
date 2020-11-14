import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {
        Vyr bubo = new Vyr("sklany", 5, 10);
        Vir helen = new Vir("vietor", 50);
        ArrayList <VieZmiznut> vejry = new ArrayList<>();
        vejry.add(bubo);
        vejry.add(helen);

        for (VieZmiznut vejr : vejry){
            System.out.println(vejr.coRobis());
        }
        System.out.println("**********************");
        for (VieZmiznut vejr : vejry){
            vejr.zmizni();
        }
        System.out.println("**********************");
        for (VieZmiznut vejr : vejry){
            System.out.println(vejr.coRobis());
        }
        System.out.println("**********************");

    }
}
