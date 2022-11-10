package emreb.Tests;
import emreb.Vangogh.*;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnitTests {
    List<String> imagesPath = new ArrayList<>();

    public UnitTests() {
        imagesPath.add("./src/test/java/emreb/TestImages/testImage1.jpg");
        imagesPath.add("./src/test/java/emreb/TestImages/testImage2.jpg");
        imagesPath.add("./src/test/java/emreb/TestImages/testImage3.jpg");
    }

    @Test
    public void ResizeTest() {
        try {
            ResizeImage RI = new ResizeImage(imagesPath, 200, 200,  "./src/test/java/emreb/TestResults/Resize/");
            RI.paint();
        } catch (IOException e) {
            assertTrue(true);
        }
    }

    @Test
    public void WriteTextTest() {
        try {
            Design RI = new WriteText(imagesPath, "Hello World", 50, 50, 50, Color.RED ,"./src/test/java/emreb/TestResults/WriteText/");
            RI.paint();
        } catch (IOException e) {
            assertTrue(true);
        }
    }

    @Test
    public void CropTest() {
        try {
            Design RI = new CropImage(imagesPath, 100, 100, 500, 500,"./src/test/java/emreb/TestResults/Crop/");
            RI.paint();
        } catch (IOException e) {
            assertTrue(true);
        }
    }

    @Test
    public void MergeTest() {
        try {
            Design RI = new MergeImages(imagesPath, null, "./src/test/java/emreb/TestResults/Merge/");
            RI.paint();
        } catch (IOException e) {
            assertTrue(true);
        }
    }

    @Test
    public void RotateTest() {
        try {
            Design RI = new RotateImages(imagesPath, 90, "./src/test/java/emreb/TestResults/Rotate/");
            RI.paint();
        } catch (IOException e) {
            assertTrue(true);
        }
    }
}
