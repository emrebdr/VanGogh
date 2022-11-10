package emreb.Server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import emreb.Models.LogLevel;
import emreb.Models.Response;
import emreb.Request.Request_Models;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import static emreb.Utils.Logger.Log;

public class Server {
    String host;
    int port;

    public Server(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private void assignContexts(HttpServer server) {
        server.createContext("/resize").setHandler(Server::handlePost);
        server.createContext("/crop").setHandler(Server::handlePost);
        server.createContext("/write_text").setHandler(Server::handlePost);
        server.createContext("/merge").setHandler(Server::handlePost);
    }

    public void StartServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(this.port), 0);
        this.assignContexts(server);
        Log(LogLevel.INFO, "Server started on port " + this.port);
        server.start();
    }

    private static void handlePost(HttpExchange exchange) throws IOException {
        byte[] response;
        String type = exchange.getRequestMethod();
        if(type.equalsIgnoreCase("POST")) {
            try {
                InputStream body = exchange.getRequestBody();
                Request_Models RM = new Request_Models(exchange.getHttpContext().getPath(), body);
                RM.get_Request().run();
            } catch (ParseException e) {
                Log(LogLevel.ERROR, "Error: Invalid Request Type. " + e.getMessage());
            }

            response = Response.Success();
            exchange.sendResponseHeaders(200, response.length);
        } else {
            response = Response.Error();
            exchange.sendResponseHeaders(500, response.length);
        }

        OutputStream os = exchange.getResponseBody();
        os.write(response);
        os.close();
    }
}
