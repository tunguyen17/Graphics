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
      d[curr].s = Matrix.coppy(newS);
      d[curr].a = newA;
      d[curr].r = newR;
      d[curr].sPrime = Matrix.coppy(newSPrime);
      d[curr].aPrime = newAPrime;
      d[curr].collision = false;
      if(curr < n-2){curr++;} else {
        curr = 0;
      }
    } catch(ArrayIndexOutOfBoundsException e){curr = 0;}
  }

  public void add(double[][] newS, int newA, double newR){

    try{
      d[curr].s = Matrix.coppy(newS);
      d[curr].a = newA;
      d[curr].r = newR;
      d[curr].collision = true;

      if(curr < n-2){curr++;} else {
        curr = 0;
      }
    } catch(ArrayIndexOutOfBoundsException e){curr = 0;}
  }

  public Experience[] getBatch(){
    int u = (int) ( n*Math.random() );
    for(int i = 0; i < k; i++){
      while(d[u].r == 0){
        u = (int) ( n*Math.random() );
      }
      batch[i] = d[u];
    }
    return batch;
  }
}
