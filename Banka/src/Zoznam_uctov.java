import javax.sound.midi.Soundbank;
import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Zoznam_uctov {
    private Map<Integer, Ucet> zoznam_uctov;
    private int last_used_id;

    Zoznam_uctov() throws IOException {
        this.zoznam_uctov = new TreeMap<>();
        BufferedReader in = new BufferedReader(new FileReader("ucty"));
        String line;
        this.last_used_id = Integer.parseInt(in.readLine());
        while ((line = in.readLine())!=null){
            String udaje [] = line.split("-");
            Ucet novy_ucet = new Ucet(Integer.parseInt(udaje[0]), Integer.parseInt(udaje[1]), Double.parseDouble(udaje[2]));
            zoznam_uctov.put(novy_ucet.getUcet_id(), novy_ucet);
        }
    }

    public void vypisZoznamu(){
        for (int id : zoznam_uctov.keySet()) {
            System.out.println("ID uctu: "+zoznam_uctov.get(id).getUcet_id()+" , ID klienta uctu: "+zoznam_uctov.get(id).getUser_id()+" , Zostatok: "+zoznam_uctov.get(id).getBalance());
        }
    }

    public void vypisUctu(int ID){
        boolean nachadza_sa = false;
        for (int id : zoznam_uctov.keySet()) {
            if(zoznam_uctov.get(id).getUser_id()==ID) {
                nachadza_sa = true;
                System.out.println("ID uctu: "+zoznam_uctov.get(id).getUcet_id()+" , ID klienta uctu: "+zoznam_uctov.get(id).getUser_id()+" , Zostatok: "+zoznam_uctov.get(id).getBalance());
            }
        }
        if(!nachadza_sa){
            System.out.println("Klient s tymto ID este nema ucet");
        }

    }

    public void pridajUcet(Ucet ucet){
        zoznam_uctov.put(ucet.getUcet_id(),ucet);
    }

    public void zmenaZostatku(int id, double suma){

        zoznam_uctov.get(id).setBalance(suma);
    }


    public void odstranUctu(int ID){
        Scanner sc = new Scanner(System.in);
        boolean nachadza_sa = false;
        for (int id : zoznam_uctov.keySet()) {
            if(zoznam_uctov.get(id).getUser_id()==ID) {
                System.out.println("ID uctu: "+zoznam_uctov.get(id).getUcet_id()+" , ID klienta uctu: "+zoznam_uctov.get(id).getUser_id()+" , Zostatok: "+zoznam_uctov.get(id).getBalance());
                nachadza_sa = true;
            }
        }
        if(nachadza_sa) {
            System.out.println("Zadajte ID uctu, ktory chcete odstranit");
            int id_ucet = sc.nextInt();
            nachadza_sa = false;
            for(int id : zoznam_uctov.keySet()){
                if(id_ucet==id){
                    nachadza_sa = true;
                    break;
                }
            }
            if(nachadza_sa){
                if(zoznam_uctov.get(id_ucet).getUser_id()==ID){
                    zoznam_uctov.remove(id_ucet);
                }
                else {
                    System.out.println("Uzivatel nema ucet s takym ID");
                }
            }else {
                System.out.println("Ucet s takym ID neexistuje");
            }
        }
        if(!nachadza_sa){
            System.out.println("Klient s tymto ID este nema ucet");
            System.out.println();
        }

    }

    public int getLast_used_id() {
        return last_used_id;
    }

    public void setLast_used_id(int last_used_id) {
        this.last_used_id = last_used_id;
    }

    public Map<Integer, Ucet> getZoznam_uctov() {
        return zoznam_uctov;
    }

    public void ulozenie() throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter("ucty"));
        System.out.println(last_used_id);
        out.write(last_used_id+"");
        out.newLine();
        for (int id : zoznam_uctov.keySet()){
            out.write(zoznam_uctov.get(id).getUcet_id()+"."+zoznam_uctov.get(id).getUser_id()+"."+zoznam_uctov.get(id).getBalance());
            out.newLine();
        }
        out.close();
    }
}
