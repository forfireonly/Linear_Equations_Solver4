package solver;

import java.io.IOException;

public class SystemOfEquations extends EquationSolver{
    private double a = 0.0;
    private double b = 0.0;
    private double c = 0.0;
    private double d = 0.0;
    private double e = 0.0;
    private double f = 0.0;


    @Override
    public void solve() throws IOException {
        readInput();
        double y = (f - c * d / a) / (e - b * d / a);
        AXEqualsB equation = new AXEqualsB();
        equation.setA(a);
        equation.setB(c - b * y);
        equation.solve();
        System.out.printf("%.5f ", y);


    }

    @Override
    public void readInput() throws IOException {

        String[] coeficientFirstEquation = reader.readLine().split(" ");
        a = Double.parseDouble(coeficientFirstEquation[0]);
        b = Double.parseDouble(coeficientFirstEquation[1]);
        c = Double.parseDouble(coeficientFirstEquation[2]);

        String[] coeficientSecondEquation = reader.readLine().split(" ");
        d = Double.parseDouble(coeficientSecondEquation[0]);
        e = Double.parseDouble(coeficientSecondEquation[1]);
        f = Double.parseDouble(coeficientSecondEquation[2]);
    }
}
