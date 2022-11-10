package emreb.Request;

import emreb.Vangogh.CropImage;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;

public class Crop_Request extends BaseRequest {
    public Crop_Request(InputStream data) throws IOException, ParseException {
        super(data);
    }

    @Override
    protected void doOperation() throws IOException {
        CropImage CI;
        String style = decideImageCropStyles();

        if (style.equals("ratio")) CI = new CropImage(this.getImages(), this.getCropRatio(), this.getOutputPath());
        else CI = new CropImage(this.getImages(), this.getCropX(), this.getCropY(), this.getCropWidth(), this.getCropHeight(), this.getOutputPath());
        CI.paint();
    }

    private String decideImageCropStyles() {
        if (this.body.has("ratio")) {
            return "ratio";
        } else if (this.body.has("width") && this.body.has("height")) {
            return "default";
        }
        return "none";
    }

    private int getCropRatio() {
        if (this.body.get("ratio") != null) {
            int cropRatio = this.body.get("ratio").getAsInt();
            if (cropRatio > 0) return cropRatio;
        }
        return -1;
    }

    private int getCropWidth() {
        if (this.body.get("width") != null) {
            int cropWidth = this.body.get("width").getAsInt();
            if (cropWidth > 0) return cropWidth;
        }
        return -1;
    }

    private int getCropHeight() {
        if (this.body.get("height") != null) {
            int cropHeight = this.body.get("height").getAsInt();
            if (cropHeight > 0) return cropHeight;
        }
        return -1;
    }

    private int getCropX() {
        if (this.body.get("x") != null) {
            int x = this.body.get("x").getAsInt();
            if (x > 0) return x;
        }
        return -1;
    }

    private int getCropY() {
        if (this.body.get("y") != null) {
            int y = this.body.get("y").getAsInt();
            if (y > 0) return y;
        }
        return -1;
    }
}
