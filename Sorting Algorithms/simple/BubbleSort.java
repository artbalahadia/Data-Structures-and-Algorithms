package sorting.simple;

import java.util.Arrays;

import sorting.SorterBase;

public class BubbleSort extends SorterBase 
{
	@Override
	public void sort(Comparable[] target) 
	{
//		Arrays.sort(target);
	
		for(int i = target.length-1; i > 0; i--) {
			for(int j = 0; j < i; j++) {
				if(target[j].compareTo(target[j+1]) > 0) {
					Comparable temp = target[j];
					target[j] = target[j+1];
					target[j+1] = temp;
				}
			}
		}
		
	}
}
