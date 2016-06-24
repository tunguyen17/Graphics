/*
* This is where the program will be run
*/

import java.awt.BorderLayout;

public class Runner{
  public static void main(String[] args) throws InterruptedException{

    Robot prez = new Robot(50,50);

    Container window = new Container();
    World earth = new World(prez);

    window.add(earth, BorderLayout.CENTER);
    window.setVisible(true);

    //Repaint things
    while(true){
      prez.move(1,1);
      Thread.sleep(20);
      earth.repaint();
    }
  }
}
