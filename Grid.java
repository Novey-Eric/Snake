import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JComponent;
public class Grid extends JComponent {

	private static final long serialVersionUID = 1L;
	//private int[][] grid;
	private ArrayList<Point> snake = new ArrayList<Point>(1);
	//x,y
	private double theta = Math.PI;
	//public final static int SNAKED = 1;
	private Point food;
	private boolean dead =false;

	Grid() {
		snake.add(new Point(500,500));
		food = new Point((int)(Math.random()*(900)), (int)(Math.random()*(800)));
	}

	public void paintComponent(Graphics g) {
		g.drawString("Score: "+snake.size(), 50, 50);
		if(dead) {
			g.drawString("You died", 100, 100);
			theta=-10*Math.PI/6;
		}
		for(int i = 0; i<snake.size(); i++) {
			g.setColor(Color.black);
			g.fillOval(snake.get(i).x, snake.get(i).y, 8, 8);
			g.setColor(Color.green);
			g.fillOval(food.x, food.y, 8, 8);
		}
	}

	public void updateTheta(int pos) {
		if(pos>0) {
			//.3 is good
			theta+=.3;
		}else {
			theta-=.3;
		}
	}

	public double getTheta() {
		return theta;
	}
	public boolean getDead() {
		return dead;
	}

	public void reset() {
		snake.clear();
		snake.add(new Point(500,500));
		food = new Point((int)(Math.random()*(900)), (int)(Math.random()*(800)));
		dead=false;
		theta=Math.PI;

	}

	public void updateGrid() {

		int xMove = (int)(10*Math.cos(theta));
		int yMove = (int)(10*Math.sin(theta));
		snake.add(new Point(snake.get(snake.size()-1).x+xMove,snake.get(snake.size()-1).y+yMove));
		if(Math.sqrt(Math.pow(food.getX()-snake.get(snake.size()-1).x, 2)+Math.pow(food.getY()-snake.get(snake.size()-1).y, 2))<8) {
			//System.out.println("no way thi sworks");
			food = new Point((int)(Math.random()*(900)), (int)(Math.random()*(800)));
		}else
			snake.remove(0);
		//24 turning radius
		for(int i = 0; i<snake.size()-1; i++) {
			if(Math.sqrt(Math.pow(snake.get(i).getX()-snake.get(snake.size()-1).x, 2)+Math.pow(snake.get(i).getY()-snake.get(snake.size()-1).y, 2))<7
					|| snake.get(snake.size()-1).x<0
					|| snake.get(snake.size()-1).x>1000
					|| snake.get(snake.size()-1).y<0
					|| snake.get(snake.size()-1).y>870
					) {
				//System.out.println("your dead bub");
				dead = true;
			}
		}
		repaint();
	}
}