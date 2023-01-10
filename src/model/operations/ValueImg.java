package model.operations;

import model.Color;

/**
 * Function object to make a grayscale image from the value of each pixel.
 */
public class ValueImg extends AbstractGrayscaleOp {

  @Override
  protected int getColorVal(Color color) {
    return color.getValue();
  }
}
