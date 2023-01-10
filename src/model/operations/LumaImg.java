package model.operations;

import model.Color;

/**
 * Function object to make a grayscale image from the luma of each pixel.
 */
public class LumaImg extends AbstractGrayscaleOp {

  @Override
  protected int getColorVal(Color color) {
    return color.getLuma();
  }
}
