package lists;


/**
 *
 * @author Tony
 */
public class MyLinkedList implements AdvancedList
{
	/*
	 * Our linkedlist will consist of Nodes beginning from a head that has a property of item (the Object) and a next (that serves as
	 * the linking node). The size property will keep track of the number of nodes in our list and will be incremented/decremented
	 * as we add and remove nodes.
	 */
	private Node head;
	private Node tail;
	private int size;
	
	private class Node {
		private Object item;
		private Node next;
	}
    
    @Override
    public int size()
    {
        return size;
    }

    @Override
    public boolean isEmpty()
    {
        return head == null;
    }

    @Override
    public boolean add(Object e)
    {
    	/*
    	 * Adds a node at the end of the list (enqueue)
    	 */
    	Node oldTail = tail;
    	tail = new Node();
    	tail.item = e;
    	tail.next = null;
    	if(isEmpty()) {
    		head = tail;
    	} else {
    		oldTail.next = tail;
    	}
    	size++;
//    	System.out.println(tail.item.toString() + " has been added");
    	return true;
    }

    @Override
    public Object remove(int index)
    {
    	/*
    	 * Removes a node at an index using a loop that checks each "next" node. The iteration stops at the node before 
    	 * the index and changes its next to the index's next (if a node is available). 
    	 */
    	if(isEmpty() || index < 0 || index > size) {
    		return null;
    	}
    	  	
    	Node tempNode = head;
    	
    	if(index == 0) {
    		head = tempNode.next;
    		size--;
    		return tempNode.item;
    	}
    	
    	for(int i = 0; i < index; i++) {
    		if(i == index-1) {
    			Node deleteNode = tempNode.next;
    			Object deleteItem = deleteNode.item;
    			size--;
    				if(deleteNode.next != null) {
    					tempNode.next = deleteNode.next;
    				} else {
    					tempNode.next = null;
    				}
//    			System.out.println(deleteItem.toString() + " has been deleted");
    			return deleteItem;
    		}
    		tempNode = tempNode.next;
    	}

        return null;
    }

    @Override
    public void clear()
    {
    	if(isEmpty()) {
    		return;
    	}
    	
    	while(!isEmpty()) {
    		Node deleteNode = head;
        	head = head.next;
        	deleteNode = null;
        	size--;
    	}
    }

    @Override
    public Object get(int index)
    {
    	// loops through the list until we hit index and returns the item of the node at index
    	if(isEmpty() || index < 0 || index > size) {
    		return null;
    	}
    	
    	Node tempNode = head;
    	
    	
    	for(int i = 0; i <= index; i++) {
    		if(i == index) {
    			return tempNode.item;
    		}
    		tempNode = tempNode.next;
    	}
    	
        return null;
    }

    @Override
    public Object set(int index, Object element)
    {
    	// loops through the list until we hit index and changes the item of the node at index
    	if(isEmpty() || index < 0 || index > size) {
    		return null;
    	}
    	
    	Node tempNode = head;
    
    	for(int i = 0; i <= index; i++) {
    		if(i == index) {
    			Object oldItem = tempNode.item;
    			tempNode.item = element;
    			return oldItem;
    		}
    		tempNode = tempNode.next;
    	}
    	
        return null;
    }

    @Override
    public boolean remove(Object o)
    {
    	// same implementation as remove(index), but uses object parameter in the loop to find the matching node.
    	if(isEmpty() || !contains(o)) {
    		return false;
    	}
    	
    	Node tempNode = head;
    	
		if(tempNode.item == o) {
			head = tempNode.next;
			size--;
			return true;
		}
    	
    	for(int i = 0; i < size; i++) {
    		if(tempNode.next.item == o) {
    			Node deleteNode = tempNode.next;
    			if(deleteNode.next != null) {
    				tempNode.next = deleteNode.next;
    			} else {
    				tempNode.next = null;
    			}
    			size--;
//    			System.out.println(o.toString() + " has been deleted");
    			return true;
    		}
    		tempNode = tempNode.next;
    	}
        return false;
    }

    @Override
    public boolean contains(Object o)
    {
    	if(isEmpty()) {
    		return false;
    	}
    	
    	Node tempNode = head;
    	
    	for(int i = 0; i < size; i++) {
    		if(tempNode.item == o) {
    			return true;
    		}
    		tempNode = tempNode.next;
    	}
    	
    	
        return false;
    }

    @Override
    public int indexOf(Object o)
    {
    	if(isEmpty()) {
    		return -1;
    	}
    	
    	Node tempNode = head;
    	
    	for(int i = 0; i < size; i++) {
    		if(tempNode.item == o) {
    			return i;
    		}
    		tempNode = tempNode.next;
    	}
    	
        return -1;
    }

    @Override
    public int lastIndexOf(Object o)
    {
    	if(isEmpty()) {
    		return -1;
    	}
    	
    	if(!contains(o)) {
    		return -1;
    	}
    	
    	Node tempNode = head;
    	int lastIndex = 0;
    	
    	for(int i = 0; i < size; i++) {
    		if(tempNode.item == o) {
    			lastIndex = i;
    		}
    		tempNode = tempNode.next;
    	}
    	
        return lastIndex;
    }
}
