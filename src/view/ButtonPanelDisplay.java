package view;

import java.util.Random;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import controller.Features;
import model.operations.BlueCompImg;
import model.operations.BlurFilter;
import model.operations.BrightenImg;
import model.operations.GreenCompImg;
import model.operations.HorizontalFlipImg;
import model.operations.IntensityImg;
import model.operations.LumaImg;
import model.operations.Mosaic;
import model.operations.RedCompImg;
import model.operations.SepiaTransformation;
import model.operations.SharpenFilter;
import model.operations.ValueImg;
import model.operations.VerticalFlipImg;

/**
 * Creates a panel for every operation that can be applied in this program, represented as a button
 * which is created in this class.
 */
public class ButtonPanelDisplay extends JPanel {
  private final JButton loadButton;
  private final JButton saveButton;
  private final JButton flipHorizontalButton;
  private final JButton flipVerticalButton;
  private final JButton brightenButton;
  private final JButton darkenButton;
  private final JButton redCompButton;
  private final JButton greenCompButton;
  private final JButton blueCompButton;
  private final JButton intensityCompButton;
  private final JButton valueCompButton;
  private final JButton lumaCompButton;
  private final JButton sepiaButton;
  private final JButton sharpenButton;
  private final JButton blurButton;

  private final JButton mosaicButton;

  /**
   * Constructs the panel of buttons which is displayed on the GUI; Sets each field as a new button
   * that the user can interact with.
   */
  public ButtonPanelDisplay() {
    // Creates the load button
    this.loadButton = new JButton("Load");
    loadButton.setActionCommand("Load Button");
    this.add(loadButton);

    // Creates the save button
    this.saveButton = new JButton("Save");
    saveButton.setActionCommand("Save Button");
    this.add(saveButton);

    // Creates the flipHorizontal button
    this.flipHorizontalButton = new JButton("Flip Horizontally");
    flipHorizontalButton.setActionCommand("Flip Horizontal Button");
    this.add(flipHorizontalButton);

    // Creates the flipVertical button
    this.flipVerticalButton = new JButton("Flip Vertically");
    flipVerticalButton.setActionCommand("Flip Vertical Button");
    this.add(flipVerticalButton);

    // Creates the brighten button
    this.brightenButton = new JButton("Brighten");
    brightenButton.setActionCommand("Brighten Button");
    this.add(brightenButton);

    // Creates the darken button
    this.darkenButton = new JButton("Darken");
    darkenButton.setActionCommand("Darken Button");
    this.add(darkenButton);

    // Creates the red component greyscale button
    this.redCompButton = new JButton("Red Component Greyscale");
    redCompButton.setActionCommand("Red Component Button");
    this.add(redCompButton);

    // Creates the green component greyscale button
    this.greenCompButton = new JButton("Green Component Greyscale");
    greenCompButton.setActionCommand("Green Component Button");
    this.add(greenCompButton);

    // Creates the blue component greyscale button
    this.blueCompButton = new JButton("Blue Component Greyscale");
    blueCompButton.setActionCommand("Blue Component Button");
    this.add(blueCompButton);

    // Creates the intensity component greyscale button
    this.intensityCompButton = new JButton("Intensity Component Greyscale");
    intensityCompButton.setActionCommand("Intensity Component Button");
    this.add(intensityCompButton);

    // Creates the value component greyscale button
    this.valueCompButton = new JButton("Value Component Greyscale");
    valueCompButton.setActionCommand("Value Component Button");
    this.add(valueCompButton);

    // Creates the luma component greyscale button
    this.lumaCompButton = new JButton("Luma Component Greyscale");
    lumaCompButton.setActionCommand("Luma Component Button");
    this.add(lumaCompButton);

    // Creates the sepia color transformation button
    this.sepiaButton = new JButton("Sepia Transform");
    sepiaButton.setActionCommand("Sepia Transform Button");
    this.add(sepiaButton);

    // Creates the sharpen filter button
    this.sharpenButton = new JButton("Sharpen");
    sharpenButton.setActionCommand("Sharpen Button");
    this.add(sharpenButton);

    // Creates the blur filter button
    this.blurButton = new JButton("Blur");
    blurButton.setActionCommand("Blur Button");
    this.add(blurButton);

    // Creates the mosaic button
    this.mosaicButton = new JButton("Mosaic");
    mosaicButton.setActionCommand("Mosaic Button");
    this.add(mosaicButton);
  }

  /**
   * Adds the button event to each button field in this class, which allows for when a button is
   * pushed for both the model and view to be updated accordingly; directly links intended button
   * behavior with button object.
   * @param features a FeaturesImpl which contains all the features which can be performed on an
   *                 image: loading an image, saving an image, or performing a filtering operation
   *                 on it.
   */
  public void addFeatures(Features features) {
    loadButton.addActionListener(evt -> features.loadImage());
    saveButton.addActionListener(evt -> features.saveImage());
    flipHorizontalButton.addActionListener(evt -> features.filterImage(new HorizontalFlipImg()));
    flipVerticalButton.addActionListener(evt -> features.filterImage(new VerticalFlipImg()));
    brightenButton.addActionListener(evt -> {
      String increment = JOptionPane.showInputDialog("Increment to brighten:", "0");
      int inc;
      try {
        inc = Integer.parseInt(increment);
      } catch (NumberFormatException e) {
        inc = 0;
      }
      features.filterImage(new BrightenImg(inc));
    });
    darkenButton.addActionListener(evt -> {
      String increment = JOptionPane.showInputDialog("Increment to darken:", "0");
      int inc;
      try {
        inc = Integer.parseInt(increment);
      } catch (NumberFormatException e) {
        inc = 0;
      }
      features.filterImage(new BrightenImg(inc * -1));
    });
    redCompButton.addActionListener(evt -> features.filterImage(new RedCompImg()));
    greenCompButton.addActionListener(evt -> features.filterImage(new GreenCompImg()));
    blueCompButton.addActionListener(evt -> features.filterImage(new BlueCompImg()));
    intensityCompButton.addActionListener(evt -> features.filterImage(new IntensityImg()));
    valueCompButton.addActionListener(evt -> features.filterImage(new ValueImg()));
    lumaCompButton.addActionListener(evt -> features.filterImage(new LumaImg()));
    sepiaButton.addActionListener(evt -> features.filterImage(new SepiaTransformation()));
    sharpenButton.addActionListener(evt -> features.filterImage(new SharpenFilter()));
    blurButton.addActionListener(evt -> features.filterImage(new BlurFilter()));
    mosaicButton.addActionListener(evt -> {
      String increment = JOptionPane.showInputDialog("Increment to change value of seeds:", "0");
      int inc;
      try {
        inc = Integer.parseInt(increment);
      } catch (NumberFormatException e) {
        inc = 0;
      }
      try {
        features.filterImage(new Mosaic(inc, new Random()));
      } catch (IllegalStateException e) {
        if (e.getMessage().equals("Input a number greater than 0 and less than total pixels of the image!")) {
          JOptionPane.showMessageDialog(this, "Entered number greater than total pixels in image", "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
    });
  }
}
