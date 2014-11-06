package taojava.labs.sorting;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit tests for the built-in sorter.
 * 
 * @author Samuel A. Rebelsky
 */
public class NewQuickSorterTest
{
  @Test
  public void test1()
  {
    TestUtils.test1(new NewQuicksorter<Integer>());
  } // test1

  @Test
  public void test2()
  {
    TestUtils.test2(new NewQuicksorter<Integer>());
  } // test2
} // NewQuickSorterTest
