package LinAlg;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

class Point{
    public double x;
    public double y;
    public Point(double x, double y){this.x = x; this.y = y;}

    public static Point stringToPoint(String pointString){
        System.out.println(pointString);
        pointString = pointString.substring(1, pointString.length()-1);
        Point p = new Point(0,0);
        p.x = Double.valueOf(pointString.split(",")[0]);
        p.y = Double.valueOf(pointString.split(",")[1]);
        return p;
    }
}

public class Polynomial {
    double[] coefficients;


    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static double[] cleanup(double[] orig){
        double[] newArr = new double[orig.length];
        for (int i = 0; i < orig.length; i++){
            newArr[i] = round(orig[i], 2);
        }
        return newArr;
    }

    public Polynomial(int degree){
        coefficients = new double[degree+1];
    }

    public Polynomial(double[] coefficients){
        this.coefficients = cleanup(coefficients);
    }

    public double solveForX(double x){
        double result = 0;
        for (int i = 0; i < coefficients.length; i++){
            result += coefficients[i] * Math.pow(x, coefficients.length-1-i);
        }
        return result;
    }

    public double[] getEveryTerm(double x){
        double[] result = new double[this.coefficients.length];
        for (int i = 0; i < coefficients.length; i++){
            result[i] = coefficients[i] * Math.pow(x, coefficients.length-1-i);
        }
        return result;
    }

    private static String[] superscripts = new String[]{"⁰", "¹", "²", "³", "⁴", "⁵", "⁶", "⁷", "⁸", "⁹"};

    private String powerToString(double power){
        char[] arr = String.valueOf((int)power).toCharArray();
        String result = "";
        for (char i : arr){
            result += superscripts[i-'0'];
        }
        
        return result;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < coefficients.length; i++){
            if (coefficients.length - 1 - i != 0 && coefficients[i] != 0){
                if (((coefficients[i] - (double)((int)coefficients[i]))) < 0.0001d && coefficients[i] != 1) result+= String.valueOf((int)coefficients[i]);
                else if (coefficients[i] != 1) result += String.valueOf(coefficients[i]);
                result += "x" + ((coefficients.length-1-i == 1) ? "" : powerToString(coefficients.length - 1 -i));
            } else {
                if (((coefficients[i] - (double)((int)coefficients[i]))) < 0.0001d) result+= String.valueOf((int)coefficients[i]);
                else result += String.valueOf(coefficients[i]);
            } 
            result += (i == coefficients.length-1) ? "": (coefficients[i+1] < 0) ? "" : "+";
        }
        return result;
    }

    public double[] getFullVectorForm(Point p){
        double[] terms = this.getEveryTerm(p.x);
        double[] newTerms = new double[terms.length+1];
        for (int i = 0; i < terms.length; i++){
            newTerms[i] = terms[i];
        }
        newTerms[newTerms.length-1] = p.y;
        return newTerms;
    }

}
