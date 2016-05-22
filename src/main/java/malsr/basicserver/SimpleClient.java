package malsr.basicserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

public class SimpleClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleClient.class);

    public static void main(String[] args) {

        try (
                Socket socket = new Socket("localhost", 2003);
                OutputStream outputStream = socket.getOutputStream();
                InputStream inputStream = socket.getInputStream();
        ) {

            LOGGER.info("Connection established with server {}", socket.getRemoteSocketAddress());

            PrintWriter writer = new PrintWriter(outputStream, true);
            writer.write("Why Hello this is a simple server example");
            writer.write("\nWhy again Hello this is a simple server example");
            writer.write("\nWhy this is a simple server example");
            writer.write("\nanother sentence here we go");

            writer.flush();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            System.out.println(bufferedReader.readLine());
        } catch (IOException e) {
            LOGGER.warn("Encountered error while talking to server", e);
        }

//            DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
//            outToServer.writeChars("Why Hello this is a simple server example");
//            outToServer.writeChars("\nWhy Hello this is a simple server example");
//            outToServer.writeChars("\nWhy Hello this is a simple server example");
    }
}
