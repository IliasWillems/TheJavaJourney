package MazeMaker;

import java.util.Random;

import LinkedList.LinkedList;

public class Maze {
	int width;
	int height;
	Segment[][] body;
	int[][] mazeMatrix;

	public Maze(int width, int height) {
		this.width = width;
		this.height = height;
		body = new Segment[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				body[i][j] = new Segment();
			}
		}
		mazeMatrix = new int[width][height];
	}
	
	/**
	 * This method returns an integer matrix representing this maze. A cell in the returned matrix is '1' if the corresponding maze-cell is connected to another
	 * cell in the maze and '0' otherwise.
	 */
	public int[][] toMatrix() {
		int[][] matrix = new int[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (this.body[i][j].left != null | this.body[i][j].right != null | this.body[i][j].top != null | this.body[i][j].bottom != null) {
					matrix[i][j] = 1;
				}
			}
		}
		return matrix;
	}
	
	/**
	 * This method returns the orientation of (x2, y2) to (x1, y1). For example: If (x2, y2) is to the left of (x1, y1), this method will return 'l'.
	 */
	private static char getOrientation(int x1, int y1, int x2, int y2) {
		char orientation;
		if (x1 == x2) {
			if (y2 == y1 + 1) {
				orientation = 'b';
			} 
			else if (y1 == y2 + 1) {
				orientation = 't';
			} else {
				throw new IllegalArgumentException("The given coordinates are not neighbours.");
			}
		}
		else if (y1 == y2) {
			if (x2 == x1 + 1) {
				orientation = 'r';
			}
			else if (x1 == x2 + 1) {
				orientation = 'l';
			} else {
				throw new IllegalArgumentException("The given coorindates are not neighbours.");
			}
		} else {
			throw new IllegalArgumentException("The given coorindates are not neighbours.");
		}
		return orientation;
	}
	
	/**
	 * This method connects points a and b in the maze via a random path.
	 */
	public void randomConnect(int[] a, int[] b) {
		if (a.length != 2 | b.length != 2) 
			throw new IllegalArgumentException("The input does not represent two 2-dimensional points.");
		
		int DeltaX = Math.abs(b[0] - a[0]);
		int DeltaY = Math.abs(b[1] - a[1]);
		int totalNumberOfSteps = DeltaX + DeltaY;
		Random RandomIntegerGenerator = new Random();
		
		boolean goRight = true;
		boolean goBottom = true;
		if (b[0] < a[0])
			goRight = false;
		if (b[1] < a[1])
			goBottom = false;
		
		Segment currentSegment = body[a[0]][a[1]];
		int horizontalSteps = 0;
		int verticalSteps = 0;
		
		for (int i = 0; i < totalNumberOfSteps; i++) {
			// While it is still possible to continue in two directions, go in random directions.
			if (DeltaX != 0 && DeltaY != 0) {
				if (RandomIntegerGenerator.nextInt(2) == 0) {  // Move in horizontal direction.
					if (goRight) {
						horizontalSteps++;
						currentSegment.connect('r', body[a[0] + horizontalSteps][a[1] + verticalSteps]);
						currentSegment = currentSegment.right;
						DeltaX--;
					} else {
						horizontalSteps--;
						currentSegment.connect('l', body[a[0] + horizontalSteps][a[1] + verticalSteps]);
						currentSegment = currentSegment.left;
						DeltaX--;
					}
				} else {									   // Move in vertical direction.
					if (goBottom) {
						verticalSteps++;
						currentSegment.connect('b', body[a[0] + horizontalSteps][a[1] + verticalSteps]);
						currentSegment = currentSegment.bottom;
						DeltaY--;
					} else {
						verticalSteps--;
						currentSegment.connect('t', body[a[0] + horizontalSteps][a[1] + verticalSteps]);
						currentSegment = currentSegment.top;
						DeltaY--;
					}
				}
			} else {
				if (DeltaX == 0) {
					if (goBottom) {
						verticalSteps++;
						currentSegment.connect('b', body[a[0] + horizontalSteps][a[1] + verticalSteps]);
						currentSegment = currentSegment.bottom;
						DeltaY--;
					} else {
						verticalSteps--;
						currentSegment.connect('t', body[a[0] + horizontalSteps][a[1] + verticalSteps]);
						currentSegment = currentSegment.top;
						DeltaY--;
					}
				} else {
					if (goRight) {
						horizontalSteps++;
						currentSegment.connect('r', body[a[0] + horizontalSteps][a[1] + verticalSteps]);
						currentSegment = currentSegment.right;
						DeltaX--;
					} else {
						horizontalSteps--;
						currentSegment.connect('l', body[a[0] + horizontalSteps][a[1] + verticalSteps]);
						currentSegment = currentSegment.left;
						DeltaX--;
					}
				}
			}
		}
	}
	
	/**
	 * This method creates a random main path of the maze.
	 */
	public void makeMainPath() {
		int[][] MainPathIntegerMatrix = MainPathFinder.createMainPath(width, height);
		int maxValue = MainPathIntegerMatrix[width-1][height-1];
		
		for (int i = 1; i < maxValue; i++) {
			randomConnect(MainPathFinder.getPositionOf(MainPathIntegerMatrix, i), MainPathFinder.getPositionOf(MainPathIntegerMatrix, i+1));
		}
		
		mazeMatrix = this.toMatrix();
	}
	
	/**
	 * Returns a LinkedList containing the coordinates of possible directions.
	 */
	public LinkedList findPossibleDirections(int x, int y) {
		LinkedList possibleDirections = new LinkedList();
		
		if (y-1 >= 0)
			if (mazeMatrix[x][y-1] == 0)
				possibleDirections.add(x, y-1);
		if (y+1 < height)
			if (mazeMatrix[x][y+1] == 0)
				possibleDirections.add(x, y+1);
		if (x-1 >= 0)
			if (mazeMatrix[x-1][y] == 0)
				possibleDirections.add(x-1, y);
		if (x+1 < width)
			if (mazeMatrix[x+1][y] == 0)
				possibleDirections.add(x+1, y);
		
		return possibleDirections;
	}

	/**
	 * Creates a random path starting at the given coordinates.
	 * This method requires that the mazeMatrix is up to date with the maze (Segment[][]) to work properly.
	 */
	public void makeRandomPathStartingAt(int x, int y) {
		int currentX = x;
		int currentY = y;
		boolean Continue = true;
		
		while (Continue) {
			LinkedList pos = findPossibleDirections(currentX, currentY);
			
			if (pos.getSize() == 0) {
				Continue = false;
			} else {
				Random RandomIndexGenerator = new Random();
				int index = RandomIndexGenerator.nextInt(pos.getSize());
				int otherX = pos.getValueAt(index)[0];
				int otherY = pos.getValueAt(index)[1];
				
				body[currentX][currentY].connect(getOrientation(currentX, currentY, otherX, otherY), body[otherX][otherY]);
				mazeMatrix[otherX][otherY] = 1;
				
				currentX = otherX;
				currentY = otherY;
				
				if (RandomIndexGenerator.nextInt(((int) (width*height)/5)) == 1) // scaling factor for chance of path ending randomly
					Continue = false;
			}
		}
	}
	
	/**
	 * This method tries to make random branch paths branching out of already existing paths.
	 */
	public void makeRandomPaths() {
		
		// Make a LinkedList object of all the positions of the one's in mazeMatrix.
		LinkedList pathPositions = new LinkedList();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (mazeMatrix[i][j] != 0)
					pathPositions.add(i, j);
			}
		}
		
		// Chose random positions of one's (amount based on some factor found 2 lines below).
		Random RandomIndexGenerator = new Random();
		int[] indexArray = new int[(int) (width+height)/10];  // Scaling factor for number of side paths; Tweaking may be required
		for (int i = 0; i < indexArray.length; i++) {
			indexArray[i] = RandomIndexGenerator.nextInt(pathPositions.getSize());
		}
		
		for (int i = 0; i < indexArray.length; i++) {
			makeRandomPathStartingAt(pathPositions.getValueAt(indexArray[i])[0], pathPositions.getValueAt(indexArray[i])[1]);
		}
	}
	
	/**
	 * Returns whether the maze contains null-cells.
	 */
	public boolean stillEmptySpaceInMaze() {
		boolean stillEmptySpace = false;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (mazeMatrix[i][j] == 0)
					stillEmptySpace = true;
			}
		}
		return stillEmptySpace;
	}
	
	/**
	 * This method fills the maze with random paths.
	 */
	public void fillMazeWithRandomPaths() {
		while(stillEmptySpaceInMaze()) {
			makeRandomPaths();
		}
	}
	
	/**
	 * This method detects 2x2 areas in the maze that form a loop and places a random edge inside it.
	 */
	public void cleanUp() {
		for (int i = 0; i < width - 1; i++) {
			for (int j = 0; j < height - 1; j++) {
				if (body[i][j].formsLoop()) {
					body[i][j].disconnect(body[i][j+1]);
				}
			}
		}
	}
	
	public static Maze makeMaze(int width, int height) {
		Maze maze = new Maze(width, height);
		maze.makeMainPath();
		maze.fillMazeWithRandomPaths();
		maze.cleanUp();
		
		return maze;
	}
	
	public char[] findDirections(char[][] currentSolution, int curX, int curY) {
		int length = 0;
		int index = 0;
		boolean right = false;
		boolean left = false;
		boolean top = false;
		boolean bottom = false;
		
		if (curX + 1 < width) {
			if (currentSolution[curX + 1][curY] == 0 && body[curX][curY].right != null) {
				right = true;
				length++;
			}
		}
		if (curX - 1 >= 0) {
			if (currentSolution[curX - 1][curY] == 0 && body[curX][curY].left != null) {
				left = true;
				length++;
			}
		}
		if (curY + 1 < height) {
			if (currentSolution[curX][curY + 1] == 0 && body[curX][curY].bottom != null) {
				bottom = true;
				length++;
			}
		}
		if (curY - 1 >= 0) {
			if (currentSolution[curX][curY - 1] == 0 && body[curX][curY].top != null) {
				top = true;
				length++;
			}
		}
		
		char[] posDir = new char[length];
		if (right) {
			posDir[index] = 'r';
			index++;
		}
		if (left) {
			posDir[index] = 'l';
			index++;
		}
		if (top) {
			posDir[index] = 't';
			index++;
		}
		if (bottom) {
			posDir[index]  = 'b';
			index++;
		}
		
		if (index != length) {
			throw new UnsupportedOperationException("Method doesn't work");
		}
		return posDir;
	}
	
	public char[][] copyCharMatrix(char[][] matrix) {
		char[][] copy = new char[matrix.length][matrix[0].length];
		for (int i = 0; i < copy.length; i++) {
			for (int j = 0; j < copy[0].length; j++) {
				copy[i][j] = matrix[i][j];
			}
		}
		return copy;
	}
	
	public void setSolution(char[][] solutionMatrix) {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (solutionMatrix[i][j] != 0) {
					body[i][j].solutionPath = solutionMatrix[i][j];
				}
			}
		}
	}
	
	public void solveMaze(char[][] currentSolution, int curX, int curY) {
		
		// ------------ Only used for debugging; not part of the actual program. ----------------
		char[][] dummy = currentSolution;
		for(int i = 0; i < dummy.length; i++) {
      	    for(int j = 0; j < dummy[0].length; j++)
            {
      	    if (dummy[i][j] >= 0)
              	System.out.print(" ");
          	System.out.print(dummy[i][j]+" ");
          	
            }
            System.out.println(" ");
		}
		// --------------------------------------------------------------------------------------
		
		if (currentSolution[width-1][height-1] != 0) {
			setSolution(currentSolution);
			return;
		}
		
		char[] posDir = findDirections(currentSolution, curX, curY);
		
		for (int i = 0;  i < posDir.length; i++) {
			char[][] newSolution = copyCharMatrix(currentSolution);
			if (posDir[i] == 'r') {
				newSolution[curX][curY] = posDir[i];
				solveMaze(newSolution, curX + 1, curY);
			}
			else if (posDir[i] == 'l') {
				newSolution[curX][curY] = posDir[i];
				solveMaze(newSolution, curX - 1, curY);
			}
			else if (posDir[i] == 't') {
				newSolution[curX][curY] = posDir[i];
				solveMaze(newSolution, curX, curY - 1);
			}
			else if (posDir[i] == 'b') {
				newSolution[curX][curY] = posDir[i];
				solveMaze(newSolution, curX, curY + 1);
			}
		}
	}
}
