package searches;

public class IterativeSearch implements Searchable
{
	private IterativeSearch()
	{
		
	}
	
	public int search(int[] a, int x)
	{
		int i = 0;
		
		while(i < a.length)
		{
			if(a[i] == x)
				return i;
			i ++;
		}
		
		return -1;
	}
}