package lists;

//import lists.solutions.*;

/**
 *
 * @author Tony
 */
public class MyArray implements AdvancedList
{
	
	private Object[] myArray = new Object[0];
	private int size = myArray.length;

    
    @Override
    public int size()
    {
        return size;
    }

    @Override
    public boolean isEmpty()
    {
    	if(myArray.length == 0) {
    		return true;
    	} else if(myArray[0] == null) {
    		return true;
    	}
    	return false;
    }

    @Override
    public boolean add(Object e)
    {
    	if(myArray.length == 0) {
    		size++;
    		Object[] tempArray = new Object[size];
    		myArray = tempArray;
    		myArray[size-1] = e;
    		return true;
    	} else if(myArray.length > 0){
    		size++;
    		Object[] tempArray = new Object[size];
    		for(int i = 0; i < size-1; i++) {
    			tempArray[i] = myArray[i];
    		}
    		myArray = tempArray;
    		myArray[size-1] = e;
    		return true;
    	}

    	return false;
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
