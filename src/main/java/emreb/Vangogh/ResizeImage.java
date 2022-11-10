package emreb.Vangogh;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class ResizeImage extends Design {
    private int resizeWidth;
    private int resizeHeight;
    private int resizeRatio;

    public ResizeImage(List<String> imagesPath, int resizeWidth, int resizeHeight, String outputPath) {
        this.imagesPath = imagesPath;
        this.resizeWidth = resizeWidth;
        this.resizeHeight = resizeHeight;
        this.outputPath = outputPath;
    }

    public ResizeImage(List<String> imagesPath, int resizeRatio, String outputPath) {
        this.imagesPath = imagesPath;
        this.outputPath = outputPath;
        this.resizeRatio = resizeRatio;
    }

    @Override
    void doImageOperation() {
        this.images.parallelStream().forEach(image -> {
            this.processedImages.add(this.doImageResize(image));
        });
    }

    private Image doImageResize(Image image) {
        if (this.resizeRatio != 0) this.calculateResizeRatio(image);

        BufferedImage resizedImage = new BufferedImage(this.resizeWidth, this.resizeHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);

        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.drawImage(image.getImage(), 0, 0, this.resizeWidth, this.resizeHeight, null);
        graphics2D.dispose();

        return new Image(resizedImage);
    }

    private void calculateResizeRatio(Image image) {
        this.resizeWidth = image.getImage().getWidth() * this.resizeRatio / 100;
        this.resizeHeight = image.getImage().getHeight() * this.resizeRatio / 100;
    }
}
