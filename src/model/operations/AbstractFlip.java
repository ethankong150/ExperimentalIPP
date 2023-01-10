package model.operations;

import model.Color;
import model.Image;
import model.ImageOperation;
import model.Pixel;

/**
 * Abstract function object to flip an image.
 */
public abstract class AbstractFlip implements ImageOperation {

  @Override
  public Image apply(Image img) {
    int height = img.getHeight();
    int width = img.getWidth();
    Pixel[][] pixels = new Pixel[height][width];

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        Color pixelColor = this.getNewColor(img, row, col, height, width);
        pixels[row][col] = new Pixel(pixelColor);
      }
    }
    return new Image(pixels);
  }

  protected abstract Color getNewColor(Image img, int row, int col, int height, int width);
}
