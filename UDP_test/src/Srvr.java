import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Srvr {
    public static void main(String[] args) {

        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket(4445);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        for ( int i = 0 ; i < 1000 ; i++ ) {
            byte[] buf = new byte[256];
            DatagramPacket dp = new DatagramPacket( buf , 256 ) ;
            try {
                ds.receive(dp);
            } catch (IOException e) {
                e.printStackTrace();
            }


            InetAddress adress = dp.getAddress();
            int port = dp.getPort();

            System.out.println( port );
            System.out.println( adress );
            String temp = "test" ;
            byte[] data = temp.getBytes();

            DatagramPacket answ = new DatagramPacket( data , data.length , adress , port ) ;
            try {
                ds.send( answ );
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        ds.close();


    }
}
