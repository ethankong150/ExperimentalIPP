package model.operations;

import model.Color;
import model.Image;
import model.ImageOperation;
import model.Pixel;

/**
 * Abstract function object to make a grayscale image.
 */
public abstract class AbstractGrayscaleOp implements ImageOperation {

  @Override
  public Image apply(Image img) {
    int height = img.getHeight();
    int width = img.getWidth();
    Pixel[][] pixels = new Pixel[height][width];

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        Color pixelColor = img.getPixelAt(row, col).getColor();
        int colorVal = this.getColorVal(pixelColor);
        int maxVal = pixelColor.getMaxValue();
        pixels[row][col] = new Pixel(new Color(colorVal, maxVal));
      }
    }

    return new Image(pixels);
  }

  protected abstract int getColorVal(Color color);
}
