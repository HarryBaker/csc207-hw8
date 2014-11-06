package taojava.labs.sorting;

import java.io.PrintWriter;
/**
 * @author Harry Baker, Harriet Zucker, China Mauck
 */

public class IntAnalysis
{
  public static void main(String[] args)
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    int[] builders[] =
        { IntAnalyzer.randomIntArrBuilder1(40000),
         IntAnalyzer.increasingIntArrBuilder1(40000),
         IntAnalyzer.mostlyArrBuilder1(40000),
         IntAnalyzer.decreasingIntArrBuilder1(40000) };
    String[] builderNames =
        { "Random", "Increasing", "Mostly Sorted", "Decreasing" };
    IntAnalyzer.combinedAnalysis(pen, builders, builderNames);
  } // main(String[])
}// class IntAnalysis
/*
Results for array size 10000:
int sort
Random
[0, 304, 25]
int sort
Increasing
[0, 1, 0]
int sort
Mostly Sorted
[0, 7, 0]
int sort
Decreasing
[0, 397, 33]

Results for array size 20000:
int sort
Random
[0, 943, 79]
int sort
Increasing
[0, 1, 0]
int sort
Mostly Sorted
[0, 171, 14]
int sort
Decreasing
[0, 1869, 156]

Results for array size 40000:
int sort
Random
[0, 3633, 303]
int sort
Increasing
[0, 1, 0]
int sort
Mostly Sorted
[0, 684, 57]
int sort
Decreasing
[0, 6695, 558]
 */
