import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.Timer;

public class SnakeRunner extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private Timer timer = new Timer(30, this);
	private Grid grid = new Grid();
	private boolean[] adTF = {false, false};

	SnakeRunner(){
		this.setBounds(0,0,1000,870);
		this.add(grid);
		this.addKeyListener(this);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		timer.start();
	}

	public static void main(String[] args) {
		new SnakeRunner();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(adTF[0]) {
			grid.updateTheta(0);
		}else if(adTF[1]) {
			grid.updateTheta(1);
		}
		grid.updateGrid();
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		char key = e.getKeyChar();
		switch (key){
		case 'a':
			adTF[0]=true;
			adTF[1]=false;
			//System.out.println("a");
			//grid.updateTheta(0);
			break;
		case 'd':
			adTF[1]=true;
			adTF[0]=false;
			//System.out.println("d");
			//grid.updateTheta(1);
			break;
		case 'r':
			grid.reset();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		char key = e.getKeyChar();
		switch (key){
		case 'a':
			adTF[0]=false;
			//System.out.println("a");
			//grid.updateTheta(0);
			break;
		case 'd':
			adTF[1]=false;
			//System.out.println("d");
			//grid.updateTheta(1);
			break;
		}
	}
}