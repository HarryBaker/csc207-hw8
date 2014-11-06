package taojava.labs.sorting;

import java.io.PrintWriter;

/**
* A very simple analysis of a few sorting algorithms.
*
* @author Samuel A. Rebelsky
* @author Harry Baker, Hattie Zucker, China Mauck
*/
public class SampleAnalysisI
{
  public static void main(String[] args)
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    @SuppressWarnings("unchecked")
    Sorter<Integer>[] sorters1 =
        (Sorter<Integer>[]) new Sorter[] { new InsertionSorter<Integer>(),
                                          new NewInsertionSorter<Integer>(),
                                          new NewerInsertionSorter<Integer>() };
    String[] sorterNames = { "Insertion A", "Insertion B", "Insertion C" };
    @SuppressWarnings("unchecked")
    ArrayBuilder<Integer>[] builders =
        (ArrayBuilder<Integer>[]) new ArrayBuilder[] {
                                                      SorterAnalyzer.randomIntArrBuilder,
                                                      SorterAnalyzer.increasingIntArrBuilder,
                                                      SorterAnalyzer.mostlyArrBuilder,
                                                      SorterAnalyzer.decreasingIntArrBuilder };
    String[] builderNames =
        { "Random", "Increasing", "Mostly Sorted", "Decreasing" };
    SorterAnalyzer.combinedAnalysis(pen, sorters1, sorterNames,
                                    SorterAnalyzer.standardIntComparator,
                                    builders, builderNames);
  } // main(String[]
} // SampleAnalysisI
/*
Sorter          Builder         Input Size      Average Time    
------          -------         ------------    ------------    
InsertionA      Random                 10000 [62, 82, 66]
InsertionB      Random                 10000 [61, 81, 67]
InsertionC      Random                 10000 [40, 61, 44]
InsertionA      Random                 20000 [267, 279, 270]
InsertionB      Random                 20000 [294, 299, 296]
InsertionC      Random                 20000 [184, 194, 190]
InsertionA      Random                 40000 [1160, 1204, 1181]
InsertionB      Random                 40000 [1251, 1287, 1266]
InsertionC      Random                 40000 [813, 854, 828]
InsertionA      Increasing             10000    [0, 1, 0]
InsertionB      Increasing             10000    [0, 1, 0]
InsertionC      Increasing             10000    [0, 1, 0]
InsertionA      Increasing             20000    [0, 1, 0]
InsertionB      Increasing             20000    [0, 1, 0]
InsertionC      Increasing             20000    [0, 1, 0]
InsertionA      Increasing             40000    [0, 1, 0]
InsertionB      Increasing             40000    [0, 1, 0]
InsertionC      Increasing             40000    [0, 1, 0]
InsertionA      Mostly Sorted          10000 [15, 21, 17]
InsertionB      Mostly Sorted          10000 [16, 30, 20]
InsertionC      Mostly Sorted          10000 [10, 17, 12]
InsertionA      Mostly Sorted          20000 [60, 68, 63]
InsertionB      Mostly Sorted          20000 [63, 73, 69]
InsertionC      Mostly Sorted          20000 [41, 50, 45]
InsertionA      Mostly Sorted          40000 [241, 258, 247]
InsertionB      Mostly Sorted          40000 [262, 276, 267]
InsertionC      Mostly Sorted          40000 [162, 173, 165]
InsertionA      Decreasing             10000 [127, 135, 130]
InsertionB      Decreasing             10000 [138, 144, 140]
InsertionC      Decreasing             10000 [85, 93, 87]
InsertionA      Decreasing             20000 [500, 516, 504]
InsertionB      Decreasing             20000 [540, 562, 548]
InsertionC      Decreasing             20000 [335, 348, 339]
InsertionA      Decreasing             40000 [1981, 2007, 1991]
InsertionB      Decreasing             40000 [2155, 2185, 2166]
InsertionC      Decreasing             40000 [1341, 1377, 1353]

From the analysis we can see that shifting is, for the most part, the most efficient way to do insertion sort, 
because it consistently runs in less time than the other sorting algorithms. If the elements are already sorted,
insertion sort is a really fast method to use. When the elements are decreasing, insertion sort takes a long time.
*/