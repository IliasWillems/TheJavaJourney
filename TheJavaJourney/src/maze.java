import java.util.Random;


public class maze {
	int[][] body;
	int size;
	boolean found;
	
	public maze(int length) {
		body = new int [length][];
		
		for (int i = 0; i < length; i++) {
		body[i] = new int[length];	
		}
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if (i == 0 | j == 0 | i == length - 1 | j == length-1)
					body[i][j] = -2;
			}
		}
		
		size = length;
		found = false;
	}
	
	int[][] reduce(int[][] array) {
		 int actualSize = 0;
		 for (int i = 0; i < array.length; i++) {
			 if (array[i][0] != -1)
					 actualSize++;
		 }
		 
		 int [][] reducedArray = new int[actualSize][2];
		 int index = 0;
		 for (int i = 0; i < array.length; i++) {
			 if (array[i][0] != -1) {
				 reducedArray[index][0] = array[i][0];
				 reducedArray[index][1] = array[i][1];
				 index++;
			 }
		 }
		 return reducedArray;
	 }
	
	int[][] findPossibleDirections(int[][] matrix, int x, int y) {
		int[][] pos = new int[4][2];
		
		if (matrix[x+1][y] == 0) {
			pos[0][0] = x+1;
			pos[0][1] = y;
		} else {
			pos[0][0] = -1;
			pos[0][1] = -1;
		}
		
		if (matrix[x][y+1] == 0) {
			pos[1][0] = x;
			pos[1][1] = y+1;
		} else {
			pos[1][0] = -1;
			pos[1][1] = -1;
		}
		
		if (matrix[x][y-1] == 0) {
			pos[2][0] = x;
			pos[2][1] = y-1;
		} else {
			pos[2][0] = -1;
			pos[2][1] = -1;
		}
		
		if (matrix[x-1][y] == 0) {
			pos[3][0] = x-1;
			pos[3][1] = y;
		} else {
			pos[3][0] = -1;
			pos[3][1] = -1;
		}
		
		return reduce(pos);
		
	}
	
	int[][] copyMatrix(int[][] matrix) {
		int [][] newMatrix = new int[matrix.length][];
		for (int i = 0; i < matrix.length; i++) {
			int[] newrow = new int[matrix.length];
			for (int j = 0; j < matrix.length; j++) {
				newrow[j] = matrix[i][j];
			}
			newMatrix[i] = newrow;
		}
		return newMatrix;
	}
	
	int getRandomNumber(int min, int max) {
	    Random r = new Random();
	    return r.nextInt((max - min) + 1) + min;
	}
	
	void createMainPath (int[][] matrix, int currentx, int currenty, int step) {
		
		if (matrix[size-2][size-2] != 0 | this.body[size-2][size-2] != 0) {
			if (!this.found) {
				this.body = matrix;
				this.found = true;
			}
			return;
		}
		
		int[][] possible = findPossibleDirections(matrix, currentx, currenty);
		if (possible.length == 0) {
			return;
		}
		
		for (int i = 0; i < possible.length; i++) {
			
			int [][] newmatrix = copyMatrix(matrix);
			newmatrix[currentx][currenty] = step;
			
			//search a value for r (randomly) such that possible[r] hasn't been used yet.
			int r = getRandomNumber(0, possible.length - 1);
			while (possible[r][0] == -1)
				r = getRandomNumber(0, possible.length - 1);
			
			//use this value for r and flag it as 'been used'.
			createMainPath (newmatrix, possible[r][0], possible[r][1], step+1);
			possible[r][0] = -1;
		}
	}
	
	boolean emptySpaceInMaze() {		
		boolean empty = false;
		
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				if (this.body[i][j] == 0)
					empty = true;
			}
		}
		return empty;
	}
	
	int getMaxMatrix() {
		int max = 0;
		
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				if (this.body[i][j] > max)
					max = this.body[i][j];
			}
		}
		return max;
	}
	
	int[] locate(int number) {
		int[] location = new int[2];
		for (int i = 1; i < this.size-1; i++) {
			for (int j = 1; j < this.size-1; j++) {
				if (this.body[i][j] == number) {
					location[0] = i;
					location[1] = j;
				}
			}
		}
		return location;
	}
	
	int[][] checkNeighbouringZero(int[] coordinates) {
		int x = coordinates[0];
		int y = coordinates[1];
		int[][] pos = new int[4][2];
		
		if (this.body[x+1][y] == 0) {
			pos[0][0] = x+1;
			pos[0][1] = y;
		} else {
			pos[0][0] = -1;
			pos[0][1] = -1;
		}
		
		if (this.body[x][y+1] == 0) {
			pos[1][0] = x;
			pos[1][1] = y+1;
		} else {
			pos[1][0] = -1;
			pos[1][1] = -1;
		}
		
		if (this.body[x][y-1] == 0) {
			pos[2][0] = x;
			pos[2][1] = y-1;
		} else {
			pos[2][0] = -1;
			pos[2][1] = -1;
		}
		
		if (this.body[x-1][y] == 0) {
			pos[3][0] = x-1;
			pos[3][1] = y;
		} else {
			pos[3][0] = -1;
			pos[3][1] = -1;
		}
		
		return reduce(pos);
		
	}
	
	int[][] findZeroNextToPath(boolean searchMain) {
		int[] zeroCoordinates = new int[2];
		int[] origenCoordinates = new int[2];
		
		int MainOrSidePath;
		
		if (searchMain)
			MainOrSidePath = 1;
		else
			MainOrSidePath = getRandomNumber(1, 3);
		
		//Search for a zero next to the main path.
		if (MainOrSidePath < 3) {
			boolean found = false;
			
			int max = getMaxMatrix();
			int start = getRandomNumber(1, max);
			
			for (int i = 0; i < max; i++) {
				if (!found && (start+i)%max != 0 && (start+i)%max != 1) {
					
					System.out.println("looking for number " + (start+i)%max);
					
					int [] coordinates = locate((start+i)%max);
					int [][] neighbouringZero = checkNeighbouringZero(coordinates);
					
					if (neighbouringZero.length != 0) {
						found = true;
						
						int randomDirection = getRandomNumber(0, neighbouringZero.length -1);
						zeroCoordinates = neighbouringZero[randomDirection];
						origenCoordinates = coordinates;
					}
				}
			}
			if (!found)
				MainOrSidePath = 3;
		}
		if (MainOrSidePath == 3) {
			boolean found = false;
			
			System.out.println("Finding zero next to sidePath");
			
			for (int counter = 0; counter < this.size*(3/2); counter++) {
				int randomRow = getRandomNumber(1, this.size-2);
				for (int i = 0; i < this.size-2; i++) {
					if (this.body[randomRow][i] == 1) {
						int[] coordinates = {randomRow, i};
						int[][] neighbouringZero = checkNeighbouringZero(coordinates);
						
						if (neighbouringZero.length != 0) {
							found = true;
							
							int randomDirection = getRandomNumber(0, neighbouringZero.length -1);
							zeroCoordinates = neighbouringZero[randomDirection];
							origenCoordinates = coordinates;
						}
					}
				}
			}
			
			for (int row = 0; row < this.size-1; row++) {
				for (int i = 0; i < this.size-2; i++) {
					if (this.body[row][i] == 1) {
						int[] coordinates = {row, i};
						int[][] neighbouringZero = checkNeighbouringZero(coordinates);
						
						if (neighbouringZero.length != 0) {
							found = true;
							
							int randomDirection = getRandomNumber(0, neighbouringZero.length -1);
							zeroCoordinates = neighbouringZero[randomDirection];
							origenCoordinates = coordinates;
						}
					}
				}
			}
			
			if (!found)
				return findZeroNextToPath(true);
		}
		
		int[][] rtrn = new int[2][2];
		rtrn[0] = zeroCoordinates;
		rtrn[1] = origenCoordinates;
		return rtrn;
	}
	
	path findSidePathWithCoords(int[] coords, path[] sidePathList) {
		path rtrn = new path();
		boolean PROCEDE;
		
		for (int i = 0; i < sidePathList.length; i++) {
			path current = sidePathList[i];
			PROCEDE = true;
			while (PROCEDE) {
				if (current.matrixCoordinates == coords)
					rtrn = current;
				
				if (current.pathOrigen != null) {
					current = current.pathOrigen;
				} else {
					PROCEDE = false;
				}
			}
		}
		return rtrn;
	}
	
	path buildPath(path SidePath) {
		
		for(int i = 0; i < this.size; i++)
    	{
      	    for(int j = 0; j < this.size; j++)
            {
      	    if (this.body[i][j] >= 0)
              	System.out.print(" ");
          	System.out.print(this.body[i][j]+" ");
          	
            }
            System.out.println(" ");
        }
		
		int[][] pos = checkNeighbouringZero(SidePath.matrixCoordinates);
		
		if (getRandomNumber(0, this.size) == this.size-1 | pos.length == 0) {
			SidePath.endPath = true;
			return SidePath;
			
		} else {
			int randomDirection = getRandomNumber(0, pos.length -1);
			path newPath = new path();
			newPath.pathOrigen = SidePath;
			newPath.matrixCoordinates = pos[randomDirection];
			this.body[newPath.matrixCoordinates[0]][newPath.matrixCoordinates[1]] = 1;
			return buildPath(newPath);
		}
	}
	
	public static path[] append(path[] list, path element) {
		path[] newlist;
		if (list.length == 0) {
			newlist = new path[1];
			newlist[0] = element;
		} else {
			newlist = new path[list.length + 1];
			for (int i = 0; i < list.length; i++) {
				newlist[i] = list[i];
			}
			newlist[list.length] = element;
		}
		return newlist;
	}
	
	void fillMaze() {
		path[] sidePathEndList = new path[0];
		
		while (emptySpaceInMaze()) {
			
			for(int i = 0; i < this.size; i++)
	    	{
	      	    for(int j = 0; j < this.size; j++)
	            {
	      	    if (this.body[i][j] >= 0)
	              	System.out.print(" ");
	          	System.out.print(this.body[i][j]+" ");
	          	
	            }
	            System.out.println(" ");
	        }
			
			int[][] coords = findZeroNextToPath(false);
			if (this.body[coords[1][0]][coords[1][1]] == 1) {
				path SidePath = new path();
				SidePath.pathOrigen = findSidePathWithCoords(coords[1], sidePathEndList);
				SidePath.matrixCoordinates = coords[0];
				this.body[coords[0][0]][coords[0][1]] = 1;
				
				path last = buildPath(SidePath);
				sidePathEndList = append(sidePathEndList, last);
			} else {
				path SidePath = new path();
				SidePath.mainPathOrigen = this.body[coords[1][0]][coords[1][1]];
				SidePath.matrixCoordinates = coords[0];
				this.body[coords[0][0]][coords[0][1]] = 1;

				path last = buildPath(SidePath);
				sidePathEndList = append(sidePathEndList, last);
			}
		}
	}
}
