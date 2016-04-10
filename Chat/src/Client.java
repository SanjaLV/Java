import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {



        InputBuffer Buffer = new InputBuffer();
        InputBuffer Console = new InputBuffer();

        ChatUI rUI = new ChatUI();
        rUI.SetUpBuffer(Buffer, Console);

        Thread UI = new Thread( rUI  );



        Console.set ("Starting UI" ) ;
        UI.start();

        Console.set("UI started");

        BufferedReader in;
        PrintWriter out;

        Console.set("Establishing connection to server");
        Socket socket = new Socket("localhost", 9001);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        Console.set("Connected to server");


        while ( true ) {
            String line = in.readLine();
            if (line.startsWith("SUBMITNAME")) {
                Console.set("Name: ");
                while (Buffer.get() == null) {
                    Thread.sleep(300);
                }

                out.println(Buffer.get());
                Buffer.set(null);

            } else if (line.startsWith("NAMEACCEPTED")) {
                Console.set("NAME ACCEPTED");
                break;
            }
        }
        while ( true ) {
            if ( Buffer.get() != null ) {
                out.println(Buffer.get() ) ;
                Buffer.set(null);
            }
            if ( in.ready() ) {
                String line = in.readLine();
                Console.set(line);
            }

        }


    }


}
