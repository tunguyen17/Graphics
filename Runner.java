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

    Sensor[] yoda = new Sensor[5];
    Robot prez = new Robot(200, 200);

    World earth = new World(500, 500, prez, yoda, prez.getXPath(), prez.getYPath(), box);
    Container window = new Container(earth);
    MyKeyListener logi = new MyKeyListener(prez);
    earth.addKeyListener(logi);

    Tester tester = new Tester(earth, prez, box);
    yoda[0] = new Sensor(prez, -Math.PI/2, tester);
    yoda[1] = new Sensor(prez, -Math.PI/4, tester);
    yoda[2] = new Sensor(prez, 0, tester);
    yoda[3] = new Sensor(prez, Math.PI/4, tester);
    yoda[4] = new Sensor(prez, Math.PI/2, tester);

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
