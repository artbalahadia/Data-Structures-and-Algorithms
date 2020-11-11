package sorting.simple;

import java.util.Arrays;

import sorting.SorterBase;

public class ShellSort extends SorterBase
{
	@Override
	public void sort(Comparable[] target) 
	{
//		Arrays.sort(target);
		
		for(int mid = target.length/2; mid > 0; mid /= 2) {
			for(int i = mid; i < target.length; i++) {
				Comparable startRef = target[i];
				int j = i;
				while(j >= mid && target[j-mid].compareTo(startRef) > 0){
					target[j] = target[j-mid];
					j -= mid;
				}
				target[j] = startRef;
			}
		}
	}
	
}
