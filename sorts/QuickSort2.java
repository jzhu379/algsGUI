package sorts;

public class QuickSort2 implements Sortable
{
	public QuickSort2()
	{
		
	}
	
	public String toString()
	{
		return "quick sort v2";
	}
	
	public void sort(int[] a)
	{
		quicksort(a, 0, a.length - 1);
	}
	
	private void quicksort(int[] a, int min, int max)
	{
		if(min < max)
		{
			int p = order(a, min, max);
			quicksort(a, min, p - 1);
			quicksort(a, p + 1, max);
		}
	}
	
	private int order(int[] a, int min, int max)
	{
		int p = a[min];
		int i = min + 1;
		int j = max;
		
		while(i < j)
		{
			while(a[i] <= p && i < max)
				i ++;
			while(a[j] >= p && j > min)
				j --;

			
			if(i < j)
			{
				swap(a, i, j);
				i ++;
				j --;
			}
		}
		
		if(i == j)
		{
			if(a[j] < p)
			{
				swap(a, min, j);
				return j;
			}
			else
			{
				swap(a, min, j - 1);
				return j - 1;
			}
		}
		else
		{
			swap(a, min, j);
			return j;
		}
	}
}