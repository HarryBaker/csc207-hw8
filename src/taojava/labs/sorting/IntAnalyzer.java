package taojava.labs.sorting;

import java.io.PrintWriter;

import java.util.Arrays;
import java.util.Random;

/*
 * @authors: Sam Rebelksy, Harry Baker, Harriet Zucker, China Mauck
 */
public class IntAnalyzer
{

  //+---------------+---------------------------------------------------
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
  * Build arrays of random integer values.
  */
  public static final int[] randomIntArrBuilder1(int length)
  {
    int[] vals = new int[length];
    Random random = new Random();
    for (int i = 0; i < length; i++)
      vals[i] = random.nextInt(length);
    return vals;
  }; // randomIArrayBuilder(int)

  /**
  * Build arrays of integer values in increasing order.
  */
  public static final int[] increasingIntArrBuilder1(int length)
  {
    int[] vals = new int[length];
    for (int i = 0; i < length; i++)
      vals[i] = i;
    return vals;
  }; //increasingIntArrBuilder1(int)

  /**
   * Builds an array that is mostly in order
   */
  public static final int[] mostlyArrBuilder1(int length)
  {
    int[] vals = new int[length];
    Random random = new Random();
    for (int i = 0; i < length; i++)
      vals[i] = i;
    for (int i = 0; i < .1 * length; i++)
      {
        Utils.swapi(vals, random.nextInt(length), random.nextInt(length));
      } // for
    return vals;
  }; //mostlyArrBuilder1(int)

  /**
   * Builds an array that is in reverse order
   */
  public static final int[] decreasingIntArrBuilder1(int length)
  {
    int[] vals = new int[length];
    for (int i = 0; i < length; i++)
      vals[i] = length - i;
    return vals;
  }; //decreasingIntArrBuilder1(int)

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
  public static long basicAnalysis(int[] builder, int size)
  {
    // Prepare for timing
    SimpleTimer timer = new SimpleTimer();
    // Build the array.
    int[] values = builder;
    // Run the garbage collector so that garbage collection
    // is less likely to be counted as part of the time for
    // sorting.
    gc();
    // Start the timer. (Duh.)
    timer.start();
    // Do the real work.
    IntSorter.sorti(values);
    // Stop the timer.
    timer.pause();
    // And report the time taken
    return timer.elapsed();
  } // basicAnalysis(Sorter<T>, ArrayBuilder<T>, int)

  /**
  * Repeatedly perform basic analysis and gather statistics
  * (e.g., minimum time, maximum time, average time.
  */
  public static long[]
    compoundAnalysis(int[] builder, int size, int repetitions)
  {
    long[] values = new long[repetitions];
    for (int count = 0; count < repetitions; count++)
      {
        values[count] = basicAnalysis(builder, size);
      } // for
    long minimum, maximum, average;
    minimum = maximum = average = values[0];
    for (int count = 1; count < repetitions; count++)
      {
        average += values[count];
        if (values[count] < minimum)
          minimum = values[count];
        if (values[count] > maximum)
          maximum = values[count];
      } // for
    return new long[] { minimum, maximum, average / repetitions };
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
  public static void combinedAnalysis(PrintWriter pen, int[] builders[],
                                      String[] builderNames)
  {
    for (int b = 0; b < builders.length; b++)
      {
        long[] stats = compoundAnalysis(builders[b], 40000, REPETITIONS);
        pen.println("int sort");
        pen.println(builderNames[b]);
        pen.println(Arrays.toString(stats));
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
}//class IntAnalyzer
