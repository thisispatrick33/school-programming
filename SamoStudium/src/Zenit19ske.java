import java.util.Scanner;
class Zenit19ske {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String [][] plocha = {
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "D"}
        };
        String [] input = sc.nextLine().split(" ");
        int ujoY = Integer.parseInt(input[1])-1;
        int ujoX = Integer.parseInt(input[0])-1;

        if(ujoY==12 && ujoX==12){
            System.out.println("Ujo je doma!");
        }
        else{
            plocha[ujoX][ujoY] = "U";

            for (int i=0; i < 13; i++){
                if(i<12&&i>ujoY){
                    plocha[ujoX][i]="x";
                }
                else if(i==12&&ujoX!=12&&i>ujoY){
                    plocha[ujoX][i]="x";
                }
            }
            for (int i=0; i < 12; i++){
                if (i>ujoX){
                    plocha[i][12]="x";
                }
            }

            System.out.println("###############");
            for (int i=0; i < 13; i++){
                System.out.print("#");
                for (int r=0; r < 13; r++){
                    System.out.print(plocha[i][r]);
                }
                System.out.println("#");
            }
            System.out.println("###############");
        }
    }
}
