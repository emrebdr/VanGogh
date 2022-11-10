package emreb.Request;

import emreb.Vangogh.RotateImages;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;

public class Rotate_Request extends BaseRequest {

    public Rotate_Request(InputStream data) throws IOException, ParseException {
        super(data);
    }

    @Override
    protected void doOperation() throws IOException {
        RotateImages RI = new RotateImages(this.getImages(), this.getRotateAngle(), this.getOutputPath());
        RI.paint();
    }

    private int getRotateAngle() {
        if (this.body.get("angle") != null) {
            int rotateAngle = this.body.get("angle").getAsInt();
            if (rotateAngle > 0) return rotateAngle;
        }
        return -1;
    }
}
