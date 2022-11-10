package emreb.Main;

import emreb.Server.Server;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Dotenv dotenv = Dotenv.load();
        Server server = new Server(dotenv.get("VAN_GOGH_HOST"), Integer.parseInt(Objects.requireNonNull(dotenv.get("VAN_GOGH_PORT"))));
        server.StartServer();
    }
}
