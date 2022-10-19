package simple_http_server;


import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class SimpleHttpServer {
    public static int DEFAULT_PORT = 9000;
    public static int port;
    private HttpServer httpServer;

    @SuppressWarnings("static-access")
	private void start(int port) {
        this.port = port;
        try {
            httpServer = HttpServer.create(new InetSocketAddress(port), 0);
            System.out.println("server started at " + port);
            httpServer.createContext("/",new Handlers.RootHandler());
            httpServer.createContext("/echoHeader", new Handlers.EchoHeaderHandler());
            httpServer.createContext("/echoGet", new Handlers.EchoGetHandler());
            httpServer.createContext("/echoPost", new Handlers.EchoPostHandler());
            httpServer.setExecutor(null);
            httpServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SimpleHttpServer httpsServer = new SimpleHttpServer();
        httpsServer.start(DEFAULT_PORT);
    }
}
