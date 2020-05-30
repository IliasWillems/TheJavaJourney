package MazeMaker;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MazeDrawer extends JPanel {
	
	public static void main(String[] args) {
        MazeDrawer drawer = new MazeDrawer();
        
        JFrame jf = new JFrame();
        jf.setTitle("Maze");
        jf.setSize(1700, 1000);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(drawer);
    }
	
	public void paintSegment(Graphics g, Segment segment, int i, int j) {
		int x = 10 + i*20;
		int y = 10 + j*20;

		g.setColor(Color.WHITE);
		g.fillRect(x+1, y+1, 19, 19);
		
		if (segment.top != null)
			g.drawLine(x+1, y, x+19, y);
		if (segment.bottom != null)
			g.drawLine(x+1, y+20, x+19, y+20);
		if (segment.left != null)
			g.drawLine(x, y+1, x, y+19);
		if (segment.right != null)
			g.drawLine(x+20, y+1, x+20, y+19);
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = 70;
        int height = 40;
        
        // Get a random maze
        Maze maze = Maze.makeMaze(width, height);
        
        // Set background
        g.setColor(Color.BLACK);
        g.fillRect(10, 10, 20*width + 1, 20*height + 1);
        
        // Paint each segment
        for (int i = 0; i < width; i++) {
        	for (int j = 0; j < height; j++) {
        		paintSegment(g, maze.body[i][j], i, j);
        	}
        }
        
        // Mark beginning and ending
        g.drawLine(10, 11, 10, 29);
        g.drawLine(10 + 20*width, -9 + 20*height, 10 + 20*width, 9 + 20*height);
    }
}
