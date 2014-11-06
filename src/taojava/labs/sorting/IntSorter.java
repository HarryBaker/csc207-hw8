package taojava.labs.sorting;

/**
 * Sort using insertion sort.
 * 
 * @author Harry Baker, Harriet Zucker, China Mauck.
 */
public class IntSorter
{
  /**
  * Sort an array, vals, of ints using insertion sort. See the Sorter<T> interface for additional
  * details.
  */
  public static int[] sorti(int[] vals)
  {
    for (int i = 1; i < vals.length; i++)
      {
        newerInsert(vals, i);
      } // for
    return vals;
  } // sorti(T[], Comparator<T>)

  /**
   * a newer version of insert that shifts values in the array instead of swapping them
   * @param vals
   * @param n
   */
  static void newerInsert(int[] vals, int n)
  {

    int i = n;
    int tmp = vals[i];
    while ((i > 0) && (vals[i -1] > tmp))
      {
        vals[i] = vals[i - 1];
        i--;
      } // while
    vals[i] = tmp;
  } // newerInsert(T[], Comparator<T>, int)
} // IterativeMergeSorter<T>
