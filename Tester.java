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


  public boolean robotCollision(){
    boolean collided = false;
    if(img.getRGB(robot.xPos, robot.yPos) ==-14336 || img.getRGB(robot.xPos, robot.yPos) == -16777216){
      collided = true;}
    return collided;
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
