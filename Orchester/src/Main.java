import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static boolean validate(String [] data){
        boolean valid = false;
        try {
            switch (data[0]){
                case "u":
                    valid = (data.length == 5 && data[1].length() > 0 && data[2].length() > 0 && data[3].length() > 0 && Double.parseDouble(data[4]) > 0);
                    break;
                case "d":
                    valid = (data.length == 7 && data[1].length() > 0 && Double.parseDouble(data[2]) > 0 && data[3].length() > 0 && Integer.parseInt(data[4]) > 0 && Integer.parseInt(data[5]) > 0 && validateLadenie(1, data[6]));
                    break;
                case "s":
                    valid = (data.length == 7 && data[1].length() > 0 && Double.parseDouble(data[2]) > 0 && data[3].length() > 0 && Integer.parseInt(data[4]) > 0 && Integer.parseInt(data[5]) > 0 && validateLadenie(Integer.parseInt(data[5]), data[6]));
                    break;
                case "S":
                    valid = (data.length == 8 && data[1].length() > 0 && Double.parseDouble(data[2]) > 0 && data[3].length() > 0 && Integer.parseInt(data[4]) > 0 && Integer.parseInt(data[5]) > 0 && validateLadenie(Integer.parseInt(data[5]), data[6]) && data[7].length() > 0);
                    break;
                case "k":
                case "r":
                    valid = (data.length == 6 && data[1].length() > 0 && Double.parseDouble(data[2]) > 0 && data[3].length() > 0 && Integer.parseInt(data[4]) > 0 && Integer.parseInt(data[5]) > 0);
                    break;
                default:
                    System.out.println("Zly vstup");
            }
        }catch (NumberFormatException e){
            valid = false;
        }
        return valid;
    }

    public static boolean validateLadenie(int pocetPovolenych, String ladenie){
        boolean valid = false;
        String allowedChars = "CDEFGAH#b";
        String lengthcomparison = "CDEFGAH";
        boolean onlyAllowedChars = false;
        for(int i = 0; i < ladenie.length(); i++){
            if (allowedChars.indexOf(ladenie.charAt(i)) != -1){
                onlyAllowedChars = true;
            }
            else {
                onlyAllowedChars = false;
                break;
            }
        }
        if(onlyAllowedChars){
            int pocet = 0;
            for(int i = 0; i < ladenie.length(); i++){
                if(lengthcomparison.indexOf(ladenie.charAt(i)) != -1){
                    pocet++;
                }
            }
            if(pocet == pocetPovolenych){
                for(int i = 0; i < ladenie.length(); i++){
                    boolean tmp = (ladenie.charAt(i) == '#' || ladenie.charAt(i) == 'b');
                    if( (i == 0 && tmp) || (i != 0 && i != ladenie.length()-1 && tmp && (ladenie.charAt(i+1) == '#' || ladenie.charAt(i+1) == 'b'))){
                        valid = false;
                        break;
                    }
                    else {
                        valid = true;

                    }

                }
            }
        }
        return valid;
    }

    private static Map<String, ArrayList<Hrac>> priradenie(ArrayList <Hrac> hraci, ArrayList<VieZniet> nastroje){
        Map<String, ArrayList<Hrac>> priradenie = new HashMap<>();
        for ( VieZniet n : nastroje) {
            int zostatok = n.getPocet();
            for (Hrac h : hraci) {
                if(n.getDruh().equals("husle")){
                    SlacikovyNastroj sln = (SlacikovyNastroj)n;
                    if(h.getNastroj().equals(sln.getSekcia()) && zostatok > 0){
                        ArrayList<Hrac> itemsList = priradenie.get(sln.getSekcia()) == null ? new ArrayList<>() : priradenie.get(sln.getSekcia());
                        itemsList.add(h);
                        priradenie.put(((SlacikovyNastroj) n).getSekcia(), itemsList);
                        zostatok -= 1;
                    }
                }
                else {
                    if(h.getNastroj().equals(n.getDruh()) && zostatok > 0){
                        ArrayList<Hrac> itemsList = priradenie.get(n.getDruh()) == null ? new ArrayList<>() : priradenie.get(n.getDruh());
                        itemsList.add(h);
                        priradenie.put(n.getDruh(), itemsList);
                        zostatok -= 1;
                    }
                }
            }
        }
        return priradenie;
    }

    public static void cenaOrchestra(ArrayList <VieZniet> nastroje){
        Double cena = 0.0;
        for ( VieZniet n : nastroje) {
            cena += n.getPocet()*n.getCena();
        }

        System.out.println("Cena orchestra je "+cena+" eur\n");
    }

    private static void obsadenie(Map<String, ArrayList<Hrac>> orchester){
        System.out.println("Obsadenie orchestra je: ");
        for ( String druh : orchester.keySet()) {
            ArrayList<Hrac> priradeni = orchester.get(druh);
            for ( Hrac h  : priradeni) {
                System.out.println(h.getMeno()+" "+h.getPriezvisko()+" - hra na nastroji: "+druh);
            }
        }
        System.out.println();
    }

    private static void cenaVystupenia(Map<String, ArrayList<Hrac>> orchester){
        Double cena = 0.0;

        for ( String druh : orchester.keySet() ) {
            ArrayList<Hrac> priradeni = orchester.get(druh);
            for ( Hrac h  : priradeni) {
                cena += h.getSadzba();
            }
        }

        System.out.println("Cena za vystupenie je "+cena+" eur\n");
    }

    private static void zahrajSkladbu(Map<String, ArrayList<Hrac>> orchester, ArrayList <VieZniet> nastroje){
        String skladba = "";
        for ( String druh : orchester.keySet() ) {
            for( VieZniet n : nastroje ){
                if(druh.equals(n.getDruh()) || (n.getDruh().equals("husle") && druh.equals(((SlacikovyNastroj) n).getSekcia()))){
                    for(int i = 0; i < orchester.get(druh).size(); i++){
                        skladba += n.getZvuk()+" ";
                    }
                }
            }
        }
        System.out.println("Zahrame skladbu: \n"+skladba+"\n");
    }

    public static void main(String[] args) throws IOException {
        ArrayList <VieZniet> nastroje = new ArrayList<>();
        ArrayList <Hrac> hraci = new ArrayList<>();

        BufferedReader in = new BufferedReader(new FileReader("src/orchester.txt"));

        nastroje.add(new Lod(150));

        String line;
        while ((line = in.readLine()) != null){
            String [] data = line.split(",");
            switch (data[0]){
                case "u":
                    if(validate(data)){
                        hraci.add(new Hrac(data[1], data[2], data[3], Double.parseDouble(data[4])));
                    }else {
                        System.out.println("Zly vstup");
                    }
                    break;
                case "s":
                    if(validate(data)){
                        nastroje.add(new StrunovyNastroj(data[1], Double.parseDouble(data[2]), data[3], Integer.parseInt(data[4]), Integer.parseInt(data[5]), data[6]));
                    }else {
                        System.out.println("Zly vstup");
                    }
                    break;
                case "S":
                    if(validate(data)){
                        nastroje.add(new SlacikovyNastroj(data[1], Double.parseDouble(data[2]), data[3], Integer.parseInt(data[4]), Integer.parseInt(data[5]), data[6], data[7]));
                    }else {
                        System.out.println("Zly vstup");
                    }
                    break;
                case "d":
                    if(validate(data)){
                        nastroje.add(new DychovyNastroj(data[1], Double.parseDouble(data[2]), data[3], Integer.parseInt(data[4]), Integer.parseInt(data[5]), data[6]));
                    }else {
                        System.out.println("Zly vstup");
                    }
                    break;
                case "r":
                    if(validate(data)){
                        nastroje.add(new RytmickyNastroj(data[1], Double.parseDouble(data[2]), data[3], Integer.parseInt(data[4]), Integer.parseInt(data[5])));
                    }else {
                        System.out.println("Zly vstup");
                    }
                    break;
                case "k":
                    if(validate(data)){
                        nastroje.add(new KlavesovyNastroj(data[1], Double.parseDouble(data[2]), data[3], Integer.parseInt(data[4]), Integer.parseInt(data[5])));
                    }else {
                        System.out.println("Zly vstup");
                    }
                    break;
                default:
                    System.out.println("Zly vstup");
            }
        }

        for ( VieZniet n : nastroje) {
            System.out.println(n.toString());
        }
        for ( Hrac h : hraci) {
            System.out.println(h.toString());
        }

        Map<String, ArrayList<Hrac>> orchester = priradenie(hraci, nastroje);

        cenaOrchestra(nastroje);

        cenaVystupenia(orchester);

        obsadenie(orchester);
        zahrajSkladbu(orchester, nastroje);

        System.out.println("Vsetky zvuky");
        for ( VieZniet n : nastroje) {
            System.out.println(n.getZvuk());
        }

    }
}
