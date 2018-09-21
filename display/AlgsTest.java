package display;

import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Random;
import java.util.stream.Collectors;
import sorts.*;

public class AlgsTest
{
	private AlgsTest()
	{	
	}
	
	public static Map<Sortable, Long> results(int iterations, int length)
	{
		Map<Sortable, Long> algs = new HashMap<Sortable, Long>();
		algs.put(new BubbleSort(), new Long(0));
		algs.put(new InsertionSort(), new Long(0));
		algs.put(new IterativeSort(), new Long(0));
		algs.put(new MergeSort(), new Long(0));
		algs.put(new QuickSort1(), new Long(0));
		algs.put(new QuickSort2(), new Long(0));

		
		for(int i = 0; i < iterations; i ++)
		{
			int[] array = randomArray(length, -1000, 1000);
			
			for(Sortable x : algs.keySet())
			{
				int[] array_test = Arrays.copyOf(array, array.length);
				long t_0 = System.nanoTime();
				x.sort(array_test);
				long t_1 = System.nanoTime();
								
				long time = t_1 - t_0;
				algs.replace(x, time + algs.get(x));
			}
		}
		
		/*
		List<Entry<Sortable, Long>> list_sorted = new ArrayList<Entry<Sortable, Long>>(algs.entrySet());
		list_sorted.sort(Entry.comparingByValue());
		
		Map<Sortable, Long> algs_sorted = new LinkedHashMap<Sortable, Long>();
		
		for(Entry<Sortable, Long> x : list_sorted)
			algs_sorted.put(x.getKey(), x.getValue());
		*/
		
		Map<Sortable, Long> sorted_map = algs.entrySet().stream().sorted(Entry.comparingByValue()).collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		
		return sorted_map;
	}
	
	public static int[] randomArray(int length, int min, int max)
	{
		//min and max both inclusive
		Random r = new Random();
		int[] a = new int[length];
		
		for(int i = 0; i < a.length; i ++)
		{
			a[i] = min + r.nextInt(max - min + 1);
		}
		
		return a;
	}
}