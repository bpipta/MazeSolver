package maze;

public class StackLinked<E> implements Stack<E> {
	
	private DoublyLinkedList<E> theStack;
	
	public StackLinked()
	{
		theStack = new DoublyLinkedList<>();
	}

	@Override
	public void push(E v) {
		theStack.addLast(v);
	}

	@Override
	public E pop() {
		
//		if(isEmpty()) {
//			return null;
//		}
		return theStack.removeTail();
	}

	@Override
	public E top() {
		
		if(isEmpty()) {
			return null;
			
		}
		E result= theStack.removeTail();
		theStack.addLast(result);
		return result;
		
	}

	@Override
	public int size() {
		
		return theStack.size();
	}
	
	public boolean isEmpty() {
		return theStack.isEmpty();
	}

}
