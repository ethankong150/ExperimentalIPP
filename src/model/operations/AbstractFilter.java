package model.operations;

import model.Color;
import model.Image;
import model.ImageOperation;
import model.Pixel;

/**
 * Abstract function object to apply a filter that uses a kernel to an image.
 */
public abstract class AbstractFilter implements ImageOperation {

  private final double[][] kernel;

  /**
   * Create a filter operation with the given kernel.
   *
   * @param kernel the kernel to be applied to each pixel
   */
  AbstractFilter(double[][] kernel) {
    this.kernel = kernel;
  }

  @Override
  public Image apply(Image img) {
    int height = img.getHeight();
    int width = img.getWidth();
    Pixel[][] pixels = new Pixel[height][width];

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        pixels[row][col] = this.colorPixel(img, row, col, height, width);
      }
    }
    return new Image(pixels);
  }

  private Pixel colorPixel(Image img, int row, int col, int height, int width) {
    int kernelDifference = kernel.length / 2;
    int maxColor = img.getMaxValue();
    int redSum = 0;
    int greenSum = 0;
    int blueSum = 0;

    for (int i = 0; i < kernel.length; i++) {
      for (int j = 0; j < kernel.length; j++) {
        if ((row - (kernelDifference - i) >= 0) &&
                (col - (j - kernelDifference) >= 0) &&
                (row - (kernelDifference - i) < height) &&
                (col - (j - kernelDifference) < width)) {
          Color pixelColor = img.getPixelAt(row - (kernelDifference - i),
                  col - (j - kernelDifference)).getColor();
          redSum += pixelColor.getRed() * kernel[i][j];
          greenSum += pixelColor.getGreen() * kernel[i][j];
          blueSum += pixelColor.getBlue() * kernel[i][j];
        }
      }
    }
    redSum = rgbClamp(redSum, maxColor);
    greenSum = rgbClamp(greenSum, maxColor);
    blueSum = rgbClamp(blueSum, maxColor);

    return new Pixel(new Color(redSum, greenSum, blueSum, maxColor));
  }

  private int rgbClamp(int value, int maxColor) {
    return Math.min((Math.max(value, 0)), maxColor);
  }
}

