import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        int size;
        int[] bi;
        long[] price;
        long sum = 0, min;
        boolean[] passed;
        int number;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            size = Integer.parseInt(reader.readLine());
            String[] line;
            bi = new int[size + 1];
            price = new long[size + 1];
            passed = new boolean[size + 1];
            line = reader.readLine().split(" ");
            for (int i = 0; i < size; i++) {
                bi[i + 1] = Integer.parseInt(line[i]);
            }
            line = reader.readLine().split(" ");
            for (int i = 0; i < size; i++) {
                price[i + 1] = Long.parseLong(line[i]);
                if (bi[i + 1] == i + 1) {
                    passed[i + 1] = true;
                    sum += price[i + 1];
                }
            }
        } catch (Exception e) {
            return;
        }
        ArrayList<Integer> indexes = new ArrayList<>();
        outter:
        for (int i = 1; i < size + 1; i++) {
            if (!passed[i]) {
                number = bi[i];
                min = price[i];
                passed[i] = true;
                indexes.clear();
                indexes.add(i);
                if (bi[number] == number)
                    continue;
                while (!passed[number]) {
                    min = Long.min(price[number], min);
                    indexes.add(number);
                    passed[number] = true;
                    number = bi[number];
                    if (bi[number] == number)
                        continue outter;
                }
                if (number == i) {
                    sum += min;
                } else {
                    number = indexes.indexOf(new Integer(number));
                    if (number == -1)
                        continue;
                    min = price[indexes.get(number)];
                    for (int j = number; j < indexes.size(); j++) {
                        min = Long.min(min, price[indexes.get(j)]);
                    }
                    sum += min;
                }
            }
        }
        System.out.println(sum);
    }
}