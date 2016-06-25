/*
* This is where the program will be run
*/
//package graphics;

import java.awt.BorderLayout;

public class Runner{
  public static void main(String[] args) throws InterruptedException{

    Robot prez = new Robot(300,300);

    Sensor[] yoda = new Sensor[5];

    yoda[0] = new Sensor(prez, -Math.PI/2, 0);
    yoda[1] = new Sensor(prez, -Math.PI/4, 1);
    yoda[2] = new Sensor(prez, 0, 2);
    yoda[3] = new Sensor(prez, Math.PI/4, 3);
    yoda[4] = new Sensor(prez, Math.PI/2, 4);


    Container window = new Container();
    World earth = new World(prez, yoda, prez.getXPath(), prez.getYPath());

    MyKeyListener logi = new MyKeyListener(prez);


    earth.addKeyListener(logi);

    window.add(earth, BorderLayout.CENTER);
    window.setVisible(true);

    earth.requestFocusInWindow();
    //Repaint things
    while(true){
      Thread.sleep(50);
      for(int i = 0; i<5; i++){yoda[i].detectBorder();}
      prez.move();
      if(prez.borderCollision()){
        prez.setPos(300, 300);
        prez.setHeading(0);
      }
      earth.repaint();
    }
  }
}
