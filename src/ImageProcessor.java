import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;

import controller.Controller;
import controller.ControllerImpl;
import controller.GraphicalController;
import model.ImageProcessorModel;
import model.ImgProcessorModelImpl;
import view.GUIView;
import view.IGraphicalUserInterfaceView;
import view.ImageProcessorView;
import view.TextView;

/**
 * Class for running an image processing application.
 */
public final class ImageProcessor {

  /**
   * Runs an image processor.
   *
   * @param args user input to run different version of the program in correlation with the jar
   *             file.
   */
  public static void main(String[] args) {
    if (args.length == 0) {
      ImageProcessorModel model = new ImgProcessorModelImpl();
      IGraphicalUserInterfaceView view = new GUIView();
      Controller controller = new GraphicalController(model, view);
      controller.run();
    }
    else if (args[0].equals("-file")) {
      Reader inputStream;
      try {
        inputStream = new FileReader(args[1]);
      } catch (FileNotFoundException e) {
        throw new IllegalArgumentException(String.format("file %s could not be found", args[1]));
      }
      Appendable appendable = System.out;
      ImageProcessorModel model = new ImgProcessorModelImpl();
      ImageProcessorView view = new TextView(appendable);
      Controller controller = new ControllerImpl(model, view, inputStream);
      controller.run();
    }
    else if (args[0].equals("-text")) {
      ImageProcessorModel model = new ImgProcessorModelImpl();
      Appendable appendable = System.out;
      Readable readable = new InputStreamReader(System.in);
      ImageProcessorView view = new TextView(appendable);
      Controller controller = new ControllerImpl(model, view, readable);
      controller.run();
    }
    else {
      throw new IllegalArgumentException("invalid arguments");
    }
  }
}
