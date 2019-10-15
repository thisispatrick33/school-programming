import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Zoznam_klientov {
    private Map<Integer, Klient> zoznam_klientov;
    private int last_used_id;

            /* Konstruktor */

    Zoznam_klientov() throws IOException {
        this.zoznam_klientov = new TreeMap<>();
        BufferedReader in = new BufferedReader(new FileReader("klienti"));
        String line;
        this.last_used_id = Integer.parseInt(in.readLine());
        while ((line = in.readLine())!=null){
            String udaje [] = line.split(" ");
            Klient novy_klient = new Klient(Integer.parseInt(udaje[0]), udaje[1], udaje[2], udaje[3], udaje[4]);
            zoznam_klientov.put(novy_klient.getUser_id(), novy_klient);
        }
    }

    /* Vypis zoznamu */

    public void vypisZoznamu(){
        for (int id : zoznam_klientov.keySet()) {
            System.out.println(zoznam_klientov.get(id).getUser_id()+" "+zoznam_klientov.get(id).getMeno()+" "+zoznam_klientov.get(id).getPriezvisko()+" "+zoznam_klientov.get(id).getAdresa()+" "+zoznam_klientov.get(id).getCOP());
        }
    }
    public void vypisKlienta(int id) {
        boolean nachadza_sa = false;
        for (int ID : zoznam_klientov.keySet()) {
            if (ID == id) {
                nachadza_sa = true;
            }
        }
        if (nachadza_sa) {
            System.out.println(zoznam_klientov.get(id).getUser_id() + " " + zoznam_klientov.get(id).getMeno() + " " + zoznam_klientov.get(id).getPriezvisko() + " " + zoznam_klientov.get(id).getAdresa() + " " + zoznam_klientov.get(id).getCOP());
        }
        else {
            System.out.println("Klient s takymto ID neexistuje");
        }
    }

    /* Pridanie klienta */

    public void pridajKlienta(Klient klient){
        zoznam_klientov.put(klient.getUser_id(),klient);
    }

    /* Odstranenie klienta */

    public void odstranKlienta(int id){
        boolean nachadza_sa = false;
        for (int ID : zoznam_klientov.keySet()) {
            if (ID == id) {
                nachadza_sa = true;
            }
        }
        if (nachadza_sa) {
            zoznam_klientov.remove(id);
        }
        else {
            System.out.println("Klient s takymto ID neexistuje");
        }
    }

    public int getLast_used_id() {
        return last_used_id;
    }

    public void setLast_used_id(int last_used_id) {
        this.last_used_id = last_used_id;
    }

    public boolean nachadzaSa(int id){
        boolean nachadza_sa = false;
        for (int ID : zoznam_klientov.keySet()) {
            if (ID == id) {
                nachadza_sa = true;
            }
        }
        return nachadza_sa;
    }
}
