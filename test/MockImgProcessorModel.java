import java.util.Map;

import model.Image;
import model.ImageOperation;
import model.ImageProcessorModel;
import model.ImgProcessorModelImpl;

/**
 * A mock model to test the controller by tracking inputs.
 */
public class MockImgProcessorModel implements ImageProcessorModel {

  private final StringBuilder log;

  private final ImageProcessorModel delegate;

  /**
   * Create a MockImgProcessorModel with the given StringBuilder.
   *
   * @param log StringBuilder to track inputs to methods
   */
  public MockImgProcessorModel(StringBuilder log, Map<String, Image> images) {
    this.log = log;
    this.delegate = new ImgProcessorModelImpl(images);
  }

  @Override
  public void load(Image img, String name) {
    this.log.append(String.format("load name = %s\n", name));
  }

  @Override
  public void filter(ImageOperation op, String name, String destName) {
    this.log.append(String.format("filter op = %s, name = %s, destName = %s\n",
            op.getClass().getName(), name, destName));
  }

  @Override
  public Map<String, Image> getImages() {
    return delegate.getImages();
  }
}
