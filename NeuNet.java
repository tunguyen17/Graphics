import java.io.PrintWriter;
import java.io.IOException;

public class NeuNet{

  /*Fields*/
  PrintWriter out;

  //inputs layer
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


  /*Constructor*/
  public NeuNet(){

    try{out = new PrintWriter("weight.txt");} catch(IOException e){System.out.println("Error");}

    //inputs layer
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
    inputs = sensorInput;

    //Hidden layer 1
    z2 = Matrix.add(Matrix.mul(inputs, w1), w1b);
    a2 = Matrix.s(z2);


    //Output
    z3 = Matrix.add(Matrix.mul(a2, w2), w2b);
    q = Matrix.s(z3);
  }

  public void learn(Memory mem){
    //new parameters`

    //Forward the NETWORK
    mem.getBatch();
    double[][] state = mem.getS();
    double[][] statePrime = mem.getSPrime();
    int[] actions = mem.getActions();
    double[] rewards = mem.getRewards();
    boolean[] collisions = mem.getCollisions();

    double[][] z2Temp = Matrix.add(Matrix.mul(state, w1), w1b);
    double[][] a2Temp = Matrix.s(z2Temp);
    double[][] z3Temp = Matrix.add(Matrix.mul(a2Temp, w2), w2b);
    double[][] qTemp = Matrix.s(z3Temp);

    double[][] targets = Matrix.coppy(qTemp);

    double[][] z2TempPrime = Matrix.add(Matrix.mul(statePrime, w1), w1b);
    double[][] a2TempPrime = Matrix.s(z2TempPrime);
    double[][] z3TempPrime = Matrix.add(Matrix.mul(a2TempPrime, w2), w2b);
    double[][] qTempPrime = Matrix.s(z3TempPrime);

    for(int i = 0; i < qTempPrime.length; i++){
      if(collisions[i]) {targets[i][actions[i]] = 0.1;} else{
        targets[i][actions[i]] = 0.1;
      }
    }

    for(int i =0; i < actions.length; i++){
      System.out.print(actions[i] + "  ");
    }
    System.out.println();

    Matrix.printMat(qTemp, "QTemp");
    Matrix.printMat(targets, "target");

    //Back Prompangation

    double[][] delta2;
    double[][] delta3;

    //W2
    delta3 = Matrix.hMul( Matrix.subtract(targets, qTemp), Matrix.sPrime(z3Temp) );
    deltaW2 = Matrix.mul( Matrix.transpose(a2Temp), delta3 );

    //W1
    delta2 = Matrix.hMul( Matrix.mul(delta3, Matrix.transpose(w2)), Matrix.sPrime(z2Temp) );
    deltaW1 = Matrix.mul(Matrix.transpose(state), delta2);

    //Update W
    w1 = Matrix.subtract(w1, Matrix.sMul(55, deltaW1));
    w2 = Matrix.subtract(w2, Matrix.sMul(55, deltaW2));

    w1b = Matrix.subtract(w1b, Matrix.sMul(55, delta2));
    w2b = Matrix.subtract(w2b, Matrix.sMul(55, delta3));

  }

  public void export(){
    out.println("W1");
    out.println("-------");
    for(int i = 0; i<w1.length; i++){
      for(int j = 0; j<w1[0].length; j++){
        out.print(w1[i][j] + "  ");
      }
      out.println();
    }
    out.println();

    out.println("w2");
    out.println("-------");
    for(int i = 0; i<w2.length; i++){
      for(int j = 0; j<w2[0].length; j++){
        out.print(w1[i][j] + "  ");
      }
      out.println();
    }
    out.println();
  }
}
