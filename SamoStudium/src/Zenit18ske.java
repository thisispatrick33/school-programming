import java.util.Scanner;

class Zenit18ske {
    public static int n = 0;

    private static int permute(String c) {
        if (c.length() == 4) {
            System.out.println(c);
            n--;
            if(0 == n){
                System.exit(0);
            }
            return 1;
        }
        for (int i = 0; i < 26; ++i) {
            permute(c + (char)(97+i));
        }
        return 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        permute("");
    }
}