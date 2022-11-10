package emreb.Request;

import com.google.gson.JsonArray;
import emreb.Vangogh.MergeImages;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Merge_Request extends BaseRequest {
    public Merge_Request(InputStream data) throws IOException, ParseException {
        super(data);
    }

    @Override
    protected void doOperation() throws IOException {
        MergeImages MI = new MergeImages(this.getImages(), this.getPositions(), this.getOutputPath());
        MI.paint();
    }

    private List<int[]> getPositions() {
        List<int[]> positions = new ArrayList<>();
        if (this.body.get("positions") != null) {
            JsonArray positionsArray = this.body.get("positions").getAsJsonArray();
            for (int i = 0; i < positionsArray.size(); i++) {
                int[] position = new int[2];
                position[0] = positionsArray.get(i).getAsJsonArray().get(0).getAsInt();
                position[1] = positionsArray.get(i).getAsJsonArray().get(1).getAsInt();
                positions.add(position);
            }

            return positions;
        }

        return null;
    }
}
