package emreb.Request;

import emreb.Vangogh.WriteText;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class WriteText_Request extends BaseRequest {
    public WriteText_Request(InputStream data) throws IOException, ParseException {
        super(data);
    }

    @Override
    protected void doOperation() throws IOException {
        WriteText WT = new WriteText(this.getImages(), this.getText(), this.getTextX(), this.getTextY(), this.getFontSize(), this.getColor(), this.getOutputPath());
        WT.paint();
    }

    private String getText() {
        if (this.body.get("text") != null) return this.body.get("text").getAsString();
        return "";
    }

    private int getTextX() {
        if (this.body.get("x") != null) {
            int text_x = this.body.get("x").getAsInt();
            if (text_x > 0) return text_x;
        }

        return 0;
    }

    private int getTextY() {
        if (this.body.get("y") != null) {
            int text_y = this.body.get("y").getAsInt();
            if (text_y > 0) return text_y;
        }

        return 0;
    }

    private int getFontSize() {
        if (this.body.get("fontSize") != null) {
            int font_size = this.body.get("fontSize").getAsInt();
            if (font_size > 0) return font_size;
        }

        return 18;
    }

    private Color getColor() {
        if (this.body.get("color") != null) {
            String color = this.body.get("color").getAsString();
            return switch (color) {
                case "red" -> Color.RED;
                case "blue" -> Color.BLUE;
                case "green" -> Color.GREEN;
                case "white" -> Color.WHITE;
                case "yellow" -> Color.YELLOW;
                case "orange" -> Color.ORANGE;
                case "pink" -> Color.PINK;
                case "gray" -> Color.GRAY;
                case "cyan" -> Color.CYAN;
                case "magenta" -> Color.MAGENTA;
                default -> Color.BLACK;
            };
        }

        return Color.BLACK;
    }
}
