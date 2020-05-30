package MazeMaker;

import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

class MazeMakerTest {

	@Test
	void test() {
		int width = 20;
		int height = 10;

		Maze maze = Maze.makeMaze(width, height);
		maze.solveMaze(new char[width][height], 0, 0);
		
		int[][] solutionPath = new int[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (maze.body[i][j].solutionPath != 0)
					solutionPath[i][j] = 1;
			}
		}
		
		int[][] dummy = solutionPath;
		
		for(int i = 0; i < dummy.length; i++) {
      	    for(int j = 0; j < dummy[0].length; j++)
            {
      	    if (dummy[i][j] >= 0)
              	System.out.print(" ");
          	System.out.print(dummy[i][j]+" ");
          	
            }
            System.out.println(" ");
		}
	}

}
