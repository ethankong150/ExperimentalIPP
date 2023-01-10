package controller;

import java.util.Map;

import model.Image;
import model.ImageProcessorModel;
import view.IGraphicalUserInterfaceView;

/**
 * Represents the controller for the GUIView of the image processing application. Initializes
 * a controller and then adds the buttons as features, displays the GUI objects in the frame, and
 * gets the map of images.
 */
public class GraphicalController implements Controller {
  
  private final ImageProcessorModel model;
  
  private final IGraphicalUserInterfaceView view;
  
  private final Features features;
  
  
  /**
   * Constructs a graphical controller which is used to run the GUI view of the image processing
   * application.
   *
   * @param model the ImageProcessorModelImpl passed to the controller which allows the model to
   *              keep track of pixels and update according based on the operations performed.
   * @param view  the GUIView passed to the controller which takes information from the model and
   *              displays it to the user via the GUI.
   */
  public GraphicalController(ImageProcessorModel model, IGraphicalUserInterfaceView view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException("arguments cannot be null");
    }
    this.model = model;
    this.view = view;
    this.features = new FeaturesImpl(model, view);
  }
  
  /**
   * Runs the controller, displaying information from the model which is determined based on user
   * inputs and then passed to the view for the user to see via the GUI.
   *
   * @return the images in the application as a map of the image name and Image containing their
   * pixels.
   */
  public Map<String, Image> run() {
    this.view.addFeatures(this.features);
    this.view.showGUIObjects();
    return this.model.getImages();
  }
}
