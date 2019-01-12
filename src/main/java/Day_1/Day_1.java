package Day_1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Day_1 {

    public static void main(String[] args) throws IOException {

        List<Integer> numbers = extractIntegersFromInputData();

        findFirstPartSolution(numbers);
        findSecondPartSolution(numbers);
    }

    private static void findFirstPartSolution(List<Integer> input) {
        int currentFrequency = 0;

        for (Integer value : input) {
            currentFrequency += value;
        }
        System.out.println("First part solution: " + currentFrequency);
    }

    private static void findSecondPartSolution(List<Integer> input) {
        int currentFrequency = 0;
        List<Integer> frequencies = new ArrayList<>();
        frequencies.add(currentFrequency);

        while (true) {
            for (Integer value : input) {

                currentFrequency += value;

                if (frequencies.contains(currentFrequency)) {
                    System.out.println("Second part solution: " + currentFrequency);
                    return;
                }
                frequencies.add(currentFrequency);
            }
        }
    }

    private static List<Integer> extractIntegersFromInputData() throws IOException{
        List<String> input = Files.readAllLines(new File("C:/Users/macio/Desktop/advent-of-code/day-1-input.txt").toPath());
        List<Integer> numbers = new ArrayList<>();

        for (String value : input) {
            if (value.contains("-")) {
                numbers.add(-Integer.parseInt(value.replace("-", "")));
            } else {
                numbers.add(Integer.parseInt(value.replace("+", "")));
            }
        }
        return numbers;
    }
}
