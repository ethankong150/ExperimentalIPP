import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import model.Color;
import model.Image;
import model.Pixel;
import view.Histogram;

import static org.junit.Assert.assertEquals;

/**
 * Class used to test the Histogram class.
 */
public class HistogramTest {
  Map<Integer, Integer> testRedHistogram;
  Map<Integer, Integer> testGreenHistogram;
  Map<Integer, Integer> testBlueHistogram;
  Map<Integer, Integer> testIntensityHistogram;
  Pixel[][] pixels;
  Histogram testHistogram;
  Map<String, Map<Integer, Integer>> histogramMap;
  Histogram testHistogram2;

  @Before
  public void init() {
    testRedHistogram = new HashMap<>();
    testGreenHistogram = new HashMap<>();
    testBlueHistogram = new HashMap<>();
    testIntensityHistogram = new HashMap<>();
    histogramMap = new HashMap<>();

    testRedHistogram.put(138, 1);
    testRedHistogram.put(124, 1);
    testRedHistogram.put(12, 1);
    testRedHistogram.put(89, 1);

    testGreenHistogram.put(24, 2);
    testGreenHistogram.put(224, 1);
    testGreenHistogram.put(167, 1);

    testBlueHistogram.put(127, 3);
    testBlueHistogram.put(12, 1);

    testIntensityHistogram.put(96, 1);
    testIntensityHistogram.put(91, 1);
    testIntensityHistogram.put(121, 1);
    testIntensityHistogram.put(89, 1);

    histogramMap.put("Red", testRedHistogram);
    histogramMap.put("Green", testGreenHistogram);
    histogramMap.put("Blue", testBlueHistogram);
    histogramMap.put("Intensity", testIntensityHistogram);

    pixels = new Pixel[2][2];

    pixels[0][0] = new Pixel(new Color(138, 24, 127, 255));
    pixels[0][1] = new Pixel(new Color(124, 24, 127, 255));
    pixels[1][0] = new Pixel(new Color(12, 224, 127, 255));
    pixels[1][1] = new Pixel(new Color(89, 167, 12, 255));

    Image image = new Image(pixels);

    testHistogram = new Histogram(image);
    testHistogram2 = new Histogram(histogramMap);
  }

  @Test
  public void testValidInitialization() {
    assertEquals(testHistogram2, testHistogram);
  }
}
