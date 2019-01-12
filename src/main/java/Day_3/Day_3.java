package Day_3;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day_3 {

    private static final Pattern PATTERN = Pattern.compile("^#.*@.(.*),(.*):.(.*)x(.*)$");

    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(new File("C:/Users/macio/Desktop/advent-of-code/day-3-input.txt").toPath());

        List<Integer> fabricPiecesOccurencesNumbers = new ArrayList<>(Collections.nCopies(1000000, 0));
        List<List<Integer>> numbersSets = new ArrayList<>();

        updateFabricPiecesOccurencesValues(fabricPiecesOccurencesNumbers, numbersSets);

        findFirstPartSolution(fabricPiecesOccurencesNumbers, numbersSets, input);
        findSecondPartSolution(fabricPiecesOccurencesNumbers, numbersSets, input);
    }

    private static void updateFabricPiecesOccurencesValues(List<Integer> fabricPiecesOccurencesNumbers, List<List<Integer>> numbersSets) {
        for (List<Integer> numberSet : numbersSets) {

            int leftDistance = numberSet.get(0);
            int topDistance = numberSet.get(1);
            int width = numberSet.get(2);
            int height = numberSet.get(3);

            int firstFabricPiece = 1000 * topDistance + leftDistance;

            for (int i = firstFabricPiece ; i < firstFabricPiece + width ; i++) {
                for (int j = i ; j < i + 1000*height ; j=j+1000) {
                    int value = fabricPiecesOccurencesNumbers.get(j);
                    value++;
                    fabricPiecesOccurencesNumbers.set(j,value);
                }
            }
        }
    }

    private static void findFirstPartSolution(List<Integer> fabricPiecesOccurencesNumbers, List<List<Integer>> numbersSets, List<String> input) {

        for (String line : input) {
            Matcher matcher = PATTERN.matcher(line);
            if (matcher.matches()) {
                numbersSets.add(Arrays.asList(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4))));
            }
        }
        updateFabricPiecesOccurencesValues(fabricPiecesOccurencesNumbers, numbersSets);

        int result = 0;
        for (Integer fabricPieceOccurenceNumber : fabricPiecesOccurencesNumbers) {
            if (fabricPieceOccurenceNumber > 1) {
                result++;
            }
        }
        System.out.println("First part solution: " + result);
    }

    private static void findSecondPartSolution(List<Integer> fabricPiecesOccurencesNumbers, List<List<Integer>> numbersSets, List<String> input) {
        for (List<Integer> numberSet : numbersSets) {

            List<Integer> values = new ArrayList<>();

            int leftDistance = numberSet.get(0);
            int topDistance = numberSet.get(1);
            int width = numberSet.get(2);
            int height = numberSet.get(3);

            int firstFabricPiece = 1000 * topDistance + leftDistance;

            for (int i = firstFabricPiece ; i < firstFabricPiece + width ; i++) {
                for (int j = i ; j < i + 1000*height ; j=j+1000) {
                    values.add(fabricPiecesOccurencesNumbers.get(j));
                }
            }

            boolean good = true;
            for (Integer value : values) {
                if (value != 1) {
                    good = false;
                }
            }

            if (good) {
                System.out.println("Second part solution: " + input.get(numbersSets.indexOf(numberSet)).substring(1, 5));
            }
        }
    }
}
