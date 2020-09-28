package lists;

import java.util.Arrays;

//import lists.solutions.*;

/**
 *
 * @author Tony
 */
public class MyArray implements AdvancedList
{
	/*
	 * Our array will start at length 0 with variables @size and @count that keeps track of changes in the array.
	 * The size returns the length of the array and the count returns the addition/subtraction of elements in the array.
	 * This array is dynamic therefore inserting/removing elements will cause the array to resize.
	 */
	private Object[] myArray = new Object[0];
	private int size = size();
	private int count = 0;

    
    @Override
    public int size()
    {
        return myArray.length;
    }

    @Override
    public boolean isEmpty()
    {
    	// checks if no elements exist in our array
    	if(size == 0 || count == 0) {
    		return true;
    	}
    	return false;
    }

    @Override
    public boolean add(Object e)
    {
    	/*
    	 * When inserting elements, the array is first resized - using a tempArray with the length of +1. If there are
    	 * elements in the previous array (tracked by count variable), the elements are first copied into the tempArray. Then
    	 * we assign the resized tempArray to the original array (myArray). The object will then be added to the last index
    	 * (count variable) and increments count if the object was added.
    	 */
    	if(size == count) {
    		size++;
    		Object[] tempArray = new Object[size];
    		if(count > 0) {
    			for(int i = 0; i < size-1; i++) {
    				tempArray[i] = myArray[i];
    			}
    		}	
    		myArray = tempArray;
    	}	
		myArray[count] = e;
		// checker if object is added
		if(myArray[count] == e) {
		// System.out.println(e.toString() + " has been added");
			count++;
			return true;

		}   	
		return false;
    }

    @Override
    public Object remove(int index)
    {
    	/*
    	 * When removing elements, we first check the bounds if it is valid - returning null if index is out of bounds.
    	 * The object to be removed is given a memory in order to return after it has been removed. The array will be shifted
    	 * from right to left, starting from the index of the object to be removed. We decrement the count to keep track of
    	 * the remaining objects in the array. Then the array will be resized, using the tempArray with the length of count.
    	 */
    	if(isEmpty() || index > size || index < 0) {
    		return null;
    	}
    	Object removeObject = myArray[index];

    	if(count > 0) {
    		for(int i = index; i < count-1; i++) {
    			myArray[i] = myArray[i+1];
    		}
		// checker if removed
    	//	System.out.println(removeObject.toString() + " has been removed");
    		count--;
    	}
		Object[] tempArray = new Object[count];
		for(int i = 0; i < count; i++) {
			tempArray[i] = myArray[i];
		}
		size = count;
		myArray = tempArray;
    	
        return removeObject;
    }

    @Override
    public void clear()
    {   
    	// Resets the array, count and size
    	myArray = new Object[0];
    	count = 0;
    	size = 0;
    }

    @Override
    public Object get(int index)
    {
    	// Validates if array is empty and if index is within bounds
    	if(isEmpty() || index > size || index < 0) {
    		return null;
    	}
        return myArray[index];
    }

    @Override
    public Object set(int index, Object element)
    {
    	// Validates if array is empty and if index is within bounds
    	if(isEmpty() || index > size || index < 0) {
    		return null;
    	}
    	Object toChange = myArray[index];
    	myArray[index] = element;
        return toChange;
    }

    @Override
    public boolean remove(Object o)
    {
    	/*
    	 *  Validates if array is empty, then iterates through the array and stops once the object finds its match.
    	 *  Uses the remove method with the object's index as the parameter.
    	 */
    	if(!isEmpty()) {
    		for(int i = 0; i < size; i++) {
    			if(o == myArray[i]) {
    				remove(i);
    				return true;
    			}
    		}
    	}
    	return false;
        
    }

    @Override
    public boolean contains(Object o)
    {
    	// Validates the array and iterates through until it finds its match. Returns true if the object exists in the array.
    	if(isEmpty()) {
    		return false;
    	}
    	for(int i = 0; i < size; i++) {
    		if(myArray[i] == o) {
    			return true;
    		}
    	}
        return false;
    }

    @Override
    public int indexOf(Object o)
    {
    	// Validates the array and iterates through until it finds its match. The index of the matching object is returned.
    	if(isEmpty()) {
    		return -1;
    	}
 
    	for(int i = 0; i < size; i++) {
    		if(myArray[i] == o) {
    			return i;
    		}
    	}
        return -1;
    }

    @Override
    public int lastIndexOf(Object o)
    {
    	/*
    	 *  Validates the array and iterates through matching objects in the array. If duplicate values of object exist,
    	 *  The lastIndex variable gets updated until it reaches the end of the array.
    	 */
    	if(isEmpty()) {
    		return -1;
    	}
    	int lastIndex = -1;
    	for(int i = 0; i < size; i++) {
    		if(myArray[i] == o) {
    			lastIndex = i;
    		}
    	}

        return lastIndex;
    }
}
