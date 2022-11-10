package emreb.Vangogh;

import java.awt.*;
import java.util.List;

public class WriteText extends Design {
    private final String text;
    private final int x;
    private final int y;
    private final int fontSize;
    private final Color color;

    public WriteText(List<String> imagesPath, String text, int x, int y, int fontSize, Color color, String outputPath) {
        this.imagesPath = imagesPath;
        this.text = text;
        this.x = x;
        this.y = y;
        this.fontSize = fontSize;
        this.color = color;
        this.outputPath = outputPath;
    }

    public WriteText(List<String> imagesPath, String text, int x, int y, int fontSize, Color color) {
        this.imagesPath = imagesPath;
        this.text = text;
        this.x = x;
        this.y = y;
        this.fontSize = fontSize;
        this.color = color;
    }

    @Override
    void doImageOperation() {
        this.images.parallelStream().forEach(image -> {
            this.processedImages.add(this.doImageWriteText(image));
        });
    }

    private emreb.Vangogh.Image doImageWriteText(emreb.Vangogh.Image image) {
        Graphics graphics = image.getImage().getGraphics();
        graphics.setFont(new Font("Arial", Font.BOLD, fontSize));
        graphics.setColor(color);
        graphics.drawString(text, x, y);
        graphics.dispose();

        return new Image(image.getImage());
    }
}
