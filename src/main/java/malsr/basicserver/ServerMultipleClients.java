package malsr.basicserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class ServerMultipleClients {

    public static void main(String[] args) {
            try (final ServerSocket serverSocket = new ServerSocket(2003)) {

                while (true) {
                    new Thread() {

                        @Override
                        public void run() {
                            try (Socket clientConnection = serverSocket.accept();
                                 InputStream clientInputStream = clientConnection.getInputStream();
                                 BufferedReader clientBufferedReader = new BufferedReader(new InputStreamReader(clientInputStream));) {

                                String clientInput;
                                SocketAddress remoteSocketAddress = clientConnection.getRemoteSocketAddress();

                                while ((clientInput = clientBufferedReader.readLine()) != null) {
                                    System.out.println("Client " + remoteSocketAddress + " said = " + clientInput);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
