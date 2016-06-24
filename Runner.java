/*
* This is where the program will be run
*/

import java.awt.BorderLayout;

public class Runner{
  public static void main(String[] args){

    Container window = new Container();
    World earth = new World();

    window.add(earth, BorderLayout.CENTER);
    window.setVisible(true);
  }
}
