package sorting.advanced;

import java.util.Arrays;

public class QuickSort extends QuickSortBase 
{
	@Override
	public void sort(Comparable[] target) 
	{
//		Arrays.sort(target);
		
		sort(target, 0, target.length-1);
		
	}	
	
	public void sort(Comparable[] target, int left, int right) {
		
		if(left < right) {
			int pivot = partition(target, left, right);
			
			sort(target, left, pivot-1);
			sort(target, pivot+1, right);
		}
		
	}
	
	public int partition(Comparable[] target, int left, int right) {
		
		Comparable pivot = target[right];
		int i = left-1;
		
		for(int j = left; j < right; j++) {
			if(target[j].compareTo(pivot) <= 0) {
				i++;
				Comparable temp = target[i];
				target[i] = target[j];
				target[j] = temp;
			}
		}
		
		Comparable temp = target[i+1];
		target[i+1] = target[right];
		target[right] = temp;
		
		return i+1;
	}
	
}