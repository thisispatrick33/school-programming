import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Zenit19kkc {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(reader.readLine());
        String s = reader.readLine();
        ArrayList<String> linkedlist = new ArrayList<>();
        for (int i = 0; i < Math.pow(2, k); i++){
            linkedlist.add(reader.readLine());
        }
        for (int i = 0; i < s.length()/k; i++){
            System.out.print(linkedlist.indexOf(s.substring(i*k, i*k+k)));
        }
        System.out.println();
    }
}
