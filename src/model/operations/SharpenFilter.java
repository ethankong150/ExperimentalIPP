package model.operations;

/**
 * Function object to sharpen an image.
 */
public class SharpenFilter extends AbstractFilter {

  /**
   * Create an operation to blur an image.
   */
  public SharpenFilter() {
    super(new double[][]{{-0.125, -0.125, -0.125, -0.125, -0.125},
                         {-0.125, 0.25, 0.25, 0.25, -0.125},
                         {-0.125, 0.25, 1, 0.25, -0.125},
                         {-0.125, 0.25, 0.25, 0.25, -0.125},
                         {-0.125, -0.125, -0.125, -0.125, -0.125}});
  }
}
