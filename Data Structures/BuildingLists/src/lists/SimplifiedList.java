package lists;

/**
 *
 * @author Tony
 */
public interface SimplifiedList
{
    /**
     * @return The size of the list
     */
    public int size();
    
    /**
     * @return true if the list is empty
     */
    public boolean isEmpty();
    
    /**
     * Add the provided value to the end of the list
     * @param e the value to save
     * @return true if the value was added
     */
    public boolean add(Object e);
    
    /**
     * Remove the object at the provided index
     * @param index the item to remove (greater than or equal to zero and less than the size)
     * @return the object removed or null if the index is out of range
     */
    public Object remove(int index);
    
    /**
     * Clears the list of all values
     */
    public void clear();
    
    /**
     * Get the item at the specified index
     * @param index the item to retrieve (greater than or equal to zero and less than the size)
     * @return the object at that index or null if the index is out of range
     */
    public Object get(int index);

    /**
     * Set the provided object to the provided position in the list
     * @param index an index, should be less than size
     * @param element the object to save
     * @return the object that was previously at that location, or null if the index is greater than the size
     */
    public Object set(int index, Object element);
}