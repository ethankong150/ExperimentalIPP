package model.operations;

import model.Color;

/**
 * Function object to make a grayscale image from the blue component.
 */
public class BlueCompImg extends AbstractGrayscaleOp {

  @Override
  protected int getColorVal(Color color) {
    return color.getBlue();
  }
}
