public class Experience{
  //Fields
  //s
  public double[][] s;
  public int a;
  public double r;
  public double[][] sPrime;
  public int aPrime;
  public boolean collision;

  public Experience(double[][] newS, int newA, double newR, double[][] newSPrime, int newAPrime){
    s = newS;
    a = newA;
    r = newR;
    sPrime = newSPrime;
    aPrime = newAPrime;
    collision = false;
  }
  public Experience(double[][] newS, int newA, double newR){
    s = newS;
    a = newA;
    r = newR;
    collision = true;
  }

  public void print(){
    System.out.println("a - " + a + " r - " + r + " Collision - " + collision);
  }
}
