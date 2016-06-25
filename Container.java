/*
* This is the container of the drawing board
*/
package graphics;

import javax.swing.JFrame;

public class Container extends JFrame{
  //Fields

  public Container(){
    super(); //Call the constructor of JFrame
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(500, 520);
  }
}
