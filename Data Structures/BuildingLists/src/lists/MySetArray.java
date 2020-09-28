package lists;
import java.util.*;

//import lists.solutions.*;

/**
 *
 * @author Tony
 */
public class MySetArray implements AdvancedList
{
	
	/*
	 * This list implements an ArrayList but with a functionality of a Set. That is, no duplicate
	 * values exist and the elements do not refer to an index - unlike the traditional ArrayList.
	 * 
	 * There are a few methods of Sets that are similar to ArrayLists that were used in this List:
	 *  	+ size
	 *  	+ isEmpty
	 *  	+ clear
	 *  	+ remove (index)
	 *  	+ contains
	 *  	+ indexOf
	 *  
	 *  Other methods that were modified to mimic the characteristics of a Set:
	 *  	+ add - validates if the object to be added already exists in the Set. If it does not exist,
	 * 				then we add the object. Otherwise it does not get added and we notify user that it already exist.
	 * 				After adding the object, we shuffle the array to keep the elements unordered, just like in a Set.
	 * 		+ set - as Sets do not have its own "set" method, we first delete the object the index provided, then add
	 * 				the new/updated object from the parameter.
	 * 		+ remove(object) - to copy the Set's remove method, we validate if the Set contains the object to be removed,
	 * 				in addition to the check if isEmpty validation.
	 * 
	 * 	A few methods that have caused an error are the following:
	 * 		+ get/set/remove(index) - all these tests will fail due to the fact that our Set does not hold its order, as
	 * 				in a regular Set implementation, there are no indexes available. In the tester file, the tests references
	 * 				and validates to specific indexes in the array which results to failed cases, because our Set will return
	 * 				a random object in our Set. Also, since our Set will reject duplicate values, other test cases, for instance
	 * 				using the "advancedValues" array will throw an error as our Set will not have the same size and values.
	 * 		+ lastIndexOf - since elements in our Array do not have unique values, each element will only have one occurence.
	 * 				In this case, it will only return one instance.
	 * 		+ indexOf - due to our Set having random positions, the indexOf will not have the same index as the object passed
	 * 				as a parameter.
	 * 
	 * 	Conclusion
	 * 			With this project, we realize that arrays are not the best dynamic data structure. In most cases, when adding/removing
	 * 		elements, we often have to size up or down in order to make our array "adjustable" and be able to adapt. In terms of a 
	 * 		Set, an array will find it hard to implement other methods of a Set. For example, when removing elements in a Set,
	 * 		we reference the specific element directly, this uses less code and much faster to locate and resize. In an Array, we 
	 * 		have to loop through the array until we find a match of the object, then remove the object and then downsizes the Array.
	 * 		this uses more code and takes a much longer algorithm - O(n).
	 * 		
	 */
	
	private Object[] myArray = new Object[0];
	private int size = size();
	private int count = 0;
    
    @Override
    public int size()
    {
    	// same implementation as our Array List
        return myArray.length;
    }

    @Override
    public boolean isEmpty()
    {
    	// same implementation as our Array List
    	if(size == 0 || count == 0) {
    		return true;
    	}
    	return false;
    }

    @Override
    public boolean add(Object e)
    {
    	/*
    	 * Following our add method in the array list, the difference in our Set implementation is that prior to adding
    	 * elements in our Set, we check for similar objects that already exist. This is done by a regular loop that serves
    	 * as an iterator in the original Set API. When the loop finds a match/duplicate, we notify that the specific
    	 * object will not be added as it already exists at an index [i]. Otherwise, if it has no duplicate objects, then we
    	 * add it in the Set. After adding, the Set gets shuffled, in order to mimic a characteristic of a Set wherein elements
    	 * do not hold its order.
    	 */
    	if(size == count) {
    		size++;
    		Object[] tempArray = new Object[size];
    		if(count > 0) {
    			for(int i = 1; i < size; i++) {
    				tempArray[i] = myArray[i-1];
    			}
    		}	
    		myArray = tempArray;
    	}
    	for(int i = 0; i < size; i++) {
    		if(myArray[i] == e) {
    			System.out.println(e.toString() + " already exists in our Set. (@array index " + i +")");
    			return false;
    		}
    	}
		myArray[0] = e;
		if(myArray[0] == e) {
		 System.out.println(e.toString() + " has been added");
			count++;
			// this algorithm shuffles the array (reference: Fisher-Yates shuffle algorithm)
			Random random = new Random();
			for(int i = 0; i < size; i++) {
				int randomPosition = random.nextInt(size);
				Object temp = myArray[i];
				myArray[i] = myArray[randomPosition];
				myArray[randomPosition] = temp;
			}
			return true;
		}

		return false;
    }

    @Override
    public Object remove(int index)
    {
    	// same implementation as our Array List
    	if(isEmpty() || index > size || index < 0) {
    		return null;
    	}
    	Object removeObject = myArray[index];

    	if(count > 0) {
    		for(int i = index; i < count-1; i++) {
    			myArray[i] = myArray[i+1];
    		}
		// checker if removed
    		System.out.println(removeObject.toString() + " has been removed");
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
    	// same implementation as our Array List
    	myArray = new Object[0];
    	count = 0;
    	size = 0;
    }

    @Override
    public Object get(int index)
    {
    	// same implementation as our Array List
    	if(isEmpty() || index >= size || index < 0) {
    		return null;
    	}
        return myArray[index];
    }

    @Override
    public Object set(int index, Object element)
    {
    	/*
    	 * As Sets do not have a "set" method where we update a specific element, we will first remove the object at the 
    	 * given index. Then, we add the element passed as a parameter using the add method - which checks for duplicates
    	 * and shuffles the Set to keep it unordered.
    	 */
    	if(isEmpty() || index >= size || index < 0) {
    		return null;
    	}
    	Object toChange = myArray[index];
    	remove(index);
    	if(!add(element)) {
    		System.out.println("Attempt to change " + toChange.toString() + " to " + element.toString() + " failed due to duplicate element.");
    	} else {
    		add(element);
    	}
        return toChange;
    }

    @Override
    public boolean remove(Object o)
    {
    	/*
    	 *  In addition to our Array implementation of remove, we check if the Set contains the object parameter, just like
    	 *  how we would in a Set.
    	 */
    	
    	if(!isEmpty() && contains(o)) {
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
    	// same implementation as our Array List
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
    	// same implementation as our Array List
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
    	// This method of lastIndex will not work in a Set as elements only have one occurence.
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
}
