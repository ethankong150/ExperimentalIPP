import java.util.Map;

import model.Image;
import model.ImageOperation;
import model.ImageProcessorModel;

/**
 * A mock model to test the controller by always throwing exceptions.
 */
public class MockExceptionsModel implements ImageProcessorModel {

  @Override
  public void load(Image img, String name) {
    // this method is not expected to throw any exceptions
  }

  // Mock model for testing purposes for filter method.
  @Override
  public void filter(ImageOperation op, String name, String destName) {
    throw new IllegalArgumentException(String.format("cannot find image of name %s", name));
  }

  @Override
  public Map<String, Image> getImages() {
    return null;
  }
}
