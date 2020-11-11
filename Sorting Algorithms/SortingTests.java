package sorting;
import java.util.Arrays;
import java.util.Random;

import sorting.advanced.HeapSort;
import sorting.advanced.MergeSort;
import sorting.advanced.QuickSort;
import sorting.simple.BubbleSort;
import sorting.simple.InsertionSort;
import sorting.simple.SelectionSort;
import sorting.simple.ShellSort;

public class SortingTests 	
{
	public class TestCase
	{
		Comparable[] original;
		Comparable[] expected;
		
		Comparable[] getTestCase()
		{
			return Arrays.copyOf(original, original.length);
		}
	}
	
	public class Timings
	{
		Sorter sorter;
		long randomized;
		long sorted;
		long inverseSorted;
		
		@Override
		public String toString() 
		{
			return sorter.getClass().getName() + ".......\nTiming on Random: " + randomized +
					"\nTiming on Sorted: " + sorted +
					"\nTiming on Inverse Sorted: " + inverseSorted;
		}
	}
	
	private static final int TEST_SIZE = 10;
	private static final int TIME_SIZE = 50000;
	private final Random r = new Random(); 

	private Character[] createRandomTester(int size)
	{
		Character[] tester = new Character[size];
		for (int i = 0; i < size; i++)
		{
			tester[i] = (char) ('A' + r.nextInt(26));
		}
		return tester;
	}
	
	private TestCase setupRandomTest(int size)
	{
		TestCase test = new TestCase();
		test.original = createRandomTester(size);
		test.expected = Arrays.copyOf(test.original, size);
		Arrays.sort(test.expected);
		return test;
	}
	
	private boolean testSort(Sorter sorter, TestCase test)
	{
		boolean success = true;
		Comparable[] testCase = test.getTestCase();

		sorter.sort(testCase);
		for (int i = 1; i < testCase.length; i++)
		{
			if (testCase[i].compareTo(testCase[i-1]) < 0)
			{
				System.out.println(sorter.getClass().getName() + " Sort Failed:");
				System.out.println("Original : " + Arrays.toString(test.original));
				System.out.println("Your sort: " + Arrays.toString(testCase));
				System.out.println("Target   : " + Arrays.toString(test.expected));
				success = false;
				break;
			}
		}
		if (success)
		{
			System.out.println(sorter.getClass().getName() + " Sort Passed!");
		}
		return success;
	}

	private long timeSort(Sorter sorter, Comparable[] testMe)
	{
		long start = System.currentTimeMillis();
		sorter.sort(testMe);
		return System.currentTimeMillis() - start;
	}
	

	private TestCase setupOrdered(int size)
	{
		TestCase test = new TestCase();
		Integer[] sorted = new Integer[size];
		for (int i = 0; i < sorted.length; i++)
		{
			sorted[i] = i;
		}
		test.original = sorted;
		test.expected = sorted;
		return test;
	}

	private TestCase setupInverse(int size)
	{
		TestCase test = new TestCase();
		Integer[] inverse = new Integer[size];
		for (int i = 0; i < inverse.length; i++)
		{
			inverse[i] = inverse.length - i;
		}
		test.original = inverse;
		test.expected = Arrays.copyOf(inverse, inverse.length);
		Arrays.sort(test.expected);
		return test;
	}

	
	public Timings testTiming(Sorter sorter, TestCase random, TestCase ordered, TestCase inverseOrdered)
	{
		Timings timings = new Timings();
		timings.sorter = sorter;
		timings.randomized = timeSort(sorter, random.getTestCase());
		
		timings.sorted = timeSort(sorter, ordered.getTestCase());

		timings.inverseSorted = timeSort(sorter, inverseOrdered.getTestCase());
		return timings;
	}
	
	public static void main(String[] args) 
	{
//		Sorter[] tests = {new BubbleSort(), new InsertionSort(), new SelectionSort(), new ShellSort(),  
//					 	  new MergeSort(), new QuickSort(), new HeapSort()};
		Sorter[] tests = { new HeapSort()};

		boolean success = true;
		SortingTests test = new SortingTests();
		TestCase testCase = test.setupRandomTest(TEST_SIZE);
		for (Sorter s : tests)
		{
			try
			{
				success = success & test.testSort(s, testCase);
			} catch (Error e)
			{
				success = false;
				System.out.println(s.getClass().getName() + " Sort Failed:  Exception thrown in timing");
				e.printStackTrace();
			}
		}

		if (success)
		{
			TestCase random = test.setupRandomTest(TIME_SIZE);
			TestCase ordered = test.setupOrdered(TIME_SIZE);
			TestCase invered = test.setupInverse(TIME_SIZE);
			for (Sorter s : tests)
			{
				try
				{
					System.out.println(test.testTiming(s, random, ordered, invered));
				} catch (Error e)
				{
					success = false;
					System.out.println(s.getClass().getName() + " Sort Failed:  Exception thrown in timing");
					e.printStackTrace();
				}
			}
		}
	}
}
