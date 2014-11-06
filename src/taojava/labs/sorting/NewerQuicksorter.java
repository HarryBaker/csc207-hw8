package taojava.labs.sorting;

import java.util.Comparator;

public class NewerQuicksorter<T>
    extends NewQuicksorter<T>
{
  @Override
  /**
   * Select the middle element of the subarray as the pivot.
   */
  public T selectPivot(T[] vals, Comparator<T> order, int lb, int ub)
  {
    return vals[(ub+lb)/2];
  }// selectPivot(T[], Comparator<T>, int, int) 
} // NewerQuickSorter
