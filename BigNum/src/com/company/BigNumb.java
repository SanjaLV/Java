package com.company;

import com.sun.media.sound.AiffFileReader;

import java.util.*;


public class BigNumb {


    public BigNumb() {
    }

    private int charToInt(char c){
        return ( (int)c - (int)'0' );
    }
    final private static int BLOCK_SIZE = 10;
    final private static int BLOCK_CHARS = 1;
    //private Vector<Integer> data = new Vector<Integer>(8);
    private Deque<Integer> data = new LinkedList<Integer>();
    public BigNumb ( String s ) {
        for (int i = 0 ; i < s.length() ;  i++ ) {
                data.addFirst(charToInt(s.charAt(i)));
        }
        // 12345 => 2345 + 0001
        // 55555 => 5555 + 0005
        // ==    => 7900 + 0006
        if ( data.size() == 1 && data.getFirst() == 0 ) {
            data.clear();
        }

        //Collections.reverse(data);
    }

    public String toString() {
        if ( data.size() == 0 ) {
            return  ("0");
        }
        Iterator<Integer> itr = data.descendingIterator();
        String temp = "";
        while ( itr.hasNext() ) {
            Integer element = itr.next();
            temp = temp + element;
        }
        return  temp;

    }


    public static BigNumb sum(BigNumb a, BigNumb b) {
        int rem = 0;

        BigNumb c = new BigNumb();

        Iterator<Integer> Aitr = a.data.iterator();
        Iterator<Integer> Bitr = b.data.iterator();

        while ( Aitr.hasNext() || Bitr.hasNext() ) {
            int temp = rem;
            if ( Aitr.hasNext() )
                temp += Aitr.next();
            if ( Bitr.hasNext() )
                temp += Bitr.next();

            c.data.addLast(temp % BLOCK_SIZE);
            rem = temp / BLOCK_SIZE;
        }
        if ( rem != 0 )
            c.data.addLast(rem);

        return  c;
    }
    /**
     * BigNumb * BigNumb for O(N*M)
     */
    public static BigNumb mult(BigNumb a , BigNumb b ) {
        BigNumb c = new BigNumb();
        // n^2
        if ( a.data.size() == 0 || b.data.size() == 0 ) {
            return  c;
        }

        Iterator<Integer> Bitr = b.data.iterator();
        int cz = 0 ;
        while ( Bitr.hasNext() ) {

            int d = Bitr.next();
            if (d != 0 ) {
                BigNumb temp = BigNumb.mult(a , d);
                temp = BigNumb.addZeros(temp , cz ) ;
                c = BigNumb.sum ( c , temp ) ;
            }
            cz++;


        }

        return  c;
    }

    /**
     * Multiply BigNumb by int
     * @param a BigNumb
     * @param b Multiplicator
     * @return BigNumber * smallint
     */
    public static BigNumb mult ( BigNumb a , int b ) {
        BigNumb c = new BigNumb();
        if ( b == 0 )
            return c;
        int rem = 0 ;

        Iterator<Integer> itr = a.data.iterator();

        while ( itr.hasNext() ) {
            int element = itr.next();
            int temp = rem + element * b;
            c.data.addLast( temp % BLOCK_SIZE ) ;
            rem = temp / BLOCK_SIZE;
        }

        if ( rem != 0 )
            c.data.addLast(rem);

        return  c;
    }
    /**
     * Multiply BigNumb by 10^howMuch for O(howMuch)
     * @param a BigNumb
     * @param howMuch power
     */
    public static BigNumb addZeros ( BigNumb a , int howMuch ) {
        for ( int i = 0 ; i < howMuch ; i++ )
            a.data.addFirst(0);
        return  a;
    }

    public static BigNumb power ( BigNumb n , int k ) {
        if ( k == 0 ) {
            return  new BigNumb("1");

        }
        BigNumb c = new BigNumb();
        if ( k % 2 == 0 ) {
            return  BigNumb.power(BigNumb.mult(n, n), k / 2) ;
        }
        else {
            return  BigNumb.mult ( BigNumb.power( BigNumb.mult(n,n) , k / 2 ) , n );
        }
    }
}
