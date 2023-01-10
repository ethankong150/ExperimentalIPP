package model.operations;

/**
 * Function object to create a sepia-toned image.
 */
public class SepiaTransformation extends AbstractColorTransformation {

  /**
   * Create a sepia operation.
   */
  public SepiaTransformation() {
    super(new double[][]{{0.393, 0.769, 0.189},
                         {0.349, 0.686, 0.168},
                         {0.272, 0.534, 0.131}});
  }
}