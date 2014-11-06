package taojava.labs.sorting;

import java.util.Arrays;
import java.util.Comparator;

/**
* Sort using insertion sort.
*
* @author Samuel A. Rebelsky
*/
public class NewerInsertionSorter<T>
    extends SorterBridge<T>
{
  /**
  * Sort vals using insertion sort. See the Sorter<T> interface for additional
  * details.
  */
  @Override
  public T[] sorti(T[] vals, Comparator<T> order)
  {
    for (int i = 1; i < vals.length; i++)
      {
        newerInsert(vals, order, i);
      } // for
    return vals;
  } // sorti(T[], Comparator<T>)

  void newerInsert(T[] vals, Comparator<T> order, int n)
  {
    // Invariants:
    // I1(i): Utils.sorted(values,0,i).
    // I2(i): Utils.sorted(values,i+1,n).
    // I3(i): For all l and r, 0 <= l <= i, i < r <= n,
    // order.compare(vals[l],vals[r]) <= 0
    // Analysis:
    // I1(n) holds at the because it's a precondition.
    // I2(n) holds at the beginning because that subarray is empty
    // I3(n) holds at the beginning because the second subarray is empty
    int i = n;
    T tmp = vals[i];
    while ((i > 0) && (order.compare(vals[i - 1], tmp) > 0))
      {
        vals[i] = vals[i - 1];
        // Analysis:
        // I1(i-1) holds, and I1(i) also hold, because we put a
        // copy of element i at position i-1.
        // I2(i-1) and I2(i) holds b/c all the values in position 0..(i-1) 
        // were less than or equal to the values in positions (i+1)..n by I3
        // I3(i-1) holds b/c we know that the value at position i-1 was
        // the largest, and i is the same as i - 1
        // Conclusion:
        // The invariants still hold for i and i - 1, because of the doubled
        // element created at every shift
        i--;
      } // while
    vals[i] = tmp;
    // At this point, either i is 0, in which case I2 tells us that
    // that the elements in position 1..n are sorted and I3 tells us
    // that the element in position 0 is smaller than all of those, or
    // i is positive, in which case we know that the left part is
    // sorted (I1), the right part is sorted (I2), and the element at
    // the boundary is in the right position.
  } // newerInsert(T[], Comparator<T>, int)
} // InsertionSorter<T>