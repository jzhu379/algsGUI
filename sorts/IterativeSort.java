package sorts;

public class IterativeSort implements Sortable
{	
	public IterativeSort()
	{
		
	}
	
	public void sort(int[] a)
	{		
		for(int i = 0; i < a.length - 1; i ++)
		{
			int min_index = i;
			
			for(int j = i + 1; j < a.length; j ++)
			{
				if(a[j] < a[min_index])
				{
					min_index = j;
				}
			}
			
			if(min_index != i)
			{
				swap(a, i, min_index);
			}
		}
	}
	
	public String toString()
	{
		return "iterative sort";
	}
}