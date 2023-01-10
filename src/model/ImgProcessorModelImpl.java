package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents an image processing application that filters and stores images.
 */
public class ImgProcessorModelImpl implements ImageProcessorModel {

  private final Map<String, Image> images;

  /**
   * Create an ImageProcessingModel with the given images.
   *
   * @param images the images in this model
   */
  public ImgProcessorModelImpl(Map<String, Image> images) {
    if (images == null) {
      throw new IllegalArgumentException("argument cannot be null");
    }
    this.images = images;
  }

  /**
   * Create an ImageProcessingModel with no images.
   */
  public ImgProcessorModelImpl() {
    this(new HashMap<String, Image>());
  }

  @Override
  public void load(Image img, String name) {
    this.images.put(name, img);
  }

  @Override
  public void filter(ImageOperation op, String name, String destName) {
    Image img = this.images.get(name);
    if (img == null) {
      throw new IllegalArgumentException(String.format("cannot find image of name %s", name));
    }
    Image newImg = op.apply(img);
    this.images.put(destName, newImg);
  }

  @Override
  public Map<String, Image> getImages() {
    return Map.copyOf(this.images);
  }
}
