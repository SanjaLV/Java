import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChatUI implements Runnable {
    private InputBuffer Buffer , Console;
    private BufferedReader cin;

    public void SetUpBuffer( InputBuffer ib , InputBuffer cn ) {
        this.Buffer = ib;
        this.Console = cn;
    }
    @Override
    public void run() {
        cin = new BufferedReader(new InputStreamReader( System.in ))  ;

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        while ( true ) {

            synchronized ( Console ) {
                if (Console.get() != null) {
                    System.out.println(Console.get());
                    Console.set(null) ;
                }
            }
            try {
                if ( cin.ready() ) {
                    String temp = cin.readLine();
                    Buffer.set(temp);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}

