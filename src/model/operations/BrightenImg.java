package model.operations;

import model.Color;
import model.Image;
import model.ImageOperation;
import model.Pixel;

/**
 * Function object to make a brighter image.
 */
public class BrightenImg implements ImageOperation {

  private final int increment;

  /**
   * Create an operation to brighten an image.
   *
   * @param increment the amount by which the image will be brightened
   */
  public BrightenImg(int increment) {
    this.increment = increment;
  }

  @Override
  public Image apply(Image img) {
    int height = img.getHeight();
    int width = img.getWidth();
    Pixel[][] pixels = new Pixel[height][width];

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        Color pixelColor = img.getPixelAt(row, col).getColor();
        pixels[row][col] = new Pixel(pixelColor.brighterColor(this.increment));
      }
    }

    return new Image(pixels);
  }
}
