public class Sensor{
  //Fields
  Robot robot;

  double relativeHeading;

  int maxDistance = 30;
  int sensorBar;

  int num;

  //constructor
  //newrelativeHeading with respect to the robot
  public Sensor(Robot newRobot, double newrelativeHeading, int newNumber){
    num = newNumber;
    robot = newRobot;
    sensorBar = 0;
    relativeHeading = newrelativeHeading;
  }

  //Getter
  public int getEndpointX(){
    return (int) (robot.getX() + maxDistance*Math.cos(relativeHeading+robot.getHeading()) + 0.5);
  }

  public int getEndpointY(){
    return (int) (robot.getY() + maxDistance*Math.sin(relativeHeading+robot.getHeading()) + 0.5);
  }

  public int detectBorder(){

    if( getEndpointX() < 15 || getEndpointX() > 475 ){
      sensorBar = 1;
    } else if ( getEndpointY() < 15 || getEndpointY() > 475 ){
      sensorBar = 1;
    } else{sensorBar = 0;}

    if(sensorBar==1){
      //System.out.println("Border Detected" + num);
    }

    return sensorBar;
  }

}
