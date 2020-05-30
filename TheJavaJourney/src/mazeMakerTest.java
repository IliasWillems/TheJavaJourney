import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class mazeMakerTest {

	@Test
	void test() {
		maze Maze = new maze(7);
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

		Maze.fillMaze();
	}

}
