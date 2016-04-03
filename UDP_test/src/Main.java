import java.io.IOException;
import java.net.*;

public class Main {
    public static void main(String[] args) {
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket( ) ;
        } catch (SocketException e) {
            e.printStackTrace();
        }


        final String ServerIp = "127.0.0.1";
        final int ServerPort = 4445;

        InetAddress ip = null;
        try {
            ip = InetAddress.getByName(ServerIp);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        final int size = 256   ;

        byte[] data = new byte[size];


        DatagramPacket dp = new DatagramPacket( data , size, ip , ServerPort ) ;

        for ( int d = 0 ; d < 1000 ; d++ ) {

            try {
                ds.send(dp);
            } catch (IOException e) {
                e.printStackTrace();
            }

            byte[] as = new byte[256];

            DatagramPacket answ = new DatagramPacket(as, 256);


            try {
                ds.receive(answ);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String temp = new String(as);


            System.out.println(temp);

        }
        ds.close();

    }
}
