import java.util.Scanner;
class Zenit19skg {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String jednaDiera = "AabDdegOoPpQqR0469";
        String dveDiery = "B8";
        int output = 0;
        for(int i = 0; i < input.length(); i++){
            if(jednaDiera.indexOf(input.charAt(i))>=0){
                output += 1;
            }
            if(dveDiery.indexOf(input.charAt(i))>=0){
                output += 2;
            }
        }
        System.out.println(output);
    }
}
