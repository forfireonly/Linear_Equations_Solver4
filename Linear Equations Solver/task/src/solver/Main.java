package solver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String pathToFile = args[1];
        String pathToFileToWrite = args[3];
        File file = new File(pathToFile);
        boolean isEchelon = false;

        ArrayList<String> inputFile = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                inputFile.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + pathToFile);
        }

        System.out.println(inputFile);
        Matrix myMatrix = new Matrix();
        myMatrix.setMatrixArrayList(inputFile);
        myMatrix.setFileNameToWrite(pathToFileToWrite);
        myMatrix.createMatrix();

            System.out.println("Start solving the equation.");
            isEchelon = myMatrix.checkEchelon();
            if (isEchelon) {
                myMatrix.reduce();
            }

            if (isEchelon) {
                myMatrix.writeToFile();
            } else {
                myMatrix.gaussGordan();

                    myMatrix.reduceForm();
                    myMatrix.writeToFile();
            }
    }
}
