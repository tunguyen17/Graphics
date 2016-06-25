/*
* ROBOT status
*/
package graphics;

public class Robot{
  //Fields
  public int xPos;
  public int yPos;

  public PathArray xPath;
  public PathArray yPath;

  public double heading = 0; //Initial heading 0


  //Constructor
  public Robot(int newX,int newY){
    //Initialize the position of the robot
    xPos = newX;
    yPos = newY;

    xPath = new PathArray();
    yPath = new PathArray();

    xPath.append(xPos);
    yPath.append(yPos);
  }

  //Getter
  public int getX(){return xPos;}
  public int getY(){return yPos;}
  public double getHeading(){return heading;}
  public PathArray getXPath(){return xPath;}
  public PathArray getYPath(){return yPath;}

  //Setter
  public void setPos(int newX, int newY){
    xPos = newX;
    yPos = newY;
  }
  public void turnLeft(){heading-=0.05;}
  public void turnRight(){heading+=0.05;}
  public void setHeading(double newHeading){heading = newHeading;}

  /* Need to test this more
  public void bounce(){
    heading += Math.PI;
    System.out.println("heading");
  }
  */

  //Methods//
  public void move(){
    xPos+=5*Math.cos(heading);
    yPos+=5*Math.sin(heading);
    xPath.append(xPos);
    yPath.append(yPos);
  }

  public void back(){
    xPos-=5*Math.cos(heading);
    yPos-=5*Math.sin(heading);;
  }

}
