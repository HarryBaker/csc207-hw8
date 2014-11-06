package taojava.labs.sorting;

import java.util.Comparator;
import java.util.Random;

public class NewQuicksorterRandom<T>
extends NewQuicksorter<T>
{
@Override
/**
 * Select a random element of the array as the pivot.
 * @author Harry Baker, Harriet Zucker, China Mauck
 */
public T selectPivot(T[] vals, Comparator<T> order, int lb, int ub)
{
  Random random = new Random();
  return vals[lb + random.nextInt(ub-lb)];
} // selectPivot(T[], Comparator<T>, int, int)
} // NewQuickSorterRandom
