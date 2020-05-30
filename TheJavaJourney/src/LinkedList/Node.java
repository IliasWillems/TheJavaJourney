package LinkedList;

public class Node {
	int x;
	int y;
	Node previous;
	Node next;
	
	public Node(int x, int y, Node prev, Node next) {
		this.x = x;
		this.y = y;
		this.previous = prev;
		this.next = next;
	}
}
