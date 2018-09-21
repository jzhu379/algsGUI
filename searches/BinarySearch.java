package searches;

public class BinarySearch implements Searchable
{
	private BinarySearch()
	{
		
	}
	
	public int search(int[] a, int x)
	{
		int min = 0;
		int max = a.length - 1;
		
		while(min <= max)
		{
			int mid = (max + min) / 2;
			
			if(a[mid] > x)
				max = mid - 1;
			else if(a[mid] < x)
				min = mid + 1;
			else
				return mid;
		}
		
		return -1;
	}
}