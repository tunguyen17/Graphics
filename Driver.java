import java.util.Arrays;

public class Driver{
  //Fields
  public int fitness =0;

  //for text color
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_CYAN = "\u001B[36m";

  public Robot robot;

  public int[] sensorCluster;
  public Sensor[] sensorList;

  public int prevState = 0;
  public int currState = 0; //Default value is 0

  public int action = 1;
  public int oldAction = 1;

  public double reward;

  public int iteration = 0;

  public int counter = 0;

  public Tester tester;

  public World world;

  //NEURAL NETWORK THINGS
  public NeuNet nn;

  public int maxQI = 1; //Max Q index

  //Constructor
  public Driver(World newWorld, Robot newRobot, Sensor[] newSensorList, Tester newTester){
    world = newWorld;
    robot = newRobot;
    sensorList = newSensorList;
    tester = newTester;

    sensorCluster = new int[sensorList.length];

    nn = new NeuNet();
  }

  //methods

  public void learn(){

    iteration++;
    System.out.println("-------- ITERATION " + iteration);
    //updateSensor();
    //nn.printMat(nn.q, "q");
    double target = 0;
    reward = 0; //Reset reward
    oldAction = action; //update old action

    nn.forward(updateSensor()); // Q(s, a)

    //Action chooser
    if(Math.random()<0.01 && iteration < 2000) action = (int) (3.0*Math.random());
      else action = nn.max(); //a

    drive(action); // carry out action a
    robot.move();

    //GET THE REWARDS FOR THE ACTION || r
    if(robot.collided){

      reward = 0.00001;
      //Reset State
      prevState = 0;
      currState = 0;
      counter = 0;
      fitness = 0;

      //System.out.println(ANSI_RED + "Robot collided " + reward + ANSI_RESET);
      robot.reset();

      target = reward;

    } else{
      //no Collision

      reward = 0.05;
      target = reward + nn.q[0][action];

      fitness++;

    }
    nn.back2(target, oldAction);
    System.out.println("target" + target);
    System.out.println("FITNESS: " + fitness);
    nn.export();
  }

  //Finding value of the current state
  public double[][] updateSensor(){
    double[][] s = new double[1][5];

    for(int i = 0; i< sensorList.length; i++){
      s[0][i] = sensorList[i].getDistance()/100.;
    }
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


}
