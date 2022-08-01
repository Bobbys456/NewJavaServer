package HttpServer;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class InputWorkerThread extends Thread{

    Socket socket;
    InputStream inputStream = null;
    StringBuilder requeststr = new StringBuilder();
    int _byte;

    public InputWorkerThread(Socket socket) throws IOException {
        this.socket = socket;
        inputStream = socket.getInputStream();


    }

    @Override
    public void run() {
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);
        while(true)
        {
            while (true) {
                try {
                    if (!((_byte = reader.read()) >= 0)) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                requeststr.append((char)_byte);
            }
            System.out.println(requeststr);
        }
    }
}
