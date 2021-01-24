package homework;  // Version 1.0

import algs13.Queue;
import stdlib.StdOut;

/**
 * Complete the 5 methods marked ToDo
 * You must not change the declaration of any method.
 */

/**
 *  The LLST class implements methods of the Ordered Symbol table API using
 *  an *unordered* linked-list of generic key-value pairs.  
 *  The methods:  put, get, and delete are already implemented
 */
public class LLST<Key extends Comparable<Key>, Value extends Comparable<Value>> {
    private Node first;      // the linked list of key-value pairs

    // a helper linked list data type
    private class Node {
        private Key key;
        private Value val;
        private Node next;

        public Node(Key key, Value val, Node next)  {
            this.key  = key;
            this.val  = val;
            this.next = next;
        }
    }

    /**
     * Initializes an empty symbol table.
     */
    public LLST() {
    	first = null;
    }

    /**
     * Returns the value associated with the given key in this symbol table.
     */
    public Value get(Key key) {
        if (key == null) throw new NullPointerException("argument to get() is null"); 
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key))
                return x.val;
        }
        return null;
    }

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old 
     * value with the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is null.
     */
    public void put(Key key, Value val) {
        if (key == null) throw new NullPointerException("first argument to put() is null"); 
        if (val == null) {
            delete(key);
            return;
        }

        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
    }

    /**
     * Removes the specified key and its associated value from this symbol table     
     * (if the key is in this symbol table).    
     */
    public void delete(Key key) {
        if (key == null) throw new NullPointerException("argument to delete() is null"); 
        first = delete(first, key);
    }

    // delete key in sub-list beginning at Node x
    // return: the sub-list with the key removed
    // warning: function call stack too large if table is large
    private Node delete(Node x, Key key) {
        if (x == null) return null;
        if (key.equals(x.key)) {
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }

    /**
     * size returns the number of key-value pairs in the symbol table.
     * it returns 0 if the symbol table is empty.
     */
   
    public int size () {
    	int count = 0;
    	if(first == null) {		// returns empty list
    		return count;
    	}
    	for(Node x = first; x != null; x = x.next) {
    		count++;
    		if(x.next == null) {	// checks end of list and returns size
    			return count;
    		}
    	}
    	return -1;  // ToDo 1
    }
    /**
     * secondSmallestKey returns the second smallest key in the symbol table.
     * it returns null if the symbol table is empty or if it has only one key.
     * See if you can write it with only one loop
     */
    public Key secondSmallestKey () {
    	if(size() > 1) {
    		Key secondSmallest = first.key;
    		for(Node x = first; x != null; x = x.next) {
    			if(x.key.compareTo(first.key) <= 0) {
    				secondSmallest = first.key;
    				first.key = x.key;
    			}
    		}
    		// System.out.println(secondSmallest);
    		// System.out.println(first.key);
    		return secondSmallest;
    	}
    	return null; // ToDo 2    fix this
    }


    /**
     * rank returns the number of keys in this symbol table that are less than the parameter key.
     * your implementation should be recursive. You will want to create a helper function
     */
    public int rank (Key key) {
    	if(size() > 0) {
    		return rank(key, first, 0);
    	}
        return -1; // ToDo 3    fix this
    }
    
    public int rank (Key key, Node x, int count) {
    	int counter = count;
    	if(x != null) {
        	if(key.compareTo(x.key) > 0){
        		counter = rank(key, x.next, count+1);

        	} else {
        		counter = rank(key, x.next, count);
        	}
    	}
    	return counter;
    }

    /**
     * ceiling returns the smallest key in the symbol table that is greater than or equal to the given key.
     * it returns null if there is no such key.
     */
    public Key ceiling (Key key) {
    	Key ceilingKey = null;
    	if(size() > 0) {
    		for(Node x = first; x != null; x= x.next) {
    			if(x.key.equals(key)) {
    				return ceilingKey = x.key;
    			} else if(x.key.compareTo(key) > 0) {
    				ceilingKey = x.key;
    			}
    			if(x.key.compareTo(ceilingKey) <= 0 && x.key.compareTo(key) > 0) {
    				ceilingKey = x.key;
    			}
    		}
    		return ceilingKey;
    	}
    	return null; // Todo 4  fix this
    }

    /**
     * A level.  
     * 
     * inverse returns the inverse of this symbol table.
     * if the symbol table contains duplicate values, you can use any of the keys for the inverse values
     */
    public LLST<Value, Key> inverse () {
    	
    	System.out.println("*** Original Symbol Table <Key, Value> ***");
    	for(Node x = first; x != null; x= x.next) {
    		System.out.println("(" + x.key + ", " + x.val + ")");
    	}
    	System.out.println("-------------------------------------------");
    	System.out.println("*** Inverse Symbol Table <Value, Key> ***");
    	LLST<Value, Key> inverseST = new LLST<Value, Key>();	// creates an instance of an inversed symbol table (Value, Key)
    	for(Node x = first; x != null; x = x.next) {
    		inverseST.put(x.val, x.key);	// adds each node in an inversed manner, switching from (key, val) to (val, key)
    		System.out.println("(" + x.val + ", " + x.key + ")");
    	}
    	
    	return inverseST;
    	
    }
    
    public Iterable<Key>  keys() {
    	Queue<Key> theKeys = new Queue<Key>();
    	for ( Node temp = first; temp != null; temp=temp.next) {
    		theKeys.enqueue(temp.key);
    	}
    	return theKeys;
    }
}