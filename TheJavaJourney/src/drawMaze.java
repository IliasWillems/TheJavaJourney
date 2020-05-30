import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class drawMaze extends JPanel{
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.WHITE);
		
		
		//set size of matrix
		int size = 10;
		int unit = 800/size;
		
		//Make a maze
		maze Maze = new maze(size);
		Maze.createMainPath(Maze.body, 1, 1, 2);
		
		for(int i = 0; i < Maze.size; i++)
    	{
      	    for(int j = 0; j < Maze.size; j++)
            {
      	    if (Maze.body[i][j] >= 0)
              	System.out.print(" ");
          	System.out.print(Maze.body[i][j]+" ");
          	
            }
            System.out.println(" ");
        }

		//set color of lines
		g.setColor(Color.BLACK);

		//Draw boundary
		for (int i = size; i <= 800+size-unit; i += unit) {
			if (i == size | i == 800+size-unit) {
				for (int j = size; j <= 800; j += unit) {
					g.fillRect(j, i, unit, unit);
				}
			} else {
				g.fillRect(size, i, unit, unit);
				g.fillRect(800+size-unit, i, unit, unit);
			}
		}
		
		//Draw main path
		g.drawRect(size+unit-1, size+unit-1, unit, unit);
		for (int i = 3; i <= Maze.getMaxMatrix(); i++) {
			int[] location = Maze.locate(i);
			int xcoord = location[1];
			int ycoord = location[0];
			g.drawRect(size+xcoord*unit-1, size+ycoord*unit-1, unit, unit);
			
			/*
			String open = opensToDirection(Maze, i);
			if (open == "left")
				g.setColor(Color.WHITE);
				g.drawLine(size+xcoord*unit-1, size+ycoord*unit-1, size+xcoord*unit, size+(ycoord+1)*unit-1);
			*/
		}
		
	}
	
	public static String opensToDirection(maze Maze, int positionInMainPath) {
		int[] current = Maze.locate(positionInMainPath);
		int[] origin = Maze.locate(positionInMainPath-1);
		String rtrn;
		
		if (current[0] - origin[0] == 0) {
			if (current[1] - origin[1] == 1) {
				rtrn = "left";
			} else {
				rtrn = "right";
			}
		} else {
			if (current[1] - origin[1] == 0) {
				rtrn = "up";
			} else {
				rtrn = "down";
			}
		}
		return rtrn;
	}
}
