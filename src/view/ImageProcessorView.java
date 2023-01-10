package view;

import java.io.IOException;

/**
 * This interface represents the operations that are offered by a view for an
 * image processing application.
 */
public interface ImageProcessorView {

  /**
   * Render the given message.
   *
   * @param message the message to be rendered
   * @throws IOException if transmission of the message to the intended destination fails
   */
  void renderMessage(String message) throws IOException;
}
