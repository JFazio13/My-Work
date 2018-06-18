/*James Fazio
jfazio2@u.rochester.edu
No collaboration
*/
public class Queue<AnyType> {
	private DoubleLinkedList<AnyType> myQueue = new DoubleLinkedList();
	
	public void enqueue(AnyType t) {
		myQueue.insertFirst(t);
	}
	
	public Object dequeue() {
		return myQueue.deleteLast();
	}
	
	public Object peek() { // Originally intended to be used in the evaluation method to view the front of the queue without dequeuing it, but it was not needed.
		return myQueue.peekLast();
	}
	
	public void displayPostfix() { // Displays the converted expression in the console
		System.out.print("Postfix expression: ");
		myQueue.printListReverseLine();
	}
	
	public boolean isEmpty() {
		return myQueue.isEmpty();
	}
	
	public void displayQueue() { // Used for testing purposes only
		System.out.println("Queue:");
		myQueue.printList();
	}
}
