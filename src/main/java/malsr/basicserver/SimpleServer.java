package malsr.basicserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleServer.class);

    public static void main(String[] args) {

        boolean keepListening = true;

        //Creates a server bound to port 2003
        try (
                ServerSocket serverSocket = new ServerSocket(2003)) {

            while (keepListening) {
                try (
                        Socket socket = serverSocket.accept();
                        InputStream inputStream = socket.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        OutputStream outputStream = socket.getOutputStream();
                ) {

                    PrintWriter printWriter = new PrintWriter(outputStream);
                    LOGGER.info("Connected to Client on Remote address {}", socket.getRemoteSocketAddress());

                    while (bufferedReader.read() != -1) {
                        //first character is cutoff because already reading a char first
                        LOGGER.info(bufferedReader.readLine());
                        printWriter.println("Ok Fine we got you");
                        printWriter.flush();
                    }
                } catch (Exception e) {
                    LOGGER.warn("Error occurred while reading stream", e);
                }
            }
        } catch (IOException e) {
            LOGGER.warn("Server encountered an error", e);
        }
    }
}
