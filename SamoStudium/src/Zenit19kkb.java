import java.util.Scanner;

class Zenit19kkb {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String [] input = sc.nextLine().split(" ");
        double n = Double.parseDouble(input[0]);
        double k = Double.parseDouble(input[1]);
        if(n==1){
            System.out.println(1);
        }
        else {
            for (int x = 1; x < 10000; x++){
                if(Math.pow(n, x)>k){
                    System.out.println((int)Math.pow(n, x-1));
                    System.exit(0);
                }
            }
        }
    }
}
