package emreb.Vangogh;

import emreb.Models.LogLevel;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static emreb.Utils.Logger.Log;

public abstract class Design {
    protected List<String> imagesPath;
    protected List<Image> images;
    protected String outputPath = "./";
    protected List<Image> processedImages = new ArrayList<>();

    public void paint() throws IOException {
        this.readImage();
        this.doImageOperation();
        this.saveImage();
    }

    private void saveImage() {
        this.processedImages.parallelStream().forEach(image -> {
            try {
                ImageIO.write(image.getImage(), "png", new File(this.outputPath + image.getImageName()));
            } catch (IOException e) {
                Log(LogLevel.ERROR, "Image save error: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }

    protected void readImage() {
        this.images = ReadImages.DoReadImages(this.imagesPath);
    }

    abstract void doImageOperation();
}
