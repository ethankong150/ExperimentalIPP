package model;

import java.util.Arrays;
import java.util.Objects;

/**
 * This class represents an image from an ASCII PPM file.
 */
public class Image {

  private final int width;

  private final int height;

  private final Pixel[][] pixels;

  /**
   * Create an Image with the given name and pixels.
   *
   * @param pixels array of Pixels that makes up the image
   */
  public Image(Pixel[][] pixels) {
    this.pixels = Objects.requireNonNull(pixels);
    this.height = this.pixels.length;
    this.width = this.pixels[0].length;
  }

  /**
   * Get the width of this image.
   *
   * @return the width of this image
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * Get the height of this image.
   *
   * @return the height of this image
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * Get the pixel at the given row and column.
   *
   * @param row the row of the pixel sought, starting at 0
   * @param col the column of the pixel sought, starting at 0
   * @return the pixel at the given row and column
   * @throws IllegalArgumentException if the given row or column is not within the
   *                                  dimensions of the image
   */
  public Pixel getPixelAt(int row, int col) throws IllegalArgumentException {
    if (row < 0 || row >= this.height || col < 0 || col >= this.width) {
      throw new IllegalArgumentException("row and col must be within dimensions of the image");
    }
    return this.pixels[row][col];
  }

  /**
   * Gets the maximum value of a component of the colors in this image.
   *
   * @return the maximum value of a component of the colors in this image
   */
  public int getMaxValue() {
    return this.getPixelAt(0, 0).getColor().getMaxValue();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Image)) {
      return false;
    }
    Image other = (Image) obj;

    return this.width == other.width
            && this.height == other.height
            && Arrays.deepEquals(this.pixels, other.pixels);
  }

  @Override
  public int hashCode() {
    return Objects.hash(width, height, Arrays.deepHashCode(pixels));
  }
}
