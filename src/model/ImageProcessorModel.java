package model;

import java.util.Map;

/**
 * This interface represents the operations that can be done in the application.
 */
public interface ImageProcessorModel {

  /**
   * Load the specified image and refer to it as the given name.
   *
   * @param img  the image to load
   * @param name the name to call the image
   */
  void load(Image img, String name);

  /**
   * Create an image from the given image operation and refer to it as the given name.
   *
   * @param op       ImageOperation to be performed
   * @param name     name of the original image
   * @param destName name to call the new image
   */
  void filter(ImageOperation op, String name, String destName);

  /**
   * Gets all the images in this application.
   *
   * @return the images in this application
   */
  Map<String, Image> getImages();
}
