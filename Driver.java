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

  public double[][] qMat;

  public int prevState = 0;
  public int currState = 0; //Default value is 0

  public double[] prevQ;
  public double[] currQ;

  public int prevAction =0;
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

    qMat = new double[32768][5];

    currQ = new double[5];
    prevQ = new double[5];

  }

  //methods

  public void learn(){

    /*
    System.out.println("------");
    for(int i = 0; i<5; i ++){
      System.out.println("Sensor " + i + " " + world.sensorReadings[i][0] + " ---- " + world.sensorReadings[i][1]);
    }
    System.out.println("---------");
    */

    currQ = qMat[update()];


    //Save old action and update new action
    prevAction = action;
    action = max(currQ);

    drive(action);

    if( robot.collided==true ){
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



    // newQ += learnging rate * (reward + gamma.maxCurrentQ(currentState, currentAction) - oldQ)
    prevQ[prevAction] += 0.3 * (reward + 0.3* (currQ[action] - prevQ[prevAction]) );

    //System.out.print(currQ[0]+"  ");
    //System.out.print(currQ[1]+"  ");
    //System.out.println(currQ[2]+"  ");

    //update Q-matrix
    qMat[prevState] = prevQ;
    prevQ = currQ;
    qMat[0] =new double[] {0, 0, 0, 0, 0};
  }

  //Finding value of the current state;
public int update(){

  //Updte previousState and reset currentState
  prevState = currState;
  currState = 0;

  for(int i = 0; i< sensorList.length; i++){
    currState += sensorList[i].detect()[0]*Math.pow(2,i);
  }

  return currState;
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

    if(Math.random() <0.5 && iteration < 30){
      max = (int) (3*Math.random());
      System.out.println("________DoRA Explora____________________");
    } else if (array[0] == array[1] && array[1] == array[2]){max = (int) (3*Math.random());}
    else {
      //expliotation
      if(array[0] > array[1]) max = 0; else max = 1;
      if(array[max] < array[2]) max = 2;
    }
  return max;
  }

}
