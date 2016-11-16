import java.util.NoSuchElementException;


public class CircularList<T>
{
	private Node tail;
	
	class Node
	{
		T data;
		Node next;
		Node()
		{
			this(null,null);
		}
		
		Node(T data,Node next)
		{
			this.data = data;
			this.next = next;
		}
		
		Node getNext(){return next;}
		T getData(){return data;}
	}
	
	CircularList()
	{
		this.tail = new Node();
	}
	
	public void insert(T data)
	{
		Node temp = new Node(data,null);
		if(tail.getNext() == null)
		{
			tail.next = temp;
			temp.next = temp;
		}else
		{
			temp.next = tail.getNext().getNext();
			tail.getNext().next = temp;
			tail.next = temp;
		}
			
	}
	
	public T getLastElement(){return tail.getNext().getNext().getData();}
	
	public T remove() throws NoSuchElementException
	{
		if(tail.getNext() == null)throw new NoSuchElementException();
		T output = tail.getNext().getNext().getData();
		if(!hasOneEl())
			tail.getNext().next = tail.getNext().getNext().getNext();
		else
		{
			tail.getNext().next = null;
			tail.next = null;
		}
		return output;
			
	}
	
	public boolean hasOneEl(){return tail.getNext().getNext() == tail.getNext();}
	public boolean isEmpty(){return tail.next == null;}
	
	public String toString()
	{
		String output = "";
		if(tail.getNext() == null)return output;
		Node temp = tail.getNext().getNext();//take the "oldest" item in the list
		do
		{
			output+=temp.getData()+"\n";
			temp = temp.getNext();
		}while(temp!=tail.getNext().getNext());
		return output;
	}
}

