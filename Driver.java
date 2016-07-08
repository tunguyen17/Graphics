import java.util.Arrays;

public class Driver{
  //Fields

  //for text color
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_CYAN = "\u001B[36m";

  public Robot robot;

  public int[] sensorCluster;
  public Sensor[] sensorList;

  public int prevState = 0;
  public int currState = 0; //Default value is 0

  public int action = 0;

  public double reward;

  public int iteration=0;

  public int counter = 0;

  public Tester tester;

  public World world;
  //Constructor
  public Driver(World newWorld, Robot newRobot, Sensor[] newSensorList, Tester newTester){
    world = newWorld;
    robot = newRobot;
    sensorList = newSensorList;
    tester = newTester;

    sensorCluster = new int[sensorList.length];

  }

  //methods

  public void learn(){

    //drive(action);

    //GET THE REWARDS FOR THE ACTION
    if( tester.robotCollision() ){
      reward = -30;

      //Reset State
      prevState = 0;
      currState = 0;
      counter = 0;

      System.out.println(ANSI_RED + "Robot collided " + reward + ANSI_RESET);
      robot.reset();

      iteration++;
      System.out.println("-------- ITERATION " + iteration);

    } else if( currState == 0 && prevState != 0){
      counter++;
      reward = 10;
      System.out.println(ANSI_CYAN + "Robot escaped " + reward + " -- " + counter + ANSI_RESET);
    }else reward=0;

  }

  //Finding value of the current state;
public double[][] updateSensor(){
  double[][] s = new double[1][5];

  for(int i = 0; i< sensorList.length; i++){
    s[0][i] = sensorList[i].getDistance();
    System.out.print(s[0][i] + "  ");
  }
  System.out.println();
  return s;
}

  public void drive(int input){
    switch(input){

      case 0: robot.turnLeft();
              break;

      case 1: break;

      case 2: robot.turnRight();
              break;

      default: break;

    }
  }


  //Methods for finding max qValue
  public int max(double[] array){

    int max;

    //Exploration
    if(Math.random() <0.05 && iteration < 30){
      max = (int) (5*Math.random());
      System.out.println("________DoRA Explora____________________");
    } else {
      //expliotation
      if(array[0] > array[1]) max = 0; else max = 1;
      if(array[max] < array[2]) max = 2;
    }
  return max;
  }

}
