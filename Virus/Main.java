import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt(), relashion = scanner.nextInt(),
                armin = scanner.nextInt(), limit = scanner.nextInt(),
                amount = scanner.nextInt(), number1, number2;
        ArrayList<Integer>[] persons = new ArrayList[number];
        for (int i = 0; i < relashion; i++) {
            number1 = scanner.nextInt();
            number2 = scanner.nextInt();
            if (persons[number1] == null)
                persons[number1] = new ArrayList<>();
            if (persons[number2] == null)
                persons[number2] = new ArrayList<>();
            persons[number1].add(number2);
            persons[number2].add(number1);
        }
        boolean[] hasConsiderd =new boolean[number];
        ArrayList<Integer> current =persons[armin];
        ArrayList<Integer> next =new ArrayList<>();
        int[] percent =new int[number];
        percent[armin] =amount;
        amount-=10;
        while (limit > 0){
            if (amount < 0)
                break;
            if (current == null)
                break;
            if (current.size() == 0)
                break;
            for (int i = 0; i < current.size(); i++) {
                if ((!hasConsiderd[current.get(i)]) && (current.get(i)!= armin) && (persons[current.get(i)]!= null)){
                    for (int k = 0; k < persons[current.get(i)].size(); k++) {
                        if (!hasConsiderd[persons[current.get(i)].get(k)])
                            next.add(persons[current.get(i)].get(k));
                    }
                    percent[current.get(i)] =amount;
                    hasConsiderd[current.get(i)] =true;
                }
            }
            current =next;
            next =new ArrayList<>();
            limit--;
            amount-=10;
        }
        for (int i = 0; i < percent.length; i++) {
            System.out.println(percent[i]);
        }
    }
}