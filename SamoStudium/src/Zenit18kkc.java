import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Zenit18kkc {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String [] input = in.readLine().split(":");

        int angleM = Integer.parseInt(input[1])*6;
        int angleH = (int)((Integer.parseInt(input[0])*30) + (Integer.parseInt(input[1])*0.5));
        int difference = angleH-angleM;

        System.out.println(Math.abs(difference < 360-(difference) ? difference : 360-(difference))+"\u00B0");
    }

}
