import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


import controller.commands.Load;
import model.ImageProcessorModel;
import model.ImgProcessorModelImpl;
import model.Image;
import model.Pixel;
import model.Color;
import model.operations.BlueCompImg;
import model.operations.BlurFilter;
import model.operations.BrightenImg;
import model.operations.GreenCompImg;
import model.operations.HorizontalFlipImg;
import model.operations.IntensityImg;
import model.operations.LumaImg;
import model.operations.LumaTransformation;
import model.operations.Mosaic;
import model.operations.RedCompImg;
import model.operations.SepiaTransformation;
import model.operations.SharpenFilter;
import model.operations.ValueImg;
import model.operations.VerticalFlipImg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * A JUnit test class for the ImageProcessingModel class.
 */
public class ImageProcessingModelTests {
  
  ImageProcessorModel model1;
  
  Map<String, Image> images1;
  
  ImageProcessorModel model2;
  
  Map<String, Image> images2;
  
  Image img2;
  
  ImageProcessorModel model3;
  
  Map<String, Image> images3;
  
  Image img3;
  ImageProcessorModel model4;
  Map<String, Image> images4;
  Image img4;
  
  @Before
  public void setUp() throws IOException {
    this.images1 = new HashMap<String, Image>();
    this.model1 = new ImgProcessorModelImpl(images1);
    
    Pixel[][] p2 = new Pixel[4][3];
    for (int row = 0; row < 4; row++) {
      for (int col = 0; col < 3; col++) {
        p2[row][col] = new Pixel(new Color(((4 * row) + col) * 10, ((3 * row) + col) * 20,
            ((2 * row) + col) * 30, 255));
      }
    }
    this.img2 = new Image(p2);
    
    this.images2 = new HashMap<String, Image>();
    this.images2.put("image3-4-255", img2);
    this.model2 = new ImgProcessorModelImpl(images2);
    
    Pixel[][] p3 = new Pixel[2][2];
    p3[0][0] = new Pixel(new Color(56, 78, 200, 255));
    p3[0][1] = new Pixel(new Color(177, 30, 25, 255));
    p3[1][0] = new Pixel(new Color(52, 188, 230, 255));
    p3[1][1] = new Pixel(new Color(90, 156, 120, 255));
    this.img3 = new Image(p3);
    
    this.images3 = new HashMap<String, Image>();
    this.images3.put("image2-2-255", img3);
    this.model3 = new ImgProcessorModelImpl(images3);
    
    Pixel[][] p4 = new Pixel[2][2];
    p4[0][0] = new Pixel(new Color(250, 50, 237, 255));
    p4[0][1] = new Pixel(new Color(45, 30, 250, 255));
    p4[1][0] = new Pixel(new Color(124, 250, 93, 255));
    p4[1][1] = new Pixel(new Color(250, 138, 40, 255));
    
    this.img4 = new Image(p4);
    
    this.images4 = new HashMap<String, Image>();
    this.images4.put("pngImage2-2-255", img4);
    this.model4 = new ImgProcessorModelImpl(images4);
    
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException() {
    Map<String, Image> i1 = null;
    new ImgProcessorModelImpl(i1);
  }
  
  @Test
  public void testLoad() throws FileNotFoundException {
    assertTrue(this.images1.isEmpty());
    model1.load(img2, "image3-4-255");
    
    Pixel[][] p1 = new Pixel[4][3];
    for (int row = 0; row < 4; row++) {
      for (int col = 0; col < 3; col++) {
        p1[row][col] = new Pixel(new Color(((4 * row) + col) * 10, ((3 * row) + col) * 20,
            ((2 * row) + col) * 30, 255));
      }
    }
    Image i1 = new Image(p1);
    
    assertEquals(i1, this.images1.get("image3-4-255"));
  }
  
  @Test
  public void testRedComponent() {
    this.model2.filter(new RedCompImg(), "image3-4-255", "red-grayscale");
    
    Pixel[][] p1 = new Pixel[4][3];
    for (int row = 0; row < 4; row++) {
      for (int col = 0; col < 3; col++) {
        p1[row][col] = new Pixel(new Color(((4 * row) + col) * 10, 255));
      }
    }
    Image i1 = new Image(p1);
    
    assertEquals(i1, this.images2.get("red-grayscale"));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testRedComponentException() {
    this.model1.filter(new RedCompImg(), "image3-4-255", "grayscale");
  }
  
  @Test
  public void testGreenComponent() {
    this.model2.filter(new GreenCompImg(), "image3-4-255", "green-grayscale");
    
    Pixel[][] p1 = new Pixel[4][3];
    for (int row = 0; row < 4; row++) {
      for (int col = 0; col < 3; col++) {
        p1[row][col] = new Pixel(new Color(((3 * row) + col) * 20, 255));
      }
    }
    Image i1 = new Image(p1);
    
    assertEquals(i1, this.images2.get("green-grayscale"));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testGreenComponentException() {
    this.model1.filter(new GreenCompImg(), "image3-4-255", "grayscale");
  }
  
  @Test
  public void testBlueComponent() {
    this.model2.filter(new BlueCompImg(), "image3-4-255", "blue-grayscale");
    
    Pixel[][] p1 = new Pixel[4][3];
    for (int row = 0; row < 4; row++) {
      for (int col = 0; col < 3; col++) {
        p1[row][col] = new Pixel(new Color(((2 * row) + col) * 30, 255));
      }
    }
    Image i1 = new Image(p1);
    
    assertEquals(i1, this.images2.get("blue-grayscale"));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testBlueComponentException() {
    this.model1.filter(new BlueCompImg(), "image3-4-255", "grayscale");
  }
  
  @Test
  public void testValue() {
    this.model3.filter(new ValueImg(), "image2-2-255", "value-grayscale");
    
    Pixel[][] p1 = new Pixel[2][2];
    p1[0][0] = new Pixel(new Color(200, 255));
    p1[0][1] = new Pixel(new Color(177, 255));
    p1[1][0] = new Pixel(new Color(230, 255));
    p1[1][1] = new Pixel(new Color(156, 255));
    Image i1 = new Image(p1);
    
    assertEquals(i1, this.images3.get("value-grayscale"));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testValueException() {
    this.model1.filter(new ValueImg(), "image3-4-255", "grayscale");
  }
  
  @Test
  public void testIntensity() {
    this.model3.filter(new IntensityImg(), "image2-2-255", "intensity-grayscale");
    
    Pixel[][] p1 = new Pixel[2][2];
    p1[0][0] = new Pixel(new Color(111, 255));
    p1[0][1] = new Pixel(new Color(77, 255));
    p1[1][0] = new Pixel(new Color(156, 255));
    p1[1][1] = new Pixel(new Color(122, 255));
    Image i1 = new Image(p1);
    
    assertEquals(i1, this.images3.get("intensity-grayscale"));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIntensityException() {
    this.model1.filter(new IntensityImg(), "image3-4-255", "grayscale");
  }
  
  @Test
  public void testLuma() {
    this.model3.filter(new LumaImg(), "image2-2-255", "luma-grayscale");
    
    Pixel[][] p1 = new Pixel[2][2];
    p1[0][0] = new Pixel(new Color(82, 255));
    p1[0][1] = new Pixel(new Color(61, 255));
    p1[1][0] = new Pixel(new Color(162, 255));
    p1[1][1] = new Pixel(new Color(139, 255));
    Image i1 = new Image(p1);
    
    assertEquals(i1, this.images3.get("luma-grayscale"));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testLumaException() {
    this.model1.filter(new LumaImg(), "image3-4-255", "grayscale");
  }
  
  @Test
  public void testHorizontalFlip() {
    this.model2.filter(new HorizontalFlipImg(), "image3-4-255", "horizontal-flip");
    
    Pixel[][] p1 = new Pixel[4][3];
    for (int row = 0; row < 4; row++) {
      for (int col = 0; col < 3; col++) {
        p1[row][2 - col] = new Pixel(new Color(((4 * row) + col) * 10, ((3 * row) + col) * 20,
            ((2 * row) + col) * 30, 255));
      }
    }
    Image i1 = new Image(p1);
    
    assertEquals(i1, this.images2.get("horizontal-flip"));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testHorizontalFlipException() {
    this.model1.filter(new HorizontalFlipImg(), "image3-4-255", "flipped");
  }
  
  @Test
  public void testVerticalFlip() {
    this.model2.filter(new VerticalFlipImg(), "image3-4-255", "vertical-flip");
    
    Pixel[][] p1 = new Pixel[4][3];
    for (int row = 0; row < 4; row++) {
      for (int col = 0; col < 3; col++) {
        p1[3 - row][col] = new Pixel(new Color(((4 * row) + col) * 10, ((3 * row) + col) * 20,
            ((2 * row) + col) * 30, 255));
      }
    }
    Image i1 = new Image(p1);
    
    assertEquals(i1, this.images2.get("vertical-flip"));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testVerticalFlipException() {
    this.model1.filter(new VerticalFlipImg(), "image3-4-255", "flipped");
  }
  
  @Test
  public void testBrighten() {
    this.model3.filter(new BrightenImg(90), "image2-2-255", "brighter");
    
    Pixel[][] p1 = new Pixel[2][2];
    p1[0][0] = new Pixel(new Color(146, 168, 255, 255));
    p1[0][1] = new Pixel(new Color(255, 120, 115, 255));
    p1[1][0] = new Pixel(new Color(142, 255, 255, 255));
    p1[1][1] = new Pixel(new Color(180, 246, 210, 255));
    Image i1 = new Image(p1);
    
    assertEquals(i1, this.images3.get("brighter"));
  }
  
  
  @Test
  public void testBrightenNegative() {
    this.model3.filter(new BrightenImg(-30), "image2-2-255", "darker");
    
    Pixel[][] p1 = new Pixel[2][2];
    p1[0][0] = new Pixel(new Color(26, 48, 170, 255));
    p1[0][1] = new Pixel(new Color(147, 0, 0, 255));
    p1[1][0] = new Pixel(new Color(22, 158, 200, 255));
    p1[1][1] = new Pixel(new Color(60, 126, 90, 255));
    Image i1 = new Image(p1);
    
    assertEquals(i1, this.images3.get("darker"));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testBrightenException() {
    this.model1.filter(new BrightenImg(40), "image3-4-255", "brighter");
  }
  
  @Test
  public void testColorTransformLuma() {
    this.model3.filter(new LumaTransformation(), "image2-2-255", "luma-grayscale");
    
    Pixel[][] p1 = new Pixel[2][2];
    p1[0][0] = new Pixel(new Color(82, 255));
    p1[0][1] = new Pixel(new Color(60, 255));
    p1[1][0] = new Pixel(new Color(162, 255));
    p1[1][1] = new Pixel(new Color(139, 255));
    Image i1 = new Image(p1);
    
    assertEquals(i1, this.images3.get("luma-grayscale"));
  }
  
  @Test
  public void testColorTransformSepia() {
    this.model3.filter(new SepiaTransformation(), "image2-2-255", "sepia-transform");
    
    Pixel[][] p1 = new Pixel[2][2];
    p1[0][0] = new Pixel(new Color(119, 106, 83, 255));
    p1[0][1] = new Pixel(new Color(97, 86, 67, 255));
    p1[1][0] = new Pixel(new Color(208, 185, 144, 255));
    p1[1][1] = new Pixel(new Color(178, 158, 123, 255));
    Image i1 = new Image(p1);
    
    assertEquals(i1, this.images3.get("sepia-transform"));
  }
  
  @Test
  public void testFilterBlur() {
    this.model3.filter(new BlurFilter(), "image2-2-255", "blur-filter");
    
    Pixel[][] p1 = new Pixel[2][2];
    p1[0][0] = new Pixel(new Color(47, 54, 88, 255));
    p1[0][1] = new Pixel(new Color(65, 46, 60, 255));
    p1[1][0] = new Pixel(new Color(42, 76, 98, 255));
    p1[1][1] = new Pixel(new Color(53, 69, 73, 255));
    Image i1 = new Image(p1);
    
    assertEquals(i1, this.images3.get("blur-filter"));
  }
  
  @Test
  public void testFilterSharpen() {
    this.model3.filter(new SharpenFilter(), "image2-2-255", "sharpen-filter");
    
    Pixel[][] p1 = new Pixel[2][2];
    p1[0][0] = new Pixel(new Color(135, 171, 255, 255));
    p1[0][1] = new Pixel(new Color(226, 135, 162, 255));
    p1[1][0] = new Pixel(new Color(132, 253, 255, 255));
    p1[1][1] = new Pixel(new Color(161, 229, 233, 255));
    Image i1 = new Image(p1);
    
    assertEquals(i1, this.images3.get("sharpen-filter"));
  }
  
  @Test
  public void testFilterMosaic() {
    Map<String, Image> images = new HashMap<>();
    ImgProcessorModelImpl modelMosaic = new ImgProcessorModelImpl(images);
    modelMosaic.load(new Image(new Pixel[][]{
        {new Pixel(new Color(0, 0, 255, 255)),
            new Pixel(new Color(255, 0, 255, 255))},
        {new Pixel(new Color(0, 0, 255, 255)),
            new Pixel(new Color(255, 0, 255, 255))},
        {new Pixel(new Color(0, 0, 255, 255)),
            new Pixel(new Color(255, 0, 255, 255))}}), "i1");
    
    modelMosaic.filter(new Mosaic(2, new Random(10)), "i1", "i1m");
    
    Pixel[][] p1 = new Pixel[][]{
      {new Pixel(new Color(127, 0, 255, 255)),
          new Pixel(new Color(127, 0, 255, 255))},
      {new Pixel(new Color(127, 0, 255, 255)),
          new Pixel(new Color(127, 0, 255, 255))},
      {new Pixel(new Color(127, 0, 255, 255)),
          new Pixel(new Color(127, 0, 255, 255))}};
    Image i1 = new Image(p1);
    
    Image img = images.get("i1m");
    
    assertEquals(i1, images.get("i1m"));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testFilterMosaicIAE1() {
    Map<String, Image> images = new HashMap<>();
    ImgProcessorModelImpl modelMosaic = new ImgProcessorModelImpl(images);
    modelMosaic.load(new Image(new Pixel[][]{
        {new Pixel(new Color(0, 0, 255, 255)),
            new Pixel(new Color(255, 0, 255, 255))},
        {new Pixel(new Color(0, 0, 255, 255)),
            new Pixel(new Color(255, 0, 255, 255))},
        {new Pixel(new Color(0, 0, 255, 255)),
            new Pixel(new Color(255, 0, 255, 255))}}), "i1");
  
    modelMosaic.filter(new Mosaic(7, new Random(10)), "i1", "i1m");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testFilterMosaicIAE2() {
    Map<String, Image> images = new HashMap<>();
    ImgProcessorModelImpl modelMosaic = new ImgProcessorModelImpl(images);
    modelMosaic.load(new Image(new Pixel[][]{
        {new Pixel(new Color(0, 0, 255, 255)),
            new Pixel(new Color(255, 0, 255, 255))},
        {new Pixel(new Color(0, 0, 255, 255)),
            new Pixel(new Color(255, 0, 255, 255))},
        {new Pixel(new Color(0, 0, 255, 255)),
            new Pixel(new Color(255, 0, 255, 255))}}), "i1");
    
    modelMosaic.filter(new Mosaic(-1, new Random(10)), "i1", "i1m");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testFilterMosaicIAE3() {
    Map<String, Image> images = new HashMap<>();
    ImgProcessorModelImpl modelMosaic = new ImgProcessorModelImpl(images);
    modelMosaic.load(new Image(new Pixel[][]{
        {new Pixel(new Color(0, 0, 255, 255)),
            new Pixel(new Color(255, 0, 255, 255))},
        {new Pixel(new Color(0, 0, 255, 255)),
            new Pixel(new Color(255, 0, 255, 255))},
        {new Pixel(new Color(0, 0, 255, 255)),
            new Pixel(new Color(255, 0, 255, 255))}}), "i1");
    
    modelMosaic.filter(new Mosaic(2, null), "i1", "i1m");
  }
}
