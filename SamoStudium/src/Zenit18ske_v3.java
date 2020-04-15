import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Zenit18ske_v3 {

    public static void buildStrings(char[] root, int length, int n) {
        int a = 0;
        int[] pos = new int[length];
        char[] combo = new char[length];
        for(int i = 0; i < length; i++) {
            combo[i] = root[0];
        }

        while(true) {
            if(a == n){
                break;
            }
            System.out.println(String.valueOf(combo));
            a++;
            int place = length - 1;
            while(place >= 0) {
                if(++pos[place] == root.length) {
                    pos[place] = 0;
                    combo[place] = root[0];
                    place--;
                }
                else {
                    combo[place] = root[pos[place]];
                    break;
                }
            }
            if(place < 0)
                break;
        }
    }
    public static void main(String[] args)throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        char arr[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        int n = Integer.parseInt(in.readLine());
        buildStrings(arr,  4, n);
        in.close();
    }
}
