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

        boolean isFinite = true;

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
        isFinite = myMatrix.createMatrix();
        //isEchelon = myMatrix.checkEchelon();
       // if (isFinite) {
            System.out.println("Start solving the equation.");
            isEchelon = myMatrix.checkEchelon();
            if (isEchelon) {
                myMatrix.reduce();
            }
        /*if (isEchelon) {
            myMatrix.reduce();
         }*/
            //isEchelon = myMatrix.checkEchelonReduced();
            if (isEchelon) {
                myMatrix.writeToFile();
                System.out.println("Here");
            } else {
                System.out.println("Not here");
                isFinite = myMatrix.gaussGordan();
                //if (!isFinite) {
                    myMatrix.reduceForm();
                    myMatrix.writeToFile();
               // } else {
               //     myMatrix.writeToFile2();
               // }

            }
        //} else {
        //    myMatrix.writeToFile3();
       // }
    }
}
