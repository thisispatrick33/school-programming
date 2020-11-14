import java.util.Scanner;

public class Tam_a_spat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        myStack zasobnik = null;
        try {
            System.out.println("Zadajte pocet krokov, ktore sa budu vykonavat");
            zasobnik = new myStack(sc.nextInt());
        }
        catch (Exception e){
            System.out.println("Nezadali ste kladnu celociselnu hnodtu");
            System.exit(0);
        }
        System.out.println("Realny stav: "+zasobnik.toString());
        sc.nextLine();
        System.out.println("Zadajte prikaz");
        String line;
        while(true){
            line = sc.nextLine();
            if( line.startsWith("pridaj ")){
                try{
                    zasobnik.push(new myType(line.substring(line.indexOf("pridaj ")+7)));
                }
                catch (IndexOutOfBoundsException e){
                    System.out.println("Kapacita je naplnena");
                }
            }
            else if(line.equals("vyber")){
                try {
                    System.out.println("Ako dalsi krok sprav "+zasobnik.pop());
                }
                catch (Exception e){
                    System.out.println("Zasobnik je prazdny, ziadne dalsie kroky");
                }
            }
            else if( line.equals("koniec")){
                System.out.println("Ukoncenie....");
                break;
            }
            else {
                System.out.println("Neznamy prikaz");
            }
            try {
                System.out.println("Poradie krokov je : "+zasobnik.see());
            }
            catch (Exception e){
                System.out.println("Zasobnik je prazdny nemam co zobrazit");
            }
            System.out.println("Realny stav: "+zasobnik.toString());
            System.out.println();
            System.out.println("Zadajte prikaz");
        }

    }
}
