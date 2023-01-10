package controller.commands;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import controller.ProcessorCommand;
import model.Color;
import model.Image;
import model.ImageProcessorModel;
import model.Pixel;

/**
 * Load an image.
 */
public class Load implements ProcessorCommand {
  
  private final String path;
  
  private final String name;
  
  /**
   * Create a command that loads an image from the given path and refers to it by the given name.
   *
   * @param path path from which to load the image
   * @param name name by which to refer to the image
   */
  public Load(String path, String name) {
    this.path = path;
    this.name = name;
  }
  
  @Override
  public void apply(ImageProcessorModel m) {
    try {
      m.load(this.loadImage(this.path), this.name);
    } catch (IOException e) {
      throw new IllegalArgumentException("could not find file to load");
    }
  }
  
  private Image loadImage(String path) throws IOException {
    if (path.endsWith("ppm")) {
      return this.ppmToImage(path);
    } else {
      return this.fileToImage(path);
    }
  }
  
  private Image ppmToImage(String path) throws FileNotFoundException {
    Scanner sc;
    
    sc = new Scanner(new FileInputStream(path));
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    
    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());
    
    String token;
    
    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }
    
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();
    
    Pixel[][] pixels = new Pixel[height][width];
    
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        pixels[row][col] = new Pixel(new Color(r, g, b, maxValue));
      }
    }
    return new Image(pixels);
  }
  
  private Image fileToImage(String path) throws IOException {
    BufferedImage img = ImageIO.read(new File(path));
    
    Raster raster = img.getRaster();
    ColorModel cm = img.getColorModel();
    int width = img.getWidth();
    int height = img.getHeight();
    
    Pixel[][] pixels = new Pixel[height][width];
    
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        Object pixel = raster.getDataElements(col, row, null);
        int r = cm.getRed(pixel);
        int g = cm.getGreen(pixel);
        int b = cm.getBlue(pixel);
        pixels[row][col] = new Pixel(new Color(r, g, b, 255));
      }
    }
    return new Image(pixels);
  }
}
