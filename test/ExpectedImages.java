import java.util.Map;

import model.Image;

/**
 * Represents expected map of strings to images.
 */
public class ExpectedImages implements Interaction {

  private final String name;

  private final Image image;

  /**
   * Create an expected image.
   *
   * @param name the expected image name.
   * @param image the expected image.
   */
  public ExpectedImages(String name, Image image) {
    this.name = name;
    this.image = image;
  }

  @Override
  public void applyString(StringBuilder in, StringBuilder out) {
    throw new IllegalArgumentException();
  }

  @Override
  public void applyMap(StringBuilder in, Map<String, Image> images) {
    images.put(name, image);
  }
}
