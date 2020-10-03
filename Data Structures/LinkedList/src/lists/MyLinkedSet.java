package lists;


/**
 *
 * @author Tony
 */
public class MyLinkedSet implements AdvancedList
{
	/*
	 * This list implements a linkedlist with a functionality of a set. Each addition to the linkedlist is checked if a duplicate
	 * exists - in order to mimic the same ruling of a set. 
	 * 
	 * This linkedlist implements the same methods as our previous list EXCEPT our add and set methods, will validates if there are
	 * existing nodes with the same item before adding/udpating.
	 * 
	 * Having the same tester class, we receive a few errors due to the difference in functionality.
	 * 		+ get/set/remove(object) -  since our set does not accept similar items, the size does not reflect the test cases
	 * 				which refers to the size of the array with the objects to be added (i.e. advancedValues[]). This causes index
	 * 				parameters to throw an error because it will not check the same position.
	 * 		+ lastIndexOf - will always be an error because we will only have one instance of each object in our set.
	 * 
	 * Conclusion
	 * 			With this project we are able to compare the implementations of a linkedlist and a set. Each data structure has its
	 * 		own purpose and should be used according to the type of program we're creating. A linkedlist is a list of connected elements
	 * 		that accepts similar elements. While a set contains only one instance of an element. If we are to create a program that will
	 * 		only have distinct values - a bank account application for example, then we would rather have a set. On the other hand, if
	 * 		we are creating an "entry" list which has multiple instances, then a linkedlist will suit better. In conclusion, the usage
	 * 		really depends on what we are trying to implement, from there we can easily distinguish which data structure would suit
	 * 		better.
	 * 
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
    	if(contains(e)) {
    		System.out.println("Unable to add because " + e.toString() + " is already in the set.");
    		return false;
    	}
    	
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
    	System.out.println(tail.item.toString() + " has been added");
    	return true;
    }

    @Override
    public Object remove(int index)
    {
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
    	if(isEmpty() || index < 0 || index > size) {
    		return null;
    	}
    	
    	Node tempNode = head;
    	
    	if(contains(element)) {
    		System.out.println("Unable to update index " + index + " because " + element.toString() + " is already in the set.");
    		return null;
    	}
    	
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
