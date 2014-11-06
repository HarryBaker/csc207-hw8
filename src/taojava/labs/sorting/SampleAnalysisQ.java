package taojava.labs.sorting;

import java.io.PrintWriter;

/**
* A very simple analysis of a few sorting algorithms.
*
* @author Samuel A. Rebelsky
* @author Harry Baker, Hattie Zucker, China Mauck
*/
public class SampleAnalysisQ
{
  public static void main(String[] args)
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    @SuppressWarnings("unchecked")
    Sorter<Integer>[] sorters1 =
        (Sorter<Integer>[]) new Sorter[] { new NewQuicksorter<Integer>(),
                                          new NewQuicksorterMedian<Integer>(),
                                          new NewQuicksorterRandom<Integer>() };
    String[] sorterNames = { "Quicksort", "Quicksort Median", "Quicksort Random" };
    @SuppressWarnings("unchecked")
    ArrayBuilder<Integer>[] builders =
        (ArrayBuilder<Integer>[]) new ArrayBuilder[] {
                                                      SorterAnalyzer.randomIntArrBuilder,
                                                      SorterAnalyzer.increasingIntArrBuilder,
                                                      SorterAnalyzer.mostlyArrBuilder,
                                                      SorterAnalyzer.decreasingIntArrBuilder };
    String[] builderNames = { "Random", "Increasing", "Mostly Sorted", "Decreasing" };
    SorterAnalyzer.combinedAnalysis(pen, sorters1, sorterNames,
                                    SorterAnalyzer.standardIntComparator,
                                    builders, builderNames);
  } // main(String[]
} // SampleAnalysisQ
/*
Sorter          Builder         Input Size      Average Time    
------          -------         ------------    ------------    
Quicksort       Random                 10000   [1, 12, 3]
Quicksort MedianRandom                 10000   [2, 10, 3]
Quicksort RandomRandom                 10000    [2, 4, 3]
Quicksort       Random                 20000    [3, 6, 4]
Quicksort MedianRandom                 20000    [4, 8, 6]
Quicksort RandomRandom                 20000    [4, 8, 6]
Quicksort       Random                 40000   [6, 13, 8]
Quicksort MedianRandom                 40000  [8, 15, 10]
Quicksort RandomRandom                 40000   [8, 11, 9]
Quicksort       Increasing             10000   [4, 13, 7]
Quicksort MedianIncreasing             10000    [2, 4, 3]
Quicksort RandomIncreasing             10000    [2, 3, 2]
Quicksort       Increasing             20000 [15, 23, 19]
Quicksort MedianIncreasing             20000    [3, 7, 6]
Quicksort RandomIncreasing             20000    [5, 7, 6]
Quicksort       Increasing             40000 [33, 37, 34]
Quicksort MedianIncreasing             40000   [7, 13, 7]
Quicksort RandomIncreasing             40000    [6, 9, 7]
Quicksort       Mostly Sorted          10000    [2, 8, 4]
Quicksort MedianMostly Sorted          10000    [2, 4, 3]
Quicksort RandomMostly Sorted          10000    [2, 4, 2]
Quicksort       Mostly Sorted          20000   [5, 12, 8]
Quicksort MedianMostly Sorted          20000    [4, 8, 6]
Quicksort RandomMostly Sorted          20000    [3, 7, 5]
Quicksort       Mostly Sorted          40000 [11, 20, 13]
Quicksort MedianMostly Sorted          40000   [7, 10, 8]
Quicksort RandomMostly Sorted          40000   [7, 10, 8]
Quicksort       Decreasing             10000 [308, 321, 313]
Quicksort MedianDecreasing             10000    [2, 3, 2]
Quicksort RandomDecreasing             10000    [3, 4, 3]
Exception in thread "main" java.lang.StackOverflowError
Quicksort MedianDecreasing             20000    [3, 6, 5]
Quicksort RandomDecreasing             20000    [5, 6, 5]
Quicksort MedianDecreasing             40000    [6, 8, 7]
Quicksort RandomDecreasing             40000    [6, 8, 7]
Our analysis shows that choosing the method of choosing the pivot in the first
implementation of quicksort significantly increases running time, and for significantly
large input, it invokes too many recursion and causes a stack overflow error. We
also see that choosing a random pivot and choosing the median pivot make a relatively
similar run time.
*/