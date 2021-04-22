package maze;

public interface Stack<E>
{
	public void push(E v);
	public E pop();
	public E top();
	public int size();
	public boolean isEmpty();

}
