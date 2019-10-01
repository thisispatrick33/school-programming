package com.company;
/*
    Doplňte funkcionalitu pre aplikáciu Reakčná doba

    Princíp hry

    Hráč po zadaní svojho mena spustí hru.
    Na otázku "Pripraveny ?" odpovedá stlačením ENTER
    Objaví sa hlásenie "Pozooor ..."
    a po náhodne dlhej dobe v intervale 0.5 - 3 sekundy sa objaví povel "START !!!"
    Cieľom hráča je v najrýchlejšom možnom čase opäť stlačiť ENTER.
    Program vypíše čas v milisekundách, ktorý uplynul od zobrazenia povelu START po stlačenie ENTER
    a zaradí ho do usporiadanej tabuľky výkonov (Meno hráča + výkon)
    Na obrazovku vypíše, kde sa daný výkon v tabuľke nachádza a to tak, že vypíše
    5 bezprostredne predchádzajúcich výkonov
    aktuálny výkon
    5 bezprostredne nasledujúcich výkonov
    To všetko v tvare Poradové číslo v tabuľke výkonov Tab6 Meno hráča Tab25 výkon
    Celú tabuľku s novým záznamom zapíše do textového súboru na disk, každý riadok v tvare MenoHraca:vykon

    Hra po spustení načíta zo súboru aktuálnu tabuľku výkonov a požiada hráča o prihlásenie (zadanie mena)
    Potom zobrazí MENU s položkami
    1 - Spusť hru
    2 - Zmena hráča
    3 - TOP 10
    4 - Koniec
    A reaguje podľa výberu

    Hru naprogramujte ako konzolovú aplikáciu aj ako aplikáciu s GUI. Využite pritom MVC.
    Pre meranie času využite funkciu System.currentTimeMillis();
    Hra musí ošetriť aj predčasné stlačenie pred zobrazením START ako chybu a potrestať ju (spôsob trestu je na vás)
*/

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class ReactBase {
    final int CM_PLAY = 1;
    final int CM_CHANGE_PLAYER = 2;
    final int CM_TOP10 = 3;
    final int CM_QUIT = 4;
    String Player;
    boolean run = true;
    int a=0;



    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader from_file = new BufferedReader(new FileReader("stats"));


    Map<String, Integer> stats = new LinkedHashMap<String, Integer>();

    public static void main(String[] args) throws IOException, InterruptedException {
        boolean gameOn;
	    ReactBase Game = new ReactBase();
	    do
            gameOn =  Game.Run();
        while (gameOn);


    }

    public ReactBase() throws IOException {
        ImportRecords();
        NewPlayer();
    }

    public boolean Run() throws IOException, InterruptedException {
        switch(Menu()){
            case CM_PLAY:
                int LastTime = Play(Player);
                Sort(Player, LastTime);
                ShowRecords(Player, LastTime);
                SaveRecords();
                return true;
            case CM_CHANGE_PLAYER:
                NewPlayer();
                return true;
            case CM_TOP10:
                ShowRecords("", 0);
                return true;
            case CM_QUIT:
                return false;
        }
        return true;
    }

    public void ImportRecords() throws IOException {
        String line;
        while((line = from_file.readLine())!=null){
            String info [] = line.split(" ");
            stats.put(info[0], Integer.parseInt(info[1]));
        }

    }

    public void NewPlayer() throws IOException {
        System.out.println("Zadaj svoje meno.");
        Player = input.readLine();

    }

    public int Menu()throws IOException {

        System.out.println(
                "--- MENU ---\n" +
                        "1 - Spusť hru\n" +
                        "2 - Zmena hráča\n" +
                        "3 - TOP 10\n" +
                        "4 - Koniec\n");
        try {
            return Integer.parseInt(input.readLine());
        } catch(NumberFormatException e) {
            System.out.println("Zadali ste nevhodný príkaz");
            return CM_QUIT;
        }

    }


    public int Play(String who) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        Random rn = new Random();
        System.out.println("Pripraviť");
        Thread.sleep(rn.nextInt(10000));
        System.out.println("Strieľaj");
        double startTime = System.currentTimeMillis();
        sc.nextLine();
        double endTime = System.currentTimeMillis();
        double time = (endTime - startTime);
        System.out.println(time+"ms");
        return (int) time;
    }

    public void Sort(String who, int record){
        stats.put(who, record);
        List <Integer> values = new ArrayList<Integer>(stats.values());
        Map<String, Integer> temp= new LinkedHashMap<String, Integer>();
        Collections.sort(values);
        for ( int i: values) {
            for ( String x : stats.keySet()){
                if(stats.get(x)==i){
                    temp.put(x, i);
                    stats.put(x, 0);
                }
            }
        }
        stats=temp;



    }

    public void ShowRecords(String who, int record){
        if (who.length()>0) {
            List<String> names = new ArrayList<String>(stats.keySet());
            int b = names.indexOf(who);
            int minVal = (b - 5 < 0) ? 0 : (b - 5);
            for (int i = minVal; (i < b + 5) && (i < stats.size()); i++) {
                System.out.println(names.get(i) + " " + stats.get(names.get(i)));
            }
        }
        else {
            List<String> names = new ArrayList<String>(stats.keySet());
            for (int i=0; (i < 10) && (i < stats.size()); i++){
                System.out.println(names.get(i) + " " + stats.get(names.get(i)));
            }
        }
    }

    public void SaveRecords() throws IOException {
        BufferedWriter to_file = new BufferedWriter(new FileWriter("stats"));

        for ( String meno : stats.keySet() ) {
            to_file.write(meno+" "+stats.get(meno));
            to_file.newLine();
        }
        to_file.close();

    }

}
