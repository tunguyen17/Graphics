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
    System.out.println("\nnew Batch +++++++++");
    int u = (int) ( n*Math.random() );
    for(int i = 0; i < k; i++){
      u = (int) ( n*Math.random() );
      System.out.println("\n" + u);
      batch[i] = d[u];
      batch[i].print();
    }
    return batch;
  }
}
