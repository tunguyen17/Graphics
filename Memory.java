import java.lang.ArrayIndexOutOfBoundsException;

public class Memory{
  //Fields
  public Experience[] d;
  public Experience[] batch;
  public int n; //Total number of available memory
  public int k; //Number of experience of 1 batch
  public int curr = 0;

  public Memory(int newN, int newK){
    n = newN;
    k = newK;
    d = new Experience[n];
    batch = new Experience[k];
  }

  public void add(double[][] newS, int newA, double newR, double[][] newSPrime, int newAPrime){

    try{
      d[curr] = new Experience(newS, newA, newR, newSPrime, newAPrime);
      if(curr < n-1){curr++;} else {
        curr = 0;
      }
    } catch(ArrayIndexOutOfBoundsException e){curr = 0;}
  }

  public void add(double[][] newS, int newA, double newR){

    try{
      d[curr] = new Experience(newS, newA, newR);

      if(curr < n-1){curr++;} else {
        curr = 0;
      }
    } catch(ArrayIndexOutOfBoundsException e){curr = 0;}
  }

  public Experience[] getBatch(){
      int u;
    for(int i = 0; i < k; i++){
      u = (int) ( n*Math.random() );
      batch[i] = d[u];
    }
    return batch;
  }

  public double[][] getS(){
    double[][] inputs = new double[k][5];
    for(int i = 0; i < k; i++){
      for(int j = 0; j < 5; j++){
        inputs[i][j] = batch[i].s[0][j];
      }
    }
    //Matrix.printMat(inputs ,"inputs");
    return inputs;
  }

  public double[][] getSPrime(){
    double[][] inputsPrime = new double[k][5];
    for(int i = 0; i < k; i++){
      if(batch[i].collision){
          inputsPrime[i] = new double[5];
        }else{
        for(int j = 0; j < 5; j++){
          inputsPrime[i][j] = batch[i].sPrime[0][j];
        }
      }
    }
    //Matrix.printMat(inputs ,"inputs");
    return inputsPrime;
  }

  public double[] getRewards(){
    double[] rewards = new double[k];
    for(int i = 0; i < k; i++){
      rewards[i] = batch[i].r;
    }
    return rewards;
  }

  public int[] getActions(){
    int[] actions = new int[k];
    for(int i = 0; i < k; i++){
      actions[i] = batch[i].a;
    }
    return actions;
  }

  public boolean[] getCollisions(){
    boolean[] collisions = new boolean[k];
    for(int i = 0; i < k; i++){
      collisions[i] = batch[i].collision;
    }
    return collisions;
  }
}
