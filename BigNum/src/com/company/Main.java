package com.company;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.StreamSupport;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner( System.in ) ;

        String s1 , s2 ;

        /*
        s1 = in.nextLine();
        s2 = in.nextLine();
        */

	    BigNumb a = new BigNumb( "2" ) ;

        a = BigNumb.power ( a , 100000 ) ;
        System.out.println(a);

        //BigNumb b = new BigNumb( s2 ) ;
        /*BigNumb c = BigNumb.sum( a , b ) ;


        System.out.println(a);
        System.out.println("+");
        System.out.println(b);
        System.out.println("=");
        System.out.println(c);

        BigNumb c = BigNumb.mult(a , b ) ;

        System.out.println(a);
        System.out.println("*");
        System.out.println(b);
        System.out.println("=");
        System.out.println(c);
        */



    }
}
