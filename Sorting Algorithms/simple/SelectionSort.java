package sorting.simple;

import java.util.Arrays;

import sorting.SorterBase;

public class SelectionSort extends SorterBase 
{
	@Override
	public void sort(Comparable[] target)
	{
//		Arrays.sort(target);
		for(int i = 0; i < target.length; i++) {
			int smallest = i;
			for(int j = target.length-1; j >= i; j--) {
				if(target[j].compareTo(target[smallest]) <= 0) {
					smallest = j;
				}
			}
			Comparable temp = target[i];
			target[i] = target[smallest];
			target[smallest] = temp;
			
		}
		
	}
}
