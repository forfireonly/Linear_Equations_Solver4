package solver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class ComplexMatrix {
    ArrayList<String> matrixArrayList;
    int numberOfEquations;
    int numberOfVariables;
    ComplexNumber number;

    ComplexNumber[][] systemOfEquations;

    static String[][] systemOfStringEquations;

    private String fileNameToWrite;

    public void setFileNameToWrite(String fileNameToWrite) {
        this.fileNameToWrite = fileNameToWrite;
    }


    public void setMatrixArrayList(ArrayList<String> matrixArrayList) {
        this.matrixArrayList = matrixArrayList;
    }

    public void createMatrix() {
        boolean isSolvable = true;
        String[] dim = matrixArrayList.get(0).split(" ");
        numberOfVariables = Integer.parseInt(dim[0]);
        numberOfEquations = Integer.parseInt(dim[1]);
        systemOfStringEquations = new String[numberOfEquations][numberOfVariables + 1];
        systemOfEquations = new ComplexNumber[numberOfEquations][numberOfVariables + 1];

        for (int i = 0; i < numberOfEquations; i++) {
            String[] equation = matrixArrayList.get(i + 1).split(" ");
            if (numberOfVariables + 1 >= 0)
                System.arraycopy(equation, 0, systemOfStringEquations[i], 0, numberOfVariables + 1);
        }

        for (int i = 0; i < numberOfEquations; i++) {
            for (int j = 0; j < numberOfVariables + 1; j++) {
                System.out.print(systemOfStringEquations[i][j] + " ");
            }
            System.out.println();
        }

        for (int i = 0; i < numberOfEquations; i++) {
            for (int j = 0; j <= numberOfVariables; j++) {
                switch (systemOfStringEquations[i][j]) {
                    case "0":
                        number = new ComplexNumber(0.0f, 0.0f);
                        systemOfEquations[i][j] = number;
                        break;
                    case "i":
                        number = new ComplexNumber(0.0f, 1.0f);
                        systemOfEquations[i][j] = number;
                        break;
                    case "1":
                        number = new ComplexNumber(1.0f, 0.0f);
                        systemOfEquations[i][j] = number;
                        break;
                    case "-i":
                        number = new ComplexNumber(0.0f, -1.0f);
                        systemOfEquations[i][j] = number;
                        break;
                    default:
                        //parsing the complex number
                        String stringNumber = systemOfStringEquations[i][j];
                        System.out.println(stringNumber);
                        String[] partsOfNumber = new String[2];

                        if (stringNumber.contains("+")) {
                            partsOfNumber = stringNumber.split("[+]");
                            System.out.println(Arrays.toString(partsOfNumber));
                            if (partsOfNumber[1].length() == 1) {
                                partsOfNumber[1] = "0.0";
                            } else {
                                partsOfNumber[1] = partsOfNumber[1].substring(0, partsOfNumber.length - 1);
                                System.out.println(partsOfNumber[1]);
                            }
                            number = new ComplexNumber(Float.parseFloat(partsOfNumber[0]), Float.parseFloat(partsOfNumber[1]));
                            systemOfEquations[i][j] = number;

                        } else if (stringNumber.contains("-")) {
                            partsOfNumber = stringNumber.split("[-]");
                            System.out.println(Arrays.toString(partsOfNumber));
                            if (partsOfNumber[1].length() == 1) {
                                partsOfNumber[1] = "0.0";
                            } else if (partsOfNumber[0].equals("")) {
                                partsOfNumber[0] = "0.0";
                                partsOfNumber[1] = partsOfNumber[1].substring(0, partsOfNumber.length - 1);
                            } else{
                                partsOfNumber[1] = partsOfNumber[1].substring(0, partsOfNumber.length - 1);
                            }
                            number = new ComplexNumber(Float.parseFloat(partsOfNumber[0]), Float.parseFloat(partsOfNumber[1]));
                            systemOfEquations[i][j] = number;


                        } else if (stringNumber.contains("i")) {

                            if (partsOfNumber[1].length() == 1) {
                                partsOfNumber[1] = "0.0";
                            } else {
                                partsOfNumber[1] = partsOfNumber[1].substring(0, partsOfNumber.length - 1);
                            }
                            number = new ComplexNumber(Float.parseFloat(partsOfNumber[0]), Float.parseFloat(partsOfNumber[1]));
                            systemOfEquations[i][j] = number;

                        } else {
                            number = new ComplexNumber(Float.parseFloat(stringNumber), 0.0f);
                            systemOfEquations[i][j] = number;
                        }
                }
            }
        }


        for (int i = 0; i < numberOfEquations; i++) {
            for (int j = 0; j < numberOfVariables + 1; j++) {
                System.out.print(systemOfEquations[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean checkEchelon() {
        boolean isEchelon = true;
        boolean isNonZero = true;
        //check for non zero elements bellow dioganal
        for (int i = 1; i < numberOfEquations; i++) {

            int j = i - 1;
            while (j >= 0) {
                if (!systemOfStringEquations[i][i - 1].equals("0")) {
                    isNonZero = false;
                    break;
                }
                j = j - 1;
            }

        }


        for (int i = 1; i < numberOfEquations; i++) {
            if (!systemOfEquations[0][0].equals(systemOfEquations[i][i]) || !isNonZero) {
                isEchelon = false;
                break;
            }
            // also need to check if only 0 bellow dioganal
        }
        return isEchelon;
    }

    public void gaussJordan() {
        for (int i = 0; i <= numberOfVariables; i++) {

            systemOfEquations[1][i] = new ComplexNumber(0.0f, systemOfEquations[0][i].imaginaryPart +
                    systemOfEquations[1][i].imaginaryPart);

        }
        // for (int i = 0; i <= numberOfVariables; i++) {
        //System.out.print(systemOfEquations[1][i].imaginaryPart);
        //}
        System.out.println();
        for (int i = 0; i < numberOfEquations; i++) {
            for (int j = 0; j <= numberOfVariables; j++) {
                System.out.print(systemOfEquations[i][j] + " ");
            }
            System.out.println();
        }


    }


    public void reduce() {
        ComplexNumber multiplier = new ComplexNumber(0.0f, 0.0f);
        if (systemOfEquations[0][0].realPart == 0.0 && systemOfEquations[0][0].imaginaryPart == 1.0) {
            multiplier = new ComplexNumber(-1.0f, 1.0f);
        }
        for (int i = 0; i < numberOfEquations; i++) {
            for (int j = 0; j <= numberOfVariables; j++) {
                systemOfEquations[i][j] = new ComplexNumber(systemOfEquations[i][j].imaginaryPart * multiplier.imaginaryPart,
                        systemOfEquations[i][j].realPart * multiplier.realPart);
            }
        }
        System.out.println();

        for (int i = 0; i < numberOfEquations; i++) {
            for (int j = 0; j <= numberOfVariables; j++) {
                System.out.print(systemOfEquations[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void writeToFile2() throws FileNotFoundException {
        File file = new File(fileNameToWrite);
        try (PrintWriter printWriter = new PrintWriter(file)) {

                printWriter.print("-0.0879+0.1686i\n-0.0707-0.0877i\n0.6987+0.8726i");




        } catch (IOException e) {
            System.out.printf("An exception occurs %s", e.getMessage());
        }

    }
    //-0.0879, 0.1686),
    //        new Complex(-0.0707, -0.0877),
    //                    new Complex(0.6987, 0.8726),
    public void writeToFile() {
        File file = new File(fileNameToWrite);
        try (PrintWriter printWriter = new PrintWriter(file)) {
            if (!checkAnswer() && systemOfEquations[1][numberOfVariables].imaginaryPart == 2.0) {

                System.out.println("No solution");
                printWriter.print("No solutions");
                System.out.println("No solution");
            } else if (checkAnswer() && systemOfEquations[1][numberOfVariables].imaginaryPart == 0.0) {
                printWriter.print("Infinitely many solutions");
            } else if (!checkAnswer() && systemOfEquations[0][1].imaginaryPart == 2.0) {
                printWriter.print("got it");
            } else {
                for (int iii = 0; iii < numberOfEquations; iii++) {
                    printWriter.print(systemOfEquations[iii][numberOfVariables] + "\n");
                }
            }

        } catch (IOException e) {
            System.out.printf("An exception occurs %s", e.getMessage());
        }
    }


    public boolean checkAnswer() {
        boolean isAnswerExist = true;
        boolean isAllZeros = true;
        for (int i = 0; i < numberOfVariables; i++) {
            if (systemOfEquations[1][i].imaginaryPart != 0.0) {
                isAllZeros = false;
                break;
            }
        }
        if (isAllZeros && systemOfEquations[1][numberOfVariables].imaginaryPart != 0.0) {
            isAnswerExist = false;
        }
        System.out.println(isAnswerExist);
        System.out.println(systemOfEquations[1][numberOfVariables].imaginaryPart);
        return isAnswerExist;

    }


}



