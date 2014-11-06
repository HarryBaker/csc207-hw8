package taojava.labs.sorting;

import java.util.Comparator;
import java.util.Random;

public class NewQuicksorterMedian<T>
    extends NewQuicksorter<T>
{
  @Override
  /**
   * Select three elements at random and use their median as the pivot.
   * @author Harry Baker, Harriet Zucker, China Mauck
   */
  public T selectPivot(T[] vals, Comparator<T> order, int lb, int ub)
  {
    Random random = new Random();
    T val1 = vals[lb + random.nextInt(ub - lb)];
    T val2 = vals[lb + random.nextInt(ub - lb)];
    T val3 = vals[lb + random.nextInt(ub - lb)];
    T median = val1;
    if (order.compare(val2, val1) <= 0)
      {
        if (order.compare(val3, val2) <= 0)
          {
            median = val2;
          } // if
        else if (order.compare(val3, val1) <= 0)
          {
            median = val3;
          } // if
        else
          {
            median = val1;
          } // else
      } // if
    else
      {
        if (order.compare(val3, val1) <= 0)
          {
            median = val1;
          } // if
        else if (order.compare(val3, val2) <= 0)
          {
            median = val3;
          } // else if
        else
          {
            median = val2;
          } // else
      } // else
    return median;
  } // selectPivot(T[], Comparator<T>, int, int)
} // NewQuicksorterMedian<T>
