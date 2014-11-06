package taojava.labs.sorting;

import java.io.PrintWriter;

public class QuicksortAnalysis
{
  /**
  * A very simple analysis of a few sorting algorithms.
  *
  * @author Samuel A. Rebelsky
  * @author China Mauck, Hattie Zucker, Harry
  */
  public static void main(String[] args)
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    @SuppressWarnings("unchecked")
    Sorter<Integer>[] sorters =
        (Sorter<Integer>[]) new Sorter[] { new NewQuicksorter<Integer>(),
                                          new NewerQuicksorter<Integer>(),
                                          new NewQuicksorterRandom<Integer>(),
                                          new NewQuicksorterMedian<Integer>() };
    String[] sorterNames =
        { "NewQuicksorter", "NewerQuicksorter", "NewQuickSorterRandom",
         "NewQuicksorterMedian" };
    @SuppressWarnings("unchecked")
    ArrayBuilder<Integer>[] builders =
        (ArrayBuilder<Integer>[]) new ArrayBuilder[] {
                                                      SorterAnalyzer.randomIntArrBuilder,
                                                      SorterAnalyzer.increasingIntArrBuilder,
                                                      SorterAnalyzer.mostlyArrBuilder,
                                                      SorterAnalyzer.decreasingIntArrBuilder };
    String[] builderNames = { "Random", "Increasing", "Mostly Sorted","Decreasing" };
    SorterAnalyzer.combinedAnalysis(pen, sorters, sorterNames,
                                    SorterAnalyzer.standardIntComparator,
                                    builders, builderNames);
    @SuppressWarnings("unchecked")
    Sorter<Integer>[] sorters1 =
        (Sorter<Integer>[]) new Sorter[] { new InsertionSorter<Integer>(),
                                          new NewInsertionSorter<Integer>(),
                                          new NewerInsertionSorter<Integer>() };

  } // main(String[]
} // QuicksorterAnalysis
