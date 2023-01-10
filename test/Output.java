import java.util.Map;

import model.Image;

/**
 * Represents expected output.
 */
public class Output implements Interaction {

  private final String[] lines;

  /**
   * Create an expected output.
   *
   * @param lines expected output
   */
  public Output(String... lines) {
    this.lines = lines;
  }

  @Override
  public void applyString(StringBuilder in, StringBuilder out) {
    for (String line : this.lines) {
      out.append(line).append("\n");
    }
  }

  @Override
  public void applyMap(StringBuilder in, Map<String, Image> images) {
    throw new IllegalArgumentException();
  }
}
