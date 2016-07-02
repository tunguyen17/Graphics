public class Tester{
  //World
  public World world;
  public int width;
  public int height;

  public Robot robot;

  //constructor
  public Tester(World world, Robot newRobot){
    width = world.width;
    height = world.height;
    robot = newRobot;
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


}
