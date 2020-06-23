package mazeMaker;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MazeDrawer extends JPanel {

    Maze drawn_maze;

    public MazeDrawer() {}
    public MazeDrawer(Maze m) {this.drawn_maze = m;}

    public void draw() {
        JFrame jf = new JFrame();
        jf.setTitle("Maze");
        jf.setSize(1000, 1000);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(this);
    }

	public static void main(String[] args) {
        MazeDrawer drawer = new MazeDrawer();
        drawer.draw();
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
	
	public void paintSolution(Graphics g, Set<char[][]> solutionSet, int i, int j) {
		int x = 10 + i*20;
		int y = 10 + j*20;
		
		g.setColor(Color.BLUE);
		for (char[][] solution : solutionSet) {
			if (solution[i][j] == 'l')
				g.drawLine(x+10, y+10, x-10, y+10);
			if (solution[i][j] == 'r')
				g.drawLine(x+10, y+10, x+30, y+10);
			if (solution[i][j] == 't')
				g.drawLine(x+10, y+10, x+10, y-10);
			if (solution[i][j] == 'b')
				g.drawLine(x+10, y+10, x+10, y+30);
			if (solution[i][j] == 'e')
				g.drawLine(x+10, y+10, x+20, y+10);
		}
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Maze maze;
        int width;
        int height;

        if (this.drawn_maze == null) {
            width  = 60;
            height = 40;
            maze   = Maze.makeMaze(width, height);
            maze.solveMaze();
        } else {
            width  = this.drawn_maze.width;
            height = this.drawn_maze.height;
            maze   = this.drawn_maze;
        }

        // Set background
        g.setColor(Color.BLACK);
        g.fillRect(10, 10, 20*width + 1, 20*height + 1);
        
        // Paint each segment
        for (int i = 0; i < width; i++) {
        	for (int j = 0; j < height; j++) {
        		paintSegment(g, maze.body[i][j], i, j);
        	}
        }
        
        // Paint the solution path
        for (int i = 0; i < width; i++) {
        	for (int j = 0; j < height; j++) {
        		paintSolution(g, maze.solutionSet, i, j);
        	}
        }
        
        // Mark beginning and ending, complete solution path
        g.setColor(Color.WHITE);
        g.drawLine(10, 11, 10, 29);
        g.drawLine(10 + 20*width, -9 + 20*height, 10 + 20*width, 9 + 20*height);
        g.setColor(Color.BLUE);
        g.drawLine(10, 20, 20, 20);
    }
}
