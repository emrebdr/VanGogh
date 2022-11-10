package emreb.Vangogh;

import java.awt.image.BufferedImage;
import java.util.List;

public class CropImage extends Design {
    private int x;
    private int y;
    private int width;
    private int height;
    private int cropRatio;

    public CropImage(List<String> imagesPath, int x, int y, int width, int height, String outputPath) {
        this.imagesPath = imagesPath;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.outputPath = outputPath;
    }

    public CropImage(List<String> imagesPath, int cropRatio, String outputPath) {
        this.imagesPath = imagesPath;
        this.outputPath = outputPath;
        this.cropRatio = cropRatio;
    }

    @Override
    void doImageOperation() {
        this.images.parallelStream().forEach(image -> {
            this.processedImages.add(this.doImageCrop(image));
        });
    }

    private Image doImageCrop(Image image) {
        if (this.cropRatio != 0) this.calculateCropRatio(image);
        BufferedImage croppedImage = image.getImage().getSubimage(this.x, this.y, this.width, this.height);
        return new Image(croppedImage);
    }

    private void calculateCropRatio(Image image) {
        this.width = image.getImage().getWidth() * this.cropRatio / 100;
        this.height = image.getImage().getHeight() * this.cropRatio / 100;
        this.x = (image.getImage().getWidth() - this.width) / 2;
        this.y = (image.getImage().getHeight() - this.height) / 2;
    }
}
