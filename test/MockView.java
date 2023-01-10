import controller.Features;
import model.Image;
import model.operations.RedCompImg;
import view.IGraphicalUserInterfaceView;

/**
 * Mock class implementing the IGraphicalUserInterfaceView to mimic a GUIView for testing purposes.
 */
public class MockView implements IGraphicalUserInterfaceView {
  @Override
  public void showGUIObjects() {
    // void mock method
  }

  @Override
  public void displayImage(Image image) {
    // void mock method
  }

  @Override
  public void addFeatures(Features features) {
    features.loadImage();
    features.filterImage(new RedCompImg());
    features.saveImage();
  }

  @Override
  public void refresh() {
    // void mock method
  }

  @Override
  public void displayErrorMessage(String errorMessage) {
    // void mock method
  }

  @Override
  public String loadFilePath() {
    return "test/smallPNG.png";
  }

  @Override
  public String saveFilePath() {
    return "test/testSave.bmp";
  }
}
