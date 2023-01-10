package controller;

import controller.commands.Filter;
import controller.commands.Load;
import controller.commands.Save;
import model.ImageOperation;
import model.ImageProcessorModel;
import view.IGraphicalUserInterfaceView;

/**
 * Class representing the implementation of the Features interface used to apply the load, save,
 * and filter with applicable function object, which is eventually added to the corresponding
 * button when the ButtonPanelDisplay class is initialized.
 */
public class FeaturesImpl implements Features {

  private final ImageProcessorModel model;

  private final IGraphicalUserInterfaceView view;

  /**
   * Creates a features object from the given model and view.
   *
   * @param model the ImageProcessorModelImpl which allows the model to keep track of pixels
   *              and update according based on the operations performed.
   * @param view the GUIView which takes information from the model and displays it to the
   *             user via the GUI.
   */
  FeaturesImpl(ImageProcessorModel model, IGraphicalUserInterfaceView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void loadImage() {
    String path = this.view.loadFilePath();
    if (path != null) {
      ProcessorCommand load = new Load(path, this.newImgName());
      load.apply(this.model);
      this.view.displayImage(this.model.getImages().get(this.currentImgName()));
    }
  }

  @Override
  public void saveImage() {
    if (this.model.getImages().size() > 0) {
      String path = this.view.saveFilePath();
      if (path != null) {
        ProcessorCommand save = new Save(path, this.currentImgName());
        save.apply(this.model);
        this.view.displayImage(this.model.getImages().get(this.currentImgName()));
      } else {
        this.view.displayErrorMessage("No images have been loaded");
      }
    }
  }

  @Override
  public void filterImage(ImageOperation op) {
    if (this.model.getImages().size() > 0) {
      ProcessorCommand cmd = new Filter(op, this.currentImgName(), this.newImgName());
      cmd.apply(this.model);
      this.view.displayImage(this.model.getImages().get(this.currentImgName()));
    } else {
      this.view.displayErrorMessage("No images have been loaded");
    }
  }

  private String currentImgName() {
    return String.format("image%d", this.model.getImages().size() - 1);
  }

  private String newImgName() {
    return String.format("image%d", this.model.getImages().size());
  }
}
