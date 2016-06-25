package graphics;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class MyKeyListener implements KeyListener{

  //Fields
  Robot robot;

  //Constructor
  public MyKeyListener(Robot newRobot){
    super();
    robot = newRobot;
  }

  public void keyTyped(KeyEvent e){
    //System.out.println("Key typed: " + e.getKeyChar());
  }

  //Methods for the keyboard
  public void keyPressed(KeyEvent e){
    if(e.getKeyCode() == KeyEvent.VK_UP){
      System.out.println("UP");
      robot.move();
    }

    else if(e.getKeyCode() == KeyEvent.VK_DOWN){
      System.out.println("DOWN");
      robot.back();
    }

    else if(e.getKeyCode() == KeyEvent.VK_LEFT){
      System.out.println("LEFT");
      robot.turnLeft();
    }

    else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
      System.out.println("RIGHT" + Math.cos(0.5));
      robot.turnRight();
    }
  }

  public void keyReleased(KeyEvent e){
    //System.out.println("");
  }

}
