/*James Fazio
jfazio2@u.rochester.edu
No collaboration
*/
public class Stack<AnyType> {
	private LinkedList<AnyType> myStack = new LinkedList();
	
	public void push(AnyType t) {
		myStack.insert(t);
	}
	
	public Object pop() {
		return myStack.removeFirst();
	}
	
	public boolean isEmpty() {
		return myStack.isEmpty();
	}
	
	public Object peek() { // Used in conversion method to access the top of the stack without actually popping it
		return myStack.peekFirst();
	}
	
	public void displayStack() { // Used for testing purposes only
		System.out.println("Stack:");
		myStack.printList();
	}
}
