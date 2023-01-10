package model;

/**
 * Uses inheritance of the Pixel class to associate coordinates (row, col) with a Pixel object.
 */
public class NewPixel {
  
  private Pixel pixel;
  private int row;
  private int col;
  
  /**
   * Constructor that receives a Pixel object and two integers to create a NewPixel.
   *
   * @param pixel A Pixel object representing a Pixel that should be used to create the NewPixel.
   * @param row   An integer representing the corresponding row.
   * @param col   An integer representing the corresponding column.
   * @throws IllegalArgumentException when null parameter is given or when row or col is less
   *                                  than 0.
   */
  public NewPixel(Pixel pixel, int row, int col) {
    if (pixel == null) {
      throw new IllegalArgumentException("null parameter given");
    }
    if (row < 0 || col < 0) {
      throw new IllegalArgumentException("row or col cannot be less than 0");
    }
    this.pixel = pixel;
    this.row = row;
    this.col = col;
  }
  
  /**
   * Return the row of this NewPixel.
   *
   * @return An integer representing the row of this NewPixel object.
   */
  public int getRow() {
    return this.row;
  }
  
  /**
   * Return the column of this NewPixel.
   *
   * @return An integer representing the column of this NewPixel object.
   */
  public int getCol() {
    return this.col;
  }
  
  /**
   * Return the Pixel object of this NewPixel.
   *
   * @return A Pixel object representing the Pixel object of this NewPixel object.
   */
  public Pixel getPixel() {
    return this.pixel;
  }
}
