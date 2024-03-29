package taojava.labs.sorting;

import java.util.Arrays;
import java.util.Comparator;

/**
* Sort using insertion sort.
*
* @author Samuel A. Rebelsky
*/
public class NewInsertionSorter<T>
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
        newInsert(vals, order, i);
      } // for
    return vals;
  } // sorti(T[], Comparator<T>)

  void newInsert(T[] vals, Comparator<T> order, int n)
  {
    // Invariants:
    // I1(i): Utils.sorted(values,0,i).
    // I2(i): Utils.sorted(values,i+1,n).
    // I3(i): For all l and r, 0 <= l <= i, i < r <= n,
    // order.compare(vals[l],vals[r]) <= 0
    // Analysis:
    // I1(n) holds at the beginning because it's a precondition.
    // I2(n) holds at the beginning because that subarray is empty
    // I3(n) holds at the beginning because the second subarray is empty
    int i = n;
    while ((i > 0) && (order.compare(vals[i - 1], vals[i]) > 0))
      {
        T tmp = vals[i];
        vals[i] = vals[i - 1];
        vals[i - 1] = tmp;
        // Analysis:
        // I1(i-1) holds, but I1(i) does not hold, because we put an
        // an "unknown" element at position i-1.
        // I2(i-1) holds b/c all the values in position 0..(i-1) were
        // less than the values in positions (i+1)..n by I3
        // I3(i-1) holds b/c we know that the value at position i-1 was
        // the largest.
        // Conclusion:
        // We can restore the invariant by subtracting 1 from i.
        i--;
      } // while
        // At this point, either i is 0, in which case I2 tells us that
    // that the elements in position 1..n are sorted and I3 tells us
    // that the element in position 0 is smaller than all of those, or
    // i is positive, in which case we know that the left part is
    // sorted (I1), the right part is sorted (I2), and the element at
    // the boundary is in the right position.
  } // newInsert(T[], Comparator<T>, int)
} // InsertionSorter<T>