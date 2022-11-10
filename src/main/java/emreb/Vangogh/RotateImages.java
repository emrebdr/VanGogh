package emreb.Vangogh;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class RotateImages extends Design {
    private final int angle;

    public RotateImages(List<String> imagesPath, int angle, String outputPath) {
        this.imagesPath = imagesPath;
        this.angle = angle;
        this.outputPath = outputPath;
    }

    @Override
    void doImageOperation() {
        this.images.parallelStream().forEach(image -> {
            this.processedImages.add(this.doImageRotate(image));
        });
    }

    private Image doImageRotate(Image image) {
        BufferedImage rotatedImage = new BufferedImage(image.getImage().getWidth(), image.getImage().getHeight(), image.getImage().getType());
        Graphics2D graphics = rotatedImage.createGraphics();

        graphics.rotate(Math.toRadians(angle), image.getImage().getWidth() / 2, image.getImage().getHeight() / 2);
        graphics.drawImage(image.getImage(), null, 0, 0);

        return new Image(rotatedImage);
    }
}
