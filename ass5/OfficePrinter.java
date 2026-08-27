package ass5;

import java.util.*;

public class OfficePrinter {
    static void minimizeWaitingTime(int[] jobs) {
        Arrays.sort(jobs);

        int waiting = 0;
        int totalWaiting = 0;

        System.out.print("Order: ");
        for (int time : jobs) {
            System.out.print(time + " ");
            waiting += time;
            totalWaiting += waiting - time;
        }

        double average = (double) totalWaiting / jobs.length;

        System.out.println();
        System.out.println("Total waiting time = " + totalWaiting);
        System.out.println("Average waiting time = " + average);
    }

    public static void main(String[] args) {
        int[] jobs = {
            45, 120, 30, 80, 200, 10, 150, 70,
            250, 40, 180, 90, 20, 300, 60
        };

        minimizeWaitingTime(jobs);
    }
}
