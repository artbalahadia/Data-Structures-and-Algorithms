package sorting.advanced;

import java.util.Arrays;

import sorting.Sorter;

public class HeapSort implements Sorter 
{
	@Override
	public void sort(Comparable[] target) 
	{
//		Arrays.sort(target);
		
		int length = target.length;
		
		for(int i = length / 2 - 1; i >= 0; i--) {
			heapSort(target, length, i);
		}
		
		for(int i = length-1; i >= 0; i--) {
			Comparable temp = target[0];
			target[0] = target[i];
			target[i] = temp;
			
			heapSort(target, i, 0);
		}
		
	}
	
	public void heapSort(Comparable[] target, int length, int i) {
		
		int largest = i;
		int left = 2*i+1;
		int right = 2*i+2;
		
		if(left < length && target[left].compareTo(target[largest]) > 0) {
			largest = left;
		}
		
		if(right < length && target[right].compareTo(target[largest]) > 0) {
			largest = right;
		}
		
		if(largest != i) {
			Comparable temp = target[i];
			target[i] = target[largest];
			target[largest] = temp;
			
			heapSort(target, length, largest);
		}
		
	}
}