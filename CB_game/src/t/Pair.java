package t;
public class Pair {
    public int first;
    public int second;
    public Pair(int f , int s){
        first = f;
        second = s;
    }
    public Pair ( String msg , String gues , String secret , boolean write ) {
        int cor = 0 ;
        int dif = 0 ;

        for ( int i = 0 ; i < 4 ; i++ )
            for ( int j = 0 ; j < 4 ; j++ ) {
                if ( gues.charAt(i) == secret.charAt(j) ) {
                    if ( i == j )
                        cor++;
                    else
                        dif++;
                }
            }

        if ( write )
            System.out.println(msg + "OnDifPlaces : " + dif + " OnCorectPlaces : " + cor );


        this.first = cor;
        this.second = dif;
    }
}
