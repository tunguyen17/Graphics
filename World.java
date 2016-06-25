/*
* Contains the basic drawing board
*/
package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.geom.GeneralPath;

import javax.swing.JPanel;

public class World extends JPanel{
  //Fields

  //ROBOT position
  Robot robot;

  //Path of the robot
  int[] xPath;
  int[] yPath;

  PathArray path;


  //constructor
  public World(Robot newRobot, PathArray newXPath, PathArray newYPath){
    super(); //Call the constructor of JPanel

    robot = newRobot;
    setBackground(Color.WHITE);

    path = newXPath;

    xPath = newXPath.getPath();
    yPath = newYPath.getPath();

  }

  //OVERIDE THE PAINT COMPONENT
  @Override
  public void paintComponent(Graphics g){
    //super.paintComponent(g);

    Graphics2D g2 = (Graphics2D) g;

    //Border
    g2.setStroke(new BasicStroke(2)); //Border-thickness = 2px
    g2.drawRect(10, 10, 480, 480);

    //Robot Stuff
    g2.setColor(Color.GREEN);
    g2.fillOval(robot.getX()-5, robot.getY()-5, 10, 10);

    g2.setStroke(new BasicStroke(5));
    g2.drawLine(robot.getX(), robot.getY(), (int) (robot.getX() + 10*Math.cos(robot.getHeading())), (int) (robot.getY() + 10*Math.sin(robot.getHeading())));

    //Path
    g2.setColor(Color.RED);
    g2.setStroke(new BasicStroke(5));
    GeneralPath robotPath = new GeneralPath();

    robotPath.moveTo(xPath[0], yPath[0]);

    for(int i = 0; i < path.getCurI(); i++){
       robotPath.lineTo(xPath[i], yPath[i]);
       System.out.println(xPath[i]+"  "+ yPath[i]);
    }

    g2.draw(robotPath);
  }

  //Methods for collusion check

  //Collision detection
  //Note: The first two value of fillOval is the upper left corner coordinate
  public boolean borderCollision(){
    boolean collided = false;

    int curX = robot.getX();
    int curY = robot.getY();

    if( curX < 15 || curX > 475 ){
      System.out.println("Collied with wall");
      collided = true;
    }
    if( curY < 15 || curY > 475 ){
      System.out.println("Collied with wall");
      collided = true;
    }
    return collided;
  }
}
