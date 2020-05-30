package LinkedList;

public class LinkedList {
	Node First;
	Node Last;
	int size;
	
	public LinkedList() {
		First = null;
		Last = null;
		size = 0;
	}
	
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Add's the given value to the end of the list.
	 */
	public void add(int x, int y) {
		if (First == null) {
			Node node = new Node(x, y, null, null);
			node.previous = node;
			node.next = node;
			First = node;
			Last = node;
		} else {
			Node node = new Node(x, y, this.Last, null);
			this.Last.next = node;
			this.Last = node;
		}
		
		size++;
	}
	
	/**
	 * Returns the element at the given (zero-based) index and removes it from the list.
	 */
	public int[] remove(int index) {
		if (index >= size)
			throw new IllegalArgumentException("Index out of bounds.");
		int i = 0;
		Node currentNode = this.First;
		while (i < index) {
			currentNode = currentNode.next;
			i++;
		}
		
		if (currentNode.equals(this.First))
			this.First = currentNode.next;
		if (currentNode.equals(this.Last))
			this.Last = currentNode.previous;
		if (currentNode.previous != null)
			currentNode.previous.next = currentNode.next;
		if (currentNode.next != null)
			currentNode.next.previous = currentNode.previous;
		size--;
		
		int[] value = new int[2];
		value[0] = currentNode.x;
		value[1] = currentNode.y;
		return value;
	}
	
	/**
	 * Returns the element at the given (zero-based) index;
	 */
	public int[] getValueAt(int index) {
		if (index > size)
			throw new IllegalArgumentException("Index out of bounds.");
		int[] value = new int[2];
		int i = 0;
		Node currentNode = this.First;
		
		while (i < index) {
			currentNode = currentNode.next;
			i++;
		}
		
		value[0] = currentNode.x;
		value[1] = currentNode.y;
		
		return value;
	}
	
	public int getIndex(int x, int y) {
		Node currentNode = this.First;
		boolean found = false;
		int index = 0;
		
		while(!found && index < this.getSize()) {
			if (currentNode.x == x && currentNode.y == y)
				found = true;
			else if (currentNode.next != null) {
				currentNode = currentNode.next;
				index++;
				}
			else
				throw new IllegalArgumentException("element was not found");
		}
		return index;
	}
}
