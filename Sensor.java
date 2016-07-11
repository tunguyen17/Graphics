import java.awt.Image;
import java.awt.image.BufferedImage;

public class Sensor{
  //Fields
  Robot robot;

  double relativeHeading;

  int maxDistance = 100;
  int sensorBar;

  Tester tester;

  int distance;

  //constructor
  //newrelativeHeading with respect to the robot
  public Sensor(Robot newRobot, double newrelativeHeading, Tester newTester){
    robot = newRobot;
    sensorBar = 0;
    relativeHeading = newrelativeHeading;
    tester = newTester;
  }

  //Getter
  //Get the x and y corrdinate of the end points of the sensor
  public int getEndpointX(){
    return (int) (robot.getX() + maxDistance*Math.cos(relativeHeading+robot.getHeading()) + 0.5);
  }

  public int getEndpointY(){
    return (int) (robot.getY() + maxDistance*Math.sin(relativeHeading+robot.getHeading()) + 0.5);
  }

  //Get the x and y corrdinate of where the sensor read the wall
  public int getContactX(){
    return (int) (robot.getX() + distance*Math.cos(relativeHeading+robot.getHeading()) + 0.5);
  }

  public int getContactY(){
    return (int) (robot.getY() + distance*Math.sin(relativeHeading+robot.getHeading()) + 0.5);
  }

  //Get the distance from the robot to the closest reading
  public int getDistance() {return distance;}

  //Read the sensor, detect if there is any obstacle ahead
  public int[] detect(){
    distance = maxDistance;
    sensorBar = 0;
    int xPos2 = getEndpointX();
    int yPos2 = getEndpointY();

    for(int i = 0; i < maxDistance; i++){
      xPos2 = (int) (robot.getX() + (i)*Math.cos(relativeHeading+robot.getHeading()) + 0.5);
      yPos2 = (int) (robot.getY() + (i)*Math.sin(relativeHeading+robot.getHeading()) + 0.5);
      if(tester.sensorTest(xPos2, yPos2)){
        distance = i;
        sensorBar = 1;
        break;
      }
    }
    //System.out.print(distance + "  ");
    return new int[]{sensorBar, distance};
  }

}
