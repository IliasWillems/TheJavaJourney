
public class path {
	int mainPathOrigen;
	path pathOrigen;
	boolean endPath;
	int[] matrixCoordinates;
	
	public path() {
		mainPathOrigen = -1;
		pathOrigen = null;
		matrixCoordinates = null;
		endPath = false;
	}
	
	void linkAfterSidePath(path sidePath) {
		if (this.mainPathOrigen == -1 && this.pathOrigen == null)
			this.pathOrigen = sidePath;
		else
			System.out.println("tried to link a sidepath that was already connected (side)");
	}
	
	void linkAfterMainPath(int pathNumber) {
		if (this.mainPathOrigen == -1 && this.pathOrigen == null)
			this.mainPathOrigen = pathNumber;
		else
			System.out.println("tried to link a sidepath that was already connected (main)");
	}
	
}
