import java.util.Scanner;
class Zenit19skf {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String [] input = sc.nextLine().split(" ");
        int strany = Integer.parseInt(input[0]);
        int fero = Integer.parseInt(input[1]);
        if(strany==1){
            System.out.println(1);
        }else if(strany-fero >= fero){
            System.out.println(fero+1);
        }else {
            System.out.println(fero-1);
        }
    }
}
