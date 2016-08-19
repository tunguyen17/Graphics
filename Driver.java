import java.util.Arrays;

public class Driver{
  //Fields
  public int fitness;
  public Memory d;

  //for text color
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_CYAN = "\u001B[36m";

  public Robot robot;

  public int[] sensorCluster;
  public Sensor[] sensorList;

  public double[][] state;
  public double[][] statePrime;

  public int action = 1;
  public int actionPrime = 1;

  public double reward;

  public int iteration = 0;

  public Tester tester;

  public World world;

  //NEURAL NETWORK THINGS
  public NeuNet nn;

  public int maxQI = 1; //Max Q index

  //Constructor
  public Driver(World newWorld, Robot newRobot, Sensor[] newSensorList, Tester newTester){

    d = new Memory(100, 5);
    world = newWorld;
    robot = newRobot;
    sensorList = newSensorList;
    tester = newTester;

    sensorCluster = new int[sensorList.length];

    nn = new NeuNet();
  }

  //methods

  public void learn(){

    System.out.println("-------- ITERATION " + iteration);

    iteration++;
    reward = 0; //Reset reward
    state = Matrix.coppy(updateSensor()); //s
    nn.forward(state); // Q(s, a)

    //Action chooser
    if(Math.random()<0.5 && iteration < 100) action = (int) (3.0*Math.random());
      else action = nn.max(); //a

    drive(action); // carry out action a
    robot.move();

    //GET THE REWARDS FOR THE ACTION || r
    if(robot.collided){
      reward = 0.00001;

      d.add(state, action, reward);
      //Reset State
      fitness = 0;

      //System.out.println(ANSI_RED + "Robot collided " + reward + ANSI_RESET);
      robot.reset();
    } else{
      //no Collision
      reward = 0.01;
      statePrime = Matrix.coppy(updateSensor()); //s'
      nn.forward(statePrime);
      actionPrime = nn.max();

      d.add(state, action, reward, statePrime, actionPrime);
      //target = reward + nn.q[0][action];

      fitness++;

    }
    System.out.println("FITNESS: " + fitness);
    if(iteration > 100){nn.learn(d);}
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
