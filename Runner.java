/*
* This is where the program will be run
*/
//package graphics;

import java.awt.BorderLayout;

public class Runner{
  public static void main(String[] args) throws InterruptedException{

    Robot prez = new Robot(100,100);

    Sensor[] yoda = new Sensor[5];

    yoda[0] = new Sensor(prez, -Math.PI/2, 0);
    yoda[1] = new Sensor(prez, -Math.PI/4, 1);
    yoda[2] = new Sensor(prez, 0, 2);
    yoda[3] = new Sensor(prez, Math.PI/4, 3);
    yoda[4] = new Sensor(prez, Math.PI/2, 4);

    //DRIVER
    Driver nancy = new Driver(prez, yoda);

    Container window = new Container();
    World earth = new World(prez, yoda, prez.getXPath(), prez.getYPath());

    MyKeyListener logi = new MyKeyListener(prez);


    earth.addKeyListener(logi);

    window.add(earth, BorderLayout.CENTER);
    window.setVisible(true);

    earth.requestFocusInWindow();
    //Repaint things
    while(true){
      Thread.sleep(5);
      for(int i = 0; i<5; i++){yoda[i].detectBorder();}
      nancy.learn();
      prez.move();

      //nancy.update();

      earth.repaint();
    }
  }
}
