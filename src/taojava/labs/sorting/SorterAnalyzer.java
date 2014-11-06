package taojava.labs.sorting;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
* Tools for analyzing sorters.
*
* @author Samuel A. Rebelsky
* @author Harry Baker, Harriet Zucker, China Mauck
*/
public class SorterAnalyzer
{
  // +---------------+---------------------------------------------------
  // | Configuration |
  // +---------------+
  /**
  * The number of repetitions we do in gathering statistics.
  */
  static final int REPETITIONS = 12;
  /**
  * The smallest array size we use.
  */
  static final int SMALLEST = 10000;
  /**
  * The largest array size we use.
  */
  static final int LARGEST = 40000;
  /**
  * The amount we scale the array size between tests.
  */
  static final int SCALE = 2;
  // +-----------+-------------------------------------------------------
  // | Constants |
  // +-----------+
  /**
  * A comparator for integers.
  */
  public static final Comparator<Integer> standardIntComparator =
      (left, right) -> left.compareTo(right);
  /**
  * Build arrays of random integer values.
  */
  public static final ArrayBuilder<Integer> randomIntArrBuilder = (length) ->
    {
      Integer[] vals = new Integer[length];
      Random random = new Random();
      for (int i = 0; i < length; i++)
        vals[i] = random.nextInt(length);
      return vals;
    }; // randomIArrayBuilder
  /**
  * Build arrays of integer values in increasing order.
  */
  public static final ArrayBuilder<Integer> increasingIntArrBuilder =
      (length) ->
        {
          Integer[] vals = new Integer[length];
          for (int i = 0; i < length; i++)
            vals[i] = i;
          return vals;
        }; //ArrayBuilder<Integer> increasingIntArrBuilder
  /**
   * Builds an array that is mostly in order
   */
  public static final ArrayBuilder<Integer> mostlyArrBuilder = (length) ->
    {
      Integer[] vals = new Integer[length];
      Random random = new Random();
      for (int i = 0; i < length; i++)
        vals[i] = i;
      for (int i = 0; i < .1 * length; i++)
        {
          Utils.swap(vals, random.nextInt(length), random.nextInt(length));
        } // for
    return vals;
  }; //ArrayBuilder<Integer> mostlyArrBuilder
  /**
   * Builds an array that is in reverse order
   */
  public static final ArrayBuilder<Integer> decreasingIntArrBuilder =
      (length) ->
        {
          Integer[] vals = new Integer[length];
          for (int i = 0; i < length; i++)
            vals[i] = length - i;
          return vals;
        };//ArrayBuilder<Integer> decreasingIntArrBuilder

  // +--------------+----------------------------------------------------
  // | Class Fields |
  // +--------------+
  // +---------------+---------------------------------------------------
  // | Class Methods |
  // +---------------+
  /**
  * Determine the amount of time sorter takes to sort an array of
  * the given size created by builder.
  *
  * @param sorter
  * The sorting routine we are testing.
  * @param builder
  * The constructor for the array we will sort.
  * @param order
  * The comparator we use in sorting.
  * @param size
  * The size of the array that we sort.
  */
  public static <T> long basicAnalysis(Sorter<T> sorter, Comparator<T> order,
                                       ArrayBuilder<T> builder, int size)
  {
    // Prepare for timing
    SimpleTimer timer = new SimpleTimer();
    // Build the array.
    T[] values = builder.build(size);
    // Run the garbage collector so that garbage collection
    // is less likely to be counted as part of the time for
    // sorting.
    gc();
    // Start the timer. (Duh.)
    timer.start();
    // Do the real work.
    sorter.sort(values, order);
    // Stop the timer.
    timer.pause();
    // And report the time taken
    return timer.elapsed();
  } // basicAnalysis(Sorter<T>, ArrayBuilder<T>, int)

  /**
  * Repeatedly perform basic analysis and gather statistics
  * (e.g., minimum time, maximum time, average time.
  */
  public static <T> long[] compoundAnalysis(Sorter<T> sorter,
                                            Comparator<T> order,
                                            ArrayBuilder<T> builder, int size,
                                            int repetitions)
  {
    long[] values = new long[repetitions];
    for (int count = 0; count < repetitions; count++)
      {
        values[count] = basicAnalysis(sorter, order, builder, size);
      }//for
    long minimum, maximum, average;
    minimum = maximum = average = values[0];
    for (int count = 1; count < repetitions; count++)
      {
        average += values[count];
        if (values[count] < minimum)
          minimum = values[count];
        if (values[count] > maximum)
          maximum = values[count];
      }//for
    return new long[] { minimum, maximum, average / repetitions };
    //While it's possible for an average proceedure to int.MAX_VALUE, 
    //it never will because all the values we chose are lower than 4000.
  } // compoundAnalysis(Sorter<T>, ArrayBuilder<T>, int, int)

  /**
  * Given a variety of sorters and builders, does some analysis
  * of each sorter/builder pair using a variety of array sizes
  * and prints out the results.
  *
  * @param pen
  * Where to send the output
  * @param sorters
  * The objects that do the sorting
  * @param sorterNames
  * The names of those sorters
  * @param builders
  * The objects to build the arrays
  * @param builderNames
  * The names of those builders
  */
  public static <T> void combinedAnalysis(PrintWriter pen, Sorter<T>[] sorters,
                                          String[] sorterNames,
                                          Comparator<T> order,
                                          ArrayBuilder<T> builders[],
                                          String[] builderNames)
  {
    pen.printf("%-16s%-16s%-16s%-16s\n", "Sorter", "Builder", "Input Size",
               "Average Time");
    pen.printf("%-16s%-16s%-16s%-16s\n", "------", "-------", "------------",
               "------------");
    for (int b = 0; b < builders.length; b++)
      {
        for (int size = SMALLEST; size <= LARGEST; size *= SCALE)
          {
            for (int i = 0; i < sorters.length; i++)
              {
                long[] stats =
                    compoundAnalysis(sorters[i], order, builders[b], size,
                                     REPETITIONS);
                pen.printf("%-16s%-16s%12d %12s\n", sorterNames[i],
                           builderNames[b], size, Arrays.toString(stats));
              } // for
          } // for size
      } // for builder : builders
  } // combinedAnalysis(PrintWRiter, Sorter<T>, String[], ...)

  /**
  * Force garbage collection to the best of our ability.
  */
  public static void gc()
  {
    // Right now, we use the quick and dirty "suggest garbage
    // collection". Over the long term, we will probably try
    // something like "get the pid and execute 'jcmd <pid> GC.run'"
    // The pid *might* be in the environment.
    System.gc();
  } // gc()
} // class SorterAnalyzer