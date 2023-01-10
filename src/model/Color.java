package model;

import java.util.Objects;

/**
 * Represents the color of a pixel.
 */
public class Color {

  private final int red;

  private final int green;

  private final int blue;

  private final int maxValue;

  /**
   * Create a color with a maximum value such that each color component is
   * between 0 and the maximum value.
   *
   * @param red value of the red component
   * @param green value of the green component
   * @param blue value of the blue component
   * @param maxValue maximum value of a component of this color
   * @throws IllegalArgumentException if maxValue is less than 1 or any of the first three
   *     arguments are greater than maxValue or less than 0
   */
  public Color(int red, int green, int blue, int maxValue)
          throws IllegalArgumentException {
    if (maxValue < 1) {
      throw new IllegalArgumentException("maxColorValue must be at least 1");
    }
    if (red > maxValue || green > maxValue || blue > maxValue
            || red < 0 || green < 0 || blue < 0) {
      throw new IllegalArgumentException("components must be between 0 and maxValue");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.maxValue = maxValue;
  }

  /**
   * Create a color with all three components set to a single value that is no greater than
   * the given maximum value.
   *
   * @param colorValue value of each component
   * @param maxValue maximum value of a component of this color
   * @throws IllegalArgumentException if maxValue is less than 1 or any of the first three
   *     arguments are greater than maxValue or less than 0
   */
  public Color(int colorValue, int maxValue)
          throws IllegalArgumentException {
    this(colorValue, colorValue, colorValue, maxValue);
  }

  /**
   * Gets the red component of this color.
   *
   * @return the red component of this color
   */
  public int getRed() {
    return this.red;
  }

  /**
   * Gets the green component of this color.
   *
   * @return the green component of this color
   */
  public int getGreen() {
    return this.green;
  }

  /**
   * Gets the blue component of this color.
   *
   * @return the blue component of this color
   */
  public int getBlue() {
    return this.blue;
  }

  /**
   * Gets the maximum value of a component of this color.
   *
   * @return the maximum value of a component of this color
   */
  public int getMaxValue() {
    return this.maxValue;
  }

  /**
   * Gets the largest component of this color.
   *
   * @return the largest component of this color
   */
  public int getValue() {
    return Math.max(this.blue, Math.max(this.red, this.green));
  }

  /**
   * Gets the average of the components of this color.
   *
   * @return the average of the components of this color
   */
  public int getIntensity() {
    return (this.red + this.green + this.blue) / 3;
  }

  /**
   * Gets the weighted sum of the components of this color: 0.2126red + 0.7152green + 0.0722blue.
   *
   * @return the weighted sum of the components of this color
   */
  public int getLuma() {
    return Math.round(((float)0.2126 * this.red)
            + ((float)0.7152 * this.green)
            + ((float)0.0722 * this.blue));
  }

  /**
   * Makes a color with every component increased by the given increment up to
   * this color's maxValue.
   *
   * @param increment amount by which to change each component
   * @return the brightened color
   */
  public Color brighterColor(int increment) {
    return new Color(this.brighterComponent(increment, this.red),
            this.brighterComponent(increment, this.green),
            this.brighterComponent(increment, this.blue),
            this.maxValue);
  }

  private int brighterComponent(int increment, int component) {
    int newVal = increment + component;
    if (newVal > this.maxValue) {
      newVal = this.maxValue;
    }
    else if (newVal < 0) {
      newVal = 0;
    }
    return newVal;
  }

  @Override
  public boolean equals(Object obj) {
    if (! (obj instanceof Color)) {
      return false;
    }
    Color other = (Color) obj;

    return this.red == other.red
            && this.green == other.green
            && this.blue == other.blue
            && this.maxValue == other.maxValue;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.red, this.green, this.blue, this.maxValue);
  }
}
