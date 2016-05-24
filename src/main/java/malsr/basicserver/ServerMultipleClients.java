package malsr.basicserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class ServerMultipleClients {

    public static void main(String[] args) {
            try (final ServerSocket serverSocket = new ServerSocket(2003)) {

                int count = 0;
                while (true) {
                    System.out.println("Creating thread =" + count++);

                    final Socket clientConnection = serverSocket.accept();

                    new Thread() {

                        @Override
                        public void run() {
                            System.out.println("Executing run method");
                            try (InputStream clientInputStream = clientConnection.getInputStream();
                                 BufferedReader clientBufferedReader = new BufferedReader(new InputStreamReader(clientInputStream));) {

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
                    }.start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
