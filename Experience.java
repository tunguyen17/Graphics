public class Experience{
  //Fields
  //s
  public double[][] s;
  public int a;
  public double r;
  public double[][] sPrime;
  public double aPrime;
  public boolean collision = false;

  public Experience(double[][] newS, int newA, double newR, double[][] newSPrime, double newAPrime){
    s = newS;
    a = newA;
    r = newR;
    sPrime = newSPrime;
    aPrime = newAPrime;
  }
}
