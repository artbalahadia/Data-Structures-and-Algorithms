
import java.util.Arrays;

/**
 *
 * @author Tony
 */
public class BasicManipulations
{
    public int total(int[] values)
    {
    	// calculates total - exits loop if empty array
    	int totalOfArray = 0;
    	for(int i = 0; i < values.length; i++) {
    		totalOfArray += values[i];
    	}
        return totalOfArray;
    }
    
    public double mean(int[] values)
    {
    	// returns 0 if empty array, otherwise get total and divide by length
    	int size = values.length;
        if(size == 0) {
        	return 0;
        }
        double meanOfArray, totalOfArray = 0;
        for(int i = 0; i < size; i++) {
        	totalOfArray += values[i];
        }
        meanOfArray = totalOfArray / size;
        return meanOfArray;
    }
    
    public int count(int[] values, int find)
    {
    	// increments count for every instance of "find" in array
    	int count = 0;
    	for(int i = 0; i < values.length; i++) {
    		if(find == values[i]) {
    			count++;
    		}
    	}
        return count;
    }
    
    public double median(int[] values)
    {
    	/*	sorts array (ascending), then calculates median of array; 
    	 *  if length is odd - take middle + middle-1 then divide by 2, otherwise just middle
    	 */
    	
    	Arrays.sort(values);
        double medianOfArray;
        int size = values.length;
        if(size == 0) {
        	return 0;
        } else if(size % 2 == 0) {
        	medianOfArray = (double) (values[size / 2] + values[(size / 2) - 1]) / 2;
        } else {
        	medianOfArray = values[size / 2];
        }
        return medianOfArray;
    }
    
    public int largest(int[] values)
    {
    	// traverses through array to find max value
    	if(values.length == 0) {
    		return 0;
    	}
        int largestValue = values[0];
        for(int i = 1; i < values.length; i++) {
        	if(values[i] > largestValue) {
        		largestValue = values[i];
        	}
        }
        return largestValue;
    }

    public int smallest(int[] values)
    {
    	// traverses through array to find min value
    	if(values.length == 0) {
    		return 0;
    	}
        int smallestValue = values[0];
        for(int i = 1; i < values.length; i++) {
        	if(values[i] < smallestValue) {
        		smallestValue = values[i];
        	}
        }
        return smallestValue;
    }
    
    public boolean tenTimes(int[] values)
    {
    	/* uses a nested loop to traverse array for each element. 
    	 * if an element has a value of 0, the loop skips to avoid 0 * 10 = 0 == true;
    	 */
        for(int i = 0; i < values.length; i++) {
        	for(int j = 1; j < values.length - 1; j++) {
        		if(values[i] == 0 || values[j] == 0) {
        			continue;
        		}
        		if(values[i] * 10 == values[j]) {
        			return true;
        		}
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
        
        testError = "median() failed: ";
        checkEquals(b.median(basic), 7.5, basic, testError);
        checkEquals(b.median(negative), -5.0, negative, testError);
        checkEquals(b.median(multiple), 2.5, multiple, testError);
        checkEquals(b.median(mixed), 0.0, mixed, testError);
        checkEquals(b.median(empty), 0.0, empty, testError);
        System.out.println("median() finished");

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