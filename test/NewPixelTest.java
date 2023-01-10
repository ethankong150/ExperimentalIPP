import org.junit.Test;

import model.Color;
import model.NewPixel;
import model.Pixel;

import static org.junit.Assert.assertEquals;


public class NewPixelTest {
  
  Pixel p = new Pixel(new Color(0, 0, 255, 255));
  NewPixel np = new NewPixel(p, 3, 5);
  
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorIAE1() {
    new NewPixel(null,3, 3);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorIAE2() {
    new NewPixel(null,-3, 3);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorIAE3() {
    new NewPixel(null,3, -3);
  }
  
  @Test
  public void testGetRow() {
    assertEquals(this.np.getRow(), 3);
  }
  
  @Test
  public void testGetCol() {
    assertEquals(this.np.getCol(), 5);
  }
  
  @Test
  public void testGetPixel() {
    assertEquals(this.np.getPixel(), this.p);
  }
  
}