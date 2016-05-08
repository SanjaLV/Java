import java.util.Random;

public class NAMES {
    static Random r = new Random();
    //static String[] males = {"Jon" , "Rob" , "Robert" , "Ned" , "Tirion" , "Jaime" , "Brandon" , "Sam" , "Joffrey" , "Oberin" , "Teon" , "Sandor"};
    //static String[] females = {"Emma" , "Stone" , "Sand" , "Water" , "Stark" , "Lanister" , "Baratheon" , "Tyrrel" , "Frey" , "Bolton" , "Greyjoy" , "Martell"};
    static String[] surnames = {"Snow" , "Stone" , "Sand" , "Water" , "Stark" , "Lanister" , "Baratheon" , "Tyrrel" , "Frey" , "Bolton" , "Greyjoy" , "Martell"};
    static String[] all = new String[5*16*2];
    static char[] vowels = { 'a' , 'e' , 'u' , 'o' , 'i' }; // 5
    static char[] consonants = { 'r' , 't' , 'p' , 's' , 'd' , 'f' , 'g' , 'h' , 'j' , 'k' , 'l' , 'c' , 'v' , 'b' , 'n' , 'm'}; // 16

    static void generate () {


        int writer = 0;
        for (char  i : vowels) {
            for ( char j : consonants ) {
                all[writer] = i+""+j;
                writer++;
            }
        }

        for ( char i : consonants ) {
            for ( char j : vowels ) {
                all[writer] = i+""+j;
                writer++;
            }
        }



    }

    public static String getMALE() {

        int nameSize = r.nextInt(3)+1;
        String temp = "";

        while ( nameSize > 0 ) {
            if ( temp.isEmpty() ) {
                int cur = r.nextInt(all.length);
                char d = all[cur].charAt(0);
                d -= 32;
                temp = "" + d + ""+ all[cur].charAt(1);

            }
            else
                temp = temp + all[ r.nextInt(all.length)];
            nameSize--;
        }
        boolean done = false;
        while ( ! done ) {
            int cur = r.nextInt(all.length);
            char d = all[cur].charAt(1);

            for ( char c : consonants )
                if ( c == d ) {
                    temp += all[cur];
                    done = true;
                    break;
                }

        }

        return temp;



    }

    public static String getFEMALE() {

        int nameSize = r.nextInt(3)+1;
        String temp = "";

        while ( nameSize > 0 ) {
            if ( temp.isEmpty() ) {
                int cur = r.nextInt(all.length);
                char d = all[cur].charAt(0);
                d -= 32;
                temp = "" + d + ""+ all[cur].charAt(1);

            }
            else
                temp = temp + all[ r.nextInt(all.length)];
            nameSize--;
        }
        boolean done = false;
        while ( ! done ) {
            int cur = r.nextInt(all.length);
            char d = all[cur].charAt(1);

            for ( char c : vowels )
                if ( c == d ) {
                    temp += all[cur];
                    done = true;
                    break;
                }

        }

        return temp;



    }

    public static String getSURNAME() {
        return surnames[r.nextInt(surnames.length)];

    }
}
