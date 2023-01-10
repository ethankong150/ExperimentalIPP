package model.operations;

import model.Color;

/**
 * Function object to make a grayscale image from the red component.
 */
public class RedCompImg extends AbstractGrayscaleOp {

  @Override
  protected int getColorVal(Color color) {
    return color.getRed();
  }
}
