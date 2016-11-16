import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringStackImpl<T> implements StringStack<T>
{
	private LinkedList<T> list;
	private int index;
	
	StringStackImpl(int size)
	{
		this();
	}
	
	StringStackImpl()
	{
		this.list = new LinkedList<T>();
		this.index = 0;
	}
	
	public boolean isEmpty()
	{
		return index == 0;
	}
	
	public void push(T item)
	{
		list.addToFront(item);
		++index;
	}
	
	public T pop() throws NoSuchElementException
	{
		--index;
		return list.removeFromFront();
	}
	
	public T peek() throws NoSuchElementException
	{
		//Make it better...
		return list.getHead().getNext().getData();
	}
	
	public void printStack(PrintStream stream)
	{
		stream.println(list);
	}
	
	public int size()
	{
		return index;
	}
}
