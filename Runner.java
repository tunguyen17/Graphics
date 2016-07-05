/*
* This is where the program will be run
*/
//package graphics;

public class Runner{
  public static void main(String[] args) throws InterruptedException{
    //Box
    Box[] box = new Box[3];
    box[0] = new Box(10, 20, 100, 100);
    box[1] = new Box(150, 20, 100, 100);
    box[2] = new Box(400, 200, 100, 200);

    Sensor[] yoda = new Sensor[15];
    Robot prez = new Robot(200, 200);

    World earth = new World(1000, 500, prez, yoda, prez.getXPath(), prez.getYPath(), box);
    Container window = new Container(earth);
    MyKeyListener logi = new MyKeyListener(prez);
    earth.addKeyListener(logi);

    Tester tester = new Tester(earth, prez, box);

    yoda[0] = new Sensor(prez, -Math.PI/2, tester, 75);
    yoda[1] = new Sensor(prez, -Math.PI/4, tester, 75);
    yoda[2] = new Sensor(prez, 0, tester, 75);
    yoda[3] = new Sensor(prez, Math.PI/4, tester, 75);
    yoda[4] = new Sensor(prez, Math.PI/2, tester, 75);

    yoda[5] = new Sensor(prez, -Math.PI/2, tester, 50);
    yoda[6] = new Sensor(prez, -Math.PI/4, tester, 50);
    yoda[7] = new Sensor(prez, 0, tester, 50);
    yoda[8] = new Sensor(prez, Math.PI/4, tester, 50);
    yoda[9] = new Sensor(prez, Math.PI/2, tester, 50);

    yoda[10] = new Sensor(prez, -Math.PI/2, tester, 25);
    yoda[11] = new Sensor(prez, -Math.PI/4, tester, 25);
    yoda[12] = new Sensor(prez, 0, tester, 25);
    yoda[13] = new Sensor(prez, Math.PI/4, tester, 25);
    yoda[14] = new Sensor(prez, Math.PI/2, tester, 25);

    Driver nancy = new Driver(prez, yoda, tester);

    earth.requestFocusInWindow();
    //Repaint things
    while(true){
      Thread.sleep(20);
      for(int i = 0; i<5; i++){yoda[i].detectBorder();}
      nancy.learn();
      prez.move();

      //if(tester.borderCollision()){prez.reset();}
      earth.repaint();
    }
  }
}
