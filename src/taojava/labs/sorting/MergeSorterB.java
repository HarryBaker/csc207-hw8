package taojava.labs.sorting;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Sort using recursive merge sort.
 * 
 * @author Samuel A. Rebelsky
 * @author Hattie, China and Harry
 * 
 * We used ideas taken from Zoe's group's code for having a 
 * single scratch array.
 */
public class MergeSorterB<T>
    extends SorterBridge<T>
{
  /**
    * 
    // Sort all the elements
    MergeSort(values)
    MergeSort(values, lb, ub, new scratch array)
    // Sort elements in positions [lb..ub), using scratch for temporary
    // storage.
    MergeSort(values, lb, ub, scratch)
      If the subarray is small
        do nothing
      Otherwise
        Sort the first half (using scratch as necessary)
        Sort the second half (using scratch as necessary)
        Copy the sorted halves into scratch
        Merge back into values
   */
  @Override
  public T[] sort(T[] vals, Comparator<T> order)
  {
    T[] scratch = (T[]) new Object[vals.length];
    // Base case: Singleton arrays need not be sorted.
    if (vals.length <= 1)
      {
        return vals;
      } // if length <= 1
    else
      {
        //Additional call to sort that takes into account 
        //scratch array
        return sort(vals, order, 0, vals.length, scratch);
      } // else
  } // sort

  public T[] sort(T[] vals, Comparator<T> order, int lb, int ub, T[] scratch)
  {
    // Base case: Singleton arrays need not be sorted.
    int mid;
    int length = ub - lb;
    if (length <= 1)
      {
        return vals;
      } // if length <= 1
    else
      {
        mid = lb + (length / 2);
        //sorts left half of the array on the scratch, using 
        //mid as the new upper bound
        sort(vals, order, lb, mid, scratch);
        //sorts right half of the array on the scratch, using 
        //mid as the new lower bound
        sort(vals, order, mid, ub, scratch);
        //copy remaining elements from vals to scratch
        for (int i = 0; i < ub; i++)
          {
            scratch[i] = vals[i];
          }
      } // else
    return Utils.merge(order, scratch, lb, mid, scratch, mid, ub, vals, lb, ub);
  } // sort
} // MergeSorterB<T>
