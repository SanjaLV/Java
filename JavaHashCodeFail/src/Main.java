/*
Tue-Moe(19)
524287
max collision = 2 coount = 30
 */

import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        TreeMap<Integer,Integer> count = new TreeMap<Integer,Integer>();

        StringBuilder z = new StringBuilder( "a" ) ;
        StringBuilder temp = null;
        Character d = 'b';

        for(int i = 0 ; i < 18 ; i++ ) {
            temp = new StringBuilder(z) ;
            z = z.append(d).append(temp);
            d++;
            temp = null;
        }
        //System.out.println(z);
        System.out.println(z.length());
        int MaxCollision = 0 ;
        int CountOfCollision = 0;
        //String tempS = new String( " " );


        for ( int j = 0 ; j < z.length() ; j++ ) {

            String tempS = z.substring(0, j+1);
            //tempS = tempS + z.charAt(j) ;
            /*
            * System.out.print(tempS);
            * System.out.print(" hash = ");
            * System.out.println( tempS.hashCode() ) ;
            */

            Integer key = tempS.hashCode();
            Integer val = count.get(key);
            if ( val == null )
                val = 0 ;
            count.put(key , val+1 ) ;
            if ( val + 1 > MaxCollision ) {
                CountOfCollision = 1;
                MaxCollision = val+1;
            }
            else if ( val+1 == MaxCollision )
                CountOfCollision++;



        }

        System.out.println("max collision = " + MaxCollision + " coount = " + CountOfCollision);
    }

}
