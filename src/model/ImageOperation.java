package model;

/**
 * Interface for applying operations to images.
 */
public interface ImageOperation {

  /**
   * Applies this image operation to the given image.
   *
   * @param img the image on which to operate
   * @return the new image
   */
  Image apply(Image img);
}
