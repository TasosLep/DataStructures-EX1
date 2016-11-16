import java.io.PrintStream;import java.util.NoSuchElementException; /** * Defines the methods for a FIFO queue that handles String items */public class StringQueueImpl<T> implements StringQueue<T>
{	private LinkedList<T> list;
	private int index;
		StringQueueImpl(int size)
	{
		this();    }
			StringQueueImpl()    {		this.list = new LinkedList<T>();		this.index = 0;    }
   	public boolean isEmpty()	{
		return index == 0;
	}		 	public void put(T item)//putting an item in the queue	{		list.addToBack(item);		++index;	}			/**         * remove and return the oldest item of the queue         * @return oldest item of the queue	 * @throws NoSuchElementException if the queue is empty	 */	public T get() throws NoSuchElementException	{		if(isEmpty())		{			throw new NoSuchElementException();		}		else		{			--index;			return list.removeFromFront();		}	}        /**	 * return without removing the oldest item of the queue 	 * @return oldest item of the queue	 * @throws NoSuchElementException if the queue is empty	 */	public T peek() throws NoSuchElementException{				if(isEmpty())			throw new NoSuchElementException();		else			return list.getHead().getNext().getData();					}	/**	 * print the elements of the queue, starting from the oldest          * item, to the print stream given as argument. For example, to          * print the elements to the	 * standard output, pass System.out as parameter. E.g.,	 * printQueue(System.out);	 */	public void printQueue(PrintStream stream)
	{
		stream.println(list);
	}		public int size()
	{
		return index;
	}}