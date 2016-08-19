import java.io.PrintWriter;
import java.io.IOException;

public class NeuNet{

  /*Fields*/
  PrintWriter out;

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

  //Back Hidden layer 1
  public double[][] z2Temp; //5x5
  public double[][] a2Temp; //5x5

  //W2
  public double[][] w2; //5x3
  public double[][] deltaW2; //5x3

  //W2b
  public double[][] w2b; //1*3

  //Hidden layer 2
  public double[][] z3; //1x3
  public double[][] q; //1x3
  public double[][] oldQ;

  //Back Hidden layer 2
  public double[][] z3Temp; //5x3
  public double[][] qTemp; //5x3
  public double[][] qTempPrime; //5x3

  //Target value
  public double[][] target;

  public int[] actions;
  public double[] rewards;
  public boolean[] collisions;

  /*Constructor*/
  public NeuNet(){

    try{out = new PrintWriter("weight.txt");} catch(IOException e){System.out.println("Error");}

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

    //Back Hidden layer 1
    z2Temp = new double[5][5];
    a2Temp = new double[5][5];

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

    //Back Output layer
    z3Temp = new double[5][3];
    qTemp = new double[5][3];
    qTempPrime = new double[5][3];

    target = new double[5][3];
    actions = new int[5];
    rewards = new double[5];
    collisions = new boolean[5];
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
    oldQ = Matrix.coppy(q);
    oldInputs = inputs;
    inputs = sensorInput;

    //Hidden layer 1
    z2 = Matrix.add(Matrix.mul(inputs, w1), w1b);
    a2 = Matrix.s(z2);


    //Output
    z3 = Matrix.add(Matrix.mul(a2, w2), w2b);
    q = Matrix.s(z3);
  }

  //Update for collision
  public void back(double[][] t){


    //max(q, r, gamma)

    double[][] delta2;
    double[][] delta3;

    Matrix.printMat(Matrix.subtract(q, target), "Loss");

    //W2
    delta3 = Matrix.hMul( Matrix.subtract(q, target), Matrix.sPrime(z3) );
    deltaW2 = Matrix.mul( Matrix.transpose(a2), delta3 );

    //W1
    delta2 = Matrix.hMul( Matrix.mul(delta3, Matrix.transpose(w2)), Matrix.sPrime(z2) );
    deltaW1 = Matrix.mul(Matrix.transpose(inputs), delta2);

    //Update W
    w1 = Matrix.subtract(w1, Matrix.sMul(15, deltaW1));
    w2 = Matrix.subtract(w2, Matrix.sMul(15, deltaW2));

    w1b = Matrix.subtract(w1b, Matrix.sMul(15, delta2));
    w2b = Matrix.subtract(w2b, Matrix.sMul(15, delta3));
  }



  public void back2(double t, int index){

    for(int i = 0; i < 3; i++){
      target[0][i] = oldQ[0][i];
    }
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
    w1 = Matrix.subtract(w1, Matrix.sMul(15, deltaW1));
    w2 = Matrix.subtract(w2, Matrix.sMul(15, deltaW2));

    w1b = Matrix.subtract(w1b, Matrix.sMul(15, delta2));
    w2b = Matrix.subtract(w2b, Matrix.sMul(15, delta3));
  }

  public void learn(Memory mem){
    //new parameters`

    //Forward the NETWORK
    mem.getBatch();

    //Hidden layer 1
    //z2 = Matrix.add(Matrix.mul(mem.getInputs(), w1), w1b);
    //a2 = Matrix.s(z2);
    z2Temp = Matrix.add(Matrix.mul(mem.getS(), w1), w1b);
    a2Temp = Matrix.s(z2Temp);
    //Output
    //z3 = Matrix.add(Matrix.mul(a2, w2), w2b);
    //q = Matrix.s(z3);

    z3Temp = Matrix.add(Matrix.mul(a2Temp, w2), w2b);
    qTemp = Matrix.s(z3Temp);


    z2Temp = Matrix.add(Matrix.mul(mem.getSPrime(), w1), w1b);
    a2Temp = Matrix.s(z2Temp);


    //Output
    //z3 = Matrix.add(Matrix.mul(a2, w2), w2b);
    //q = Matrix.s(z3);

    z3Temp = Matrix.add(Matrix.mul(a2Temp, w2), w2b);
    qTempPrime = Matrix.s(z3Temp);

    target = Matrix.coppy(qTemp);
    actions = mem.getActions();
    rewards = mem.getRewards();
    collisions = mem.getCollisions();
    for(int i = 0; i < qTempPrime.length; i++){
      if(collisions[i]) {target[i][actions[i]] = rewards[i];} else{
        target[i][actions[i]] = rewards[i] + qTempPrime[i][actions[i]];
      }
    }

    //Back Prompangation

    double[][] delta2;
    double[][] delta3;

    //W2
    delta3 = Matrix.hMul( Matrix.subtract(target, qTemp), Matrix.sPrime(z3Temp) );
    deltaW2 = Matrix.mul( Matrix.transpose(a2Temp), delta3 );

    //W1
    delta2 = Matrix.hMul( Matrix.mul(delta3, Matrix.transpose(w2)), Matrix.sPrime(z2Temp) );
    deltaW1 = Matrix.mul(Matrix.transpose(mem.getS()), delta2);

    //Update W
    w1 = Matrix.subtract(w1, Matrix.sMul(15, deltaW1));
    w2 = Matrix.subtract(w2, Matrix.sMul(15, deltaW2));

    w1b = Matrix.subtract(w1b, Matrix.sMul(15, delta2));
    w2b = Matrix.subtract(w2b, Matrix.sMul(15, delta3));

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
