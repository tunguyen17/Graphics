/*
* Contains the basic drawing board
*/
//package graphics;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.geom.GeneralPath;

import java.awt.Image;
import java.awt.image.BufferedImage; //For detecting color

import javax.swing.JPanel;

public class World extends JPanel{
  //Fields
  public int width;
  public int height;

  //ROBOT position
  public Robot robot;
  public Sensor[] sensor;

  //Path of the robot
  public PathArray xPath;
  public PathArray yPath;

  public PathArray path;

  public Box[] box;

  BufferedImage img;

  Graphics2D g2;

  //constructor
  public World(int newWidth, int newHeight, Robot newRobot, Sensor[] newSensor, PathArray newXPath, PathArray newYPath, Box[] newBox){
    super(); //Call the constructor of JPanel

    width = newWidth;
    height = newHeight;

    robot = newRobot;
    sensor = newSensor;
    setBackground(Color.WHITE);

    path = newXPath;

    xPath = newXPath;
    yPath = newYPath;

    box = newBox;

    img = new BufferedImage(2*width, 2*height, BufferedImage.TYPE_INT_RGB);
    g2 = (Graphics2D) (img.getGraphics());
  }

  @Override
  public void paint(Graphics g){
    createImage();
    g.drawImage(img, 0, 0, this);
  }

  public void createImage(){

    //Border
    g2.setStroke(new BasicStroke(2)); //Border-thickness = 2px
    g2.drawRect(15, 15, width, height);

    //drawBox
    for(int i = 0; i< box.length; i++){
      g2.drawRect(box[i].xPos, box[i].yPos, box[i].width, box[i].height);
    }
    //Robot Stuff
    g2.setColor(Color.GREEN);
    g2.fillOval(robot.getX()-5, robot.getY()-5, 10, 10);

    //head of robot
    g2.setStroke(new BasicStroke(5));
    g2.drawLine(robot.getX(), robot.getY(), (int) (robot.getX() + 10*Math.cos(robot.getHeading())), (int) (robot.getY() + 10*Math.sin(robot.getHeading())));

    //sensor
    for(int i = 0; i<sensor.length; i++){
      if(sensor[i].detectBorder()==1 || sensor[i].detectBox()==1){
        g2.setColor(Color.RED);
      } else{g2.setColor(Color.BLUE);}

      g2.setStroke(new BasicStroke(1));
      g2.fillOval(sensor[i].getEndpointX()-1, sensor[i].getEndpointY()-1, 3, 3);
    }
    //Path
    for(int i = 0; i < path.getLength(); i++){
      g2.setColor(new Color(255 - 4*i,255 - 2*i, 255 -  5*i));
      g2.fillOval(xPath.getPath()[i]-2, yPath.getPath()[i]-2, 4, 4);

    }

  }

}
