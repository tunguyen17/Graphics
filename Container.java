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
    setSize(world.width + 190, world.height + 190);
    setVisible(true);
  }
}
