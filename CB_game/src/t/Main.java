import t.Pair;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {

    private static String GetInputFromUser(boolean uniq){
        Scanner io = new Scanner(System.in);
        String x = null;
        try {
            x = io.nextLine();
            if ( x.length() != 4 )
                throw new InputMismatchException();
            for ( int i = 0 ; i < 4 ; i++ )
                if ( x.charAt(i) < '0' || x.charAt(i) > '9' )
                    throw new InputMismatchException();
            for ( int i = 0 ; i < 3 ; i++ )
                for ( int j = i+1 ; j < 4 ; j++ )
                    if ( x.charAt(i) == x.charAt(j) && uniq)
                        throw new InputMismatchException();
        } catch (InputMismatchException e) {
            System.out.println("Wrong input" );
            x = null;

        }
        return x;
    }


    private static String genRandomString( ) {
        boolean[] have;
        have = new boolean[11];
        for ( int i = 0 ; i < 10 ; i++ )
            have[i] = false;
        have[10]=true;

        String res = "";
        int temp;
        for ( int i = 0 ; i < 4 ; i++ ) {
            temp = 10;
            while ( have[temp] )
                temp = (int)(Math.random()*10);
            res = res + temp;
            have[temp] = true;
        }
        return res;

    }


    public static void main(String[] args) {


        System.out.println("Pick secret number");
        String pl_secret = null;
        while ( pl_secret == null )
            pl_secret = GetInputFromUser(true);
        String ai_secret = genRandomString();



        TreeSet<String> possible = new TreeSet<>();

        for ( int i = 0 ; i < 1 ; i++ ) {
            for (int ii = 0; ii < 10; ii++)
                for (int j = 0; j < 10; j++)
                    for (int jj = 0; jj < 10; jj++)
                        possible.add(i + "" + ii + "" + j + "" + jj);
        }


        int turn = 0;
        while ( true ) {
            turn++;
            System.out.println(">>>gues");
            String pl_gues = null;
            while ( pl_gues == null )
                pl_gues = GetInputFromUser(false);
            System.out.println(possible.size());
            String ai_gues = possible.higher("");
            //String ai_gues = genRandomString();
            System.out.println("he ask " + ai_gues);
            Pair ppl = new Pair("player ", pl_gues, ai_secret , true);

            if ( ppl.first == 4 ) {
                System.out.println("player wins score : " + turn);
                break;
            }
            Pair ai_this = new Pair("ai ", ai_gues, pl_secret , true );

            if ( ai_this.first == 4  ) {
                System.out.println("ai wins score : " + turn);
                break;
            }

            String temp = "";
            while ( temp != null ) {

                Pair this_one = new Pair("" , ai_gues , temp , false);
                if ( this_one.first != ai_this.first || this_one.second != ai_this.second )
                    // delete from set temp
                temp = possible.higher(temp);
            }



        }

    }
}
