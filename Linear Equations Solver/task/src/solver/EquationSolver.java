package solver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class EquationSolver {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public abstract void solve() throws IOException;
    public abstract void readInput() throws IOException;

}
