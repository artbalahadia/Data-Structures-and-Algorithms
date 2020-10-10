
import java.util.Arrays;

/**
 *
 * @author Tony
 */
public class BasicManipulations
{
    public int total(int[] values)
    {
    	return total(values, 0);
    }
    
    public int total(int[] values, int index) {
    	if(index == values.length) {
    		return 0;
    	} else {
    		return values[index] + total(values, index+1);
    	}
    }
    
    public double mean(int[] values)
    {
    	if(values.length == 0) {
    		return 0;
    	} 
        return (double) total(values, 0) / values.length;
    }
    
    public int count(int[] values, int find)
    {
    	return count(values, find, 0);
    }
    
    public int count(int[] values, int find, int index) {
    	int count = 0; 	
    	if(index == values.length) {
    		return 0;
    	}  	
    	if(values[index] == find) {
    		count++;
    	}
    	return count + count(values, find, index+1);
    }
    
    public int largest(int[] values)
    {
    	int length = values.length;
    	return largest(values, length);
    }
    
    public int largest(int[] values, int length) {	
    	if(length == 0) {
    		return 0;
    	}
    	if(length == 1) {
    		return values[0];
    	} else {
    		return Math.max(values[length-1], largest(values, length-1));
    	}
    }
    
    
    public int smallest(int[] values)
    {
    	int length = values.length;
    	return smallest(values, length);
    }
    
    public int smallest(int[] values, int length) {
    	if(length == 0) {
    		return 0;
    	}
    	if(length == 1) {
    		return values[0];
    	} else {
    		return Math.min(values[length-1], smallest(values, length-1));
    	}
    }
    
    public boolean tenTimes(int[] values)
    {
    	int size = values.length;
    	if(size < 2) {
    		return false;
    	}	
    	return tenTimes(values, size, 0); 	
    }
    
    public boolean tenTimes(int[] values, int size, int x) {
    	for(int y = size-1; y >= 0; y--) {
    		if(x == y) {
    			continue;
    		}
    		if((values[x]*10) == values[y] || (values[y]*10) == values[x]) {
//    			System.out.println(Arrays.toString(values));
//    			System.out.println(values[x]*10 + " is our x and size index is " + values[y]);
    			return true;
    		} else if(y == 0) {
//    			START RECURSION
    			tenTimes(values, size, x+1);
    		}
    	}  	
    	return false;
    }
    

    //...................................................
    private static void checkEquals(Object check, Object expected, int[] input, String message)
    {
        if (check.equals(expected))
        {
        } else
        {
            System.out.print(message);
            System.out.println(" got " + check + " expected " + expected + " for input " + Arrays.toString(input));
        }
    }
    
    
    public static void main(String[] args)
    {
        BasicManipulations b = new BasicManipulations();
        int[] basic = {2, 5, 10, 15};
        int[] negative = {0, -5, -10};
        int[] mixed = {-5, -1, 0, 1, 5};
        int[] empty = {};
        
        String testError = "total() failed: ";
        checkEquals(b.total(basic), 32, basic, testError);
        checkEquals(b.total(negative), -15, negative, testError);
        checkEquals(b.total(mixed), 0, mixed, testError);
        checkEquals(b.total(empty), 0, empty, testError);
        System.out.println("total() finished");
        
        testError = "mean() failed: ";
        checkEquals(b.mean(basic), 8.0, basic, testError);
        checkEquals(b.mean(negative), -5.0, negative, testError);
        checkEquals(b.mean(mixed), 0.0, mixed, testError);
        checkEquals(b.mean(empty), 0.0, empty, testError);
        System.out.println("mean() finished");

        testError = "count() failed: ";
        int[] multiple = {0, 5, 2, 3, 5, 2, -5, 5};
        checkEquals(b.count(basic, 10), 1, basic, testError);
        checkEquals(b.count(multiple, 5), 3, multiple, testError);
        checkEquals(b.count(empty, 0), 0, empty, testError);
        System.out.println("count() finished");
        
//        testError = "median() failed: ";
//        checkEquals(b.median(basic), 7.5, basic, testError);
//        checkEquals(b.median(negative), -5.0, negative, testError);
//        checkEquals(b.median(multiple), 2.5, multiple, testError);
//        checkEquals(b.median(mixed), 0.0, mixed, testError);
//        checkEquals(b.median(empty), 0.0, empty, testError);
//        System.out.println("median() finished");

        testError = "largest() failed: ";
        checkEquals(b.largest(basic), 15, basic, testError);
        checkEquals(b.largest(negative), 0, negative, testError);
        checkEquals(b.largest(multiple), 5, multiple, testError);
        checkEquals(b.largest(mixed), 5, mixed, testError);
        checkEquals(b.largest(empty), 0, empty, testError);
        System.out.println("largest() finished");

        testError = "smallest() failed: ";
        checkEquals(b.smallest(basic), 2, basic, testError);
        checkEquals(b.smallest(negative), -10, negative, testError);
        checkEquals(b.smallest(multiple), -5, multiple, testError);
        checkEquals(b.smallest(mixed), -5, mixed, testError);
        checkEquals(b.smallest(empty), 0, empty, testError);
        System.out.println("smallest() finished");

        testError = "tenTimes() failed: ";
        int[] positive    = {1, 10, 100};
        int[] negativeTen = {-1, -10, -100};
        checkEquals(b.tenTimes(positive), true, positive, testError);
        checkEquals(b.tenTimes(negativeTen), true, negativeTen, testError);
        checkEquals(b.tenTimes(multiple), false, negative, testError);
        checkEquals(b.tenTimes(mixed), false, mixed, testError);
        checkEquals(b.tenTimes(empty), false, empty, testError);
        System.out.println("tenTimes() finished");
    }
}