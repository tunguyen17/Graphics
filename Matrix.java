public class Matrix{
  //Methods

  //*PRINT MATRIX*// || METHOD CHECKED

  public static void printMat(double[][] c, String abc){
    System.out.println(abc);
    System.out.println("-------");
    for(int i = 0; i<c.length; i++){
      for(int j = 0; j<c[0].length; j++){
        System.out.print(c[i][j] + "  ");
      }
      System.out.println();
    }
    System.out.println("-------");
    System.out.println();
  }

  //*MATRIX DOT PRODUCT || MATRIX MULTIPLICATION*// || METHOD CHECKED

  public static double[][] mul(double[][] a, double[][] b){
    //i ~ row, j ~ column
    double[][] c = new double[a.length][b[0].length];
    for(int i = 0; i < c.length; i++){
      for(int j = 0; j < c[0].length; j++){
        for(int k = 0; k < a[0].length; k++){
          c[i][j] += a[i][k]*b[k][j];
        }
      }
    }
    return c;
  }

  //*HADAMARD PRODUCT || ELEMENT WISE PRODUCT || Entry matrix has to have the same dimension*// || METHOD CHECKED

  public static double[][] hMul(double[][] a, double[][] b){
    double[][] c = new double[a.length][a[0].length];
    for(int i = 0; i< a.length; i++){
      for(int j = 0; j < a[0].length; j++){
        c[i][j] = a[i][j]*b[i][j];
      }
    }
    return c;
  }

  //*SCALAR PRODUCT*// || METHOD CHECKED

  public static double[][] sMul(double c, double[][] a){
    double[][] b = new double[a.length][a[0].length];
    for(int i = 0; i< a.length; i++){
      for(int j = 0; j < a[0].length; j++){
        b[i][j] = c*a[i][j];
      }
    }
    return b;
  }

  //*MATRIX SUBTRACTION*// || METHOD CHECKED

  public static double[][] subtract(double[][] a, double[][] b){
    double[][] c = new double[a.length][a[0].length];
    for(int i = 0; i< a.length; i++){
      for(int j = 0; j < a[0].length; j++){
        c[i][j] = a[i][j] - b[i][j];
      }
    }
    return c;
  }

  public static double[][] add(double[][] a, double[][] b){
    double[][] c = new double[a.length][a[0].length];
    for(int i = 0; i< a.length; i++){
      for(int j = 0; j < a[0].length; j++){
        c[i][j] = a[i][j] + b[i][j];
      }
    }
    return c;
  }

  //*TRANSPOSE*// || METHOD CHECKED
  public static double[][] transpose(double[][] a){
    double t[][] = new double[a[0].length][a.length];
    for(int i = 0; i < a.length; i++){
      for(int j = 0; j < a[0].length; j++){
        t[j][i] = a[i][j];
      }
    }
    return t;
  }

  /////////////NEURAL NETWORK STUFF/////////////////////

  //*RANDOMIZE MATRIX*// || METHOD CHECKED
  public static void random(double[][] w){
    for(int i = 0; i < w.length; i++){
      for(int j = 0; j < w[0].length; j++){
        w[i][j] = Math.random();
      }
    }
  }

  //*SIGMOID FUNCTION*// || METHOD CHECKED

  public static double[][] s(double[][] w){
    double[][] a = new double[w.length][w[0].length];
    for(int i = 0; i < a.length; i++){
      for(int j = 0; j < a[0].length; j++){
        a[i][j] =  1 / ( 1 + Math.exp( -w[i][j] ) );
      }
    }
    return a;
  }

  //*SIGMOID PRIME FUNCTION*// || METHOD CHECKED

  public static double[][] sPrime(double[][] w){
    double[][] a = new double[w.length][w[0].length];
    for(int i = 0; i < a.length; i++){
      for(int j = 0; j < a[0].length; j++){
        a[i][j] =  ( (Math.exp(-w[i][j])) / (Math.pow( (1+Math.exp(-w[i][j])), 2 )) );
      }
    }
    return a;
  }

}
