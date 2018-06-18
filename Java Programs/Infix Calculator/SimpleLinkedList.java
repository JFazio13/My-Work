/*James Fazio
jfazio2@u.rochester.edu
No collaboration
*/

public interface SimpleLinkedList<AnyType> {
	public void insert(AnyType x);
    public void delete(AnyType x);
    public boolean contains(AnyType x);
    public AnyType lookup(AnyType x);
    public boolean isEmpty();
    public void printList();
}
