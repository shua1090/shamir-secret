package LinAlg;

public class Echelon {
    static void inplaceGaussianElimination(Matrix m){
        int counterRow = 0;
        int counterCol = 0;
        while (counterRow < m.rowLength && counterCol < m.colLength){
            double workingNum = m.mat[counterRow][counterCol];

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
    static void inplaceJordanStuff(Matrix m){
        int max = Integer.min(m.rowLength, m.colLength);
        System.out.println(max);
        int counterRow = max-1;
        int counterCol = max-1;
        while (counterRow > 0 && counterCol > 0){
            double workingNum = m.mat[counterRow][counterCol];

            for (int i = 0; i < m.mat[counterRow].length; i++){
                m.mat[counterRow][i] /= workingNum;
            }
            for (int row = counterRow - 1; row >= 0; row++){
                double multiplier = m.mat[row][counterCol] * m.mat[counterRow][counterCol];
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
    public static void main(String[] args) {
        Matrix m = new Matrix(3,4);
        m.setRow(0, new double[]{1,1,2,8});
        m.setRow(1, new double[]{-1, -2,3,1});
        m.setRow(2, new double[]{3, -7,4,10});

        //Algo:
        
        System.out.println(m.toString());
        Echelon.inplaceGaussianElimination(m);
        System.out.println(m.toString());
        Echelon.inplaceJordanStuff(m);
        System.out.println(m.toString());
    }
}
