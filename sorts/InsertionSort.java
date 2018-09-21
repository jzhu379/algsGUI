package sorts;

public class InsertionSort implements Sortable
{	
	public InsertionSort()
	{
		
	}
	
	public void sort(int[] a)
	{
		
		for(int i = 1; i <= a.length - 1; i ++)
		{
			for(int j = i - 1; j >= 0; j --)
			{
				if(a[j + 1] < a[j])
				{
					swap(a, j + 1, j);
				}
			}
		}
	}
	
	@Override
	public String toString()
	{
		return "insertion sort";
	}
}