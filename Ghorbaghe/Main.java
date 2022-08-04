import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here

        PriorityQueue<Integer> poisens =new PriorityQueue<>(Comparator.reverseOrder());
        Scanner scanner =new Scanner(System.in);
        int frogNumber =scanner.nextInt(), time =scanner.nextInt() ,sum =0 , number;
        for (int i = 0; i < frogNumber; i++) {
            poisens.add(scanner.nextInt());
        }
        while (time > 0){
            if (poisens.size() == 0)
                break;
            number =poisens.poll() / 2;
            sum+=number;
            time--;
            if (time == 0)
                break;
            poisens.add(number);
        }
        System.out.println(sum);
    }
}