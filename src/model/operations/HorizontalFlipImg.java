package model.operations;

import model.Color;
import model.Image;

/**
 * Function object to flip an image horizontally.
 */
public class HorizontalFlipImg extends AbstractFlip {

  protected Color getNewColor(Image img, int row, int col, int height, int width) {
    return img.getPixelAt(row, width - (col + 1)).getColor();
  }
}
