package mazeMaker;

public class MewsTest {

    public static void main(String[] args) {
        int width = 4;
        int height = 4;

        // First generate and solve the maze in standard output
        Maze maze = Maze.makeMaze(width, height);
        System.out.println("Maze was made");
        maze.solveMaze();
        System.out.println("Maze was solved");

        // Then print solution path mask
        for (char[][] solution : maze.solutionSet) {
        	int[][] solutionPath = new int[width][height];
        	for (int i = 0; i < width; i++) {
        		for (int j = 0; j < height; j++) {
        			if (solution[i][j] != 0)
        				solutionPath[i][j] = 1;
        			if (solutionPath[i][j] >= 0)
        				System.out.print(" ");
        			System.out.print(solutionPath[i][j] + " ");
        		}
        		System.out.println(" ");
        	}
        }

        // Finally draw maze in JFrame
        // MazeDrawer draw = new MazeDrawer(maze);
        // draw.draw();
    }

}
