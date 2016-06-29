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

  //Constructor
  public Driver(Robot newRobot, Sensor[] newSensorList){
    robot = newRobot;

    sensorList = newSensorList;

    sensorCluster = new int[sensorList.length];

    qMat = new double[32][3];

    currQ = new double[3];
    prevQ = new double[3];

  }

  //methods

  //Finding value of the current state;
  public int update(){

    //Updte previousState and reset currentState
    prevState = currState;
    currState = 0;

    for(int i = 0; i<sensorList.length; i++){
      sensorCluster[i] = sensorList[i].detectBorder();
      currState += Math.pow(2, i)*sensorCluster[i];
      //System.out.print(sensorCluster[i] + "  ");
    }

    return currState;
  }

  public void learn(){

    currQ = qMat[update()];


    //Save old action and update new action
    prevAction = action;
    action = max(currQ);

    drive(action);

    if(robot.borderCollision()){
      reward = -80;

      //Reset State
      prevState = 0;
      currState = 0;
      counter = 0;

      System.out.println(ANSI_RED + "Robot collided " + reward + ANSI_RESET);
      robot.setPos(robot.getIntX(), robot.getIntY());
      robot.setHeading(2*Math.PI*Math.random());

      iteration++;
      System.out.println("-------- ITERATION " + iteration);

    } else if( currState == 0 && prevState != 0){
      counter++;
      reward = 0;
      System.out.println(ANSI_CYAN + "Robot escaped " + reward + "--" + counter + ANSI_RESET);
    }else {reward = -100;}



    // newQ += learnging rate * (reward + gamma.maxCurrentQ(currentState, currentAction) - oldQ)
    prevQ[prevAction] += 0.3 * (reward + 0.3*currQ[action] - prevQ[prevAction]);

    //System.out.print(currQ[0]+"  ");
    //System.out.print(currQ[1]+"  ");
    //System.out.println(currQ[2]+"  ");

    //update Q-matrix
    qMat[currState] = currQ;
    prevQ = currQ;

    qMat[0] =new double[] {0, 0, 0};

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

    int i=0;

    //IF the 3 Q value are equal then pick 1 of 3
    if(array[0] == array[1] && array[1] == array[2]){
      i = (int) (3*Math.random());
      //System.out.println("________EQUAL____________________");
    } else {
      //expoitation
      if(array[0] < array[1]){ i = 1; }
      if(array[i] < array[2]){i = 2; }
      //Exploration
      if(Math.random() <0.05 && iteration < 50){
        i = (int) (3*Math.random());
        System.out.println("________DoRA Explora____________________");
      }
    }
  return i;
  }

}
