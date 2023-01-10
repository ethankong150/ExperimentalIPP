package controller.commands;

import controller.ProcessorCommand;
import model.ImageOperation;
import model.ImageProcessorModel;

/**
 * Make a new image with the given operation.
 */
public class Filter implements ProcessorCommand {
  
  private final ImageOperation op;
  
  private final String name;
  
  private final String destName;
  
  /**
   * Create a command to give an image the appropriate filter.
   *
   * @param op       the operation to apply to the image
   * @param name     the name of the image
   * @param destName the name to call the new image
   */
  public Filter(ImageOperation op, String name, String destName) {
    this.op = op;
    this.name = name;
    this.destName = destName;
  }
  
  @Override
  public void apply(ImageProcessorModel m) throws IllegalArgumentException {
    m.filter(op, name, destName);
  }
}
