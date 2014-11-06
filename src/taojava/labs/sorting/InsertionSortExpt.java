package taojava.labs.sorting;

/**
 * A quick experiment with the insertion sorter.
 * 
 * @author Samuel A. Rebelsky
 */
public class InsertionSortExpt
{
  /**
   * Run the experiments.
   */
  public static void main(String[] args)
  {
    ExptUtils.iExperiments(new IterativeMergeSorter<Integer>());
    ExptUtils.sExperiments(new IterativeMergeSorter<String>());
  } // main(String[])
} // class InsertionSortExpt
