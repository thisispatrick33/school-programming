import java.util.Scanner;

public class Cakaren {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        myQueue front = null;
        try {
            System.out.println("Zadajte kapacitu cakarne");
            front = new myQueue(sc.nextInt());
        }
        catch (Exception e){
            System.out.println("Nezadali ste kladnu celociselnu hnodtu");
            System.exit(0);
        }
        System.out.println("Realny stav: "+front.toString());
        sc.nextLine();
        System.out.println("Zadajte prikaz");
        String line;
        while(true){
            line = sc.nextLine();
            if( line.startsWith("prichod ")){
                try{
                    front.append(new myType(line.substring(line.indexOf("prichod ")+8)));
                }
                catch (IndexOutOfBoundsException e){
                    System.out.println("Kapacita je naplnena");
                }
            }
            else if( line.equals("dalsi")){
                try {
                    System.out.println("Do ambulancie vchadza "+front.get());
                }
                catch (Exception e){
                    System.out.println("Front je prazdny nema kto vstupit");
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
                System.out.println("Poradie je : "+front.see());
            }
            catch (Exception e){
                System.out.println("Front je prazdny nemam co zobrazit");
            }
            System.out.println("Realny stav: "+front.toString());
            System.out.println();
            System.out.println("Zadajte prikaz");
        }
    }
}
