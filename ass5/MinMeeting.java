package ass5;

import java.util.*;

public class MinMeeting {
    static class Meeting {
        int start, end;
        Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    static int minRooms(Meeting[] meetings) {
        Arrays.sort(meetings, Comparator.comparingInt(m -> m.start));
        PriorityQueue<Integer> rooms = new PriorityQueue<>();
        int answer = 0;

        for (Meeting m : meetings) {
            while (!rooms.isEmpty() && rooms.peek() <= m.start)
                rooms.poll();

            rooms.add(m.end);
            answer = Math.max(answer, rooms.size());
        }
        return answer;
    }

    public static void main(String[] args) {
        Meeting[] meetings = {
            new Meeting(900,1000), new Meeting(930,1330),
            new Meeting(1200,1300), new Meeting(1330,1500),
            new Meeting(1400,1430), new Meeting(1500,1700),
            new Meeting(1600,1630)
        };

        System.out.println("Minimum rooms required = " + minRooms(meetings));
    }
}
