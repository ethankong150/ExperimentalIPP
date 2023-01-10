import java.util.Map;

import model.Image;

/**
 * A class to simulate user input.
 */
public class Input implements Interaction {

  private final String value;

  /**
   * Create simulated user input.
   *
   * @param value simulated input
   */
  public Input(String value) {
    this.value = value;
  }


  @Override
  public void applyString(StringBuilder in, StringBuilder out) {
    in.append(this.value).append("\n");
  }

  @Override
  public void applyMap(StringBuilder in, Map<String, Image> images) {
    in.append(this.value).append("\n");
  }
}
