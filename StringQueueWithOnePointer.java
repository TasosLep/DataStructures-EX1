import java.util.NoSuchElementException;
import java.io.PrintStream;

public class StringQueueWithOnePointer<T> implements StringQueue<T> 
{
	private CircularList<T> c_list;
	private int index;
	
	StringQueueWithOnePointer()
	{
		c_list = new CircularList<T>();
		index = 0;
	}
	
	public boolean isEmpty()
	{
		return index == 0;
	}
	
	public void put(T item)
	{
		c_list.insert(item);
		index++;
	}
	
	public T get() throws NoSuchElementException
	{
		if(isEmpty())throw new NoSuchElementException();
		T data = c_list.remove();
		index--;
		return data;
	}
	
	public T peek() throws NoSuchElementException
	{
		return c_list.getLastElement();
	}
	
	public void printQueue(PrintStream stream)
	{
		stream.println(c_list);
	}
	
	public int size()
	{
		return index;
	}
}
