package sorts;

public class QuickSort1 implements Sortable
{	
	public QuickSort1()
	{
		
	}
	
	public String toString()
	{
		return "quick sort v1";
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
		int i = max;
		
		for(int j = max; j >= min; j --)
		{
			if(a[j] >= p)
			{
				if(i != j)
				{
					swap(a, i, j);
				}
				
				i --;
			}
		}
		
		return i + 1;
	}
}