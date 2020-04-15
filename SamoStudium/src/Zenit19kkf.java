import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Zenit19kkf {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        String [] input = reader.readLine().split(" ");
        int [] counts = {0, 0, 0, 0, 0};
        for (int i = 0; i < n; i++){
            if (input[i].equals("1")){
                counts[0] += 1;
            }
            if (input[i].equals("2")){
                counts[1] += 1;
            }
            if (input[i].equals("3")){
                counts[2] += 1;
            }
            if (input[i].equals("4")){
                counts[3] += 1;
            }
            if (input[i].equals("5")){
                counts[4] += 1;
            }
            input[i] = String.format("%3s", Integer.toString(Integer.parseInt(input[i]), 2)).replace(' ', '0');
        }
        int max = 0;
        int tmp = 0;
        for (int i=0; i<5; i++){
            if(counts[i]>tmp){
                tmp = counts[i];
                max = i;
            }
        }
        System.out.println(max+1);


    }
    private static String xor(String a, String b){
        String output = "";
        System.out.println(a);
        System.out.println(b);
        for (int i = 0; i < 3 ; i++){
            if((a.charAt(i)=='1'&&b.charAt(i)=='0')||(a.charAt(i)=='0'&&b.charAt(i)=='1')){
                output+='1';
            }
            else {
                output+='0';
            }
        }
        return output;
    }
}
