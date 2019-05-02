import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int portNumber = 6660;

//Test commit again
    public static void main(String[] args) throws InterruptedException, IOException {

        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();

            System.out.println(clientSocket.getPort() + " - " + clientSocket.getLocalPort());

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            out.println("Wolly");
//            Thread.sleep(3000);
            out.println("Hiiií");

            char[] buffer = new char[10000];
            int i = 0;
            while (true) {

                String s = in.readLine();
                if (s != null) {
                    System.out.println(s);
                    System.out.println("\n");
                    System.out.flush();
                }


//                int read = in.read();
//                if (read > 0) {
////                    char[] chars = Character.toChars(read);
//                    buffer[i] = (char) read;
//
//                    if (buffer[i] == '\n') {
//                        System.out.println(buffer);
//                        buffer = new char[10000];
//
//                    } else {
//                        i++;
//                    }
//
//                } else if (read == 0) {
//                    System.out.println("No Input");
//                } else {
//                    System.out.println("Client busted");
//                    break;
//                }
            }

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}

