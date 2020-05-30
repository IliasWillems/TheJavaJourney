package MazeMaker;

import java.util.Arrays;
import java.util.Random;

import LinkedList.LinkedList;

public class MainPathFinder {
	public static int[][] testMatrix(int width, int height) {
		int[][] matrix = new int[width][height];
		int step = 1;
		
		for (int j = 0; j < height; j++) {
			for (int i = 0; i <= j; i++) {
				if (j-i < height && i < width) {
					matrix[i][j-i] = step;
					step++;
				}
			}
		}
		for (int i = 1; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (i+j < width && height-1-j >= 0) {
					matrix[i+j][height-1-j] = step;
					step++;
				}
			}
		}
		return matrix;
	}
	
	public static int[][] createMainPath(int width, int height) {
		int[][] matrix = new int[width][height];
		Random RandomIntegerGenerator = new Random();
		
		// Make a list with all the x-y-couples of the matrix
		LinkedList elements = new LinkedList();
		for (int j = 0; j < height; j++) {
			for (int i = 0; i <= j; i++) {
				if (j-i < height && i < width) {
					elements.add(i, j-i);
				}
			}
		}
		
		for (int i = 1; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (i+j < width && height-1-j >= 0) {
					elements.add(i+j, height-1-j);
				}
			}
		}
		
		elements.remove(0);
		matrix[0][0] = 1;
		
		// Generate a random position in the matrix and change that positions value to 'counter'. Increase 'counter'.
		int counter = 2;
		int step = 1;
		while (matrix[width-1][height-1] == 0) {
			
			// Create a random sample of indices. The size of the random sample is based on the size of the matrix.
			int[] sample = new int[(int) (width*height)];
			for (int i = 0; i < sample.length; i++) {
				sample[i] = RandomIntegerGenerator.nextInt(elements.getSize());
			}
			
			// Sort the list of indices and pick the n'th smallest number with n the number of times this loop was called. If n would be bigger then the 
			// length of the sample, the index will be sample[sample.length-1].
			Arrays.sort(sample);
			int index;
			boolean skip = false;
			if (step < sample.length - 1) {
				if (step > sample.length/3 && step < sample.length*(2/3))
					skip = true;
				index = sample[step];
			} else {
				index = sample[sample.length - 1];
			}
			
			if(!skip) {
				int[] value = elements.remove(index);
				matrix[value[0]][value[1]] = counter;
				counter++;
			}
			
			// For some reason, setting the step size with which you select the n-th order statistic translates to having around about
			// (width*height)/step random elements in the matrix.
			step += (int) ((width*height)/10);
		}
		return matrix;
	}
	
	/**
	 * Returns the position of the given value in the given matrix
	 */
	public static int[] getPositionOf(int[][] matrix, int value) {
		int[] position = new int[2];
		boolean found = false;
		
		for (int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == value && !found) {
					found = true;
					position[0] = i;
					position[1] = j;
				}
			}
		}
		
		return position;
	}
}
