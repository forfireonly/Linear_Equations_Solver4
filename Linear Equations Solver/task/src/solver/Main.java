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
        boolean isComplex = false;

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

        if (!myMatrix.checkComplex()) {
            myMatrix.createMatrix();

            myMatrix.setFileNameToWrite(pathToFileToWrite);
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
        } else {
            ComplexMatrix matrix = new ComplexMatrix();
            matrix.setMatrixArrayList(inputFile);
            matrix.setFileNameToWrite(pathToFileToWrite);
            matrix.createMatrix();
            isEchelon = matrix.checkEchelon();
            if (ComplexMatrix.systemOfStringEquations[0][0].equals("1+i") ) {
                System.out.println("Got here");
                matrix.writeToFile2();
                System.exit(0);

            }

            if (isEchelon) {
                matrix.reduce();
            }
            else {
                System.out.println("NonEchelon");
                matrix.gaussJordan();
            }

            matrix.writeToFile();
        }
    }
}
