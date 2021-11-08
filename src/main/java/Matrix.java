class Matrix{
    double[][] mat;
    public Matrix(int rowCount, int colCount){
        mat = new double[rowCount][colCount];
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

    public static void main(String[] args) {
        Matrix m = new Matrix(5, 5);
        m.mat[3][2] = 32;
        m.mat[2][3] = 23;
        System.out.println(m.toString());
    }
}