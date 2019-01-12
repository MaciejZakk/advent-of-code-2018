package Day_4;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class Day_4 {

    public static void main(String[] args) throws Exception {

        Map<Integer, int[]> data = new HashMap<>();
        List<String> input = Files.readAllLines(new File("C:/Users/macio/Desktop/advent-of-code/day-4-input.txt").toPath());
        Collections.sort(input);

        extractIdAndSleepingTimeForEveryGuard(data, input);

        findFirstPartSolution(data);
        findSecondPartSolution(data);
    }

    private static void extractIdAndSleepingTimeForEveryGuard(Map<Integer, int[]> data, List<String> input) {
        int currentGuardIdentifier = 0;
        int startOfSleepMinute = 0;

        for (String line : input) {

            int currentLineMinute = Integer.parseInt(line.substring(15, 17));

            boolean shift = line.contains("shift");
            boolean wake = line.contains("wake");
            boolean sleep = line.contains("sleep");

            int id;

            if(shift) {
                id = Integer.parseInt(line.split(" ")[3].substring(1));
                currentGuardIdentifier = id;

                if(!data.containsKey(id)) {
                    data.put(currentGuardIdentifier, new int[60]);
                }
            }
            if(sleep) {
                startOfSleepMinute = currentLineMinute;
            }
            if(wake) {
                for (int minute = startOfSleepMinute; minute < currentLineMinute; minute++) {
                    data.get(currentGuardIdentifier)[minute] += 1;
                }
            }
        }
    }

    private static void findFirstPartSolution(Map<Integer, int[]> data) {
        List<Integer> keys = getAllGuardsIdentifiers(data);
        int mostOverallSleepingGuardIdentifier = keys.get(0);
        int mostSleepingGuardSleepingMinutes = 0;

        for (int id : keys) {
            int sum = Arrays.stream(data.get(id)).filter(x -> x >= 1).sum();
            if(sum > mostSleepingGuardSleepingMinutes) {
                mostSleepingGuardSleepingMinutes = sum;
                mostOverallSleepingGuardIdentifier = id;
            }
        }

        int biggestNumberOfTimesGuardWasAsleepInParticularMinute = 0;
        int mostSleptMinute = 0;

        for(int minute = 0; minute < 60; minute++) {
            int numberOfTimesGuardWasAsleepInThisMinute = data.get(mostOverallSleepingGuardIdentifier)[minute];
            if(numberOfTimesGuardWasAsleepInThisMinute > biggestNumberOfTimesGuardWasAsleepInParticularMinute) {
                biggestNumberOfTimesGuardWasAsleepInParticularMinute = numberOfTimesGuardWasAsleepInThisMinute;
                mostSleptMinute = minute;
            }
        }
        System.out.println("First part solution: " + mostOverallSleepingGuardIdentifier * mostSleptMinute);
    }

    private static void findSecondPartSolution(Map<Integer, int[]> data) {
        List<Integer> keys = getAllGuardsIdentifiers(data);
        int longestSleepingGuardInParticularMinuteIdentifier = keys.get(0);
        int biggestNumberOfTimesGuardWasAsleepInParticularMinute = 0;

        for (int id : keys) {
            int numberOfTimesGuardWasAsleepInThisMinute = Arrays.stream(data.get(id)).max().orElse(-1);
            if(numberOfTimesGuardWasAsleepInThisMinute > biggestNumberOfTimesGuardWasAsleepInParticularMinute) {
                biggestNumberOfTimesGuardWasAsleepInParticularMinute = numberOfTimesGuardWasAsleepInThisMinute;
                longestSleepingGuardInParticularMinuteIdentifier = id;
            }
        }

        int mostSleptMinuteByAnyGuard = 0;
        for(int minute = 0; minute < 60; minute++) {
            int numberOfTimesGuardWasAsleepInThisMinute = data.get(longestSleepingGuardInParticularMinuteIdentifier)[minute];
            if(numberOfTimesGuardWasAsleepInThisMinute == biggestNumberOfTimesGuardWasAsleepInParticularMinute) {
                mostSleptMinuteByAnyGuard = minute;
            }
        }
        System.out.println("Second part solution: " + longestSleepingGuardInParticularMinuteIdentifier * mostSleptMinuteByAnyGuard);
    }

    private static ArrayList getAllGuardsIdentifiers(Map<Integer, int[]> data) {
        return new ArrayList(data.keySet());
    }
}