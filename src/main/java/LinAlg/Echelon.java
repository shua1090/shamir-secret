package LinAlg;

import javax.sound.sampled.SourceDataLine;

public class Echelon {
    public static void main(String[] args) {
        Matrix m = new Matrix(3,4);
        m.setRow(0, new double[]{1,1,2,8});
        m.setRow(1, new double[]{-1, -2,3,1});
        m.setRow(2, new double[]{3, -7,4,10});

        //Algo:
        System.out.println(m.toString());
        System.out.println(m.rowLength + " " + m.colLength);
        int counterRow = 0;
        int counterCol = 0;
        while (counterRow < m.rowLength && counterCol < m.colLength){
            double workingNum = m.mat[counterRow][counterCol];
            System.out.println(m.toString());

            for (int i = 0; i < m.mat[counterRow].length; i++){
                m.mat[counterRow][i] /= workingNum;
            }

            System.out.println("After: " + m.toString());

            for (int row = counterRow + 1; row < m.rowLength; row++){
                double multiplier = m.mat[row][counterCol] * m.mat[counterRow][counterCol];
                System.out.println("Multiplier: " + multiplier + "-" + m.mat[row][counterCol] + "-" + workingNum);
                double[] currentRow = m.getRow(counterRow);
                double[] forwardRow = m.getRow(row);

                for (int i = 0; i < forwardRow.length; i++){
                    forwardRow[i] = forwardRow[i] - multiplier * currentRow[i];
                }
                m.setRow(row, forwardRow);
                // System.out.println(m.toString());
            }
            System.out.println("Finished 1 Revolution ==");

            counterRow++;
            counterCol++;
        }
        System.out.println(m.toString());
    }
}
