/*
* This is where the program will be run
*/
package graphics;

import java.awt.BorderLayout;

public class Runner{
  public static void main(String[] args) throws InterruptedException{

    Robot prez = new Robot(300,300);

    Container window = new Container();
    World earth = new World(prez, prez.getXPath(), prez.getYPath());

    MyKeyListener logi = new MyKeyListener(prez);


    earth.addKeyListener(logi);

    window.add(earth, BorderLayout.CENTER);
    window.setVisible(true);

    earth.requestFocusInWindow();
    //Repaint things
    while(true){
      Thread.sleep(100);
      prez.move();
      if(earth.borderCollision()){
        prez.setPos(300, 300);
        prez.setHeading(0);
      }
      earth.repaint();
    }
  }
}
