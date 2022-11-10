package emreb.Vangogh;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeImages extends Design {
    private final List<int[]> positions;

    public MergeImages(List<String> imagesPath, List<int[]> positions, String outputPath) {
        this.imagesPath = imagesPath;
        this.positions = positions;
        this.outputPath = outputPath;
    }

    @Override
    void doImageOperation() {
        this.processedImages.add(this.doImageMerge());
    }

    @Override
    protected void readImage() {
        List<String[]> orderedImagesPath = this.orderImages();
        this.images = ReadImages.DoReadOrderedImages(orderedImagesPath);
        this.sortImages();
        this.addPositionsToImage();
    }

    private void addPositionsToImage() {
        if (this.positions == null) {
            for (emreb.Vangogh.Image image : this.images) {
                image.setPosX(0);
                image.setPosY(0);
            }
        }
        else {
            for (int i = 0; i < this.images.size(); i++) {
                this.images.get(i).setPosX(this.positions.get(i)[0]);
                this.images.get(i).setPosY(this.positions.get(i)[1]);
            }
        }
    }

    private List<String[]> orderImages() {
        List<String[]> orderedImagesPath = new ArrayList<>();
        for (int i = 0; i < this.imagesPath.size(); i++) {
            String[] image = {this.imagesPath.get(i), String.valueOf(i)};
            orderedImagesPath.add(image);
        }

        return orderedImagesPath;
    }

    private void sortImages() {
        this.images.sort(Comparator.comparingInt(emreb.Vangogh.Image::getOrder));
    }

    private emreb.Vangogh.Image doImageMerge() {
        int width = Math.max(this.images.get(0).getImage().getWidth(), this.images.get(1).getImage().getWidth());
        int height = Math.max(this.images.get(0).getImage().getHeight(), this.images.get(1).getImage().getHeight());

        BufferedImage combine = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics graphics = combine.getGraphics();

        for (emreb.Vangogh.Image image : this.images) {
            graphics.drawImage(image.getImage(), image.getPosX(), image.getPosY(), null);
        }

        graphics.dispose();

        return new Image(combine);
    }
}
