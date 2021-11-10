package LinAlg;

class Matrix{
    double[][] mat;
    int rowLength;
    int colLength;

    public Matrix(int rowCount, int colCount){
        mat = new double[rowCount][colCount];
        this.rowLength = rowCount;
        this.colLength = colCount;
    }

    public Matrix(double[][] matrix){
        this.mat = matrix;
        this.rowLength = matrix.length;
        this.colLength = matrix[0].length;
    }

    public static double[][] matrixMaker(Polynomial p, Point[] points){
        double[][] newMatrix = new double[points.length][];
        for (int i = 0; i < points.length; i++){
            newMatrix[i] = p.getFullVectorForm(points[i]);
        }
        return newMatrix;
    }

    public double[] getRow(int rowIndex){
        return mat[rowIndex];
    }

    public double[] getColumn(int colIndex){
        double[] columnI = new double[mat.length];
        for (int i = 0; i < mat.length; i++){
            columnI[i] = mat[i][colIndex];
        }
        return columnI;
    }

    @Override
    public String toString() {
        String s = "";
        s += "[";
        for (int i = 0; i < mat.length; i++){
            if (i >= 1) s += " ";
            s += "[";
            for (int j = 0; j < mat[i].length; j++){
                s += mat[i][j] + ", ";
            }
            if (i != mat.length - 1)
                s+="],\n";
            else s+= "]";
        }
        s += "]";
        return s;
    }

    public void setRow (int rowIndex, double[] rowToSet){
        mat[rowIndex] = rowToSet;
    }

    public void setColumn(int colIndex, double[] colToSet){
        for (int i = 0; i < mat.length; i++){
            if (i > colToSet.length)
                mat[i][colIndex] = 0;
            else            mat[i][colIndex] = colToSet[i];
        }
    }

}