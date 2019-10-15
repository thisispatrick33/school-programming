import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.util.Scanner;

public class PSVM {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Zoznam_klientov zoznam_klientov = new Zoznam_klientov();
        Zoznam_uctov zoznam_uctov = new Zoznam_uctov();

        int a = 0;
        while(true){
            System.out.println("Vyberte akciu:");
            System.out.println("1 - vypis vsetkych klientov");
            System.out.println("2 - vypis udajov 1 klienta");
            System.out.println("3 - pridanie klienta");
            System.out.println("4 - odstranenie klienta");
            System.out.println("5 - vypisat vsetky ucty");
            System.out.println("6 - vypisat ucet 1 klienta");
            System.out.println("7 - vytvorit novy ucet");
            System.out.println("8 - odstranit ucet");
            System.out.println("9 - zmena zostatku");
            a = sc.nextInt();
            if(a==1){
                System.out.println();
                zoznam_klientov.vypisZoznamu();
                System.out.println();
            }
            if(a==2){
                System.out.println("Zadajte ID klienta ktoreho chcete vypisat");

                zoznam_klientov.vypisKlienta(sc.nextInt());
                System.out.println();
            }
            if(a==3){
                int id = zoznam_klientov.getLast_used_id()+1;
                zoznam_klientov.setLast_used_id(id);
                sc.nextLine();
                System.out.println("Zadaj meno klienta.");
                String meno = sc.nextLine();
                System.out.println("Zadaj priezvisko klienta.");
                String priezvisko = sc.nextLine();
                System.out.println("Zadaj adresu klienta.");
                String adresa = sc.nextLine();
                System.out.println("Zadaj COP klienta.");
                String COP = sc.nextLine();
                Klient novy = new Klient(id, meno, priezvisko, adresa, COP);
                zoznam_klientov.pridajKlienta(novy);
            }
            if(a==4){
                System.out.println("Zadajte ID klienta ktoreho chcete odstranit");
                zoznam_klientov.odstranKlienta(sc.nextInt());
            }
            if(a==5){
                System.out.println();
                zoznam_uctov.vypisZoznamu();
                System.out.println();
            }
            if(a==6){
                System.out.println("Zadajte ID klienta ktoreho ucty chcete vypisat");
                zoznam_uctov.vypisUctu(sc.nextInt());
                System.out.println();
            }
            if(a==7){
                int id = zoznam_uctov.getLast_used_id()+1;
                zoznam_uctov.setLast_used_id(id);
                sc.nextLine();
                System.out.println("Zadaj ID klienta, ktoremu chcete vytvorit ucet.");
                int user_id = sc.nextInt();
                if(zoznam_klientov.nachadzaSa(user_id)){
                    Ucet novy_ucet = new Ucet(id, user_id, 0);
                    zoznam_uctov.pridajUcet(novy_ucet);
                    System.out.println("Ucet bol uspesne vytvoreny");
                    System.out.println();
                }
                else {
                    System.out.println("Klient s takymto ID neexistuje");
                    System.out.println();
                }
            }
            if(a==8){
                System.out.println("Zadajte ID klienta ktoreho ucet chcete odstranit");
                zoznam_uctov.odstranUctu(sc.nextInt());
            }

        }


    }
}
