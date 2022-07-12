package Exam25June2022;

import java.util.*;
import java.util.stream.Collectors;

public class _01ItIsChocolateTime {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double[] numbers = Arrays.stream(scanner.nextLine().split(" "))
                .mapToDouble(Double::parseDouble).toArray();
        ArrayDeque<Double> milk = new ArrayDeque<>();
        for (double number : numbers) {
            milk.offer(number);
        }

        double[] numbers2 = Arrays.stream(scanner.nextLine().split(" "))
                .mapToDouble(Double::parseDouble).toArray();;
        ArrayDeque<Double> cacao = new ArrayDeque<>();
        for (double i : numbers2) {
            cacao.push(i);
        }
        Map<String, Integer> chocolate = new TreeMap<>();
        chocolate.put("Milk Chocolate", 0);
        chocolate.put("Dark Chocolate", 0);
        chocolate.put("Baking Chocolate", 0);

        while (!milk.isEmpty() && !cacao.isEmpty()) {
            double m = milk.poll();
            double c = cacao.pop();
            int mix = (int) ((c/(m + c)) * 100);
            switch (mix) {
                case 30:
                    chocolate.put("Milk Chocolate", chocolate.get("Milk Chocolate") + 1);
                    break;
                case 50:
                    chocolate.put("Dark Chocolate", chocolate.get("Dark Chocolate") + 1);
                    break;
                case 100:
                    chocolate.put("Baking Chocolate", chocolate.get("Baking Chocolate") + 1);
                    break;
                default:
                    milk.offerLast(m + 10);
                    break;
            }
        }

        List<Map.Entry<String, Integer>> list = chocolate.entrySet().stream().filter(e -> e.getValue() != 0)
                .collect(Collectors.toList());
        if (list.size() == 3) {
            System.out.println("It’s a Chocolate Time. All chocolate types are prepared.");
        } else {
            System.out.println("Sorry, but you didn't succeed to prepare all types of chocolates.");
        }


        for (Map.Entry<String, Integer> entry : chocolate.entrySet()) {
            if (entry.getValue() !=0){
                System.out.printf("# %s --> %d%n", entry.getKey(), entry.getValue());
            }
        }
    }
}

