package model.operations;

import model.Color;
import model.Image;
import model.ImageOperation;
import model.Pixel;

/**
 * Abstract function object to apply a color transformation to an image.
 */
public abstract class AbstractColorTransformation implements ImageOperation {
  private final double[][] transformationMatrix;

  /**
   * Create an operation to change the colors of an image.
   */
  AbstractColorTransformation(double[][] transformationMatrix) {
    this.transformationMatrix = transformationMatrix;
  }

  @Override
  public Image apply(Image img) {
    int height = img.getHeight();
    int width = img.getWidth();
    Pixel[][] pixels = new Pixel[height][width];

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        double[][] pixelMatrix = new double[3][1];
        pixelMatrix[0][0] = img.getPixelAt(row, col).getColor().getRed();
        pixelMatrix[1][0] = img.getPixelAt(row, col).getColor().getGreen();
        pixelMatrix[2][0] = img.getPixelAt(row, col).getColor().getBlue();
        double[][] newPixelMatrix = new double[transformationMatrix.length][pixelMatrix[0].length];

        for (int i = 0; i < newPixelMatrix.length; i++) {
          for (int j = 0; j < newPixelMatrix[i].length; j++) {
            for (int k = 0; k < pixelMatrix.length; k++) {
              newPixelMatrix[i][j] += transformationMatrix[i][k] * pixelMatrix[k][j];
            }
          }
        }
        int red = rgbClamp((int) newPixelMatrix[0][0]);
        int green = rgbClamp((int) newPixelMatrix[1][0]);
        int blue = rgbClamp((int) newPixelMatrix[2][0]);
        pixels[row][col] = new Pixel(new Color(red, green, blue, 255));
      }
    }
    return new Image(pixels);
  }

  private int rgbClamp(int value) {
    return Math.min(Math.max(value, 0), 255);
  }
}
