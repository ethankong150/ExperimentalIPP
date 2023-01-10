package view;

import java.io.IOException;

/**
 * This class is for displaying relevant messages.
 */
public class TextView implements ImageProcessorView {

  private final Appendable dest;

  /**
   * Create a TextView from the given destination.
   *
   * @param dest StringBuilder to display messages
   */
  public TextView(Appendable dest) {
    if (dest == null) {
      throw new IllegalArgumentException("argument cannot be null");
    }
    this.dest = dest;
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.dest.append(message);
  }
}
