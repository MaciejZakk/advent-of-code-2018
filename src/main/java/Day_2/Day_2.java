package Day_2;

import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class Day_2 {

    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(new File("C:/Users/macio/Desktop/advent-of-code/day-2-input.txt").toPath());

        findFirstPartSolution(input);
        findSecondPartSolution(input);

        findFirstPartSolutionAnotherWay(input);
    }

    private static void findFirstPartSolution(List<String> input) {

        int twoLetters = 0;
        int threeLetters = 0;

        for (String line : input) {

            char[] characters = line.toCharArray();
            List<Character> letters = new ArrayList<>();
            List<Character> referenceLetters = new ArrayList<>();

            for (Character character : characters) {
                letters.add(character);
                referenceLetters.add(character);
            }

            boolean twoIdenticalLettersFound = false;
            boolean threeIdenticalLettersFound = false;

            for (Character letter : letters) {
                referenceLetters.remove(letter);
                if (referenceLetters.contains(letter)) {
                    referenceLetters.remove(letter);
                    if (referenceLetters.contains(letter)) {
                        threeIdenticalLettersFound = true;
                    } else {
                        twoIdenticalLettersFound = true;
                    }
                }
            }
            if (twoIdenticalLettersFound) {
                twoLetters++;
            }
            if (threeIdenticalLettersFound) {
                threeLetters++;
            }
        }
        System.out.println("First part solution: " + twoLetters * threeLetters);
    }

    private static void findSecondPartSolution(List<String> input) {

        List<List<Character>> characterInput = new ArrayList<>();
        List<List<Character>> referenceCharacterInput = new ArrayList<>();

        for (String line : input) {
            characterInput.add(line.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
            referenceCharacterInput.add(line.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        }

        for (int i = 0; i < characterInput.size(); i++) {
            List<Character> first = characterInput.get(i);
            for (int j = 0; j < referenceCharacterInput.size(); j++) {
                List<Character> second = referenceCharacterInput.get(j);

                int differenceCounter = 0;
                int lastDifferenceIndex = 0;

                for (int k = 0; k < second.size(); k++) {
                    if (first.get(k) != second.get(k)) {
                        differenceCounter++;
                        lastDifferenceIndex = k;
                    }
                }
                if (differenceCounter == 1) {
                    first.remove(lastDifferenceIndex);
                    String result = first.stream().map(Object::toString).collect(Collectors.joining());
                    System.out.println(result);
                    return;
                }
            }
        }

    }

    private static void findFirstPartSolutionAnotherWay(List<String> input) {

        int doubleLetters = 0;
        int tripleLetters = 0;

        for (String line : input) {
            Map<Character, Integer> numbersOfOccurrencesForEachLetter = new HashMap<>();
            for (char c : line.toCharArray()) {
                numbersOfOccurrencesForEachLetter.merge(c, 1, (a, b) -> a + b);
            }
            if (numbersOfOccurrencesForEachLetter.containsValue(2)) doubleLetters++;
            if (numbersOfOccurrencesForEachLetter.containsValue(3)) tripleLetters++;
        }
        System.out.println("First part solution another way: " + doubleLetters * tripleLetters);
    }
}