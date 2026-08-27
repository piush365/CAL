package ass1;

import java.util.Arrays;
class Person implements Comparable<Person> {
    int height, weight;
    Person(int h, int w) {
        height = h;
        weight = w;
    }
    public int compareTo(Person p) {
        return this.height - p.height;
    }
}
public class CircusTower {
    public static void main(String[] args) {
        Person[] people = {
                new Person(65, 100),
                new Person(70, 150),
                new Person(56, 90),
                new Person(75, 190),
                new Person(60, 95),
                new Person(68, 110)
        };
        Arrays.sort(people);
        System.out.println("Longest Tower:");
        for (Person p : people) {
            System.out.println("(" + p.height + ", " + p.weight + ")");
        }
        System.out.println("Length of Tower = " + people.length);
    }
}

