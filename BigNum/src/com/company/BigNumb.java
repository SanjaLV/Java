package com.company;

import java.util.Collections;
import java.util.Vector;



public class BigNumb {
    public BigNumb() {

    }

    private int charToInt(char c){
        return ( (int)c - (int)'0' );
    }
    final private static int BLOCK_SIZE = 10;
    final private static int BLOCK_CHARS = 1;
    private Vector<Integer> data = new Vector<Integer>(4);
    public BigNumb ( String s ) {
        for (int i = 0 ; i < s.length() ;  i++ ) {
                data.add(charToInt(s.charAt(i)));
        }
        Collections.reverse(data);
    }

    public String toString() {
        Collections.reverse(data);
        String temp = new String();
        for ( int i = 0 ; i < data.size() ; i++ )
            temp = temp + data.get(i);
        Collections.reverse(data);
        return  temp;

    }


    public static BigNumb sum(BigNumb a, BigNumb b) {
        int rem = 0;

        BigNumb c = new BigNumb();
        for ( int i = 0 ; i < Math.max( a.data.size() , b.data.size() ) ; i++ ){
            int temp = rem;
            if ( i < a.data.size() )
                temp = temp + a.data.get(i);
            if ( i < b.data.size() )
                temp = temp + b.data.get(i);
            c.data.add(temp % BLOCK_SIZE) ;
            rem = temp / BLOCK_SIZE;
        }
        if ( rem != 0 ) {
            c.data.add(rem);
        }


        return  c;
    }
}
