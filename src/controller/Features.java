package controller;

import model.ImageOperation;

/**
 * Interface representing the features to be used in GUIView, with each corresponding feature or
 * operation based on the already made load, save, and filter objects.
 */
public interface Features {

  void loadImage();


  void saveImage();


  void filterImage(ImageOperation op);
}
