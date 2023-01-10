package model.operations;

import model.Color;

/**
 * Function object to make a grayscale image from the green component.
 */
public class GreenCompImg extends AbstractGrayscaleOp {

  @Override
  protected int getColorVal(Color color) {
    return color.getGreen();
  }
}
