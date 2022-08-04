import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Integer pre = -1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer>[] cities = new ArrayList[scanner.nextInt() + 1];
        int x = scanner.nextInt(), y = scanner.nextInt(), number1, number2;
        for (int i = 1; i < cities.length - 1; i++) {
            number1 = scanner.nextInt();
            number2 = scanner.nextInt();
            if (cities[number1] == null)
                cities[number1] = new ArrayList<>();
            if (cities[number2] == null)
                cities[number2] = new ArrayList<>();
            cities[number1].add(number2);
            cities[number2].add(number1);
        }
        boolean[] passed = new boolean[cities.length];
        passed[x] = true;
        passed[y] = true;
        long sum = ((long) cities.length - 1) * ((long)cities.length - 2);
        long xSide, ySide;
        for (int i = 0; i < cities[x].size(); i++) {
            if (cities[x].get(i) == y){
                cities[y].remove(new Integer(x));
                cities[x].remove(new Integer(y));
                break;
            }
            passed[cities[x].get(i)] = true;
            if (wayBetweenXAndY(cities, passed, cities[x].get(i), y)) {
                passed[cities[x].get(i)] = false;
                cities[x].remove(i);
                cities[y].remove(new Integer(pre));
                break;
            }
            passed[cities[x].get(i)] = false;
        }
        xSide = (numberOfPossibleWays(cities, passed, x));
        ySide = (numberOfPossibleWays(cities, passed, y));
        sum -= ( xSide *  ySide);
        System.out.println(sum);
    }

    public static long numberOfPossibleWays(ArrayList<Integer>[] cities, boolean[] passed, int current) {
        long sum = 1;
        for (int i = 0; i < cities[current].size(); i++) {
            if (!passed[cities[current].get(i)]) {
                passed[cities[current].get(i)] = true;
                sum += numberOfPossibleWays(cities, passed, cities[current].get(i));
                passed[cities[current].get(i)] = false;
            }
        }
        return sum;
    }

    public static boolean wayBetweenXAndY(ArrayList<Integer>[] cities, boolean[] passed, int current, int y) {
        boolean value = false;
        for (int i = 0; i < cities[current].size(); i++) {
            if (cities[current].get(i) == y) {
                pre = current;
                return true;
            }
            if (!passed[cities[current].get(i)]) {
                passed[cities[current].get(i)] = true;
                value = wayBetweenXAndY(cities, passed, cities[current].get(i) , y);
                passed[cities[current].get(i)] = false;
                if (value)
                    break;
            }
        }
        return value;
    }
}