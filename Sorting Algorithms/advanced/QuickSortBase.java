package sorting.advanced;
import java.util.Random;

import sorting.Sorter;
import sorting.SorterBase;

public abstract class QuickSortBase extends SorterBase 
{
	/**
	 * Shuffles the array provided to ensure it is not already sorted
	 * @param target the array to shuffle
	 */
	protected void shuffle(Comparable[] target)
	{
		Random r = new Random();
		for (int i = 0; i < target.length/2; i++)
		{
			int from = r.nextInt(target.length);
			int to = r.nextInt(target.length);
			swap(target, from, to);
		}
	}
}