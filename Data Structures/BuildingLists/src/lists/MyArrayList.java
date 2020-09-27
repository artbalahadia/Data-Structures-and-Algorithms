package lists;

import java.util.*;

//import lists.solutions.*;

/**
 *
 * @author Tony
 */
public class MyArrayList implements AdvancedList
{
	
	private List<Object> myArrayList = new ArrayList<Object>();
    
    @Override
    public int size()
    {
        return myArrayList.size();
    }

    @Override
    public boolean isEmpty()
    {
        return myArrayList.isEmpty();
    }

    @Override
    public boolean add(Object e)
    {
    	// checks if object isn't null, then add if so
    	if(e != null) {
    		myArrayList.add(e);
    		return true;
    	}
    	return false;
    }

    @Override
    public Object remove(int index)
    {
    	// checks if list is empty and within bounds, then removes object at index
    	if(myArrayList.isEmpty() || index >= myArrayList.size() || index < 0) {
    		return null;
    	}
    	Object removedItem = myArrayList.get(index);
        if(removedItem == null) {
        	return null;
        } else {
        	myArrayList.remove(index);
    	}
    	return removedItem;

    }

    @Override
    public void clear()
    {
    	myArrayList.clear();
    }

    @Override
    public Object get(int index)
    {
    	// checks if list is empty and within bounds, then returns the object at index
    	if(myArrayList.isEmpty() || index >= myArrayList.size() || index < 0) {
    		return null;
    	}

    	if(myArrayList.get(index) != null) {
    		return myArrayList.get(index);
    	}
        return null;
    }

    @Override
    public Object set(int index, Object element)
    {
    	// checks if list is empty and within bounds, then updates object at index with element
    	if(myArrayList.isEmpty() || index >= myArrayList.size() || index < 0) {
    		return null;
    	}
    	Object setItem = myArrayList.get(index);
    	if(setItem != null && element != null) {
    		myArrayList.set(index, element);
    	}
        return setItem;
    }

    @Override
    public boolean remove(Object o)
    {
    	/*
    	 *  checks if list contains the object and assigns a variable to get the index of the object.
    	 *  Then uses that variable to remove the object.
    	 */
    	if(myArrayList.contains(o)) {
    		Object removeItem = myArrayList.indexOf(o);
        	if(removeItem != null) {
        		myArrayList.remove(removeItem);
        		return true;
        	} 
    	}
    	return false;
        
    }

    @Override
    public boolean contains(Object o)
    {
    	if(o != null) {
    		if(myArrayList.contains(o)) {
        		return true;
        	}
    	}
    	return false;
    	
    }

    @Override
    public int indexOf(Object o)
    {
    	if(o != null) {
    		return myArrayList.indexOf(o);
    	}
        return -1;
    }

    @Override
    public int lastIndexOf(Object o)
    {
    	if(o != null) {
    		return myArrayList.lastIndexOf(o);
    	}
        return -1;
    }
}
