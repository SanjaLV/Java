import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

    BufferedReader in;
    PrintWriter out;
    Scanner scanIn;

    public ChatClient() {
        scanIn = new Scanner(System.in);
    }

    private void run() throws IOException {

        Socket socket = new Socket("localhost", 9001);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        while (true) {
            String line = in.readLine();
            if (line.startsWith("SUBMITNAME")) {
              System.out.println("Name: ");
              out.println(scanIn.nextLine());
            } else if (line.startsWith("NAMEACCEPTED")) {
              System.out.println("NAMEACCEPTED");
            } else if (line.startsWith("MESSAGE")) {
              System.out.println(line);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ChatClient client = new ChatClient();
        client.run();
    }
}