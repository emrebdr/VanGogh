package emreb.Request;

import emreb.Vangogh.ResizeImage;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;

public class Resize_Request extends BaseRequest {
    public Resize_Request(InputStream data) throws IOException, ParseException {
        super(data);
    }

    @Override
    protected void doOperation() throws IOException {
        ResizeImage RI;
        String style = decideImageResizeStyles();

        if (style.equals("ratio")) RI = new ResizeImage(this.getImages(), this.getResizeRatio(), this.getOutputPath());
        else RI = new ResizeImage(this.getImages(), this.getResizeWidth(), this.getResizeHeight(), this.getOutputPath());
        RI.paint();
    }

    private String decideImageResizeStyles() {
        if (this.body.has("resize_ratio")) {
            return "ratio";
        } else if (this.body.has("resize_width") && this.body.has("resize_height")) {
            return "default";
        }
        return "none";
    }

    private int getResizeRatio() {
        if (this.body.get("resize_ratio") != null) {
            int resizeRatio = this.body.get("resize_ratio").getAsInt();
            if (resizeRatio > 0) return resizeRatio;
        }
        return -1;
    }

    private int getResizeWidth() {
        if (this.body.get("resize_width") != null) {
            int resizeWidth = this.body.get("resize_width").getAsInt();
            if (resizeWidth > 0) return resizeWidth;
        }
        return -1;
    }

    private int getResizeHeight() {
        if (this.body.get("resize_height") != null) {
            int resizeHeight = this.body.get("resize_height").getAsInt();
            if (resizeHeight > 0) return resizeHeight;
        }
        return -1;
    }
}
