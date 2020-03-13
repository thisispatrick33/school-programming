import java.util.Scanner;
class Zenit19skh {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        if(input.charAt(0)=='0'){
            System.out.println("nie");
            System.exit(0);
        }
        if(input.matches(".*[1347].*")){
            System.out.println("nie");
            System.exit(0);
        }
        String tmp = input;
        input = input.replace('6', 'b').replace('9', 'c').replace('b', '9').replace('c', '6');
        StringBuilder input1 = new StringBuilder();
        input1.append(input);
        input1 = input1.reverse();
        if(tmp.equals(input1.toString())){
            System.out.println("nie");
        }
        else {
            System.out.println("ano");
        }
    }
}
