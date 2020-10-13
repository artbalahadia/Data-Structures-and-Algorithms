package lists;



/**
 *
 * @author Tony
 */
public class MyRecursiveLinkedList implements AdvancedList
{
    
	private Node head;
	private Node tail;
	private int size;
	
	private class Node {
		private Object item;
		private Node next;
		
		public Node(Node next, Object item) {
			this.item = item;
			this.next = next;
		}
		
	}
	
    @Override
    public int size()
    { 	
    	return size(head);
    }
    
    public int size(Node n) {
    	if(n == null) {
    		return 0;
    	} else {
    		return 1 + size(n.next);
    	}
    }

    @Override
    public boolean isEmpty()
    {
        return head == null;
    }

    @Override
    public boolean add(Object e)
    {
    	if(head == null) {
    		head = new Node(null, e);
    	} else {
    		addNode(head, e);
    		
    	}
    	size++;
    	return true;
    }
    
    public boolean addNode(Node n, Object e) {
    	
    	if(n.next != null) {
    		addNode(n.next, e);
    	} else {
    		n.next = new Node(null, e);
    	}
    	
    	
    	return true;
    	
    	
    	
    	
    	
//    	if(n == null) {
//    		if(head == null) {
//    			head = new Node(null, e);
////    			tail = head;
//    			System.out.println(e.toString() + " has been added");
//    			size++;
//    			return true;
//    		} else {
////    			tail.next = new Node(null, e);
//    			addNode(head, e);
//    			System.out.println(e.toString() + " has been added");
//    			size++;
//    			return true;
//    		}
//    	} else {
//    		Node oldTail = tail;
//    		tail = new Node(null, e);
//    		oldTail.next = newTail;
//    		System.out.println(e.toString() + " has been added");
//    		size++;
//    		return true;
//    	}
    	
    	
    }
    
    
    
    

    @Override
    public Object remove(int index)
    {
        return null;
    }

    @Override
    public void clear()
    {
    }

    @Override
    public Object get(int index)
    {
        return null;
    }

    @Override
    public Object set(int index, Object element)
    {
        return null;
    }

    @Override
    public boolean remove(Object o)
    {
        return false;
    }

    @Override
    public boolean contains(Object o)
    {
        return false;
    }

    @Override
    public int indexOf(Object o)
    {
        return -1;
    }

    @Override
    public int lastIndexOf(Object o)
    {
        return -1;
    }
}
