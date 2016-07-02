public class Tester{
  //World
  public World world;
  public int width;
  public int height;

  public Robot robot;
  public Box[] box;
  //constructor
  public Tester(World world, Robot newRobot, Box[] newBox){
    width = world.width;
    height = world.height;
    robot = newRobot;
    box = newBox;
  }

  public boolean borderCollision(){
    boolean collided = false;
    if(robot.xPos < 15 || robot.xPos > width + 15) collided = true;
    if(robot.yPos < 15 || robot.yPos > height + 15) collided = true;
    return collided;
  }

  public boolean borderCollision(int x, int y){
    boolean collided = false;
    if(x < 15 || x > width + 15) collided = true;
    if(y < 15 || y > height + 15) collided = true;
    return collided;
  }

  public boolean boxCollision(){
    boolean collided = false;
    for(int i = 0; i < box.length; i++){
      if( robot.xPos > box[i].xPos && robot.xPos < (box[i].xPos + box[i].width) ){
        if( robot.yPos > box[i].yPos && robot.yPos < (box[i].yPos + box[i].height) ){
          collided = true;
        }
      }
    }
    return collided;
  }

  public boolean boxCollusion(int x, int y){
    boolean collided = false;
    for(int i = 0; i < box.length; i++){
      if( x > box[i].xPos && x < (box[i].xPos + box[i].width) ){
        if( y > box[i].yPos && y < (box[i].yPos + box[i].height) ){
          collided = true;
        }
      }
    }
    return collided;
  }
}
