public class Sensor{
  //Fields
  Robot robot;

  double relativeHeading;

  int maxDistance;
  int sensorBar;

  Tester tester;

  //constructor
  //newrelativeHeading with respect to the robot
  public Sensor(Robot newRobot, double newrelativeHeading, Tester newTester, int distance){
    robot = newRobot;
    sensorBar = 0;
    relativeHeading = newrelativeHeading;
    tester = newTester;
    maxDistance = distance;
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

  public int detectBox(){
    if( tester.boxCollusion( getEndpointX(), getEndpointY() ) ){
      sensorBar = 1;
    } else{sensorBar = 0;}
    return sensorBar;
  }

  public int detect(){
    if( tester.borderCollision( getEndpointX(), getEndpointY() ) || tester.boxCollusion( getEndpointX(), getEndpointY() ) ){
        sensorBar = 1;
    } else {sensorBar = 0;}
    return sensorBar;
  }

}
