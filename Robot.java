/*
* ROBOT status
*/
//package graphics;

public class Robot{
  //Fields
  public int xIntPos;
  public int yIntPos;

  public int xPos;
  public int yPos;

  public PathArray xPath;
  public PathArray yPath;

  public double heading = 0; //Initial heading 0

  public boolean collided;

  //Constructor
  public Robot(int newX,int newY){
    //Initialize the position of the robot
    xPos = newX;
    yPos = newY;

    xIntPos = newX;
    yIntPos = newY;

    xPath = new PathArray(xPos);
    yPath = new PathArray(xPos);

    collided = false;
  }

  //Getter
  public int getIntX(){return xIntPos;}
  public int getIntY(){return yIntPos;}

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

  public void setIntPos(int newX, int newY){
    xIntPos = newX;
    yIntPos = newY;
  }

  public void turnLeft(){heading-=0.3;}
  public void turnRight(){heading+=0.3;}
  public void turnLeft2(){heading-=0.1;}
  public void turnRight2(){heading+=0.1;}
  public void setHeading(double newHeading){heading = newHeading;}

  /* Need to test this more
  public void bounce(){
    heading += Math.PI;
    System.out.println("heading");
  }
  */

  //Methods//
  public void move(){
    xPos+=10*Math.cos(heading)+0.5;
    yPos+=10*Math.sin(heading)+0.5;
    xPath.append(xPos);
    yPath.append(yPos);
  }

  public void back(){
    xPos-=5*Math.cos(heading);
    yPos-=5*Math.sin(heading);;
  }

  public void reset(){
    xPos = xIntPos;
    yPos = yIntPos;
    setHeading(2*Math.PI*Math.random());
    collided = false;
  }
}
