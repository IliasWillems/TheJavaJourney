package mazeMaker;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GiantMazeDrawer extends JPanel {
	
	public static void main(String[] args) {
        GiantMazeDrawer drawer = new GiantMazeDrawer();
        
        JFrame jf = new JFrame();
        jf.setTitle("Maze");
        jf.setSize(1700, 1000);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(drawer);
    }
	
	public void paintSegment(Graphics g, Segment segment, int i, int j) {
		int x = 10 + i*10;
		int y = 10 + j*10;

		g.setColor(Color.WHITE);
		g.fillRect(x+1, y+1, 9, 9);
		
		if (segment.top != null)
			g.drawLine(x+1, y, x+9, y);
		if (segment.bottom != null)
			g.drawLine(x+1, y+10, x+9, y+10);
		if (segment.left != null)
			g.drawLine(x, y+1, x, y+9);
		if (segment.right != null)
			g.drawLine(x+10, y+1, x+10, y+9);
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = 150;
        int height = 80;
        
        // Get a random maze
        Maze maze = Maze.makeMaze(width, height);
        
        // Set background
        g.setColor(Color.BLACK);
        g.fillRect(10, 10, 10*width + 1, 10*height + 1);
        
        // Paint each segment
        for (int i = 0; i < width; i++) {
        	for (int j = 0; j < height; j++) {
        		paintSegment(g, maze.body[i][j], i, j);
        	}
        }
        
        // Mark beginning and ending
        g.drawLine(10, 11, 10, 19);
        g.drawLine(10 + 10*width, 1 + 10*height, 10 + 10*width, 9 + 10*height);
    }
}
