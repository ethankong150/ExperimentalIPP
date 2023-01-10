package controller;

import java.util.Map;

import model.Image;

/**
 * The controller for an image processor.
 */
public interface Controller {

  /**
   * Run an image processor.
   */
  Map<String, Image> run();
}
