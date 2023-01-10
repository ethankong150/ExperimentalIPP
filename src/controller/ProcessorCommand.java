package controller;

import model.ImageProcessorModel;

/**
 * Interface for executing commands.
 */
public interface ProcessorCommand {

  /**
   * Execute this command on the given model.
   *
   * @param m the model on which to execute this command
   * @throws IllegalArgumentException if an invalid model is passed to the apply method.
   */
  void apply(ImageProcessorModel m) throws IllegalArgumentException;
}
