
public class NeuNet{

  /*Fields*/

  //inputs layer
  public double[][] oldInputs; //1x5
  public double[][] inputs; //1x5

  //W1
  public double[][] w1; //5x5
  public double[][] deltaW1; //5x5

  //W1b
  public double[][] w1b; //1x5

  //Hidden layer 1
  public double[][] z2; //1x5
  public double[][] a2; //1x5

  //W2
  public double[][] w2; //5x3
  public double[][] deltaW2; //5x3

  //W2b
  public double[][] w2b; //1*3

  //Hidden layer 2
  public double[][] z3; //1x3
  public double[][] q; //1x3
  public double[][] oldQ;

  //Target value
  public double[][] target;

  /*Constructor*/
  public NeuNet(){

    //inputs layer
    oldInputs = new double[1][5];
    inputs = new double[1][5];

    //W1
    w1 = new double[5][5];
    deltaW1 = new double[5][5];
    Matrix.random(w1);

    //W1b
    w1b = new double[1][5];
    Matrix.random(w1b);

    //Hidden layer 1
    z2 = new double[1][5];
    a2 = new double[1][5];

    //W2
    w2 = new double[5][3];
    deltaW2 = new double[5][3];
    Matrix.random(w2);

    //w2b
    w2b = new double[1][3];
    Matrix.random(w2b);

    //Output layer
    z3 = new double[1][3];
    q = new double[1][3];
    oldQ = new double[1][3];

    //target
    target = new double[1][3];
  }

  public int max(){
    int max = 0;

    if(q.length > 1){System.out.println("Error this function ony accept single row matrix");}
    else{
      if(q[0][0] > q[0][1]) max = 0; else max = 1;
      if(q[0][max] < q[0][2]) max = 2;
    }

    return max;
  }

  /////////////////////////**///////////////////////////////


  public void forward(double[][] sensorInput){

    //Backup oldQ
    for(int i = 0; i<q.length; i++){
      for(int j = 0; j<q[0].length; j++){
        oldQ[i][j] = q[i][j];
      }
    }
    oldInputs = inputs;
    inputs = sensorInput;

    //Hidden layer 1
    z2 = Matrix.add(Matrix.mul(inputs, w1), w1b);
    a2 = Matrix.s(z2);

    //Output
    z3 = Matrix.add(Matrix.mul(a2, w2), w2b);
    q = Matrix.s(z3);

    Matrix.printMat(q, "Q");
  }

  public void back(double t, int index){
    target[0][0] = 0;
    target[0][1] = 0;
    target[0][2] = 0;
    target[0][index] = t;

    //max(q, r, gamma)

    double[][] delta2;
    double[][] delta3;

    //W2
    delta3 = Matrix.hMul( Matrix.subtract(q, target), Matrix.sPrime(z3) );
    deltaW2 = Matrix.mul( Matrix.transpose(a2), delta3 );

    //W1
    delta2 = Matrix.hMul( Matrix.mul(delta3, Matrix.transpose(w2)), Matrix.sPrime(z2) );
    deltaW1 = Matrix.mul(Matrix.transpose(inputs), delta2);

    //Update W
    w1 = Matrix.subtract(w1, Matrix.sMul(10, deltaW1));
    w2 = Matrix.subtract(w2, Matrix.sMul(10, deltaW2));

    w1b = Matrix.subtract(w1b, Matrix.sMul(10, delta2));
    w2b = Matrix.subtract(w2b, Matrix.sMul(10, delta3));
  }

  public void back2(double t, int index){
    target[0][0] = 0;
    target[0][1] = 0;
    target[0][2] = 0;
    target[0][index] = t;

    //max(q, r, gamma)

    double[][] delta2;
    double[][] delta3;

    //W2
    delta3 = Matrix.hMul( Matrix.subtract(oldQ, target), Matrix.sPrime(z3) );
    deltaW2 = Matrix.mul( Matrix.transpose(a2), delta3 );

    //W1
    delta2 = Matrix.hMul( Matrix.mul(delta3, Matrix.transpose(w2)), Matrix.sPrime(z2) );
    deltaW1 = Matrix.mul(Matrix.transpose(oldInputs), delta2);

    //Update W
    w1 = Matrix.subtract(w1, Matrix.sMul(5, deltaW1));
    w2 = Matrix.subtract(w2, Matrix.sMul(5, deltaW2));

    w1b = Matrix.subtract(w1b, Matrix.sMul(5, delta2));
    w2b = Matrix.subtract(w2b, Matrix.sMul(5, delta3));
  }
}
