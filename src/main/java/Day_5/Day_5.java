package Day_5;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Day_5 {
    public static void main(String[] args) throws IOException {

        String content = new Scanner(new File("C:/Users/macio/Desktop/advent-of-code/day-5-input.txt")).useDelimiter("\\Z").next();
        List<Character> inputStringCharacters = content.chars().mapToObj(e->(char)e).collect(Collectors.toList());

        List<Character> result = fullyReactInputPolymer(inputStringCharacters);

        findFirstPartSolution(result);
        findSecondPartSolution(result);
    }

    private static void findFirstPartSolution(List<Character> result) {
        System.out.println(result.size());
    }

    private static void findSecondPartSolution(List<Character> result) {
        List<Character> letters = result.stream().map(Character::toLowerCase).distinct().collect(Collectors.toList());

        int finalResult = Integer.MAX_VALUE;

        for (Character letter : letters) {
            List<Character> referenceResult = result;
            referenceResult = removeAll(referenceResult, letter);
            referenceResult = removeAll(referenceResult, Character.toUpperCase(letter));

            List<Character> solution = fullyReactInputPolymer(referenceResult);
            int solutionSize = solution.size();
            if (solutionSize < finalResult) {
                finalResult = solutionSize;
            }
        }
        System.out.println(finalResult);
    }

    private static List<Character> removeAll(List<Character> list, Character element) {
        return list.stream()
                .filter(e -> !Objects.equals(e, element))
                .collect(Collectors.toList());
    }

    private static List<Character> fullyReactInputPolymer(List<Character> inputStringCharacters) {

        while (true) {
            int beforeSize = inputStringCharacters.size();
            removeDuplicateLetters(inputStringCharacters);
            int afterSize = inputStringCharacters.size();

            if (beforeSize == afterSize) {
                return inputStringCharacters;
            }
        }
    }

    private static List<Character> removeDuplicateLetters(List<Character> data) {

        boolean polymers = true;

        while (polymers) {
            for (int letterIndex = 0; letterIndex < data.size(); letterIndex++) {

                if (letterIndex == data.size()-1) {
                    continue;
                }
                Character currentLetter = data.get(letterIndex);
                Character nextLetter = data.get(letterIndex + 1);

                boolean polymerFound = polymerCheck(currentLetter, nextLetter);

                if (polymerFound) {
                    data.remove(letterIndex);
                    data.remove(letterIndex);
                }
                if (!polymerFound) {
                    polymers = false;
                }
            }
        }
        return data;
    }

    private static boolean polymerCheck(Character currentLetter, Character nextLetter) {
        boolean polymerFound = false;
        if (Character.isLowerCase(currentLetter)) {
            polymerFound = (nextLetter == Character.toUpperCase(currentLetter));
        } else if (Character.isUpperCase(currentLetter)) {
            polymerFound = (nextLetter == Character.toLowerCase(currentLetter));
        }
        return polymerFound;
    }
}

