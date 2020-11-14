package sample;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

public class Controller {
    public void initialize() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:/Users/Mamuss/IdeaProjects/school-programming/Kurzy/src/sample/meny"));
        String line;
        TreeMap<Integer, Mena> zoznam = new TreeMap<>();
        int a=0;
        while ((line = br.readLine())!=null) {
            String[] arr = line.split(" ");
            zoznam.put(a,new Mena(Double.parseDouble(arr[3]),arr[1],arr[2],arr[0]));
            a++;
        }
        for (int i: zoznam.keySet()) {
            System.out.println(zoznam.get(i));
        }
    }

}
