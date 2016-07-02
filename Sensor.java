public class Sensor{
  //Fields
  Robot robot;

  double relativeHeading;

  int maxDistance = 50;
  int sensorBar;

  Tester tester;

  //constructor
  //newrelativeHeading with respect to the robot
  public Sensor(Robot newRobot, double newrelativeHeading, Tester newTester){
    robot = newRobot;
    sensorBar = 0;
    relativeHeading = newrelativeHeading;
    tester = newTester;
  }

  //Getter
  public int getEndpointX(){
    return (int) (robot.getX() + maxDistance*Math.cos(relativeHeading+robot.getHeading()) + 0.5);
  }

  public int getEndpointY(){
    return (int) (robot.getY() + maxDistance*Math.sin(relativeHeading+robot.getHeading()) + 0.5);
  }

  public int detectBorder(){

    if( tester.borderCollision( getEndpointX(), getEndpointY() ) ){
      sensorBar = 1;
    } else{sensorBar = 0;}
    return sensorBar;
  }

}
