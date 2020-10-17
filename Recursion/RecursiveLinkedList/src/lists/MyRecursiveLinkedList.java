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
    		add(head, e);	
    	}
    	size++;
//    	System.out.println(e.toString() + " has been added");
    	return true;
    }
    
    public boolean add(Node n, Object e) {
    	
    	if(n.next != null) {
    		add(n.next, e);
    	} else {
    		n.next = new Node(null, e);
    	}
    	return true;
    }
    

    @Override
    public Object remove(int index)
    {
        if(isEmpty() || index < 0 || index > size) {
        	return null;
        }
//        System.out.println("our size is " + size);  
//        System.out.println("attempt to remove " + index);
         
        Node removedNode = remove(head, index);
        size--;
//        System.out.println("deleted item is " + removedNode.item.toString());
        return removedNode.item;
    }
    
    public Node remove(Node node, int index) {

    	Node tempNode = node;
    	
    	if(index == 0) {
    		head = tempNode.next;
    		return tempNode;
    	}
    	
    	if(index-1 == 0) {
			Node deleteNode = tempNode.next;
				if(deleteNode.next != null) {
					tempNode.next = deleteNode.next;
				} else {
					tempNode.next = null;
				}
	
			return deleteNode;
    	}	
//    	System.out.println("tempNode.next is " + tempNode.next.item.toString() + " and index is " + index);
    	tempNode = remove(tempNode.next, index-1);
    	
    	return tempNode;
    }
    

    @Override
    public void clear()
    {
    	if(isEmpty()) {
    		return;
    	}
    	
    	if(head != null) {
    		Node oldHead = head;
    		head = head.next;
    		oldHead = null;
    		size--;
    		clear();
    	}	
    }

    @Override
    public Object get(int index)
    {
    	if(isEmpty() || index < 0 || index > size) {
    		return null;
    	}
    	Node getNode = get(head, index);
        return getNode.item;
    }
    
    public Node get(Node node, int index) {
    	Node tempNode = node;	
    	if(index == 0) {
    		return tempNode;
    	}
    	tempNode = get(tempNode.next, index-1);
    	return tempNode;
    }
    

    @Override
    public Object set(int index, Object element)
    {
    	if(isEmpty() || index < 0 || index > size) {
    		return null;
    	}
    	Object setNode = set(head, index, element);
        return setNode;
    }
    
    public Object set(Node node, int index, Object element) {
    	Node tempNode = node;
    	if(index == 0) {
//    		System.out.println("changing node " + node.item.toString() + " to " + element.toString());
    		Object oldItem = tempNode.item;
    		tempNode.item = element;
    		return oldItem;
    	}
    	Object oldItem = set(node.next, index-1, element);
    	return oldItem;
    }
    

    @Override
    public boolean remove(Object o)
    {
//    	System.out.println("Our head is " + head.item.toString());
    	
        if(isEmpty() || !contains(o)) {
        	return false;
        }
//        System.out.println(o.toString() + " is head");

        if(remove(head, o) != null) {
        	return true;
        }
        
//        System.out.println("Did not remove " + o.toString());
        return false;
    }
    
    public Node remove(Node node, Object o) {
    	Node tempNode = node;
    	
//    	System.out.println("attempt " + o.toString());
    		
    	if(tempNode.item == o) {
//            System.out.println("removing " + o.toString());
    		head = tempNode.next;
    		size--;
    		return tempNode;
    	}
    	
    	if(tempNode.next == null) {
    		return null;
    	}

        if(tempNode.next.item == o) {
//        	System.out.println("Deleting " + o.toString());
        	Node deleteNode = tempNode.next;
        	if(deleteNode.next != null) {
        		tempNode.next = deleteNode.next;
        	} else {
        		tempNode.next = null;
        	}
        	size--;
        	return deleteNode;
        } else {
        	tempNode = remove(tempNode.next, o);
        }
    	return tempNode;	
    }
    
    

    @Override
    public boolean contains(Object o)
    {
    	
    	if(isEmpty()) {
    		return false;
    	}
    	
    	return contains(head, o);
    }
    
    public boolean contains(Node node, Object o) {
    	Node tempNode = node;
    	boolean isContain;
    	
    	
    	if(tempNode == null) {
    		isContain = false;
    	}
    	
    	if(tempNode.item == o) {
    		isContain = true;
    	} else if(tempNode.next != null) {
    		isContain = contains(node.next, o);
    	} else {
    		isContain = false;
    	}
    	
    	return isContain;
    	
    }
    
    

    @Override
    public int indexOf(Object o)
    {
    	if(isEmpty() || !contains(o)) {
    		return -1;
    	}
    	
        return indexOf(head, o, 0);
    }
    
    
    public int indexOf(Node node, Object o, int count) {
    	Node tempNode = node;
    	int counter = count;
    	
    	if(tempNode == null) {
    		return -1;
    	}
    	
    	if(tempNode.item == o) {
    		return counter;
    	} else {
    		counter = indexOf(node.next, o, count++);
    	}
    	
    	return counter;
    	
    }

    @Override
    public int lastIndexOf(Object o)
    {
    	if(isEmpty() || !contains(o)) {
    		return -1;
    	}
        return lastIndexOf(head, o, 0, 0);
    }
    
    public int lastIndexOf(Node node, Object o, int count, int lastIndex) {
    	Node tempNode = node;
    	int counter = count;
    	int last = lastIndex;
    	
    	
    	if(tempNode == null) {
    		return last;
    	}
    	
    	if(tempNode.item == o) {
    		last = counter;
    		if(tempNode.next != null) {
//    			System.out.println("last index is now " + last + " and count is " + counter) ;
    			last = lastIndexOf(node.next, o, counter+1, last);
    		} 
    	} else {
    		last = lastIndexOf(node.next, o, counter+1, last);
    	}
    	
    	return last;
    }
    
    
    
    
    
    
    
    
}
