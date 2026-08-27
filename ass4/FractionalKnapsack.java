package ass4;

import java.util.*;

public class FractionalKnapsack {
    static class Item {
        String name;
        double qty, value;

        Item(String name, double qty, double value) {
            this.name = name;
            this.qty = qty;
            this.value = value;
        }

        double ratio() {
            return value / qty;
        }
    }

    static double knapsack(Item[] items, double capacity) {
        Arrays.sort(items, (a, b) ->
            Double.compare(b.ratio(), a.ratio()));

        double totalValue = 0;

        System.out.println("Selected commodities:");
        for (Item item : items) {
            if (capacity <= 0)
                break;

            double take = Math.min(item.qty, capacity);
            double valueTaken = take * item.ratio();

            System.out.printf("%s -> %.2f tons, value = %.2f%n",
                              item.name, take, valueTaken);

            capacity -= take;
            totalValue += valueTaken;
        }

        return totalValue;
    }

    public static void main(String[] args) {
        Item[][] tests = {
            {
                new Item("CrudeOil", 300, 900),
                new Item("Coal", 250, 700),
                new Item("Machinery", 150, 650),
                new Item("Grain", 200, 500),
                new Item("Steel", 180, 620)
            },
            {
                new Item("Chemicals", 100, 450),
                new Item("Vehicles", 120, 800),
                new Item("Textiles", 90, 300),
                new Item("Electronics", 60, 700),
                new Item("Fertilizer", 140, 400)
            },
            {
                new Item("CrudeOil", 300, 900),
                new Item("Chemicals", 100, 450),
                new Item("Electronics", 60, 700)
            },
            {
                new Item("Coal", 250, 700),
                new Item("Grain", 200, 500),
                new Item("Textiles", 90, 300)
            },
            {
                new Item("Machinery", 150, 650),
                new Item("Steel", 180, 620),
                new Item("Vehicles", 120, 800)
            }
        };

        double[] capacities = {1000, 1000, 300, 500, 400};

        for (int i = 0; i < tests.length; i++) {
            System.out.println("Test " + (i + 1) + ":");
            double result = knapsack(tests[i], capacities[i]);
            System.out.printf("Total value: %.2f%n%n", result);
        }
    }
}
