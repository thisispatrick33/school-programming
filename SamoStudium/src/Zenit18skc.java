import java.util.Scanner;

class Zenit18skc {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.nextLine();
        String b = sc.nextLine();
        if( b.startsWith(a.substring(0, a.indexOf("*"))) && b.substring(b.indexOf(a.substring(0, a.indexOf("*")))+a.substring(0, a.indexOf("*")).length()).endsWith(a.substring(a.indexOf("*")+1)) ){
            System.out.println("ANO");
        }
        else {
            System.out.println("NIE");
        }

    }
}
