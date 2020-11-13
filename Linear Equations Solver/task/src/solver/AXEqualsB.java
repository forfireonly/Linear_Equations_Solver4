package solver;

import java.io.IOException;

public class AXEqualsB extends EquationSolver {
    private double a;
    private double b;

    public void setA(double a) {
        this.a = a;
    }
    public void setB(double b) {
        this.b = b;
    }
    @Override
    public void solve() throws IOException {

        System.out.printf("%.5f ", (double) b / a);

    }

    @Override
    public void readInput() throws IOException {
        String[] input = reader.readLine().split(" ");
        a = Double.parseDouble(input[0]);
        b = Double.parseDouble(input[1]);

    }
}
