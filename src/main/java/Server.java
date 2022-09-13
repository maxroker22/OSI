import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("Server started");
        int port = 8080;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {


                    System.out.printf("New connection accepted. Port: %d%n", clientSocket.getPort());

                    final String name = in.readLine();

                    out.println(String.format("Hello %s, your port is %d", name, clientSocket.getPort()));

                    out.flush();

                } catch (IOException e) {
                    System.err.println(e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}