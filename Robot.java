/*
* ROBOT status
*/

public class Robot{
  //Fields
  public int xPos;
  public int yPos;

  public boolean collided; //Collision boolean


  //Constructor
  public Robot(int newX,int newY){
    //Initialize the position of the robot
    xPos = newX;
    yPos = newY;

    collided = isCollided();
  }

  //Getter
  public int getX(){return xPos;}
  public int getY(){return yPos;}

  //Setter

  public void setPos(int newX, int newY){
    xPos = newX;
    yPos = newY;
  }

  public void move(int x, int y){
    xPos+=x;
    yPos+=y;
  }

  //Methods
  public boolean isCollided(){
    return true;
  }

}
