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
            System.out.println("10 - ulozenie dat a ukoncenie programu");
            a = sc.nextInt();
            if(a==1){
                System.out.println();
                zoznam_klientov.vypisZoznamu();
                System.out.println();
            }
            else if(a==2){
                System.out.println("Zadajte ID klienta ktoreho chcete vypisat");

                zoznam_klientov.vypisKlienta(sc.nextInt());
                System.out.println();
            }
            else if(a==3){
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
            else if(a==4){
                System.out.println("Zadajte ID klienta ktoreho chcete odstranit");
                int e = zoznam_klientov.odstranKlienta(sc.nextInt());
                zoznam_uctov.handleKlientDelete(e);
            }
            else if(a==5){
                System.out.println();
                zoznam_uctov.vypisZoznamu();
                System.out.println();
            }
            else if(a==6){
                System.out.println("Zadajte ID klienta ktoreho ucty chcete vypisat");
                zoznam_uctov.vypisUctu(sc.nextInt());
                System.out.println();
            }
            else if(a==7){
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
            else if(a==8){
                System.out.println("Zadajte ID klienta ktoreho ucet chcete odstranit");
                zoznam_uctov.odstranUctu(sc.nextInt());
            }
            else if(a==9){
                System.out.println("Zadajte 1 pre vyber z uctu / 2 pre vklad na ucet");
                int b = 0;
                if ((b=sc.nextInt())==1){
                    System.out.println("Zadajte sumu, ktoru chcete vybrat");
                    double vyber = sc.nextDouble();
                    System.out.println("Zadajte ID uctu na, z ktoreho chcete peniaze vybrat");
                    int ID = sc.nextInt();
                    boolean nachadza_sa = false;
                    for (int i : zoznam_uctov.getZoznam_uctov().keySet()){
                        if(ID==i){
                            nachadza_sa = true;
                            break;
                        }
                    }
                    if(nachadza_sa){
                        if(zoznam_uctov.getZoznam_uctov().get(ID).getBalance()<vyber){
                            System.out.println("Vas zostatok na ucte je:"+zoznam_uctov.getZoznam_uctov().get(ID).getBalance());
                            System.out.println("Nemozete vybrat: "+vyber);
                        }
                        else {
                            zoznam_uctov.zmenaZostatku(ID, (zoznam_uctov.getZoznam_uctov().get(ID).getBalance()-vyber));
                            System.out.println("Vyber prebehol uspesne!");
                        }
                    }
                }
                else if(b==2){
                    System.out.println("Zadajte sumu, ktoru chcete vlozit");
                    double vklad = sc.nextDouble();
                    System.out.println("Zadajte ID uctu na, ktory chcete peniaze vlozit");
                    int ID = sc.nextInt();
                    boolean nachadza_sa = false;
                    for (int i : zoznam_uctov.getZoznam_uctov().keySet()){
                        if(ID==i){
                            nachadza_sa = true;
                            break;
                        }
                    }
                    if(nachadza_sa){
                        zoznam_uctov.zmenaZostatku(ID, (zoznam_uctov.getZoznam_uctov().get(ID).getBalance()+vklad));
                        System.out.println("Peniaze sa uspsne vlozili");
                    }
                    else {
                        System.out.println("Ucet s takymto ID neexistuje.");
                    }

                }else {
                    System.out.println("Nezadali ste spravnu volbu.");
                }
            }else if(a==10){
                zoznam_klientov.ulozenie();
                zoznam_uctov.ulozenie();
                System.out.println("Zmeny boli uspesne ulozene");
                System.out.println("Shuting down.....");
                System.exit(0);
            }
            else {
                System.out.println("Nezadali ste spravnu moznost, skuste to znova");
            }

        }


    }
}
