import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import view.ImageProcessorView;
import view.TextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A JUnit test class for the TextView class.
 */
public class ViewTest {
  ImageProcessorView testView;

  @Before
  public void init() {
    testView = new TextView(System.out);
  }

  @Test
  public void testInvalidInitialization() {
    try {
      testView = new TextView(null);
      fail("throw IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("argument cannot be null")) {
        fail();
      }
    }
  }

  @Test
  public void testRenderMessage() throws IOException {
    StringBuilder sb = new StringBuilder();
    ImageProcessorView view = new TextView(sb);
    view.renderMessage("test");
    assertEquals("test", sb.toString());
    view.renderMessage(System.lineSeparator() + "hello");
    assertEquals("test" + System.lineSeparator() + "hello", sb.toString());
  }
}
