package sorting.simple;

import java.util.Arrays;

import sorting.SorterBase;

public class InsertionSort extends SorterBase 
{
	@Override
	public void sort(Comparable[] target) 
	{
//		Arrays.sort(target);
		
		for(int i = 1; i < target.length; i++) {
			Comparable startRef = target[i];
			int j;
			for(j = i; j > 0 && target[j-1].compareTo(startRef) > 0; j--) {
				target[j] = target[j-1];
			}
			target[j] = startRef;
		}
	}
}