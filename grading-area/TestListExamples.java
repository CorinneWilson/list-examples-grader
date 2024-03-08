import static org.junit.Assert.*;
import org.junit.*;
import java.util.Arrays;
import java.util.List;

class IsA implements StringChecker {
  public boolean checkString(String s) {
    return s.equalsIgnoreCase("a");
  }
}

public class TestListExamples {
  

  @Test(timeout = 500)
  public void testMergeRightEnd() {
    List<String> left = Arrays.asList("a", "b", "c");
    List<String> right = Arrays.asList("a", "d");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("a", "a", "b", "c", "d");
    assertEquals(expected, merged);
  }

  @Test(timeout = 500)
  public void testMerge() {
    List<String> left = Arrays.asList("a", "a", "a");
    List<String> right = Arrays.asList("a", "d");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("a", "a", "a", "a", "d");
    assertEquals(expected, merged);
  }

  @Test(timeout = 500)
  public void testMergeEmpty() {
    List<String> left = Arrays.asList();
    List<String> right = Arrays.asList();
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList();
    assertEquals(expected, merged);
  }

  @Test(timeout = 500)
  public void testFilter1() {
    List<String> list = Arrays.asList("a", "b", "a");
    List<String> filtered = ListExamples.filter(list,  new IsA());
    List<String> expected = Arrays.asList("a", "a");
    assertEquals(expected, filtered);
  }

  @Test(timeout = 500)
  public void testFilter2() {
    List<String> list = Arrays.asList("c", "b");
    List<String> filtered = ListExamples.filter(list,  new IsA());
    List<String> expected = Arrays.asList();
    assertEquals(expected, filtered);
  }

}
