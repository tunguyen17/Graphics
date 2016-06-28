public class Driver{
  //Fields
  Robot robot;

  //5 matrices for 5 sensors
  Double[][][] qMatrix;

  //Constructor
  public Driver(Robot newRobot){
    robot = newRobot;

    //Double matrices of 2 states (true/false) and 3 actions left straight right
    qMatrix = Double[5][2][3];
  }

  //Methods
  public Learn(){
    for(int i = 0; i < 5; i++){
      //Find State
    }
  }

  public int reward(boolean re){
    int reward = 0;
    if(re = false){reward = -10};
    return reward;
  }

  public Double[] max(Double[] array){
    int i=0;
    if(array[0] < array[1]){ i = 1; }
    if(array[i] < array[2]){i = 2; }
    return i;
  }
}
