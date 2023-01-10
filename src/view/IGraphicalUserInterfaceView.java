package view;

import controller.Features;
import model.Image;

/**
 * Interface representing the GUI view for the image processing application. Allows a user to
 * load in an image, perform different methods on the image, and then save it to their desired
 * output location, with a histogram visible on the side representing the current iamge.
 */
public interface IGraphicalUserInterfaceView {
  /**
   * Presents different objects to the user via the GUI.
   */
  void showGUIObjects();

  /**
   * Displays an image to the GUI for the user to view.
   *
   * @param image the image to be rendered
   */
  void displayImage(Image image);

  void addFeatures(Features features);

  /**
   * Refreshes the displayed image and panels as changes are made due to user interaction.
   */
  void refresh();

  /**
   * Displays any necessary error messages to the user based on their interaction with the GUI.
   *
   * @param errorMessage the error message that needs to be passed to the user.
   */
  void displayErrorMessage(String errorMessage);

  /**
   * File path the user wants to use to load in an image into the program; Pops up a file explorer
   * for the user to search through their files to load in the correct image.
   *
   * @return the file path the user is loading from as a string.
   */
  String loadFilePath();

  /**
   * File path the user wants to use to save an image from the program to; Pops up a destination
   * file explorer for the user to save the image to on their computer.
   *
   * @return the file path the user wants as a string.
   */
  String saveFilePath();
}
