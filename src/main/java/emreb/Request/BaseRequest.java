package emreb.Request;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseRequest {
    private final InputStream data;
    protected JsonObject body;

    public BaseRequest(InputStream data) throws IOException, ParseException {
        this.data = data;
        this.body = this.parseData();
    }

    public void run() throws IOException {
        this.doOperation();
    }

    private JsonObject parseData() {
        JsonParser jsonParser = new JsonParser();

        return (JsonObject) jsonParser.parse(new InputStreamReader(this.data, StandardCharsets.UTF_8));
    }

    protected abstract void doOperation() throws IOException;

    protected List<String> convertType(JsonArray array) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            list.add(array.get(i).getAsString());
        }

        return list;
    }

    protected String getOutputPath() {
        if (this.body.get("output_path") != null) {
            return this.body.get("output_path").getAsString();
        }
        return "./";
    }

    protected List<String> getImages() {
        return this.convertType(this.body.get("images").getAsJsonArray());
    }
}
