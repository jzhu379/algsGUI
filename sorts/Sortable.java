package sorts;

public interface Sortable
{
	void sort(int[] a);
	
	default void swap(int[] a, int i, int j)
	{
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
