package LinAlg;

import java.security.SecureRandom;
import java.util.Arrays;

public class ShamSecHelper {
    public static Point[] generateSecret(int amountOfKeys, int keysNeededToUnlock){
        Point[] points = new Point[amountOfKeys];
        double[] ultimateCoefficients = new double[keysNeededToUnlock];

        SecureRandom sr = new SecureRandom();
        for (int i = 0; i < ultimateCoefficients.length; i++){
            double coeff = sr.nextDouble() * 100;
            ultimateCoefficients[i] = coeff;
        }
        Polynomial p = new Polynomial(ultimateCoefficients);
        System.out.println(p);
        for (int i = 0; i < points.length; i++){
            double x;
            do {
                x = sr.nextDouble() * 10000;
            } while (x == 0);

            points[i] = new Point(x, p.solveForX(x));
        }
        return points;
    }

    public static void main(String[] args) {
        Point[] p = ShamSecHelper.generateSecret(5, 3);
        for (int i = 0; i < p.length; i++){
            System.out.println(p[i].toString() + ",");
        }
        Polynomial poly = new Polynomial(4);
        Matrix m = new Matrix(Matrix.matrixMaker(poly, p) );
        System.out.println(m);
        GuassJordanElim.inplaceSolve(m);
        System.out.println(new Polynomial(m.getColumn(m.colLength-1)));
    }
}
