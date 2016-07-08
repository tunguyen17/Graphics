import java.awt.Image;
import java.awt.image.BufferedImage;

public class Tester{
  //World
  public World world;

  public Robot robot;
  public Box[] box;

  public BufferedImage img;

  //constructor
  public Tester(World world, Robot newRobot, Box[] newBox){
    robot = newRobot;
    box = newBox;

    img = world.getImg();
  }


  //for the rewards
  public boolean robotCollision(){
    if(img.getRGB(robot.xPos, robot.yPos) ==-14336 || img.getRGB(robot.xPos, robot.yPos) == -16777216){
      robot.collided = true;
    }
    return robot.collided;
  }

  public boolean sensorTest(int x, int y){
    boolean collided = false;
    //System.out.println(img.getRGB(x,y));
    if(img.getRGB(x,y) ==-14336 || img.getRGB(x,y) == -16777216){
      collided = true;
    }

    return collided;
  }
}
