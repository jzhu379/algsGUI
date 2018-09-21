package sorts;

public class BubbleSort implements Sortable
{
	
	public BubbleSort()
	{
		
	}
	
	public void sort(int[] a)
	{		
		int n = a.length - 1;
		
		boolean swapped = true;
		
		while(swapped)
		{
			swapped = false;
			
			for(int i = 0; i < n; i ++)
			{
				if(a[i] > a[i + 1])
				{
					swap(a, i, i + 1);
					swapped = true;
				}
			}
			
			n --;
		}
	}
	
	public String toString()
	{
		return "bubble sort";
	}
}