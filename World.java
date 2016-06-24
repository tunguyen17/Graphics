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
  public int xPos = 0;
  public int yPos = 0;

  //constructor
  public World(){
    super(); //Call the constructor of JPanel
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

    g2.fillOval(xPos, yPos, 10, 10);
    System.out.println(xPos);
  }
}
