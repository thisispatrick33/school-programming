import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

class zenit18skf {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        StringBuilder output = new StringBuilder();
        int generation = 0;

        long [] input = Arrays.stream(sc.nextLine().split(" ")).mapToLong(Long::parseLong).toArray();

        if(input[2] < 2) {
            System.out.println("-1");
            System.exit(0);
        }

        while (input[1] > 0) {
            output.insert(0, " " + (input[1] % input[2]));
            generation++;
            input[1] /= input[2];
        }

        output.insert(0, LongStream.range(0, (input[0]-generation)).mapToObj(i -> " 0").collect(Collectors.joining("")));
        System.out.println(input[0] >= generation ? output.toString().trim() : "-1");
    }
}
