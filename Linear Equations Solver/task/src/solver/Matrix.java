package solver;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Matrix {
    ArrayList<String> matrixArrayList;
    float[][] systemOfEquations;
    int numberOfEquations = 0;

    private String fileNameToWrite;

    public void setFileNameToWrite(String fileNameToWrite) {
        this.fileNameToWrite = fileNameToWrite;
    }


    public void setMatrixArrayList(ArrayList<String> matrixArrayList) {
        this.matrixArrayList = matrixArrayList;
    }

    public void createMatrix() {
        numberOfEquations = Integer.parseInt(matrixArrayList.get(0));
        systemOfEquations = new float[numberOfEquations][numberOfEquations + 1];
        float[][] input = new float[numberOfEquations][numberOfEquations + 1];
        for (int i = 0; i < numberOfEquations; i++) {
            String[] row = matrixArrayList.get(i + 1).split(" ");
            for (int j = 0; j <= numberOfEquations; j++) {
                systemOfEquations[i][j] = Float.parseFloat(row[j]);
            }
        }

        for (int i = 0; i < numberOfEquations; i++) {
            for (int j = 0; j < numberOfEquations + 1; j++) {
                System.out.print(systemOfEquations[i][j]+ " ");
            }
            System.out.println();
        }

    }

    public boolean checkEchelon() {
        boolean isEchelon = true;
        for (int i = 1; i < numberOfEquations; i++) {
            if (systemOfEquations[0][0] != systemOfEquations[i][i]) {
                isEchelon = false;
                break;
            }
        }
        return isEchelon;
    }

    public void reduce() {
        float multiplier =  1 / systemOfEquations[0][0];
        for (int i = 0; i < numberOfEquations; i++) {
            systemOfEquations[i][i] = systemOfEquations[i][i] * multiplier;
        }
    }

    public boolean checkEchelonReduced () {
        boolean isEchelon = true;
        for (int i = 0; i < numberOfEquations; i++) {
            if (systemOfEquations[i][i] != 1.0) {
                isEchelon = false;
                break;
            }
        }
        return isEchelon;
    }

    public void gaussGordan() {
        float multiplier = 0.0f;
        float normalizer = 0.0f;
        System.out.println("Start solving the equation.");
        int i = 0;
        int j = 1;
        do {
            while (j < numberOfEquations || i == numberOfEquations - 1) {
                if (systemOfEquations[i][i] == 1.0 && systemOfEquations[i + 1][i + 1] != 1.0) {
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
            j = i + 2;
            i = i + 1;
        } while (i < numberOfEquations);

        /*for (int ii = 0; ii < numberOfEquations; ii++) {
            for (int jj = 0; jj < numberOfEquations + 1; jj++) {
                System.out.print(systemOfEquations[ii][jj] + " ");
            }
            System.out.println();
        }*/
    }

    public void reduceForm() {
        float multiplier = 0.0f;
        int i = numberOfEquations - 1;
        int j = numberOfEquations - 2;
        do {
            while (j >= 0 && i >= 0) {
                    multiplier = -systemOfEquations[j][i];
                    for (int z = 0; z < numberOfEquations + 1; z++) {

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
    }

    public void writeToFile() {
        File file = new File(fileNameToWrite);
        try (PrintWriter printWriter = new PrintWriter(file)) {
            for (int iii = 0; iii < numberOfEquations; iii++) {
                printWriter.print(systemOfEquations[iii][numberOfEquations] + "\n");
            }
        } catch (IOException e) {
            System.out.printf("An exception occurs %s", e.getMessage());
        }
    }
}
