/*
* This is where the program will be run
*/
//package graphics;

public class Runner{
  public static void main(String[] args) throws InterruptedException{
    Sensor[] yoda = new Sensor[5];
    Robot prez = new Robot(200, 200);

    World earth = new World(800, 800, prez, yoda, prez.getXPath(), prez.getYPath());
    Container window = new Container(earth);
    MyKeyListener logi = new MyKeyListener(prez);
    earth.addKeyListener(logi);

    Tester tester = new Tester(earth, prez);
    yoda[0] = new Sensor(prez, -Math.PI/2, tester);
    yoda[1] = new Sensor(prez, -Math.PI/4, tester);
    yoda[2] = new Sensor(prez, 0, tester);
    yoda[3] = new Sensor(prez, Math.PI/4, tester);
    yoda[4] = new Sensor(prez, Math.PI/2, tester);

    Driver nancy = new Driver(prez, yoda, tester);

    earth.requestFocusInWindow();
    //Repaint things
    while(true){
      Thread.sleep(10);
      for(int i = 0; i<5; i++){yoda[i].detectBorder();}
      nancy.learn();
      prez.move();

      //nancy.update();
      //if(tester.borderCollision()){prez.reset();}
      earth.repaint();
    }
  }
}
