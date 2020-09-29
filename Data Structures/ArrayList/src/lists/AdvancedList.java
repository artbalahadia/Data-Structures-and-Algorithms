package lists;

/**
 *
 * @author Tony
 */
public interface AdvancedList extends SimplifiedList
{
    /**
     * Checks if the provided object is in the list
     * @param o the object to search for
     * @return true if the object is within the list
     */
    public boolean contains(Object o);
    
    /**
     * Find the lowest index of the provided object
     * @param o the object to search for
     * @return the index of the value in the list or -1 if not found
     */
    public int indexOf(Object o);
    
    /**
     * Find the highest index of the provided object
     * @param o the object to search for
     * @return the index of the value in the list or -1 if not found
     */
    public int lastIndexOf(Object o);

    /**
     * Remove the first occurrence of the provided object from the list
     * @param o the object to search for
     * @return true, if the object was in the list and removed
     */
    public boolean remove(Object o);   
}