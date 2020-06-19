package MazeMaker;

public class Segment {
	/**
	 * These fields represent the Segment objects that are connected to this segment in the left, top, right and bottom direction.
	 * If this segment is not connected in a said direction, the respective field is {@code null}.
	 */
	Segment left, top, right, bottom;
	/**
	 * This field contains the direction in which the solution path will go, represented by a character ('l', 'r', 't', 'b').
	 * If this Segment is not part of the solution path, this field will be 0.
	 */
	char solutionPath;
	
	public Segment() {
		left = null;
		top = null;
		right = null;
		bottom = null;
		solutionPath = 0;
	}
	
	public void setLeft(Segment segment) {
		this.left = segment;
	}
	
	public void setRight(Segment segment) {
		this.right = segment;
	}
	
	public void setTop(Segment segment) {
		this.top = segment;
	}
	
	public void setBottom(Segment segment) {
		this.bottom = segment;
	}
	
	/**
	 * Connects this to other in the given direction. For example, if the given direction is 'right', then other will be connected to the right side of this.
	 */
	public void connect(char dir, Segment other) {
		if (!(dir == 'l' | dir == 'r' | dir == 't' | dir == 'b'))
			throw new IllegalArgumentException("Invalid direction");
		
		switch(dir) {
		case 'l':
			if (this.left != null)
				if (this.left.equals(other)) {
					break;
				} else {
					throw new IllegalArgumentException("This object already connects to another segment (left)");
				}
			if (other.right != null)
				if (other.right.equals(this))
					break;
				else
					throw new IllegalArgumentException("The given segment already connects to another segment (right)");
			this.setLeft(other);
			other.setRight(this);
			break;
			
		case 'r':
			if (this.right != null)
				if (this.right.equals(other))
					break;
				else
					throw new IllegalArgumentException("This object already connects to another segment (right)");
			if (other.left != null)
				if (other.left.equals(this))
					break;
				else
					throw new IllegalArgumentException("The given segment already connects to another segment (left)");
			this.setRight(other);
			other.setLeft(this);
			break;
			
		case 't':
			if (this.top != null) {
				if (this.top.equals(other)) {
					break;
				} else {
					throw new IllegalArgumentException("This object already connects to another segment (top)");
				}
			}
			if (other.bottom != null) {
				if (other.bottom.equals(this)) {
					break;
				} else {
					throw new IllegalArgumentException("The given segment already connects to another segment (bottom)");
				}
			}
			this.setTop(other);
			other.setBottom(this);
			break;
			
		case 'b':
			if (this.bottom != null)
				if (this.bottom.equals(other))
					break;
				else
					throw new IllegalArgumentException("This object already connects to another segment (bottom)");
			if (other.top != null)
				if (other.top.equals(this))
					break;
				else
					throw new IllegalArgumentException("The given segment already connects to another segment (top)");
			this.setBottom(other);
			other.setTop(this);
			break;
		}
	}
	
	public boolean formsLoop() {
		boolean formsLoop = false;
		if (this.right != null)
			if (this.right.bottom != null)
				if (this.right.bottom.left != null)
					formsLoop = (this.right.bottom.left.top == this);
		return formsLoop;
	}
	
	public void disconnect(Segment other) {
		boolean Removed = false;
		if (this.left != null)
			if (this.left.equals(other)) {
				this.left = null;
				other.right = null;
				Removed = true;
			}
		if (this.right != null)
			if (this.right.equals(other)) {
				this.right = null;
				other.left = null;
				Removed = true;
			}
		if (this.top != null)
			if (this.top.equals(other)) {
				this.top = null;
				other.bottom = null;
				Removed = true;
			}
		if (this.bottom != null)
			if (this.bottom.equals(other)) {
				this.bottom = null;
				other.top = null;
				Removed = true;
			}
		if (!Removed)
			throw new IllegalArgumentException("The two objects are not connected");
	}
	
	public void remove() {
		setLeft(null);
		setRight(null);
		setTop(null);
		setBottom(null);
		left.setRight(null);
		right.setLeft(null);
		top.setBottom(null);
		bottom.setTop(null);
	}
}
