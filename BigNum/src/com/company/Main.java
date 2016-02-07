package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner( System.in ) ;

        String s1 , s2 ;

        s1 = in.nextLine();
        s2 = in.nextLine();


	    BigNumb a = new BigNumb( s1 ) ;
        BigNumb b = new BigNumb( s2 ) ;

        BigNumb c = BigNumb.sum( a , b ) ;
        System.out.println(c);

    }
}
