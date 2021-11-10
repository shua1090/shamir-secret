package LinAlg;

import java.security.SecureRandom;
import java.util.Arrays;

class PointSet{
    Point[] generatedPoints; 
    Point answer;
    PointSet(Point[] generatedPoints, Point answer){
        this.generatedPoints = generatedPoints;
        this.answer = answer;
    }
}

public class PolynomialGen {
    public static PointSet generateSecret(int amountOfKeys, int keysNeededToUnlock){
        Point[] points = new Point[amountOfKeys];
        double[] ultimateCoefficients = new double[keysNeededToUnlock];

        SecureRandom sr = new SecureRandom();
        for (int i = 0; i < ultimateCoefficients.length; i++){
            double coeff = sr.nextDouble() * 100;
            ultimateCoefficients[i] = coeff;
        }
        Polynomial p = new Polynomial(ultimateCoefficients);
        for (int i = 0; i < points.length; i++){
            double x;
            do {
                x = sr.nextDouble() * 10000;
            } while (x == 0);

            points[i] = new Point(x, p.solveForX(x));
        }
        return new PointSet(points, new Point(0, p.solveForX(0)));
    }

    public static void main(String[] args) {
        long correctCount = 0;
        System.out.println("=".repeat(5) + " BEGIN TESTING " + "=".repeat(5));
        for (int i = 0; i < 1000; i++){
            long t = System.currentTimeMillis();
            int inputSecretCount = 4;
            PointSet p = PolynomialGen.generateSecret(inputSecretCount, 4);

            Polynomial poly = new Polynomial(inputSecretCount-1);
            Matrix m = new Matrix(Matrix.matrixMaker(poly, p.generatedPoints) );
            // System.out.println(m);
            GuassJordanElim.inplaceSolve(m);
            Polynomial solvedPoly = new Polynomial(m.getColumn(m.colLength-1));
            if (solvedPoly.solveForX(0) == p.answer.y){
                System.out.println(i+1 + ") " + solvedPoly.solveForX(0) + "=" + p.answer.y);
                correctCount++;
            }else {
                System.out.println("err: " + solvedPoly.solveForX(0) + "!=" + p.answer.y);
            }
        }
        System.out.println("=".repeat(5) + " TESTING END " + "=".repeat(5));
        System.out.println(correctCount + "/" + 1000 + " correct results");
    }
}
