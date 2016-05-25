package malsr.basicserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class ServerMultipleClients {

    public static void main(String[] args) throws IOException {
        boolean listenForClientConnection = true;
        try (final ServerSocket serverSocket = new ServerSocket(2003)) {
                int count = 1;
                while (listenForClientConnection) {

                    final Socket clientConnection = serverSocket.accept();
                    System.out.println("Creating thread =" + count++);

                    handleClientRequest(clientConnection);
                }
            } catch (IOException e) {
                throw e;
            }
    }

    private static void handleClientRequest(final Socket clientConnection) {
        Thread clientRequestThread = new Thread() {

            @Override
            public void run() {
                System.out.println("Executing run method");
                try (InputStream clientInputStream = clientConnection.getInputStream();
                     InputStreamReader inputStreamReader = new InputStreamReader(clientInputStream);
                     BufferedReader clientBufferedReader = new BufferedReader(inputStreamReader)) {

                    System.out.println("Got client connection");
                    String clientInput;
                    SocketAddress remoteSocketAddress = clientConnection.getRemoteSocketAddress();

                    while ((clientInput = clientBufferedReader.readLine()) != null) {
                        System.out.println("Client " + remoteSocketAddress + " said = " + clientInput);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        clientRequestThread.start();
    }
}
