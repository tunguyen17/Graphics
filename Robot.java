/*
* ROBOT status
*/
//package graphics;

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

    xPath = new PathArray(xPos);
    yPath = new PathArray(xPos);
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
  public void turnLeft(){heading-=0.09;}
  public void turnRight(){heading+=0.09;}
  public void setHeading(double newHeading){heading = newHeading;}

  /* Need to test this more
  public void bounce(){
    heading += Math.PI;
    System.out.println("heading");
  }
  */

  //Methods//
  public void move(){
    xPos+=3*Math.cos(heading);
    yPos+=3*Math.sin(heading);
    xPath.append(xPos);
    yPath.append(yPos);
  }

  public void back(){
    xPos-=5*Math.cos(heading);
    yPos-=5*Math.sin(heading);;
  }

  //Collusion check
  //Collision detection
  //Note: The first two value of fillOval is the upper left corner coordinate
  public boolean borderCollision(){
    boolean collided = false;

    if( xPos < 15 || xPos > 475 ){
      //System.out.println("Collied with wall");
      collided = true;
      xPath.reset();
      yPath.reset();
    }
    if( yPos < 15 || yPos > 475 ){
      //System.out.println("Collied with wall");
      collided = true;
      xPath.reset();
      yPath.reset();
    }
    return collided;
  }

}
