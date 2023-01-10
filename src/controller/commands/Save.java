package controller.commands;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.ProcessorCommand;
import model.Color;
import model.Image;
import model.ImageProcessorModel;

/**
 * Save an image.
 */
public class Save implements ProcessorCommand {
  
  private final String path;
  
  private final String name;
  
  /**
   * Create a command to save an image.
   *
   * @param path path to which the image is saved
   * @param name name of the image
   */
  public Save(String path, String name) {
    this.path = path;
    this.name = name;
  }
  
  @Override
  public void apply(ImageProcessorModel m) throws IllegalArgumentException {
    Image img = m.getImages().get(this.name);
    if (!m.getImages().containsKey(this.name)) {
      throw new IllegalArgumentException(String.format("this.images contains no mapping for " +
          "key %s", name));
    }
    
    try {
      this.saveImage(path, img);
    } catch (IOException e) {
      throw new IllegalArgumentException("image could not be saved to path");
    }
  }
  
  private void saveImage(String path, Image img) throws IOException {
    if (path.endsWith("ppm")) {
      this.savePPM(path, img);
    } else {
      this.saveFile(path, img);
    }
  }
  
  private void savePPM(String path, Image img) throws IOException {
    FileOutputStream savedFile = new FileOutputStream(path);
    StringBuilder fileStr = new StringBuilder();
    fileStr.append("P3\n");
    int width = img.getWidth();
    int height = img.getHeight();
    fileStr.append(String.format("%d %d\n", width, height));
    fileStr.append(String.format("%d\n", img.getMaxValue()));
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        fileStr.append(img.getPixelAt(row, col).toString());
      }
    }
    byte[] byteArr = fileStr.toString().getBytes();
    savedFile.write(byteArr);
    savedFile.close();
  }
  
  private void saveFile(String path, Image img) throws IOException {
    int width = img.getWidth();
    int height = img.getHeight();
    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        Color pixelColor = img.getPixelAt(row, col).getColor();
        int r = pixelColor.getRed();
        int g = pixelColor.getGreen();
        int b = pixelColor.getBlue();
        java.awt.Color rgbColor = new java.awt.Color(r, g, b);
        bufferedImage.setRGB(col, row, rgbColor.getRGB());
      }
    }
    String fileType = path.substring(path.lastIndexOf(".") + 1);
    ImageIO.write(bufferedImage, fileType, new File(path));
  }
}
