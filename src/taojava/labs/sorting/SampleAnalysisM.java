package taojava.labs.sorting;

import java.io.PrintWriter;

/**
* A very simple analysis of a few sorting algorithms.
*
* @author Samuel A. Rebelsky
* @author Harry Baker, Hattie Zucker, China Mauck
*/
public class SampleAnalysisM
{
  public static void main(String[] args)
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    @SuppressWarnings("unchecked")
    Sorter<Integer>[] sorters1 =
        (Sorter<Integer>[]) new Sorter[] { new MergeSorter<Integer>(),
                                          new MergeSorterB<Integer>(),
                                          new IterativeMergeSorter<Integer>() };
    String[] sorterNames = { "Merge Sort A", "Merge Sort B", "Iterative Merge" };
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
} // SampleAnalysisM
/*
Sorter          Builder         Input Size      Average Time    
------          -------         ------------    ------------    
Merge Sort A    Random                 10000   [3, 14, 4]
Merge Sort B    Random                 10000 [49, 205, 65]
Iterative Merge Random                 10000   [2, 12, 4]
Merge Sort A    Random                 20000    [4, 8, 6]
Merge Sort B    Random                 20000 [194, 198, 195]
Iterative Merge Random                 20000    [4, 8, 6]
Merge Sort A    Random                 40000 [10, 16, 12]
Merge Sort B    Random                 40000 [758, 768, 762]
Iterative Merge Random                 40000 [10, 16, 13]
Merge Sort A    Increasing             10000    [1, 2, 1]
Merge Sort B    Increasing             10000 [48, 56, 52]
Iterative Merge Increasing             10000    [0, 2, 1]
Merge Sort A    Increasing             20000    [2, 4, 3]
Merge Sort B    Increasing             20000 [190, 196, 193]
Iterative Merge Increasing             20000    [2, 4, 3]
Merge Sort A    Increasing             40000    [4, 8, 6]
Merge Sort B    Increasing             40000 [753, 763, 756]
Iterative Merge Increasing             40000    [4, 8, 6]
Merge Sort A    Mostly Sorted          10000    [1, 3, 2]
Merge Sort B    Mostly Sorted          10000 [49, 56, 53]
Iterative Merge Mostly Sorted          10000    [1, 3, 2]
Merge Sort A    Mostly Sorted          20000    [2, 5, 3]
Merge Sort B    Mostly Sorted          20000 [190, 199, 193]
Iterative Merge Mostly Sorted          20000    [2, 5, 4]
Merge Sort A    Mostly Sorted          40000   [8, 11, 9]
Merge Sort B    Mostly Sorted          40000 [755, 765, 759]
Iterative Merge Mostly Sorted          40000   [5, 11, 9]
Merge Sort A    Decreasing             10000    [1, 2, 1]
Merge Sort B    Decreasing             10000 [49, 54, 51]
Iterative Merge Decreasing             10000    [0, 2, 1]
Merge Sort A    Decreasing             20000    [2, 4, 3]
Merge Sort B    Decreasing             20000 [190, 197, 192]
Iterative Merge Decreasing             20000    [2, 4, 2]
Merge Sort A    Decreasing             40000    [4, 8, 5]
Merge Sort B    Decreasing             40000 [753, 767, 757]
Iterative Merge Decreasing             40000    [3, 7, 4]
From this analysis, we see that using the single scratch array significantly
increases running time, but we also know that it is decreasing memory usage.
We think that would be a trade-off one would have to consider depending on
the context you would use merge sort in. The iterative and first recursive 
merge sort methods both have about the same run time, but the iterative 
also only uses one scratch array. This seems to indicate that an iterative
approach is very practical when using merge sort. 
*/