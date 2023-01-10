package model;

import java.util.Objects;

/**
 * This class represents a pixel of an Image.
 */
public class Pixel {

  private final Color color;

  /**
   * Create a Pixel with the given color.
   *
   * @param color the color of the pixel
   */
  public Pixel(Color color) {
    this.color = color;
  }

  /**
   * Gets the color of this pixel.
   *
   * @return the color of this pixel
   */
  public Color getColor() {
    return this.color;
  }

  @Override
  public String toString() {
    return String.format("%d\n%d\n%d\n", this.color.getRed(),
            this.color.getGreen(), this.color.getBlue());
  }

  @Override
  public boolean equals(Object obj) {
    if (! (obj instanceof Pixel)) {
      return false;
    }
    Pixel other = (Pixel) obj;

    return this.color.equals(other.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.color);
  }
}
