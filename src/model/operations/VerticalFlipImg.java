package model.operations;

import model.Color;
import model.Image;

/**
 * Function object to flip an image vertically.
 */
public class VerticalFlipImg extends AbstractFlip {

  protected Color getNewColor(Image img, int row, int col, int height, int width) {
    return img.getPixelAt(height - (row + 1), col).getColor();
  }
}
