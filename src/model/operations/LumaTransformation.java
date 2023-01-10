package model.operations;

/**
 * Function object to make a grayscale image from the luma of each pixel.
 */
public class LumaTransformation extends AbstractColorTransformation {

  /**
   * Create a luma grayscale operation.
   */
  public LumaTransformation() {
    super(new double[][]{{0.2126, 0.7152, 0.0722},
                         {0.2126, 0.7152, 0.0722},
                         {0.2126, 0.7152, 0.0722}});
  }
}
