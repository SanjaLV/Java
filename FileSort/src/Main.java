

import java.io.*;
import java.util.Vector;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length == 0 || ( args.length == 1 && args[0].equals("/?") ) ){
            System.out.println("program_name [-s/-m] FileName1 FileName2 Result [-f/-t]");
            System.out.println("-s  sort file ") ;
            System.out.println("-m  merge two SORTED files");
            System.out.println("-f  do not ignore case A != a" );
            System.out.println("-t  ignore case a == A");
            System.out.println("RESULT is merge only!!!");
            return;
        }
        if ( args[0].equalsIgnoreCase("-s") ) {
            // sort

            File fIn = new File(args[1]);
            FileReader fr = null;
            try {
                fr = new FileReader(fIn);
            } catch (IOException e) {
                e.printStackTrace();
            }
            BufferedReader input = new BufferedReader(fr);
            Vector<String> Inp = new Vector<String>();
            while ( true ) {
                String d = input.readLine();
                if ( d != null )
                    Inp.add(d) ;
                else
                    break;
            }
            boolean ignoreCase = true;
            if ( args.length == 3 ){
                if ( args[2].equalsIgnoreCase("-f") )
                    ignoreCase = false;
            }

            MySort.Sort(Inp,ignoreCase);
            input.close();

            fIn.createNewFile();
            FileWriter fw = new FileWriter( fIn ) ;
            PrintWriter output = new PrintWriter( fw ) ;
            for ( int i = 0 ; i < Inp.size() ; i++ ) {
                output.println(Inp.get(i));
            }
            output.flush();
            output.close();
        }
        else if ( args[0].equalsIgnoreCase("-m") ) {
            File fInOne = new File ( args[1] ) ;
            File fInTwo = new File ( args[2] ) ;
            File fOut   = new File ( args[3] ) ;
            fOut.createNewFile();

            //FileReader inputOne = null;
            FileReader frOne = new FileReader( fInOne ) ;
            FileReader frTwo = new FileReader( fInTwo ) ;
            FileWriter fw    = new FileWriter( fOut   ) ;
            BufferedReader inputOne = new BufferedReader( frOne ) ;
            BufferedReader inputTwo = new BufferedReader( frTwo ) ;
            PrintWriter    output   = new PrintWriter   ( fw    ) ;


            boolean ignoreCase = true;
            if ( args.length == 5 ) {
                if ( args[4].equalsIgnoreCase("-f"))
                    ignoreCase = false;
            }

            String a = null;
            String b = null;
            boolean doA;
            boolean doB;
            while ( true ) {
                doA = false;
                doB = false;

                if (a == null)
                    a = inputOne.readLine();
                if (b == null)
                    b = inputTwo.readLine();

                if ( a == null && b == null )
                    break;


                if ( a == null || b == null ) {
                    if (a == null)
                        doB = true;
                    if (b == null)
                        doA = true;
                }
                else {

                    if (ignoreCase) {
                        if (a.compareToIgnoreCase(b) < 0)
                            doA = true;
                        else
                            doB = true;
                    } else {
                        if (a.compareTo(b) < 0)
                            doA = true;
                        else
                            doB = true;

                    }
                }

                if ( doA ) {
                    output.println(a);
                    a = null;
                }
                if ( doB ) {
                    output.println(b);
                    b = null;

                }

            }
            output.flush();
            output.close();




           // merge
        }
    }
}
