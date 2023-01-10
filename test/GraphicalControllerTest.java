import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import controller.Controller;
import controller.GraphicalController;
import model.Color;
import model.Image;
import model.ImageProcessorModel;
import model.ImgProcessorModelImpl;
import model.Pixel;
import view.GUIView;
import view.IGraphicalUserInterfaceView;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the GraphicalController class.
 */
public class GraphicalControllerTest {

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExceptionModelNull() {
    new GraphicalController(null, new GUIView());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExceptionViewNull() {
    new GraphicalController(new ImgProcessorModelImpl(), null);
  }

  @Test
  public void testRun() throws IOException {
    FileOutputStream savedFile = new FileOutputStream("test/testSave.bmp");
    StringBuilder fileStr = new StringBuilder("");
    byte[] byteArr = fileStr.toString().getBytes();
    savedFile.write(byteArr);
    savedFile.close();

    Pixel[][] p3 = new Pixel[2][2];
    p3[0][0] = new Pixel(new Color(250, 50, 237, 255));
    p3[0][1] = new Pixel(new Color(124, 250, 93, 255));
    p3[1][0] = new Pixel(new Color(45, 30, 250, 255));
    p3[1][1] = new Pixel(new Color(250, 138, 40, 255));
    Image img3 = new Image(p3);

    Pixel[][] p3Red = new Pixel[2][2];
    p3Red[0][0] = new Pixel(new Color(250, 255));
    p3Red[0][1] = new Pixel(new Color(124, 255));
    p3Red[1][0] = new Pixel(new Color(45, 255));
    p3Red[1][1] = new Pixel(new Color(250, 255));
    Image img3Red = new Image(p3Red);

    Map<String, Image> images = new HashMap<>();
    images.put("image0", img3);
    images.put("image1", img3Red);

    ImageProcessorModel model = new ImgProcessorModelImpl();
    IGraphicalUserInterfaceView view = new MockView();
    Controller controller = new GraphicalController(model, view);
    Map<String, Image> actual = controller.run();
    assertEquals(images, actual);
  }
}
