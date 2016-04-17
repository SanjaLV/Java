
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//&& - and
//|| or

public class Main {
    public static void main(String[] args) throws IOException {

        PrintWriter cout = new PrintWriter("night.html", "UTF-8");


        //String contents = "<div hello=yes> test <\\div> <test> <div me> look here <\\div><div> test<\\div>";
        //String contents = "<div> hello <\\div>";

        String contents = new String(Files.readAllBytes(Paths.get("Date.html")) , StandardCharsets.UTF_8);


        String findDiv = "(<div)([^>])*(>)";
        String findNDiv = "(<\\\\div)([^>])*(>)";
        /*
        System.out.println( " div :" + findDiv);
        System.out.println("ndiv :" + findNDiv);
        */
        Pattern div = Pattern.compile(findDiv) ;
        Pattern ndiv = Pattern.compile(findNDiv);

        int last = 0;
        StringBuilder result = new StringBuilder();

        Matcher fOpen = div.matcher( contents ) ;
        Matcher fClose = ndiv.matcher( contents ) ;

        boolean fOpen_hasNext = false;
        boolean fClose_hasNext = false;

        //System.out.println( contents.length() + " length");
        //System.out.println( contents );

        while ( true ) {
            if ( !fOpen_hasNext )
                fOpen_hasNext = fOpen.find();
            if ( !fClose_hasNext )
                fClose_hasNext = fClose.find();

            if (!(fOpen_hasNext || fClose_hasNext))
                break;


            boolean goClose = false;
            if ( fOpen_hasNext ) {
                if ( fClose_hasNext)
                    if ( fOpen.start() > fClose.start() )
                        goClose = true;

            }
            else
                goClose = true;

            if ( goClose ) {
                result.append(contents.substring(last, fClose.end()));
                result.append( "<\\div>");
                last = fClose.end();
                fClose_hasNext = false;
            }
            else {
                result.append( contents.substring( last , fOpen.end() ) ) ;
                result.append( "<div style=\"font-family:Lucida Console;color:white;background-color:black\">");
                last = fOpen.end();
                fOpen_hasNext = false;

            }

           // System.out.println( "now : " + result + " last = " + last ) ;
        }


        result.append( contents.substring(last,contents.length() ) );

        cout.println(result);
        cout.close();

    }



}
