package model.operations;

/**
 * Function object to blur an image.
 */
public class BlurFilter extends AbstractFilter {

  /**
   * Create an operation to blur an image.
   */
  public BlurFilter() {
    super(new double[][]{{0.0625, 0.125, 0.0625},
                         {0.125, 0.25, 0.125},
                         {0.0625, 0.125, 0.0625}});
  }
}
