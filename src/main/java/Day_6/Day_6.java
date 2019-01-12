package Day_6;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Day_6 {
    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(new File("C:/Users/macio/Desktop/advent-of-code/day-6-input.txt").toPath());

        for (String line : input) {
            System.out.println(line);
        }
    }
}
