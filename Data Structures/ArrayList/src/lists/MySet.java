package lists;

import java.util.*;

public class MySet implements AdvancedList {
	
	/*
	 * This list implements an ArrayList but with a functionality of a Set. That is, no duplicate
	 * values exist and the elements do not refer to an index - unlike the traditional ArrayList.
	 * 
	 * There are a few methods of Sets that are similar to ArrayLists that were used in this List:
	 *  	+ size
	 *  	+ isEmpty
	 *  	+ clear
	 *  	+ contains
	 *  
	 * Other methods were modified to mimic the functionality of a Set:
	 * 		+ add - validates if the object to be added already exists in the Set. If it does not exist,
	 * 				then we add the object. Otherwise it does not get added and we notify user that it already exist.
	 * 		+ remove(index) - iterator was used to act as the index of the Set (since it does not have indexes). We initialize
	 * 				a count variable that increments until it reaches the index being referred to. The object in that 
	 * 				position gets marked and is deleted.
	 * 		+ get - uses the iterator as well to mark the object in the index. The object in that position then gets returned.
	 * 		+ set - uses the iterator to mark the object in the index. The object in that position is deleted and
	 * 				the element parameter gets added into the set. Note that due to this, the new object is now in front of
	 * 				the set and causes the original order to not hold.
	 * 		+ indexOf - uses the iterator to mark the position of the object parameter. We return the count variable, which
	 * 				is incremented for as long as we match the object in the Set.
	 * 		+ remove(object) - validates if the object exists in our Set prior to deleting it. If it does not exist, we
	 * 				notify user that the object is not in the list.
	 * 
	 * A few methods will throw an error due to the differences between ArrayList and Set:
	 * 		+ get/set - when updating an object in our set, we first delete the object in that index (or count in this case),
	 * 				then we add the updated object. This puts the new object on top of the Set which causes the order
	 * 				to not hold. Therefore the succeeding gets/sets after the first update will fail because the original index 
	 * 				will no longer reference the same object. 
	 * 		+ lastIndexOf - since our Set only holds unique values, it will only have one sequence of an object. Unlike an
	 * 				ArrayList, where lastIndexOf can reference to multiple objects in the list.
	 * 		+ remove(object) - with our Set only adding unique values, once an object is deleted, that specific object no
	 * 				longer exists in our Set. In the case of "Advanced Happy Path" test, the "advancedValues" from the test case
	 * 				contains duplicate objects which only one instance was added. Therefore the duplicate values that were
	 * 				attempted to be deleted will throw an error.
	 * 
	 * Conclusion
	 * 		Arrays and Sets have their own advantages and disadvantages. Each data structure has its own purpose and different
	 * 	uses. For instance, if I will be building a program that implements an ID data structure, then I will want to use a
	 * 	Set as it will only hold unique IDs. Another example for using a Set is when creating a MovieList or a Song/Playlist,
	 * 	where we do not want duplicate objects in our program. For the two examples mentioned, we do not want to use an ArrayList
	 * 	because we prefer to maintain a data structure with distinct values. An implementation of an ArrayList will be preferred
	 * 	for a program like an inventory where we keep multiple elements with the same name/type/number etc.
	 */
	
	
	
	private List<Object> mySet = new ArrayList<Object>();

	@Override
	public int size() {
		// set has same method as the arraylist
		return mySet.size();
	}

	@Override
	public boolean isEmpty() {
		// set has same method as the arraylist
		return mySet.isEmpty();
	}

	@Override
	public boolean add(Object e) {
		/*
		 * sets do not allow duplicate objects, therefore prior to adding an object, we validate if the set
		 * already contains the object. If it does not, then we continue to add the object, otherwise we notify
		 * that that object already exists.
		 */
		if(e != null) {
			if(mySet.contains(e)) {
				System.out.println(e.toString() + " is already in the set.");
				return false;
			} else {
				mySet.add(e);
				return true;
			}
		}
		return false;
	}

	@Override
	public Object remove(int index) {
		// checks bounds and if isEmpty
		if(mySet.isEmpty() || index >= mySet.size() || index < 0) {
    		return null;
    	}
		/* 
		 * since sets do not have indexes, we use the iterator to match the index parameter with a
		 * count variable that increments until we find the equality. Once found we remove the object
		 * that the index is referring to.
		 */
		int count = 0;
		Iterator<Object> i = mySet.iterator();
		while(i.hasNext()) {
			Object element = i.next();
			if(count == index) {
				mySet.remove(element);

				return element;
			}
			count++;
		}
		return null;
	}

	@Override
	public void clear() {
		// set has same method as the arraylist
		mySet.clear();
	}

	@Override
	public Object get(int index) {
		// checks bounds and if isEmpty
		if(mySet.isEmpty() || index >= mySet.size() || index < 0) {
    		return null;
    	}
		/*
		 * with sets not having index, we use the iterator to return the object in which the index is
		 * referring to. We use a loop to iterate through the set and increments the count until it matches
		 * the index. 
		 */
		int count = 0;
		Iterator<Object> i = mySet.iterator();
		while(i.hasNext()) {
			Object element = i.next();
			if(count == index) {
				return element;
			}
			count++;
		}
		
		return null;
	}

	@Override
	public Object set(int index, Object element) {
		// checks bounds and if isEmpty
		if(mySet.isEmpty() || index >= mySet.size() || index < 0) {
    		return null;
    	}
		/*
		 * we again use the iterator to mark the object in the index provided. Then that object referenced to is
		 * deleted and we add the element that was to be updated. Note that adding elements in a Set puts it in
		 * the front of the Set, therefore the original order does not hold anymore.
		 */
		int count = 0;
		Iterator<Object> i = mySet.iterator();
		while(i.hasNext()) {
			Object obj = i.next();
			if(count == index) {
				mySet.remove(obj);
				mySet.add(element);
				System.out.println(element.toString() + " has been added");
				return obj;
			}
			count++;
		}
		return null;
	}

	@Override
	public boolean contains(Object o) {
		// set has same method as the arraylist
    	if(o != null) {
    		if(mySet.contains(o)) {
        		return true;
        	}
    	}
    	return false;
	}

	@Override
	public int indexOf(Object o) {
		/*
		 * since set does not have an indexOf method, we use the iterator in order to figure the position
		 * of the object (same as get). As we iterate through the set, we keep incrementing until we have found the
		 * equivalent of the object. If the object does not exist in our set, we return -1.
		 */
		Iterator<Object> i = mySet.iterator();
		int count = 0;
		while(i.hasNext()) {
			Object element = i.next();
			if(element.equals(o)){
				return count;
			}
			count++;
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		/*
		 * the lastIndexOf will keep failing due to the fact that sets only have unique values and elements
		 * will only have one instances
		 */
		Iterator<Object> i = mySet.iterator();
		int count = 0;
		while(i.hasNext()) {
			Object element = i.next();
			if(element.equals(o)){
				return count;
			}
			count++;
		}
		return -1;
	}

	@Override
	public boolean remove(Object o) {
		/*
		 * if the set contains the object, we use the remove method with the object as the parameter, 
		 * then return true if the object has been removed. Note that our set does not have duplicate
		 * values therefore if we attempt to remove the same object twice, it will throw an error the 
		 * second time as that object is no longer in our list.
		 */
    	if(mySet.contains(o)) {
        	if(o != null) {
        		System.out.println(o.toString() + " has been deleted");
        		mySet.remove(o);
        		return true;
        	} 
    	}
    	System.out.println(o.toString() + " is not in the list");
    	return false;
	}

}
