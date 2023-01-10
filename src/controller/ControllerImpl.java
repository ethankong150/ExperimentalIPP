package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Function;

import controller.commands.Filter;
import controller.commands.Load;
import controller.commands.Save;
import model.Image;
import model.ImageProcessorModel;
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
import view.ImageProcessorView;

/**
 * Controller used to load in an image by the user, run manipulation commands, and then save those
 * images to their desired output folder.
 */
public class ControllerImpl implements Controller {

  private final ImageProcessorModel model;

  private final ImageProcessorView view;

  private final Readable in;

  /**
   * Create a controller with the given model, view, and input.
   *
   * @param model the ImgProcessorModel this controller uses
   * @param view the view this controller uses
   * @param in user input
   * @throws IllegalStateException if transmission to view fails or attempt to read
   *     from readable fails
   */
  public ControllerImpl(ImageProcessorModel model, ImageProcessorView view, Readable in) {
    if (model == null || view == null || in == null) {
      throw new IllegalArgumentException("arguments cannot be null");
    }
    this.model = model;
    this.view = view;
    this.in = in;
  }

  @Override
  public Map<String, Image> run() throws IllegalStateException {
    Scanner sc = new Scanner(this.in);
    Map<String, Function<Scanner, ProcessorCommand>> knownCommands = new HashMap<>();

    knownCommands.put("load", (Scanner s)
        -> new Load(s.next(), s.next()));
    knownCommands.put("save", (Scanner s)
        -> new Save(s.next(), s.next()));
    knownCommands.put("red-component", (Scanner s)
        -> new Filter(new RedCompImg(), s.next(), s.next()));
    knownCommands.put("green-component", (Scanner s)
        -> new Filter(new GreenCompImg(), s.next(), s.next()));
    knownCommands.put("blue-component", (Scanner s)
        -> new Filter(new BlueCompImg(), s.next(), s.next()));
    knownCommands.put("value", (Scanner s)
        -> new Filter(new ValueImg(), s.next(), s.next()));
    knownCommands.put("intensity", (Scanner s)
        -> new Filter(new IntensityImg(), s.next(), s.next()));
    knownCommands.put("luma", (Scanner s)
        -> new Filter(new LumaImg(), s.next(), s.next()));
    knownCommands.put("horizontal-flip", (Scanner s)
        -> new Filter(new HorizontalFlipImg(), s.next(), s.next()));
    knownCommands.put("vertical-flip", (Scanner s)
        -> new Filter(new VerticalFlipImg(), s.next(), s.next()));
    knownCommands.put("brighten", (Scanner s)
        -> new Filter(new BrightenImg(s.nextInt()), s.next(), s.next()));
    knownCommands.put("greyscale", (Scanner s)
        -> new Filter(new LumaTransformation(), s.next(), s.next()));
    knownCommands.put("sepia", (Scanner s)
        -> new Filter(new SepiaTransformation(), s.next(), s.next()));
    knownCommands.put("blur", (Scanner s)
        -> new Filter(new BlurFilter(), s.next(), s.next()));
    knownCommands.put("sharpen", (Scanner s)
        -> new Filter(new SharpenFilter(), s.next(), s.next()));
    knownCommands.put("mosaic", (Scanner s)
            -> new Filter(new Mosaic(s.nextInt(), new Random()), s.next(), s.next()));

    while (sc.hasNext()) {
      ProcessorCommand c;
      String s = sc.next();

      if (s.equalsIgnoreCase("q") || s.equalsIgnoreCase("quit")) {
        break;
      }

      Function<Scanner, ProcessorCommand> cmd = knownCommands.getOrDefault(s, null);

      if (cmd == null) {
        this.transmit(String.format("unknown command %s", s));
      } else {
        try {
          c = cmd.apply(sc);
          c.apply(model);
        } catch (IllegalArgumentException e) {
          this.transmit(e.getMessage());
        }
      }
    }
    return this.model.getImages();
  }

  private void transmit(String message) {
    try {
      this.view.renderMessage(String.format("%s\n", message));
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }
}