import java.util.Arrays;

public class Driver{
  //Fields
  public Robot robot;
  public int[] sensorCluster;
  public Sensor[] sensorList;

  public double[][] qMat;

  public int currState = 0; //Default value is 0

  public double[] currQ;

  public int action;

  public double reward;

  //Constructor
  public Driver(Robot newRobot, Sensor[] newSensorList){
    robot = newRobot;

    sensorList = newSensorList;

    sensorCluster = new int[sensorList.length];

    qMat = new double[32][3];

    currQ = new double[3];

  }

  //methods

  //Finding value of the current state;
  public int update(){

    currState = 0; //Reset Curr state

    for(int i = 0; i<sensorList.length; i++){
      sensorCluster[i] = sensorList[i].detectBorder();
      currState += Math.pow(2, i)*sensorCluster[i];
      //System.out.print(sensorCluster[i] + "  ");
    }
    //System.out.println();
    return currState;
  }

  public void learn(){
    update(); //get the current state;

    currQ = qMat[update()];

    //getAction
    action = max(currQ);

    drive(action);

    if(robot.borderCollision()){
      reward = -10;
      System.out.println("Robot collided");
    } else reward = 0;


    // newQ += learnging rate * (reward - oldQ)
    currQ[action] += 1 * (reward - currQ[action]);

    //System.out.print(currQ[0]+"  ");
    //System.out.print(currQ[1]+"  ");
    //System.out.println(currQ[2]+"  ");

    //update Q-matrix
    qMat[currState] = currQ;

    if(robot.borderCollision()){
      robot.setPos(100, 100);
      robot.setHeading(0);

      for(int i = 0; i < 32; i++){
        for(int j = 0; j< 3; j++){
          System.out.print(qMat[i][j] + "   ");
        }
        System.out.println();
      }
      System.out.println("--------");

    }

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
    if(array[0] < array[1]){ i = 1; }
    if(array[i] < array[2]){i = 2; }
  return i;
  }

}
