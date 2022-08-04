import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer>[] nodes = new ArrayList[scanner.nextInt() + 1];
        int number1, number2;
        for (int i = 1; i < nodes.length - 1; i++) {
            number1 = scanner.nextInt();
            number2 = scanner.nextInt();
            if (nodes[number1] == null)
                nodes[number1] = new ArrayList<>();
            if (nodes[number2] == null)
                nodes[number2] = new ArrayList<>();
            nodes[number1].add(number2);
            nodes[number2].add(number1);
        }
        boolean[] passed = new boolean[nodes.length];
        passed[1] = true;
        Int length = new Int(0);
        Int size = new Int(0);
        double expect = calculateWayes(nodes, passed, 0, 1, 1.0);
        System.out.printf("%.7f\n", expect);
    }

    public static double calculateWayes(ArrayList<Integer>[] nodes, boolean[] passed,
                                        int length, int current, double probeb) {
        double expect = 0;
        boolean hasWay = false;
        double newProbeb;
        if (current == 1){
            newProbeb =probeb * (1 / ((double) nodes[current].size()));
        }else {
            newProbeb =probeb * (1 / ((double) nodes[current].size() - 1));
        }
        for (int i = 0; i < nodes[current].size(); i++) {
            if (!passed[nodes[current].get(i)]) {
                passed[nodes[current].get(i)] = true;
                expect += calculateWayes(nodes, passed, length + 1,
                        nodes[current].get(i), newProbeb);
                passed[nodes[current].get(i)] = false;
                hasWay = true;
            }
        }
        if (!hasWay) {
            expect += probeb * (double) length;
        }
        return expect;
    }
}

class Int {
    int x;

    public Int(int x) {
        this.x = x;
    }

    public double toDouble() {
        return x;
    }
}