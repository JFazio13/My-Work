import java.util.*;

/*James Fazio
jfazio2@u.rochester.edu
No collaboration
*/

public class LinkedList<AnyType> implements SimpleLinkedList {
	
	MyNode head;
	
	public LinkedList() {
		head = null;
	}

	public boolean isEmpty() { // If variable head is null, the list is empty
		if(head == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public void insert(Object x) { // Runtime should be O(1)
			MyNode link = new MyNode();
			link.data = x;
			link.next = head;
			head = link;
	}
	
	public void printList() { // Runtime should be O(n)
		MyNode first = head; // Using head directly resulted in the list being "emptied" in the process
		while(first != null) {
			System.out.println((first.data).toString());
			first = first.next;
		}
	}
	
	public boolean contains(Object x) { // Runtime should be O(n)
		boolean contains = false;
		MyNode first = head; // Using head directly resulted in the list being "emptied" in the process
		while(first != null) {
			if(first.data.equals(x)) {
				contains = true;
				break;
			} else {
				first = first.next;
			}
		}
		if(contains) {
			return true;
		} else {
			return false;
		}
	}
	
	public AnyType lookup(Object x) {
		boolean found = false;
		AnyType objectFound = null;
		MyNode first = head; // Using head directly resulted in the list being "emptied" in the process
		System.out.println("Searching for " + x + "...");
		while(first != null) {
			if(first.data.equals(x)) {
				found = true;
				objectFound = (AnyType) first.data;
				break;
			} else {
				first = first.next;
			}
		}
		if(found) {
			System.out.print("Found element: ");
			return objectFound;
		} else {
			System.out.print("No element found!" + "\n");
			return null;
		}
	}
	
	public Object removeFirst() { // Used for Stack.pop()
		Object remove = null;
		if (head == null) {
			System.out.println("Nothing to remove.");
		} else {
			remove = head.data;
			head = head.next;
		}
		return remove;
	}
	
	public Object peekFirst() { // Used for Stack.peek()
		Object peek = null;
		if (head == null) {
			peek = 0;
		} else {
			peek = head.data;
		}
		return peek;
	}
	
	public void delete(Object x) {
		
		boolean contains = false;
		boolean firstNode = false;
		MyNode first = head; // Using head directly resulted in the list being "emptied" in the process
		MyNode first2 = head; // Resets temporary head storage after learning the position of requested item to be deleted
		int positionIndicator = 1; // Used to mark the position of the item to be deleted
		Object remove = null;
		
		while(first != null) {
			if(first.data.equals(x)) {
				contains = true;
				remove = first.data;
				if(positionIndicator == 1) { // Used if the item to be deleted is the first node; avoids a NullPointerException
					head = head.next;
					firstNode = true;
					System.out.println("Object '" + remove + "' deleted" + "\n");
					break;
				}
				break;
			} else {
				first = first.next;
				positionIndicator++;
			}
		}
		
		if(!firstNode) {
			int i = 1;
			while(first2 != null) {
				if(i == positionIndicator - 1) { // With the position of the item stored in positionIndicator, the delete operation can continue.
					break;
				} else {
					first2 = first2.next;
					i++;
				}
			}
			if(contains) {
				MyNode delete = first2.next.next;
				first2.next = delete;
				System.out.println("Object '" + remove + "' deleted" + "\n");
			} else {
				System.out.println("Nothing to delete; object '" + x + "' not found" + "\n");
			}
		}
		
	}
	
}
