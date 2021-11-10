package LinAlg;

public class GuassJordanElim {
    public static void inplaceGaussianElimination(Matrix m){
        int counterRow = 0;
        int counterCol = 0;
        while (counterRow < m.rowLength && counterCol < m.colLength){
            double workingNum = m.mat[counterRow][counterCol];
            if (workingNum == 0){
                System.out.println("row/col = " + counterRow+ counterCol);
                System.out.println("m=" + m + workingNum);
                double[] temp1 = m.getRow(counterRow);
                double[] temp2 = m.getRow(counterRow+1);
                m.setRow(counterRow, temp2);
                m.setRow(counterRow+1, temp1);
                System.out.println("m=" + m);

            }
            for (int i = 0; i < m.mat[counterRow].length; i++){
                m.mat[counterRow][i] /= workingNum;
            }
            for (int row = counterRow + 1; row < m.rowLength; row++){
                double multiplier = m.mat[row][counterCol] * m.mat[counterRow][counterCol];
                double[] currentRow = m.getRow(counterRow);
                double[] forwardRow = m.getRow(row);

                for (int i = 0; i < forwardRow.length; i++){
                    forwardRow[i] = forwardRow[i] - multiplier * currentRow[i];
                }
                m.setRow(row, forwardRow);
            }
            counterRow++;
            counterCol++;
        }
    }

    public static void backsub(Matrix m){
        int max = Integer.min(m.rowLength, m.colLength);
        int counterRow = max-1;
        int counterCol = max-1;
        while (counterRow > 0 && counterCol > 0){
            double workingNum = m.mat[counterRow][counterCol];
            if (workingNum == 0){
                double[] temp1 = m.getRow(counterRow);
                double[] temp2 = m.getRow(counterRow-1);
                m.setRow(counterRow, temp2);
                m.setRow(counterRow-1, temp1);
                System.out.println("m=" + m);
            }
            for (int i = 0; i < m.mat[counterRow].length; i++){
                m.mat[counterRow][i] /= workingNum;
            }
            for (int row = counterRow - 1; row >= 0; row--){
                double multiplier = m.mat[row][counterCol];
                multiplier *= m.mat[counterRow][counterCol];
                double[] currentRow = m.getRow(counterRow);
                double[] forwardRow = m.getRow(row);

                for (int i = 0; i < forwardRow.length; i++){
                    forwardRow[i] = forwardRow[i] - multiplier * currentRow[i];
                }
                m.setRow(row, forwardRow);
            }
            counterRow--;
            counterCol--;
        }
    }

    static void inplaceSolve(Matrix m){
        inplaceGaussianElimination(m);
        // System.out.println(m);
        backsub(m);
    }
    public static void main(String[] args) {
      // m.setRow(0, new double[]{1,1,2,8});
        // m.setRow(1, new double[]{-1, -2,3,1});
        // m.setRow(2, new double[]{3, -7,4,10});
        Polynomial p = new Polynomial(new double[]{1, 1, 1,1});
        String pointString = "(-2, -32)#(-1, 15)#(1,43)#(2,144)";
        String[] strings = pointString.split("#");
        Point[] points = new Point[4];
        for (int i = 0; i < points.length; i++){
            points[i] = Point.stringToPoint(strings[i]);
        }
        //{new Point(-0.7, 1.55), new Point( 1, 16 ), new Point(-2,10)};

        Matrix m = new Matrix(Matrix.matrixMaker(p, points));
        System.out.println(m);
        GuassJordanElim.inplaceSolve(m);
        System.out.println(m);
        System.out.println(new Polynomial(m.getColumn(m.colLength-1)));
    }
}
