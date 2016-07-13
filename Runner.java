/*
* This is where the program will be run
*/
//package graphics;

public class Runner{
  public static void main(String[] args) throws InterruptedException{
    //Box
    Box[] box = new Box[3];
    box[0] = new Box(100, 100, 100, 100);
    box[1] = new Box(200, 500, 100, 50);
    box[2] = new Box(500, 200, 200,100);

    Sensor[] yoda = new Sensor[5];
    Robot prez = new Robot(500, 500);

    World earth = new World(1000, 500, prez, yoda, prez.getXPath(), prez.getYPath(), box);
    Container window = new Container(earth);
    MyKeyListener logi = new MyKeyListener(prez);
    earth.addKeyListener(logi);

    Tester tester = new Tester(earth, prez, box);

    earth.setTester(tester);

    yoda[0] = new Sensor(prez, -Math.PI/2, tester);
    yoda[1] = new Sensor(prez, -Math.PI/4, tester);
    yoda[2] = new Sensor(prez, 0, tester);
    yoda[3] = new Sensor(prez, Math.PI/4, tester);
    yoda[4] = new Sensor(prez, Math.PI/2, tester);

    Driver nancy = new Driver(earth, prez, yoda, tester);

    earth.requestFocusInWindow();
    //Repaint things
    while(true){
      Thread.sleep(20);
      nancy.learn();
      prez.move();

      //if(tester.robotCollision()){prez.reset();}
      earth.repaint();
    }
  }
}
