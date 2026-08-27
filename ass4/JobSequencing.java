package ass4;

import java.util.*;

public class JobSequencing {
    static class Job {
        String id;
        int deadline, payout;

        Job(String id, int deadline, int payout) {
            this.id = id;
            this.deadline = deadline;
            this.payout = payout;
        }
    }

    static void scheduleJobs(Job[] jobs, int slots) {
        Arrays.sort(jobs, (a, b) -> Integer.compare(b.payout, a.payout));

        Job[] result = new Job[slots];
        int totalPayout = 0;

        for (Job job : jobs) {
            for (int j = Math.min(job.deadline, slots) - 1; j >= 0; j--) {
                if (result[j] == null) {
                    result[j] = job;
                    totalPayout += job.payout;
                    break;
                }
            }
        }

        System.out.print("Schedule: ");
        for (Job job : result) {
            if (job != null)
                System.out.print(job.id + " ");
        }
        System.out.println();
        System.out.println("Total payout: " + totalPayout);
    }

    public static void main(String[] args) {
        Job[][] tests = {
            {
                new Job("T1", 2, 450), new Job("T2", 1, 300),
                new Job("T3", 3, 220), new Job("T4", 2, 600),
                new Job("T5", 1, 150)
            },
            {
                new Job("T6", 4, 700), new Job("T7", 3, 90),
                new Job("T8", 2, 500), new Job("T9", 4, 260),
                new Job("T10", 1, 800)
            },
            {
                new Job("T1", 2, 450), new Job("T4", 2, 600),
                new Job("T8", 2, 500), new Job("T10", 1, 800)
            },
            {
                new Job("T2", 1, 300), new Job("T3", 3, 220),
                new Job("T7", 3, 90), new Job("T9", 4, 260)
            },
            {
                new Job("T1", 2, 450), new Job("T3", 3, 220),
                new Job("T6", 4, 700), new Job("T10", 1, 800)
            }
        };

        for (int i = 0; i < tests.length; i++) {
            System.out.println("Test " + (i + 1) + ":");
            scheduleJobs(tests[i], 4);
            System.out.println();
        }
    }
}

