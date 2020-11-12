package solver;

import java.io.IOException;

public class AXEqualsB extends EquationSolver {
    double a = 0;
    double b = 0;
    @Override
    public void solve() throws IOException {
        readInput();
        System.out.println((double) b / a);

    }

    @Override
    public void readInput() throws IOException {
        String[] input = reader.readLine().split(" ");
        a = Double.parseDouble(input[0]);
        b = Double.parseDouble(input[1]);

    }
}
