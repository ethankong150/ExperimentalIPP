package model.operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import model.Color;
import model.Image;
import model.ImageOperation;
import model.NewPixel;
import model.Pixel;

/**
 * Function object to apply an image mosaic filter.
 */
public class Mosaic implements ImageOperation {

  private final int numSeeds;
  private final Random rand;


  /**
   * Constructor that represents the Mosaic filter command.
   *
   * @param numSeeds the number of random seeds to create an image mosaic
   * @param rand Random object that represents the randomness of choosing seeds
   * @throws IllegalArgumentException when null parameter is given
   */
  public Mosaic(int numSeeds, Random rand) {
    if (rand == null) {
      throw new IllegalArgumentException("null parameters given");
    }
    this.numSeeds = numSeeds;
    this.rand = rand;
  }

  /**
   * Applies this image operation to the given image.
   *
   * @param img the image on which to operate
   * @return the new image
   * @throws IllegalArgumentException when null image is given
   */
  @Override
  public Image apply(Image img) {

    if(img == null) {
      throw new IllegalArgumentException("Input a valid Image!");
    }

    int height = img.getHeight();
    int width = img.getWidth();

    Pixel[][] mosaicImg = new Pixel[height][width];

    if(numSeeds > height * width || numSeeds <= 0) {
      throw new IllegalArgumentException
              ("Input a number greater than 0 and less than total pixels of the image!");
    }

    //choosing seeds
    Set<NewPixel> seeds = setPixelSeeds(img, height, width);

    //maps all pixels to the closest seed
    HashMap<NewPixel, ArrayList<NewPixel>> clusters = createClusters(img, seeds);

    //change the colors of clusters to the average color
    for(NewPixel seed: clusters.keySet()) {
      Pixel averageClusterPixel = averageColor(clusters.get(seed));

      //need to iterate through the cluster and change the mosaic[][] to averageClusterPixel
      for(int i = 0; i < clusters.get(seed).size(); i++) {
        ArrayList<NewPixel> currentCluster = clusters.get(seed);

        int currentRow = currentCluster.get(i).getRow();
        int currentCol = currentCluster.get(i).getCol();

        mosaicImg[currentRow][currentCol] = averageClusterPixel;
      }
    }
    return new Image(mosaicImg);
  }
  
  private HashMap<NewPixel, ArrayList<NewPixel>> createClusters(Image img, Set<NewPixel> seeds ) {

    int height = img.getHeight();
    int width = img.getWidth();
    HashMap<NewPixel, ArrayList<NewPixel>> clusters = new HashMap<>();

    //maps all pixels to the closest seed
    for(int i = 0; i < height; i++) {
      for(int j = 0; j < width; j++) {
        //finding the closest seed to the current pixel
        Map<Double, NewPixel> distances = new HashMap<>();
        for(NewPixel seed: seeds) {
          distances.put(findDistance(seed.getRow(), seed.getCol(), i, j), seed);
        }
        NewPixel currentPixel = new NewPixel(img.getPixelAt(i, j), i, j);
        NewPixel nearestSeed = distances.get(Collections.min(distances.keySet()));

        if(clusters.containsKey(nearestSeed)) {
          ArrayList<NewPixel> temp = clusters.get(nearestSeed);
          temp.add(currentPixel);
          clusters.put(nearestSeed, temp);
        } else {
          ArrayList<NewPixel> temp = new ArrayList<>();
          temp.add(currentPixel);
          clusters.put(nearestSeed, temp);
        }
      }
    }
    return clusters;
  }
  
  private Set<NewPixel> setPixelSeeds(Image img, int height, int width) {

    Set<NewPixel> seedPixels = new HashSet<>();
    Random r = this.rand;

    //adds a random pixel to the set if the set doesn't already have the exact pixel
    while(seedPixels.size() != this.numSeeds) {
      int randRow = r.nextInt(height);
      int randCol = r.nextInt(width);
      NewPixel randomPixel = new NewPixel(img.getPixelAt(randRow, randCol), randRow, randCol);
      seedPixels.add(randomPixel);
    }
    return seedPixels;
  }
  
  private double findDistance(int seedRow, int seedCol, int pixelRow, int pixelCol) {
    return Math.sqrt(Math.pow(seedRow - pixelRow, 2) + Math.pow(seedCol - pixelCol, 2));
  }

  private Pixel averageColor(ArrayList<NewPixel> cluster) {
    int totalRed = 0;
    int totalGreen = 0;
    int totalBlue = 0;

    for(int i = 0; i < cluster.size(); i++) {
      totalRed += cluster.get(i).getPixel().getColor().getRed();
      totalGreen += cluster.get(i).getPixel().getColor().getGreen();
      totalBlue += cluster.get(i).getPixel().getColor().getBlue();
    }
    int averageRed = totalRed / cluster.size();
    int averageGreen = totalGreen / cluster.size();
    int averageBlue = totalBlue / cluster.size();

    Color averageColor = new Color(averageRed, averageGreen, averageBlue, 255);

    return new Pixel(averageColor);
  }
}
