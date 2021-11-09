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

    public void gaussjordanify(){
        int k = -1;
        int l = -1;

        int K = mat[0].length;
        int L = mat.length;
        while (true){
            k++;
            l++;
            if (l > L)break;

            boolean equalsZero = false;
            for (int i = k; i < K; i++){
                if (mat[i][l] == 0){
                    equalsZero = true;
                }
            }
            if (equalsZero){
                k--;
            } else {
                
            }
        }
    }

    public static void main(String[] args) {
        Matrix m = new Matrix(5, 5);
        m.setColumn(2, new double[]{5, 3, 4, 0, 0});
        m.setRow(1, new double[]{5, 4, 3, 2, 1});
        System.out.println(m.toString());
    }
}