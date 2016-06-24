/*
* Contains the basic drawing board
*/

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

import javax.swing.JPanel;

public class World extends JPanel{
  //Fields

  //ROBOT position
  Robot robot;

  //constructor
  public World(Robot newRobot){
    super(); //Call the constructor of JPanel

    robot = newRobot;
    setBackground(Color.WHITE);
  }

  //OVERIDE THE PAINT COMPONENT
  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D) g;

    //Border
    g2.setStroke(new BasicStroke(2)); //Border-thickness = 2px
    g2.drawRect(10, 10, 480, 480);

    g2.setColor(Color.GREEN);

    g2.fillOval(robot.getX(), robot.getY(), 10, 10);
  }
}
