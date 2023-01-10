import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import controller.Controller;
import controller.ControllerImpl;
import model.Color;
import model.Image;
import model.ImageProcessorModel;
import model.ImgProcessorModelImpl;
import model.Pixel;
import view.ImageProcessorView;
import view.TextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * A JUnit test class for the ControllerImpl class.
 */
public class ControllerTest {
  
  Map<String, Image> images;
  
  Appendable dest;
  
  ImageProcessorModel model;
  
  ImageProcessorView view;
  
  Readable in;
  
  @Before
  public void setUp() {
    this.images = new HashMap<>();
    this.dest = new StringBuilder();
    this.model = new ImgProcessorModelImpl(this.images);
    this.view = new TextView(this.dest);
    this.in = new StringReader("");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorModelNull() {
    new ControllerImpl(null, this.view, this.in);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorViewNull() {
    new ControllerImpl(this.model, null, this.in);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInNull() {
    new ControllerImpl(this.model, this.view, null);
  }
  
  @Test
  public void testAlterImage() {
    Pixel[][] p1 = new Pixel[4][3];
    for (int row = 0; row < 4; row++) {
      for (int col = 0; col < 3; col++) {
        p1[row][col] = new Pixel(new Color(((4 * row) + col) * 10, ((3 * row) + col) * 20,
            ((2 * row) + col) * 30, 255));
      }
    }
    Image img1 = new Image(p1);
    
    Pixel[][] p3 = new Pixel[2][2];
    p3[0][0] = new Pixel(new Color(250, 50, 237, 255));
    p3[0][1] = new Pixel(new Color(124, 250, 93, 255));
    p3[1][0] = new Pixel(new Color(45, 30, 250, 255));
    p3[1][1] = new Pixel(new Color(250, 138, 40, 255));
    Image img3 = new Image(p3);
    
    Pixel[][] pRed1 = new Pixel[4][3];
    for (int row = 0; row < 4; row++) {
      for (int col = 0; col < 3; col++) {
        pRed1[row][col] = new Pixel(new Color(((4 * row) + col) * 10, 255));
      }
    }
    Image red1 = new Image(pRed1);
    
    Pixel[][] pGreen1 = new Pixel[4][3];
    for (int row = 0; row < 4; row++) {
      for (int col = 0; col < 3; col++) {
        pGreen1[row][col] = new Pixel(new Color(((3 * row) + col) * 20, 255));
      }
    }
    Image green1 = new Image(pGreen1);
    
    Pixel[][] pBlue1 = new Pixel[4][3];
    for (int row = 0; row < 4; row++) {
      for (int col = 0; col < 3; col++) {
        pBlue1[row][col] = new Pixel(new Color(((2 * row) + col) * 30, 255));
      }
    }
    Image blue1 = new Image(pBlue1);
    
    Pixel[][] pHFlip1 = new Pixel[4][3];
    for (int row = 0; row < 4; row++) {
      for (int col = 0; col < 3; col++) {
        pHFlip1[row][2 - col] = new Pixel(new Color(((4 * row) + col) * 10, ((3 * row) + col) * 20,
            ((2 * row) + col) * 30, 255));
      }
    }
    Image hFlip1 = new Image(pHFlip1);
    
    Pixel[][] pVFlip1 = new Pixel[4][3];
    for (int row = 0; row < 4; row++) {
      for (int col = 0; col < 3; col++) {
        pVFlip1[3 - row][col] = new Pixel(new Color(((4 * row) + col) * 10, ((3 * row) + col) * 20,
            ((2 * row) + col) * 30, 255));
      }
    }
    Image vFlip1 = new Image(pVFlip1);
    
    
    Pixel[][] p2 = new Pixel[2][2];
    p2[0][0] = new Pixel(new Color(56, 78, 200, 255));
    p2[0][1] = new Pixel(new Color(177, 30, 25, 255));
    p2[1][0] = new Pixel(new Color(52, 188, 230, 255));
    p2[1][1] = new Pixel(new Color(90, 156, 120, 255));
    Image img2 = new Image(p2);
    
    Pixel[][] pValue2 = new Pixel[2][2];
    pValue2[0][0] = new Pixel(new Color(200, 255));
    pValue2[0][1] = new Pixel(new Color(177, 255));
    pValue2[1][0] = new Pixel(new Color(230, 255));
    pValue2[1][1] = new Pixel(new Color(156, 255));
    Image value2 = new Image(pValue2);
    
    assertNotEquals(img2, value2);
    
    Pixel[][] pIntensity2 = new Pixel[2][2];
    pIntensity2[0][0] = new Pixel(new Color(111, 255));
    pIntensity2[0][1] = new Pixel(new Color(77, 255));
    pIntensity2[1][0] = new Pixel(new Color(156, 255));
    pIntensity2[1][1] = new Pixel(new Color(122, 255));
    Image intensity2 = new Image(pIntensity2);
    
    Pixel[][] pLuma2 = new Pixel[2][2];
    pLuma2[0][0] = new Pixel(new Color(82, 255));
    pLuma2[0][1] = new Pixel(new Color(61, 255));
    pLuma2[1][0] = new Pixel(new Color(162, 255));
    pLuma2[1][1] = new Pixel(new Color(139, 255));
    Image luma2 = new Image(pLuma2);
    
    Pixel[][] pBrighten2 = new Pixel[2][2];
    pBrighten2[0][0] = new Pixel(new Color(146, 168, 255, 255));
    pBrighten2[0][1] = new Pixel(new Color(255, 120, 115, 255));
    pBrighten2[1][0] = new Pixel(new Color(142, 255, 255, 255));
    pBrighten2[1][1] = new Pixel(new Color(180, 246, 210, 255));
    Image brighten2 = new Image(pBrighten2);
    
    Pixel[][] pDarken2 = new Pixel[2][2];
    pDarken2[0][0] = new Pixel(new Color(26, 48, 170, 255));
    pDarken2[0][1] = new Pixel(new Color(147, 0, 0, 255));
    pDarken2[1][0] = new Pixel(new Color(22, 158, 200, 255));
    pDarken2[1][1] = new Pixel(new Color(60, 126, 90, 255));
    Image darken2 = new Image(pDarken2);
    
    Pixel[][] pLumaTransform2 = new Pixel[2][2];
    pLumaTransform2[0][0] = new Pixel(new Color(82, 255));
    pLumaTransform2[0][1] = new Pixel(new Color(60, 255));
    pLumaTransform2[1][0] = new Pixel(new Color(162, 255));
    pLumaTransform2[1][1] = new Pixel(new Color(139, 255));
    Image lumaTransform2 = new Image(pLumaTransform2);
    
    Pixel[][] pSepiaTransform2 = new Pixel[2][2];
    pSepiaTransform2[0][0] = new Pixel(new Color(119, 106, 83, 255));
    pSepiaTransform2[0][1] = new Pixel(new Color(97, 86, 67, 255));
    pSepiaTransform2[1][0] = new Pixel(new Color(208, 185, 144, 255));
    pSepiaTransform2[1][1] = new Pixel(new Color(178, 158, 123, 255));
    Image sepiaTransform2 = new Image(pSepiaTransform2);
    
    Pixel[][] pBlur2 = new Pixel[2][2];
    pBlur2[0][0] = new Pixel(new Color(47, 54, 88, 255));
    pBlur2[0][1] = new Pixel(new Color(65, 46, 60, 255));
    pBlur2[1][0] = new Pixel(new Color(42, 76, 98, 255));
    pBlur2[1][1] = new Pixel(new Color(53, 69, 73, 255));
    Image blur2 = new Image(pBlur2);
    
    Pixel[][] pSharpen2 = new Pixel[2][2];
    pSharpen2[0][0] = new Pixel(new Color(135, 171, 255, 255));
    pSharpen2[0][1] = new Pixel(new Color(226, 135, 162, 255));
    pSharpen2[1][0] = new Pixel(new Color(132, 253, 255, 255));
    pSharpen2[1][1] = new Pixel(new Color(161, 229, 233, 255));
    Image sharpen2 = new Image(pSharpen2);
    
    testBehaviorHelper(new Input("load test/Picture1.ppm img1"),
        new ExpectedImages("img1", img1),
        new Input("load test/smallPNG.png img3"),
        new ExpectedImages("img3", img3),
        new Input("red-component img1 img1Red"),
        new ExpectedImages("img1Red", red1),
        new Input("green-component img1 img1Green"),
        new ExpectedImages("img1Green", green1),
        new Input("blue-component img1 img1Blue"),
        new ExpectedImages("img1Blue", blue1),
        new Input("load test/Picture2.ppm img2"),
        new ExpectedImages("img2", img2),
        new Input("horizontal-flip img1 img1HorizontalFlip"),
        new ExpectedImages("img1HorizontalFlip", hFlip1),
        new Input("intensity img2 img2Intensity"),
        new ExpectedImages("img2Intensity", intensity2),
        new Input("value img2 img2Value"),
        new ExpectedImages("img2Value", value2),
        new Input("vertical-flip img1 img1VerticalFlip"),
        new ExpectedImages("img1VerticalFlip", vFlip1),
        new Input("luma img2 img2Luma"),
        new ExpectedImages("img2Luma", luma2),
        new Input("brighten -30 img2 img2Darken"),
        new ExpectedImages("img2Darken", darken2),
        new Input("brighten 90 img2 img2Brighten"),
        new ExpectedImages("img2Brighten", brighten2),
        new Input("greyscale img2 img2LumaTransform"),
        new ExpectedImages("img2LumaTransform", lumaTransform2),
        new Input("sepia img2 img2SepiaTransform"),
        new ExpectedImages("img2SepiaTransform", sepiaTransform2),
        new Input("blur img2 img2Blur"),
        new ExpectedImages("img2Blur", blur2),
        new Input("sharpen img2 img2Sharpen"),
        new ExpectedImages("img2Sharpen", sharpen2),
        new Input("q"));
    
  }
  
  @Test
  public void testInputs() {
    testInputsHelper(new Input("load test/Picture1.ppm img1"),
        new Output("load name = img1"),
        new Input("horizontal-flip img1 img1HorizontalFlip"),
        new Output("filter op = model.operations.HorizontalFlipImg, name = img1, " +
            "destName = img1HorizontalFlip"),
        new Input("blue-component img1 img1Blue"),
        new Output("filter op = model.operations.BlueCompImg, name = img1, " +
            "destName = img1Blue"),
        new Input("red-component img1 img1Red"),
        new Output("filter op = model.operations.RedCompImg, name = img1, " +
            "destName = img1Red"),
        new Input("vertical-flip img1 img1VerticalFlip"),
        new Output("filter op = model.operations.VerticalFlipImg, name = img1, " +
            "destName = img1VerticalFlip"),
        new Input("brighten 40 img1 img1Brighter"),
        new Output("filter op = model.operations.BrightenImg, name = img1, " +
            "destName = img1Brighter"),
        new Input("vertical-flip img1Red img1RedVerticalFlip"),
        new Output("filter op = model.operations.VerticalFlipImg, name = img1Red, " +
            "destName = img1RedVerticalFlip"),
        new Input("green-component img1 img1Green"),
        new Output("filter op = model.operations.GreenCompImg, name = img1, " +
            "destName = img1Green"),
        new Input("value img1 img1Value"),
        new Output("filter op = model.operations.ValueImg, name = img1, " +
            "destName = img1Value"),
        new Input("intensity img1 img1Intensity"),
        new Output("filter op = model.operations.IntensityImg, name = img1, " +
            "destName = img1Intensity"),
        new Input("luma img1 img1Luma"),
        new Output("filter op = model.operations.LumaImg, name = img1, " +
            "destName = img1Luma"),
        new Input("greyscale img1 img2LumaTransform"),
        new Output("filter op = model.operations.LumaTransformation, name = img1, " +
            "destName = img2LumaTransform"),
        new Input("sepia img1 img2SepiaTransform"),
        new Output("filter op = model.operations.SepiaTransformation, name = img1, " +
            "destName = img2SepiaTransform"),
        new Input("blur img1 img2Blur"),
        new Output("filter op = model.operations.BlurFilter, name = img1, " +
            "destName = img2Blur"),
        new Input("sharpen img1 img2Sharpen"),
        new Output("filter op = model.operations.SharpenFilter, name = img1, " +
            "destName = img2Sharpen"),
        new Input("mosaic 2 img1 img2Mosaic"),
        new Output("filter op = model.operations.Mosaic, name = img1, destName = img2Mosaic"));
    assertNotEquals("load test/Picture1.ppm img1", "load name = img1");
  }
  
  @Test
  public void testBadInputs() {
    testBadInputsHelper(new Input("horizontal-flip img1 img1HorizontalFlip"),
        new Output("cannot find image of name img1"),
        new Input("blue-component img2 img1Blue"),
        new Output("cannot find image of name img2"),
        new Input("red-component img1 img1Red"),
        new Output("cannot find image of name img1"),
        new Input("vertical-flip img1 img1VerticalFlip"),
        new Output("cannot find image of name img1"),
        new Input("brighten 40 img1 img1Brighter"),
        new Output("cannot find image of name img1"),
        new Input("green-component img1 img1Green"),
        new Output("cannot find image of name img1"),
        new Input("value img1 img1Value"),
        new Output("cannot find image of name img1"),
        new Input("intensity img1 img1Intensity"),
        new Output("cannot find image of name img1"),
        new Input("luma img1 img1Luma"),
        new Output("cannot find image of name img1"),
        new Input("greyscale img1 img1transformLuma"),
        new Output("cannot find image of name img1"),
        new Input("sepia img1 img1transformSepia"),
        new Output("cannot find image of name img1"),
        new Input("mosaic 2 img1 img1Mosaic"),
        new Output("cannot find image of name img1"),
        new Input("Q"));
    assertNotEquals("horizontal-flip img1 img1HorizontalFlip",
        "cannot find image of name img1");
  }
  
  private void testBehaviorHelper(Interaction... interactions) {
    StringBuilder supposedIn = new StringBuilder();
    Map<String, Image> expectedImages = new HashMap<String, Image>();
    
    for (Interaction interaction : interactions) {
      interaction.applyMap(supposedIn, expectedImages);
    }
    
    StringReader in = new StringReader(supposedIn.toString());
    Map<String, Image> actualImages = new HashMap<String, Image>();
    ImageProcessorModel model = new ImgProcessorModelImpl(actualImages);
    ImageProcessorView view = new TextView(new StringBuilder());
    
    Controller controller = new ControllerImpl(model, view, in);
    controller.run();
    
    assertEquals(expectedImages, actualImages);
  }
  
  private void testInputsHelper(Interaction... interactions) {
    StringBuilder supposedIn = new StringBuilder();
    StringBuilder expectedOut = new StringBuilder();
    
    for (Interaction interaction : interactions) {
      interaction.applyString(supposedIn, expectedOut);
    }
    
    StringReader in = new StringReader(supposedIn.toString());
    StringBuilder actualOut = new StringBuilder();
    ImageProcessorModel mock = new MockImgProcessorModel(actualOut, new HashMap<String, Image>());
    ImageProcessorView view = new TextView(new StringBuilder());
    
    Controller controller = new ControllerImpl(mock, view, in);
    controller.run();
    
    assertEquals(expectedOut.toString(), actualOut.toString());
  }
  
  private void testBadInputsHelper(Interaction... interactions) {
    StringBuilder supposedIn = new StringBuilder();
    StringBuilder expectedOut = new StringBuilder();
    
    for (Interaction interaction : interactions) {
      interaction.applyString(supposedIn, expectedOut);
    }
    
    StringReader in = new StringReader(supposedIn.toString());
    StringBuilder actualOut = new StringBuilder();
    ImageProcessorModel mock = new MockExceptionsModel();
    ImageProcessorView view = new TextView(actualOut);
    
    Controller controller = new ControllerImpl(mock, view, in);
    controller.run();
    
    assertEquals(expectedOut.toString(), actualOut.toString());
  }
}
