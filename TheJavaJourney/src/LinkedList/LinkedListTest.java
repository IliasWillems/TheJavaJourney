package LinkedList;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LinkedListTest {

	@Test
	void test() {
		LinkedList List = new LinkedList();
		
		List.add(1, 1);
		List.add(1, 2);
		List.add(0, 0);
		List.add(-1, 0);
		assert List.First.x == 1;
		assert List.First.y == 1;
		assert List.First.next.next.x == 0;
		assert List.Last.y == 0;
		assert List.size == 4;
		
		List.remove(2);
		assert List.First.x == 1 && List.First.x == 1;
		assert List.Last.x == -1 && List.Last.y == 0;
		assert List.Last.previous.x == 1 && List.Last.previous.y == 2;
		assert List.First.next.x == 1 && List.First.next.y == 2;
		assert List.size == 3;
		
		List.remove(2);
		assert List.First.x == 1 && List.First.x == 1;
		System.out.println(List.Last.x + " " + List.Last.y);
		assert List.Last.x == 1 && List.Last.y == 2;
		assert List.First.next.equals(List.Last);
		
		LinkedList List1 = new LinkedList();
		
		List1.add(1, 1);
		List1.add(2, 2);
		List1.add(6, 5);
		List1.add(2, -9);
		assert List1.getSize() == 4;
		assert List1.getValueAt(1)[0] == 2 && List1.getValueAt(1)[1] == 2;
		assert List1.getIndex(6, 5) == 2;
	}

}
