package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.swing.JPanel;

import model.Image;

/**
 * Represents the histogram panel of the GUI JFrame. Draws the histogram of the current image as 4
 * different line charts, one for each RGB channel and another for the intensity of each pixel.
 * Updates accordingly as a new image is loaded in or the pixels are manipulated via operation
 * buttons.
 */
public class Histogram extends JPanel {
  private final Map<Integer, Integer> redComponentHistogram;
  private final Map<Integer, Integer> greenComponentHistogram;
  private final Map<Integer, Integer> blueComponentHistogram;
  private final Map<Integer, Integer> intensityComponentHistogram;
  private Image image;


  /**
   * Constructs the data stored for each histogram. Each individual histogram line chart is
   * represented by a map with key integers and value integers. The key represents the RGB channel
   * value and the value represents the number of times that RGB value occurs in the given image.
   * @param image  an image passed to the Histogram constructor to generate the corresponding maps,
   *               eventually used to draw the histogram onto the GUI.
   */
  public Histogram(Image image) {
    int height = image.getHeight();
    int width = image.getWidth();

    redComponentHistogram = new HashMap<>();
    greenComponentHistogram = new HashMap<>();
    blueComponentHistogram = new HashMap<>();
    intensityComponentHistogram = new HashMap<>();

    this.image = image;

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        model.Color pixelColor = image.getPixelAt(i, j).getColor();
        histogramMapHelper(redComponentHistogram, pixelColor.getRed());
        histogramMapHelper(greenComponentHistogram, pixelColor.getGreen());
        histogramMapHelper(blueComponentHistogram, pixelColor.getBlue());
        histogramMapHelper(intensityComponentHistogram, pixelColor.getIntensity());
      }
    }
  }

  /**
   * Alternative histogram constructor, created by passing already existing histograms for each
   * field, rather than generating them based on image pixels.
   * @param histograms A map of the histograms, with the key represented as a string and the name
   *                   of each histogram map.
   */
  public Histogram(Map<String, Map<Integer, Integer>> histograms) {
    this.redComponentHistogram = histograms.get("Red");
    this.greenComponentHistogram = histograms.get("Green");
    this.blueComponentHistogram = histograms.get("Blue");
    this.intensityComponentHistogram = histograms.get("Intensity");
  }

  private void histogramMapHelper(Map<Integer, Integer> map, int key) {
    if (map.get(key) == null) {
      map.put(key, 1);
    }
    else {
      int value = map.get(key);
      map.put(key, value + 1);
    }
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D graphics2D = (Graphics2D) g;
    int[] yCoordsRed = mapToArray(redComponentHistogram);
    int[] yCoordsGreen = mapToArray(greenComponentHistogram);
    int[] yCoordsBlue = mapToArray(blueComponentHistogram);
    int[] yCoordsIntensity = mapToArray(intensityComponentHistogram);

    graphics2D.setColor(java.awt.Color.RED);
    drawHistogram(graphics2D, yCoordsRed);

    graphics2D.setColor(java.awt.Color.GREEN);
    drawHistogram(graphics2D, yCoordsGreen);

    graphics2D.setColor(java.awt.Color.BLUE);
    drawHistogram(graphics2D, yCoordsBlue);

    graphics2D.setColor(java.awt.Color.BLACK);
    drawHistogram(graphics2D, yCoordsIntensity);
  }

  private void drawHistogram(Graphics g, int[] yCoordsList) {
    int numPixels = this.image.getHeight() * this.image.getWidth();
    int prevX = 0;
    int prevY = 0;
    int unitX = 512 / this.image.getPixelAt(0, 0).getColor().getMaxValue();
    float unitY = (float) (750.0 / numPixels);

    for (int y : yCoordsList) {
      g.drawLine(prevX, prevY, prevX += unitX, prevY = (int) (750 -
              (y * unitY * (numPixels / 1000))));
    }
  }

  private int[] mapToArray(Map<Integer, Integer> map) {
    int[] coords = new int[map.size()];
    for (int i = 0; i < map.size(); i++) {
      int entryValue = map.getOrDefault(i, 0);
      coords[i] = entryValue;
    }
    return coords;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Histogram)) {
      return false;
    }
    else {
      Histogram other = (Histogram) o;
      return (this.redComponentHistogram.equals(other.redComponentHistogram) &&
              this.greenComponentHistogram.equals(other.greenComponentHistogram) &&
              this.blueComponentHistogram.equals(other.blueComponentHistogram) &&
              this.intensityComponentHistogram.equals(other.intensityComponentHistogram));
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(redComponentHistogram, greenComponentHistogram, blueComponentHistogram,
            intensityComponentHistogram);
  }
}