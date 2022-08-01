package HttpServer;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.StringTokenizer;

public class JavaHTTPServer {

    public static final int PORT = 8080;


    public static void main(String[] args) throws IOException {

        ServerSocket httpServer = new ServerSocket(PORT);
        System.out.println("Server started.\nListening for connections on port : " + PORT + " ...\n");

        // we listen until user halts server execution
        while (httpServer.isBound() && !httpServer.isClosed()) {
            ServerConnectorThread connection = new ServerConnectorThread(httpServer.accept());
            connection.start();
        }
    }
}





