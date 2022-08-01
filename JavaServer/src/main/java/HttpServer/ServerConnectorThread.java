package HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ServerConnectorThread extends Thread{

    Socket socket;
    OutputStream outputStream = null;
    InputStream inputStream = null;

    public ServerConnectorThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println(socket.getInetAddress());

        try {
            outputStream = socket.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String html = "<html><head><title>Simple Java HTTP Server</title></head><body><h1>This page was</h1></body></html>";

        final String CRLF = "\n\r"; //13, 10

        String response =
                "HTTP/1.1 200 OK" + CRLF + //Status line : HTTP VERSION RESPONSE_CODE RESPONSE_MESSAGE
                        "Content-Length: " + html.getBytes().length + CRLF + //HEADER
                        CRLF +
                        html +
                        CRLF + CRLF;

        try {
            outputStream.write(response.getBytes());
            InputWorkerThread inputs = new InputWorkerThread(socket);
            inputs.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
