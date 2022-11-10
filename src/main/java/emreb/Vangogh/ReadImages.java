package emreb.Vangogh;

import emreb.Models.LogLevel;

import java.util.ArrayList;
import java.util.List;

import static emreb.Utils.Logger.Log;

public class ReadImages {
    public static List<Image> DoReadImages(List<String> imagePaths) {
        List<Image> images = new ArrayList<>();
        imagePaths.parallelStream().forEach(imagePath -> {
            try {
                images.add(new Image(imagePath));
            } catch (Exception e) {
                Log(LogLevel.ERROR, "Image read error: " + e.getMessage());
            }
        });

        return images;
    }

    public static List<Image> DoReadOrderedImages(List<String[]> imagePaths) {
        List<Image> images = new ArrayList<>();
        imagePaths.parallelStream().forEach(imagePath -> {
            try {
                Image image = new Image(imagePath[0]);
                image.setOrder(Integer.parseInt(imagePath[1]));
                images.add(image);
            } catch (Exception e) {
                Log(LogLevel.ERROR, "Image read error: " + e.getMessage());
            }
        });

        return images;
    }

    public static Image DoReadSingleImage(String imagePath) {
        try {
            return new Image(imagePath);
        } catch (Exception e) {
            Log(LogLevel.ERROR, "Image read error: " + e.getMessage());
        }

        return null;
    }
}
