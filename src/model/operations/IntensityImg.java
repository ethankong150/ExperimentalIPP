package model.operations;

import model.Color;

/**
 * Function object to make a grayscale image from the intensity of each pixel.
 */
public class IntensityImg extends AbstractGrayscaleOp {

  @Override
  protected int getColorVal(Color color) {
    return color.getIntensity();
  }
}
