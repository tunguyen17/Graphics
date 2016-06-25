public class Sensor{
  //Fields
  Robot robot;

  double relativeHeading;

  int maxDistance = 50;
  boolean sensorBar;

  int num;

  //constructor
  //newrelativeHeading with respect to the robot
  public Sensor(Robot newRobot, double newrelativeHeading, int newNumber){
    num = newNumber;
    robot = newRobot;
    sensorBar = false;
    relativeHeading = newrelativeHeading;
  }

  //Getter
  public int getEndpointX(){
    return (int) (robot.getX() + maxDistance*Math.cos(relativeHeading+robot.getHeading()));
  }

  public int getEndpointY(){
    return (int) (robot.getY() + maxDistance*Math.sin(relativeHeading+robot.getHeading()));
  }

  public boolean detectBorder(){

    if( getEndpointX() < 15 || getEndpointX() > 475 ){
      sensorBar = true;
    } else if ( getEndpointY() < 15 || getEndpointY() > 475 ){
      sensorBar = true;
    } else{sensorBar = false;}

    if(sensorBar){System.out.println("Border Detected" + num);}

    return sensorBar;
  }

}
