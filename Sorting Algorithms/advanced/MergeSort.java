package sorting.advanced;

import java.util.Arrays;

import sorting.Sorter;

public class MergeSort implements Sorter 
{
	@Override
	public void sort(Comparable[] target) 
	{
//		Arrays.sort(target);
		
		mergeSort(target, target.length);
		
	}	
	
	public void mergeSort(Comparable[] target, int end) {
		
		if(end < 2) {
			return;
		}
		
		int mid = end / 2;
		Comparable[] left = new Comparable[mid];
		Comparable[] right = new Comparable[end - mid];
		
		for(int i = 0; i < mid; i++) {
			left[i] = target[i];
		}
		
		for(int j = mid; j < end; j++) {
			right[j-mid] = target[j];
		}
		
		mergeSort(left, mid);
		mergeSort(right, end-mid);
		
		merge(target, left, right, mid, end-mid);
		
	}
	
	public void merge(Comparable[] target, Comparable[] leftArr, Comparable[] rightArr,
			int left, int right) {
		
		int i = 0;
		int j = 0;
		int k = 0;
		
		while(i < left && j < right) {
			if(leftArr[i].compareTo(rightArr[j]) <= 0) {
				target[k++] = leftArr[i++];
			} else {
				target[k++] = rightArr[j++];
			}
		}
		
		while(i < left) {
			target[k++] = leftArr[i++];
		}
		while(j < right) {
			target[k++] = rightArr[j++];
		}
		
	}
	
}