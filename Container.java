/*
* This is the container of the drawing board
*/
//package graphics;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Container extends JFrame{
  //Fields

  public Container(World world){
    super(); //Call the constructor of JFrame
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    add(world, BorderLayout.CENTER);
    setSize(world.width + 40, world.height + 55);
    setVisible(true);
  }
}
