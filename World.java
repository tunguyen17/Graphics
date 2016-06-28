/*
* Contains the basic drawing board
*/
//package graphics;

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
  Sensor[] sensor;

  //Path of the robot
  PathArray xPath;
  PathArray yPath;

  PathArray path;


  //constructor
  public World(Robot newRobot, Sensor[] newSensor, PathArray newXPath, PathArray newYPath){
    super(); //Call the constructor of JPanel

    robot = newRobot;
    sensor = newSensor;
    setBackground(Color.WHITE);

    path = newXPath;

    xPath = newXPath;
    yPath = newYPath;

  }

  //OVERIDE THE PAINT COMPONENT
  @Override
  public void paintComponent(Graphics g){
    //super.paintComponent(g);
    super.paintComponent(g); //Wipe the paint board

    Graphics2D g2 = (Graphics2D) g;

    //Border
    g2.setStroke(new BasicStroke(2)); //Border-thickness = 2px
    g2.drawRect(10, 10, 480, 480);

    //Robot Stuff
    g2.setColor(Color.GREEN);
    g2.fillOval(robot.getX()-5, robot.getY()-5, 10, 10);

    //head of robot
    g2.setStroke(new BasicStroke(5));
    g2.drawLine(robot.getX(), robot.getY(), (int) (robot.getX() + 10*Math.cos(robot.getHeading())), (int) (robot.getY() + 10*Math.sin(robot.getHeading())));

    //sensor
    for(int i = 0; i<5; i++){
      if(sensor[i].detectBorder()){
        g2.setColor(Color.RED);
      } else{g2.setColor(Color.BLUE);}

      g2.setStroke(new BasicStroke(1));
      g2.drawLine(robot.getX(), robot.getY(), sensor[i].getEndpointX(), sensor[i].getEndpointY());
    }
    //Path
    for(int i = 0; i < path.getLength(); i++){
      g2.setColor(new Color(255 - 4*i,255 - 2*i, 255 -  5*i));
      g2.fillOval(xPath.getPath()[i]-2, yPath.getPath()[i]-2, 4, 4);

    }

  }

}
