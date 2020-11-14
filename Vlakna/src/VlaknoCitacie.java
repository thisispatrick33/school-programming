import java.util.Scanner;

public class VlaknoCitacie  extends Thread{
    public static boolean idem = true;
    static Scanner sc = new Scanner(System.in);

    public void run(){
        Thread.currentThread().setPriority(MAX_PRIORITY);
        String line;
        while (idem){
            if((line = sc.nextLine()).startsWith("K")){
                idem = false;
                System.out.println("bolo zadane K");
            }
            System.out.println("Zadal si "+line);
        }
    }
}
