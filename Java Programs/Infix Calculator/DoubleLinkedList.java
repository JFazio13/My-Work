import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
/*James Fazio
jfazio2@u.rochester.edu
No collaboration
*/
public class DoubleLinkedList<AnyType> {
	protected MyDoubleNode head;
	protected MyDoubleNode tail;
	
	public DoubleLinkedList() {
		head = new MyDoubleNode();
		tail = new MyDoubleNode(); 
		head.prev = null;
		head.next = tail;
		tail.prev = head;
		tail.next = null;
	}
	
	public boolean isEmpty() {
		if(head.next == tail) {
			return true;
		} else {
			return false;
		}
	}
	
	public void insertFirst(Object x) { // Used in Queue.enqueue()
		MyDoubleNode newNode = new MyDoubleNode();
		newNode.data = x;
		newNode.prev = head;
		newNode.next = head.next;
		newNode.prev.next = newNode;
		newNode.next.prev = newNode;
	}
	
	public Object peekLast() { // Originally intended to be used in Queue.peek(), but it wasn't needed.
		Object peek = null;
		if (head.next == tail) {
			peek = 0;
		} else {
			peek = tail.prev.data;
		}
		return peek;
	}
	
	public Object deleteLast() { // Used in Queue.dequeue()
		Object remove = null;
		if(head.next != tail) {
			remove = tail.prev.data;
			tail.prev = tail.prev.prev;
			tail.prev.next = tail;
		} else {
			System.out.println("Nothing to remove");
		}
		return remove;
	}
	
	public void printList() {
		MyDoubleNode first = head; // Using head directly resulted in the list being emptied in the process
		while(first.next != tail) {
			System.out.println((first.next.data).toString());
			first = first.next;
		}
	}
	
	public void printListReverse() { // Used in testing
		MyDoubleNode last = tail;
		while(last.prev != head) {
			System.out.println((last.prev.data).toString() + " ");
			last = last.prev;
		}
		System.out.println();
	}
	
	public void printListReverseLine() { // Prints list in reverse and on the same line (for displaying final postfix expression in queue)
		MyDoubleNode last = tail;
		while(last.prev != head) {
			System.out.print((last.prev.data).toString() + " ");
			last = last.prev;
		}
		System.out.println();
	}
	
	public void writeListReverse(File outFileName) { // Used in InfixToPostfix.writeOutput()
		try {
			PrintWriter output = new PrintWriter(outFileName);
			MyDoubleNode last = tail;
			while(last.prev != head) {
				output.println((last.prev.data).toString() + " ");
				last = last.prev;
			}
			System.out.println();
			output.close();
		} catch (FileNotFoundException e) {
			System.out.println("Could not write to file.");
		}
	}
	
}
