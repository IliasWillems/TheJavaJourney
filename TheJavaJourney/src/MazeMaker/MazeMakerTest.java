package MazeMaker;

import org.junit.jupiter.api.Test;

class MazeMakerTest {

	@Test
	void test() {
		int width = 4;
		int height = 4;

		Maze maze = Maze.makeMaze(width, height);
		System.out.println("Maze was made");
		maze.solveMaze();
		System.out.println("Maze was solved");
		
		for (char[][] solution : maze.solutionSet) {
			int[][] solutionPath = new int[width][height];
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					if (solution[i][j] != 0)
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
			System.out.println("------------------------------");
		}

		
		/*
		 * Wanneer ik deze test run, kan maze.solveMaze (op regel 15) SOMS het doolhof oplossen, maar meestal niet. In het geval dat hij geen oplossing vindt,
		 * eindigt het programma wel. De sysout statements in Maze.solveMaze (in de sectie "Only used for debugging; not part of the actual program.") 
		 * laten zien dat solveMaze wel degelijk door het doolhof lijkt te lopen, maar na een tijd terug backtrackt naar het begin en daar stopt. Ook de solutionPath velden
		 * van de Segment velden van de maze blijven 0, waaruit je kan besluiten dat de oplossing nooit is gevonden.
		 * 
		 * Omwille van de manier waarop de methode Maze.makeMaze is geïmplementeerd, wordt er gegarandeerd dat elk doolhof oplosbaar is. Het kan dus niet zijn dat er 
		 * geen oplossing bestaat.
		 */
	}

}
