package emreb.Vangogh;

import emreb.Models.LogLevel;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.UUID;

import static emreb.Utils.Logger.Log;

public class Image {
    private BufferedImage image;
    private final String imageName;
    private int order;
    private int posX;
    private int posY;

    public Image(String path) {
        this.readImage(path);
        this.imageName = this.generateRandomName();
    }

    public Image(BufferedImage image) {
        this.image = image;
        this.imageName = this.generateRandomName();
    }

    private boolean isUrlValid(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (IOException | URISyntaxException e) {
            return false;
        }
    }

    public void readImage(String path) {
        boolean checkUrl = this.isUrlValid(path);
        try {
            if (checkUrl) {
                this.image = ImageIO.read(new URL(path));
            } else {
                this.image = ImageIO.read(new File(System.getProperty("user.dir") + "/" + path));
            }
        } catch (IOException e) {
            Log(LogLevel.ERROR, "Image read error: Invalid image paths. " + e.getMessage());
        }
    }

    @NotNull
    private String generateRandomName() {
        return UUID.randomUUID().toString() + ".png";
    }

    public String getImageName() {
        return this.imageName;
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getOrder() {
        return this.order;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosX() {
        return this.posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosY() {
        return this.posY;
    }
}
