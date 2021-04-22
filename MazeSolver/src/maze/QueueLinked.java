package maze;
public class QueueLinked<E> implements Queue<E>
{
	private DoublyLinkedList<E> theQueue;

	public QueueLinked()
	{
		theQueue = new DoublyLinkedList<E>();
	}
	
	public void enqueue(E v) 
	{
		theQueue.addLast(v);
	}

	
	public E dequeue()
	{
		if(theQueue.size() == 0) return null;
		return theQueue.removeHead();
	}

	
	public E first() 
	{
		if(theQueue.size() == 0) return null;
		return theQueue.first();
	}

	
	public int size() 
	{
		return theQueue.size();
	}
	
	public boolean isEmpty() {
		return theQueue.isEmpty();
	}
	
	public String toString()
	{
		return theQueue.toString();
	}

}
