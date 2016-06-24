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

  //Methods
  public boolean isCollided(){
    
  }
}
