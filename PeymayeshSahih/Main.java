import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer>[] nodes = new ArrayList[scanner.nextInt() + 1];
        int number1, number2;
        for (int i = 0; i < nodes.length - 2; i++) {
            number1 = scanner.nextInt();
            number2 = scanner.nextInt();
            if (nodes[number1] == null)
                nodes[number1] = new ArrayList<>();
            if (nodes[number2] == null)
                nodes[number2] = new ArrayList<>();
            nodes[number1].add(number2);
            nodes[number2].add(number1);
        }
        ArrayList<Integer> current = new ArrayList<>();
        current.add(new Integer(1));
        ArrayList<Integer> next = new ArrayList<>();
        boolean[] hasBecame = new boolean[nodes.length];
        boolean answer = true;
        Integer tempt;
        for (int i = 1; i < nodes.length; i++) {
            tempt = scanner.nextInt();
            if (!answer)
                continue;
            if (current.size() == 0) {
                answer = false;
                continue;
            }
            if (hasBecame[tempt] || (!current.remove(tempt))) {
                answer = false;
                continue;
            } else {
                for (int j = 0; j < nodes[tempt].size(); j++) {
                    if (!hasBecame[nodes[tempt].get(j)])
                        next.add(nodes[tempt].get(j));
                }
                hasBecame[tempt] = true;
            }
            if (current.size() == 0) {
                current = next;
                next = new ArrayList<>();
            }
        }
        if (answer)
            System.out.println("Yes");
        else
            System.out.println("No");
    }
}
