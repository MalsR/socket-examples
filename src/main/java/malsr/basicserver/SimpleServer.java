package malsr.basicserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A simple server that listens for a single client connection at a time, unless the client terminates the connection
 * and it will keep listening for any new client connections
 */
public class SimpleServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleServer.class);

    public static void main(String[] args) throws IOException {
        boolean listenForClientConnection = true;

        //Creates a server bound to port 2003
        try (ServerSocket serverSocket = new ServerSocket(2003)) {
            while (listenForClientConnection) {
                try (
                        Socket socket = serverSocket.accept();
                        InputStream inputStream = socket.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        OutputStream outputStream = socket.getOutputStream();
                        PrintWriter printWriter = new PrintWriter(outputStream)
                ) {
                    LOGGER.info("Connected to Client on Remote address {} and port {}",
                            socket.getRemoteSocketAddress(), socket.getPort());

                    String clientInput;

                    //blocking call keep reading each line as it receives and assign to 'clientInput' string
                    while ((clientInput = bufferedReader.readLine()) != null) {
                        //first character will be cutoff if read() is used as the reader is reading a char first
                        LOGGER.info(clientInput);
                        printWriter.println("You said " + "\"" + clientInput + "\"");
                        printWriter.flush();
                    }
                } catch (IOException e) {
                    LOGGER.warn("Error occurred while reading stream", e);
                    throw e;
                }
            }
        } catch (IOException e) {
            LOGGER.warn("Server encountered an error", e);
            throw e;
        }
    }
}
