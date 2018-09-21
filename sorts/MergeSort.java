package sorts;

public class MergeSort implements Sortable
{	
	public MergeSort()
	{
		
	}
	
	public String toString()
	{
		return "merge sort";
	}
	
	public void sort(int[] a)
	{
		mergesort(a, 0, a.length - 1);
	}
	
	private void mergesort(int[] a, int min, int max)
	{
		if(min < max)
		{
			int mid = (max + min) / 2;
			mergesort(a, min, mid);
			mergesort(a, mid + 1, max);
			merge(a, min, mid, max);
		}
	}
	
	private void merge(int[] a, int min, int mid, int max)
	{
		int[] a_1 = new int[mid - min + 1];
		int[] a_2 = new int[max - mid];
		
		for(int j = min; j <= mid; j++)
			a_1[j - min] = a[j];
		for(int j = mid + 1; j <= max; j ++)
			a_2[j - (mid + 1)] = a[j];
		
		int i = min;	//i is index for parent array
		int j_1 = 0;
		int j_2 = 0;
		
		while(j_1 < a_1.length && j_2 < a_2.length)
		{
			int num_1 = a_1[j_1];	//numbers at current indices of sub-arrays
			int num_2 = a_2[j_2];
			
			if(num_1 < num_2)
			{
				a[i] = num_1;
				j_1 ++;
				i ++;
			}
			else if(num_2 < num_1)
			{
				a[i] = num_2;
				j_2 ++;
				i ++;
			}
			else //if num_1 = num_2, add both
			{
				a[i] = num_1;
				a[i + 1] = num_2;
				j_1 ++;
				j_2 ++;
				i += 2;
			}
		}
		
		if(j_1 < a_1.length) //if array_1 has remaining elements
		{
			while(j_1 < a_1.length)
			{
				int num_1 = a_1[j_1];
				a[i] = num_1;
				j_1 ++;
				i ++;
			}
		}
		else if(j_2 < a_2.length) //if array_2 has remaining elements
		{
			while(j_2 < a_2.length)
			{
				int num_2 = a_2[j_2];
				a[i] = num_2;
				j_2 ++;
				i ++;
			}
		}
	}
}