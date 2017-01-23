package RoundAProblemB;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Rain {

    public static int getTotalHeightIncrease(int[][] map) {
        int totalHeightIncrease = 0;
        int[][] floodedMap = getFloodedMap(map);
        for (int i=0; i<map.length; i++) {
            for (int j=0; j<map[0].length; j++) {
                totalHeightIncrease+= (floodedMap[i][j] - map[i][j]);
            }
        }
        return totalHeightIncrease;
    }
    
    public static int[][] getFloodedMap(int[][] map) {
        if (map.length <= 2 || map[0].length <= 2) {
            return map;
        }

        int[][] floodedMap = new int[map.length][map[0].length];
        int maxTerrainHeight = 0;

        //find maxTerrainHeight
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > maxTerrainHeight) {
                    maxTerrainHeight = map[i][j];
                }
            }
        }

        //create map boundaries
        for (int i = 0; i < map.length; i++) {
            floodedMap[i][0] = map[i][0];
            floodedMap[i][map[0].length - 1] = map[i][map[0].length - 1];
        }
        for (int j = 1; j < map[0].length - 1; j++) {
            floodedMap[0][j] = map[0][j];
            floodedMap[map.length-1][j] = map[map.length-1][j];
        }

        //fill inner area with water 
        for (int i = 1; i < map.length - 1; i++) {
            for (int j = 1; j < map[0].length - 1; j++) {
                floodedMap[i][j] = maxTerrainHeight;
            }
        }
        //drain water
        while (true) {
            boolean waterLevelChanged = false;
            for (int i = 1; i < map.length - 1; i++) {
                for (int j = 1; j < map[0].length - 1; j++) {
                    if (floodedMap[i][j] == map[i][j]) {
                        continue;
                    }
                    if (floodedMap[i - 1][j] < floodedMap[i][j]) {
                        floodedMap[i][j] = Math.max(floodedMap[i - 1][j], map[i][j]);
                        waterLevelChanged = true;
                    }
                    if (floodedMap[i + 1][j] < floodedMap[i][j]) {
                        floodedMap[i][j] = Math.max(floodedMap[i + 1][j], map[i][j]);
                        waterLevelChanged = true;
                    }
                    if (floodedMap[i][j - 1] < floodedMap[i][j]) {
                        floodedMap[i][j] = Math.max(floodedMap[i][j-1], map[i][j]);
                        waterLevelChanged = true;
                    }
                    if (floodedMap[i][j + 1] < floodedMap[i][j]) {
                        floodedMap[i][j] = Math.max(floodedMap[i][j+1], map[i][j]);
                        waterLevelChanged = true;
                    }
                }
            }
            if (!waterLevelChanged) {
                break;
            }
        }

        for (int i = 0; i < floodedMap.length; i++) {
            for (int j = 0; j < floodedMap[0].length; j++) {
                System.out.print(floodedMap[i][j]);
                System.out.print(" ");
            }
            System.out.println("");
        }

        return floodedMap;
    }

    public static void main(String[] args) {
        try {
            File inputFile = new File("src/RoundAProblemB/B-large-practice.in");
            Scanner sc = new Scanner(inputFile.getAbsoluteFile());
            
            File outputFile = new File("src/RoundAProblemB/output.txt");
            outputFile.delete();
            outputFile.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile.getAbsoluteFile()));

            int caseNumber = 0;
            int testCases = Integer.parseInt(sc.nextLine());
            
            for (int testCase = 0; testCase < testCases; testCase++) {
                caseNumber++;
                int columns = sc.nextInt();
                int rows = sc.nextInt();
                int[][] map = new int[columns][rows];
                for (int i=0; i<columns; i++) {
                    for (int j=0; j<rows; j++) {
                        map[i][j] = sc.nextInt();
                    }
                }

                bw.write("Case #" + caseNumber + ": " + Rain.getTotalHeightIncrease(map));
                bw.newLine();
            }
            bw.close();
            
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }
}
