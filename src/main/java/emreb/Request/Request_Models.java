package emreb.Request;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;

public class Request_Models {
    // Constructor Parameters
    private final String type;
    private final InputStream data;

    public Request_Models(String type, InputStream data) {
        this.type = type;
        this.data = data;
    }

    public BaseRequest get_Request() throws IOException, ParseException {
        return switch (this.type) {
            case "/resize" -> new Resize_Request(this.data);
            case "/crop" -> new Crop_Request(this.data);
            case "/write_text" -> new WriteText_Request(this.data);
            case "/merge" -> new Merge_Request(this.data);
            case "/rotate" -> new Rotate_Request(this.data);
            default -> null;
        };
    }
}

