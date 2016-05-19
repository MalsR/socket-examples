package malsr.basicserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleServer.class);

    public static void main(String[] args) throws IOException {

        //Creates a server bound to port 2003
        ServerSocket serverSocket = new ServerSocket(2003);

        try {
            while (true) {
                Socket accept = serverSocket.accept();
                InputStream inputStream = accept.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                while (bufferedReader.read() != -1) {
                    //first character is cutoff because already reading a char first
                    LOGGER.info(bufferedReader.readLine());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
