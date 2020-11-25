package solver;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Matrix {
    ArrayList<String> matrixArrayList;
    float[][] systemOfEquations;
    int numberOfVariables = 0;
    int numberOfEquations = 0;
    int index = 0;
    ArrayList<String> nonZeroMatrixArrayList = new ArrayList<>();
    boolean isInfinite = false;

    boolean isHasSolution = true;

    private String fileNameToWrite;

    public void setFileNameToWrite(String fileNameToWrite) {
        this.fileNameToWrite = fileNameToWrite;
    }


    public void setMatrixArrayList(ArrayList<String> matrixArrayList) {
        this.matrixArrayList = matrixArrayList;
    }

    public boolean createMatrix() {
        //numberOfEquations = Integer.parseInt(matrixArrayList.get(0));
        boolean isSolvable = true;
        int solvableIndex = 1;
        String[] dim = matrixArrayList.get(0).split(" ");
        numberOfVariables = Integer.parseInt(dim[0]);
        numberOfEquations = Integer.parseInt(dim[1]);
        int reduceNumberOfEquations = 0;
        float[][] input = new float[numberOfEquations][numberOfVariables];
        for (int i = 1; i <= numberOfEquations; i++) {
            System.out.println(matrixArrayList.get(i));
            String[] row = matrixArrayList.get(i).split(" ");
            if (checkZeroRow(row)) {
                reduceNumberOfEquations = reduceNumberOfEquations + 1;
            } else {
                nonZeroMatrixArrayList.add(matrixArrayList.get(i));
            }
        }

        numberOfEquations = numberOfEquations - reduceNumberOfEquations;
        systemOfEquations = new float[numberOfEquations][numberOfVariables + 1];

        System.out.println(nonZeroMatrixArrayList);

        for (int i = 0; i < numberOfEquations; i++) {
            String[] row = nonZeroMatrixArrayList.get(i).split(" ");
            for (int j = 0; j <= numberOfVariables; j++) {
                systemOfEquations[i][j] = Float.parseFloat(row[j]);
            }
        }
        for (int i = 0; i < numberOfEquations; i++) {
            for (int j = 0; j < numberOfVariables + 1; j++) {
                System.out.print(systemOfEquations[i][j] + " ");
            }
            System.out.println();
        }
        if (numberOfEquations < numberOfVariables) {
            isSolvable = false;
            solvableIndex = 2;
        }
        return isSolvable;
    }

    public boolean checkZeroRow(String[] row) {
        boolean isZeroRow = true;
        for (String s : row) {
            if (!s.equals("0")) {
                isZeroRow = false;
                break;
            }
        }
        return isZeroRow;
    }


    public boolean checkEchelon() {
        boolean isEchelon = true;
        for (int i = 1; i < numberOfEquations; i++) {
            if (systemOfEquations[0][0] != systemOfEquations[i][i] || systemOfEquations[0][0] == 0) {
                isEchelon = false;
                break;
            }
        }
        return isEchelon;
    }

    public void reduce() {
        float multiplier = 1 / systemOfEquations[0][0];
        for (int i = 0; i < numberOfEquations; i++) {
            systemOfEquations[i][i] = systemOfEquations[i][i] * multiplier;
            systemOfEquations[i][numberOfVariables] = systemOfEquations[i][numberOfVariables] * multiplier;
        }
    }

    public void swipeRows() {
        int column = 0;
        int nonZeroRow = 0;

        int zeroRow = 0;

        for (int i = 0; i < numberOfEquations; i++) {
            // if (systemOfEquations[i][column] == 0) {
            while (systemOfEquations[nonZeroRow][column] == 0 && nonZeroRow + 1 < numberOfEquations) {
                nonZeroRow = nonZeroRow + 1;
                if (systemOfEquations[nonZeroRow][column] != 0) {
                    break;
                }
            }

            for (int j = 0; j < numberOfVariables + 1; j++) {
                float temp = systemOfEquations[i][j];
                systemOfEquations[i][j] = systemOfEquations[nonZeroRow][j];
                systemOfEquations[nonZeroRow][j] = temp;
            }

            System.out.println("R" + (zeroRow + 1) + "<->" + " R" + (nonZeroRow + 1));
            // }
        }
        for (int i = 0; i < numberOfEquations; i++) {
            for (int j = 0; j < numberOfVariables + 1; j++) {
                System.out.print(systemOfEquations[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();

    }

    public void swipeAllRows() {
        int column = 0;

        int row = 0;

        boolean isZero = false;
        int zeroRow = 0;

        boolean isNonZero = false;
        int nonZeroRow = 0;
        System.out.println("Swiping");

        for (int i = column; i < numberOfEquations; i++) {
            if (systemOfEquations[i][i] == 0) {
                int trackerIRow = i;
                while (systemOfEquations[trackerIRow][i] == 0 & trackerIRow + 1 < numberOfEquations) {
                    trackerIRow = trackerIRow + 1;
                    if (systemOfEquations[trackerIRow][i] != 0) {
                        float normalizer = 1 / systemOfEquations[trackerIRow][i];
                        for (int j = 0; j < numberOfVariables + 1; j++) {
                            float temp = systemOfEquations[i][j];
                            systemOfEquations[i][j] = systemOfEquations[trackerIRow][j] * normalizer;
                            systemOfEquations[trackerIRow][j] = temp;
                        }
                        index = i;
                        System.out.println(index);
                    }
                }
            }

        }

        /*for (int i = 0; i < numberOfEquations; i++) {
            // if (systemOfEquations[i][column] == 0) {
            while (systemOfEquations[nonZeroRow][column] == 0 && nonZeroRow + 1 < numberOfEquations) {
                nonZeroRow = nonZeroRow + 1;
                if (systemOfEquations[nonZeroRow][column] != 0) {
                    break;
                }
            }

            for (int j = 0; j < numberOfVariables + 1; j++) {
                float temp = systemOfEquations[i][j];
                systemOfEquations[i][j] = systemOfEquations[nonZeroRow][j];
                systemOfEquations[nonZeroRow][j] = temp;
            }

            System.out.println("R" + (zeroRow + 1) + "<->" + " R" + (nonZeroRow + 1));
            // }
        }*/
        for (int i = 0; i < numberOfEquations; i++) {
            for (int j = 0; j < numberOfVariables + 1; j++) {
                System.out.print(systemOfEquations[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();

    }

    public boolean checkEchelonReduced() {
        boolean isEchelon = true;
        for (int i = 0; i < numberOfEquations; i++) {
            if (systemOfEquations[i][i] != 1.0) {
                isEchelon = false;
                break;
            }
        }
        return isEchelon;
    }

    public void swipeRows(int zeroRow) {
        int nonZeroRow = 0;
        for (int i = zeroRow + 1; i < numberOfEquations; i++) {
            if (systemOfEquations[i][zeroRow] != 0) {
                nonZeroRow = i;
                System.out.println(nonZeroRow);
                break;
            }
        }

        for (int j = 0; j < numberOfVariables + 1; j++) {
            float temp = systemOfEquations[zeroRow][j];
            systemOfEquations[zeroRow][j] = systemOfEquations[nonZeroRow][j];
            systemOfEquations[nonZeroRow][j] = temp;
        }

        System.out.println("R" + (zeroRow + 1) + "<->" + " R" + (nonZeroRow + 1));
        // }

        for (int i = 0; i < numberOfEquations; i++) {
            for (int j = 0; j < numberOfVariables + 1; j++) {
                System.out.print(systemOfEquations[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();

    }

    public boolean checkEchelonI() {
        boolean isEchelon = true;
        for (int i = 1; i < numberOfEquations; i++) {
            if (systemOfEquations[0][0] != systemOfEquations[i][i] || systemOfEquations[0][0] == 0) {
                isEchelon = false;
                index = i;
                break;
            }
        }
        return isEchelon;
    }

    public boolean gaussGordan() {
        float multiplier = 0.0f;
        float normalizer = 0.0f;
        boolean isEchelon = false;

        // for (int i = 0; i < numberOfVariables; i++) {

       /* if (systemOfEquations[0][0] == 0 || !isEchelon) {
            swipeAllRows();
        }*/
        //}

        isEchelon = checkEchelon();
        //System.out.println("Start solving the equation.");
        if (systemOfEquations[0][0] == 0 || !isEchelon) {
            swipeAllRows();
        }

        //isEchelon = checkEchelonI();
        isEchelon = checkEchelon();

        System.out.println(index);
        int i = 0;
        int j = i + 1;

        if (!isEchelon) {
            do {
                System.out.println("1");
                while (j < numberOfEquations || i == numberOfEquations - 1) {
                    //System.out.println("2");
                    if (systemOfEquations[i][i] == 1.0) {
                        System.out.println("2");
                        multiplier = -systemOfEquations[j][i];
                        int numberOfZero = 0;
                        for (int z = 0; z < numberOfVariables + 1; z++) {
                            systemOfEquations[j][z] =
                                    systemOfEquations[i][z] * multiplier +
                                            systemOfEquations[j][z];
                            if (systemOfEquations[j][z] == 0.0) {
                                numberOfZero = numberOfZero + 1;
                            }

                        }
                        if (numberOfZero == numberOfVariables + 1) {
                            isInfinite = true;

                        }
                        if (numberOfZero == numberOfVariables &&
                                systemOfEquations[numberOfEquations - 1][numberOfVariables] != 0) {
                            isHasSolution = false;
                        }
                        System.out.println(multiplier + " * " + "R" + (i + 1) + " + " +
                                "R" + (j + 1) + " -> " + "R" + (j + 1));
                        j = j + 1;


                    } else if (systemOfEquations[i][i] != 1.0) {
                        System.out.println("3");
                        // if (systemOfEquations[i][i] == 0) {
                        //     swipeRows(i);
                        // }
                        normalizer = 1 / systemOfEquations[i][i];
                        for (int w = 0; w < numberOfVariables + 1; w++) {
                            systemOfEquations[i][w] = systemOfEquations[i][w] * normalizer;
                        }
                        System.out.println(normalizer + " * " + "R" + (i + 1) + " -> " + "R" +
                                (i + 1));
                        if (i == numberOfEquations - 1) {
                            break;
                        }
                    } else {
                        int indexKeeper = i;
                        i = index + 1;
                        normalizer = 1 / systemOfEquations[i][i];
                        for (int w = 0; w < numberOfEquations + 1; w++) {
                            systemOfEquations[i][w] = systemOfEquations[i][w] * normalizer;
                        }
                        System.out.println(normalizer + " * " + "R" + (i + 1) + " -> " + "R" +
                                (i + 1));
                        if (i == numberOfEquations - 1) {
                            break;
                        }

                        index = index + 1;
                        i = indexKeeper;
                    }
                }
                //j = j + 1;
                j = i + 2;
                i = i + 1;

                System.out.println("4");

            } while (i < numberOfEquations);
        }


        for (int ii = 0; ii < numberOfEquations; ii++) {
            for (int jj = 0; jj < numberOfVariables + 1; jj++) {
                System.out.print(systemOfEquations[ii][jj] + " ");
            }
            System.out.println();
        }
        return isInfinite;
    }

    /*public void gaussGordan() {
        float multiplier = 0.0f;
        float normalizer = 0.0f;
        boolean isEchelon = false;

        // for (int i = 0; i < numberOfVariables; i++) {

        if (systemOfEquations[0][0] == 0) {
            swipeRows();
        }
        //}

        isEchelon = checkEchelon();
        //System.out.println("Start solving the equation.");
        int i = 0;
        int j = 1;

        if (!isEchelon) {
            do {
                System.out.println("1");
                while (j < numberOfEquations || i == numberOfEquations - 1) {
                    //System.out.println("2");
                    if (systemOfEquations[i][i] == 1.0 && systemOfEquations[i + 1][i + 1] != 1.0) {
                       // System.out.println("2");
                        multiplier = -systemOfEquations[j][i];
                        for (int z = 0; z < numberOfEquations + 1; z++) {

                            systemOfEquations[j][z] =
                                    systemOfEquations[i][z] * multiplier +
                                            systemOfEquations[j][z];


                        }
                        System.out.println(multiplier + " * " + "R" + (i + 1) + " + " +
                                "R" + (j + 1) + " -> " + "R" + (j + 1));
                        j = j + 1;


                    } else if (systemOfEquations[i][i] != 1.0) {
                        System.out.println("3");
                       // if (systemOfEquations[i][i] == 0) {
                       //     swipeRows(i);
                       // }
                        normalizer = 1 / systemOfEquations[i][i];
                        for (int w = 0; w < numberOfEquations + 1; w++) {
                            systemOfEquations[i][w] = systemOfEquations[i][w] * normalizer;
                        }
                        System.out.println(normalizer + " * " + "R" + (i + 1) + " -> " + "R" +
                                (i + 1));
                        if (i == numberOfEquations - 1) {
                            break;
                        }
                    }
                }
                //j = j + 1;
                j = i + 2;
                i = i + 1;


            } while (i < numberOfEquations);
        }


        for (int ii = 0; ii < numberOfEquations; ii++) {
            for (int jj = 0; jj < numberOfEquations + 1; jj++) {
                System.out.print(systemOfEquations[ii][jj] + " ");
            }
            System.out.println();
        }
    }*/

    public void reduceForm() {
        float multiplier = 0.0f;
        int i = numberOfEquations - 1;
        int j = numberOfEquations - 2;
        do {
            while (j >= 0 && i >= 0) {
                multiplier = -systemOfEquations[j][i];
                for (int z = 0; z < numberOfVariables + 1; z++) {

                    systemOfEquations[j][z] =
                            systemOfEquations[i][z] * multiplier +
                                    systemOfEquations[j][z];


                }

                System.out.println(multiplier + " * " + "R" + (i + 1) + " + " +
                        "R" + (j + 1) + " -> " + "R" + (j + 1));
                j = j - 1;
            }
            j = i - 2;
            i = i - 1;
        } while (i >= 0);

        for (int ii = 0; ii < numberOfEquations; ii++) {
            for (int jj = 0; jj < numberOfVariables + 1; jj++) {
                System.out.print(systemOfEquations[ii][jj] + " ");
            }
            System.out.println();
        }
    }

    public boolean checkAnswer() {
        boolean isAnswerExist = false;

        //if (systemOfEquations[numberOfEquations - 1] [numberOfVariables] != 0.0) {
        //   System.out.println(systemOfEquations[numberOfEquations - 1] [numberOfVariables]);
        for (int i = 0; i < numberOfVariables; i++) {
            if (systemOfEquations[numberOfEquations - 1][i] != 0.0) {
                isAnswerExist = true;
                System.out.println(systemOfEquations[numberOfEquations - 1][i]);
                break;
            }
        }
        // }

        return isAnswerExist;
    }


    public void writeToFile() {
        File file = new File(fileNameToWrite);
        try (PrintWriter printWriter = new PrintWriter(file)) {
            if (!checkAnswer() || !isHasSolution) {
                printWriter.print("No solutions");
            } else if (isInfinite  || numberOfEquations < numberOfVariables) {
                printWriter.print("Infinitely many solutions");
            } else {
                for (int iii = 0; iii < numberOfEquations; iii++) {
                    printWriter.print(systemOfEquations[iii][numberOfVariables] + "\n");
                }
            }
        } catch (IOException e) {
            System.out.printf("An exception occurs %s", e.getMessage());
        }
    }

    public void writeToFile2() {
        File file = new File(fileNameToWrite);
        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.print("Infinitely many solutions");
        } catch (IOException e) {
            System.out.printf("An exception occurs %s", e.getMessage());
        }
    }

    public void writeToFile3() {
        File file = new File(fileNameToWrite);
        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.print("No solutions");
        } catch (IOException e) {
            System.out.printf("An exception occurs %s", e.getMessage());
        }
    }
}
