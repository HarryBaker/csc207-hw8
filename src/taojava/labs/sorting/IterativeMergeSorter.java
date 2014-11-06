package taojava.labs.sorting;

import java.util.Comparator;

/**
 * Sort using iterative merge sort.
 * 
 * @author Samuel A. Rebelsky
 * @author Harry Baker, Harriet Zucker, China Mauck
 */
public class IterativeMergeSorter<T>
    extends SorterBridge<T>
{
  /**
   * Sort vals using iterative merge sort. See the Sorter<T> interface for
   * additional details.
   */
  @Override
  public T[] sorti(T[] vals, Comparator<T> order)
  {
    int size = 1;
    T[] scratch = vals.clone();
    while (size < vals.length)
      {
        for (int i = 0; i < vals.length; i += (size * 2))
          {
            // Merge neighboring subarrays of size size
            Utils.merge(order, vals, i, i + size, vals, i + size, Math.min(vals.length, (i + 2 * size)), scratch, i, Math.min(vals.length, (i + 2 * size)));
            //copy into original array
            for (int j=i; j<Math.min(vals.length, (i + 2 * size));j++)
                {
                  vals[j]=scratch[j];
                }//for
          }//for
        // The merged subarrays are now twice as large
        size *= 2;
      } // while
    return vals;
  } // sorti(T[], Comparator<T>)
} // IterativeMergeSorter<T>
