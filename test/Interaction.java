import java.util.Map;

import model.Image;

/**
 * Represents simulated interactions with an image processor.
 */
public interface Interaction {

  /**
   * Applies this Interaction to the appropriate input.
   *
   * @param in simulated user input
   * @param out expected output
   */
  void applyString(StringBuilder in, StringBuilder out);

  /**
   * Applies this Interaction to the appropriate input.
   *
   * @param in simulated user input
   * @param images expected map of strings to images
   */
  void applyMap(StringBuilder in, Map<String, Image> images);
}
