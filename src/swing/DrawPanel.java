import java.awt.*;

import javax.swing.JPanel;
import javax.swing.JFrame;

//Extends of a JPanel that inherit all of JPanel function
public class DrawPanel extends JPanel{

	//Constructor
	public DrawPanel(){
		super(); //call the constructor of JPanel
		setBackground(Color.WHITE);
	}

	@Override
	public void paintComponent(Graphics g){


		//get width and height of the frame
		int width = getWidth();
		int height = getHeight();

		//call the paintComponent
		super.paintComponent(g);

		//Drawing code
		g.drawRect(50, 50, 200, 200);
	}

	public class Frame extends JFrame{
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		setVisible(true);
	}

	public static void main(String[] args){
		DrawPanel panel = new DrawPanel();
		Frame app = new Frame(); //Frame for the drawing panel
		app.add(panel);
	}
}
