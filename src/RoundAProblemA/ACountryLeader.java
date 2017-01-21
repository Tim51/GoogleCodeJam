package RoundAProblemA;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ACountryLeader {

    public static String findLeaderFrom(ArrayList<String> names) {
        String currentLeader = names.get(0);
        int currentLeaderLetterCount = numberOfDifferentLettersIn(currentLeader);

        for (int i = 0; i < names.size(); i++) {
            String currentName = names.get(i);
            int currentNameLetterCount = numberOfDifferentLettersIn(currentName);
            if (currentNameLetterCount > currentLeaderLetterCount) {
                currentLeader = currentName;
                currentLeaderLetterCount = currentNameLetterCount;
            } else if (currentNameLetterCount == currentLeaderLetterCount) {
                if (currentLeader.compareTo(currentName) > 0) {
                    currentLeader = currentName;
                }
            }
        }
        return currentLeader;
    }

    private static int numberOfDifferentLettersIn(String name) {
        int numberOfDifferentLetters = 0;
        String letters = "";
        for (int i = 0; i < name.length(); i++) {
            if (letters.indexOf(name.charAt(i)) == -1) {
                letters += name.charAt(i);
                numberOfDifferentLetters++;
            }
        }
        if (letters.contains(" ")) {
            numberOfDifferentLetters--;
        }
        return numberOfDifferentLetters;
    }

    public static void main(String[] args) {
        try {
            File inputFile = new File("src/RoundAProblemA/A-large-practice.in");
            Scanner sc = new Scanner(inputFile.getAbsoluteFile());
            
            File outputFile = new File("src/RoundAProblemA/output.txt");
            outputFile.delete();
            outputFile.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile.getAbsoluteFile()));
            
            int caseNumber = 0;
            int testCases = Integer.parseInt(sc.nextLine());
            for (int testCase = 0; testCase < testCases; testCase++) {
                caseNumber++;
                int numberOfNamesInTestCase = Integer.parseInt(sc.nextLine());
                ArrayList<String> namesInTestCase = new ArrayList<>();
                for (int i = 0; i < numberOfNamesInTestCase; i++) {
                    namesInTestCase.add(sc.nextLine());
                }

                bw.write("Case #" + caseNumber + ": " + ACountryLeader.findLeaderFrom(namesInTestCase));
                bw.newLine();
            }
            bw.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }
}
