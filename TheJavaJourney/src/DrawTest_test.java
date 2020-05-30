import javax.swing.*;

public class DrawTest_test {
	public static void main(String[] args) {
		
		JFrame f = new JFrame("Maze");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		drawMaze m = new drawMaze();
		f.add(m);
		f.setSize(900, 900);
		f.setVisible(true);
	}
}
