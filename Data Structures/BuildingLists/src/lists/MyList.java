package lists;
import java.util.*;
//import lists.solutions.*;

/**
 *
 * @author Tony
 */
public class MyList implements AdvancedList
{
	
	private List<Object> myList = new ArrayList<Object>();
    
    @Override
    public int size()
    {
        return myList.size();
    }

    @Override
    public boolean isEmpty()
    {
        return myList.isEmpty();
    }

    @Override
    public boolean add(Object e)
    {
    	// checks if object isn't null, then add if so
    	if(e != null) {
    		myList.add(e);
    		return true;
    	}
    	return false;
    }

    @Override
    public Object remove(int index)
    {
    	// checks if list is empty and within bounds, then removes object at index
    	if(myList.isEmpty() || index >= myList.size() || index < 0) {
    		return null;
    	}
    	Object removedItem = myList.get(index);
        if(removedItem == null) {
        	return null;
        } else {
        	myList.remove(index);
    	}
    	return removedItem;

    }

    @Override
    public void clear()
    {
    	myList.clear();
    }

    @Override
    public Object get(int index)
    {
    	// checks if list is empty and within bounds, then returns the object at index
    	if(myList.isEmpty() || index >= myList.size() || index < 0) {
    		return null;
    	}

    	if(myList.get(index) != null) {
    		return myList.get(index);
    	}
        return null;
    }

    @Override
    public Object set(int index, Object element)
    {
    	// checks if list is empty and within bounds, then updates object at index with element
    	if(myList.isEmpty() || index >= myList.size() || index < 0) {
    		return null;
    	}
    	Object setItem = myList.get(index);
    	if(setItem != null && element != null) {
    		myList.set(index, element);
    	}
        return setItem;
    }

    @Override
    public boolean remove(Object o)
    {
    	if(myList.contains(o)) {
    		Object removeItem = myList.indexOf(o);
        	if(removeItem != null) {
        		myList.remove(removeItem);
        		return true;
        	} 
    	}
    	return false;
        
    }

    @Override
    public boolean contains(Object o)
    {
    	if(o != null) {
    		if(myList.contains(o)) {
        		return true;
        	}
    	}
    	return false;
    	
    }

    @Override
    public int indexOf(Object o)
    {
    	if(o != null) {
    		return myList.indexOf(o);
    	}
        return -1;
    }

    @Override
    public int lastIndexOf(Object o)
    {
    	if(o != null) {
    		return myList.lastIndexOf(o);
    	}
        return -1;
    }
}
