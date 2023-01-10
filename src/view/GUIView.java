package view;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.Features;
import model.Image;
import model.Pixel;

/**
 * Creates the GUI frame which will house all the panels for the user to see and interact with.
 * This frame implements three different panels, a panel to display the image, a panel to display
 * the histogram, and a panel to contain all the buttons that the user can interact with. This class
 * also updates the corresponding panel when the model is updated via the GraphicalController.
 */
public class GUIView extends JFrame implements IGraphicalUserInterfaceView {
  private final ButtonPanelDisplay buttons;
  // Panel for histogram to reserve the space, haven't implemented this yet
  private JPanel histogram;
  private final JLabel imageDisplay;
  private static JLabel fileLoad;
  private static JLabel fileSave;

  /**
   * Constructs the initial JFrame, setting up the panels and sizing of the different features.
   */
  public GUIView() {
    // Initializes JFrame
    super();
    this.setSize(2000, 1500);
    this.setTitle("Graphical Image Manipulation and Enhancement (GRIME)");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Initializes display panel for image
    this.setLayout(new BorderLayout());
    imageDisplay = new JLabel();
    JScrollPane scrollableImage = new JScrollPane(imageDisplay);
    scrollableImage.setPreferredSize(new Dimension(1000, 750));
    this.add(scrollableImage, BorderLayout.WEST);

    // Initializes button panel area
    buttons = new ButtonPanelDisplay();
    buttons.setPreferredSize(new Dimension(2000, 125));
    this.add(buttons, BorderLayout.SOUTH);

    this.pack();
  }

  @Override
  public void showGUIObjects() {
    this.setVisible(true);
  }

  @Override
  public void displayImage(Image image) {
    if (image != null) {
      int height = image.getHeight();
      int width = image.getWidth();

      BufferedImage rebuiltImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          Pixel currentPixel = image.getPixelAt(i, j);
          Color currentPixelColor = new Color(currentPixel.getColor().getRed(),
                  currentPixel.getColor().getGreen(), currentPixel.getColor().getBlue());
          int singleValueRGB = currentPixelColor.getRGB();
          rebuiltImage.setRGB(j, i, singleValueRGB);
        }
      }
      imageDisplay.setIcon(new ImageIcon(rebuiltImage));

      histogram = new Histogram(image);
      histogram.setLayout(new FlowLayout());
      histogram.setPreferredSize(new Dimension(255, 750));
      this.add(histogram, BorderLayout.CENTER);
      this.setVisible(true);
    }
  }

  @Override
  public void addFeatures(Features features) {
    this.buttons.addFeatures(features);
  }

  @Override
  public void refresh() {
    imageDisplay.repaint();
    histogram.repaint();
  }

  @Override
  public void displayErrorMessage(String errorMessage) {
    JOptionPane.showMessageDialog(this, errorMessage, "Error",
            JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public String loadFilePath() {
    final JFileChooser fileChooser = new JFileChooser();
    fileLoad = new JLabel();

    FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("Supports BMP, JPG," +
            "PNG, and PPM Images", "bmp", "jpg", "png", "ppm");
    fileChooser.setFileFilter(fileFilter);
    int fileChooserOption = fileChooser.showOpenDialog(this);
    if (fileChooserOption == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      String filePath = file.getAbsolutePath();
      fileLoad.setText(filePath);
      return filePath;
    }
    return null;
  }

  @Override
  public String saveFilePath() {
    final JFileChooser fileChooser = new JFileChooser();
    fileSave = new JLabel();

    int fileChooserOption = fileChooser.showSaveDialog(this);
    if (fileChooserOption == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      String filePath = file.getAbsolutePath();
      fileSave.setText(filePath);
      return filePath;
    }
    return null;
  }
}
