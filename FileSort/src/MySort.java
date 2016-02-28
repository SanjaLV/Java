import java.util.Vector;


public class MySort {
    /**
     * Sort vector of strings
     * @param  a  vector which will be sorted
     * @param  ignoreCase a == A
     */
    public static void Sort( Vector<String> a , boolean ignoreCase){
        if ( ignoreCase )
            MySort.qSortIgnoreCase(a,0,a.size()-1);
        else
            MySort.qSortNotIgnoreCase(a,0,a.size()-1);
    }
    private static void qSortNotIgnoreCase ( Vector<String>a, int l , int r ) {
        int i = l;
        int j = r;
        String piv = a.elementAt( (l+r) / 2 ) ;
        do {
            while ( a.elementAt(i).compareTo(piv) < 0 )
                i++;
            while ( piv.compareTo(a.elementAt(j)) < 0 )
                j--;
            if ( i <= j ) {
                String temp = a.elementAt(i);
                a.setElementAt(a.elementAt(j),i);
                a.setElementAt(temp,j);
                i++;
                j--;
            }

        }while(i<j);
        if ( i < r )
            qSortNotIgnoreCase(a, i, r);
        if ( l < j )
            qSortNotIgnoreCase(a, l, j);
    }
    private static void qSortIgnoreCase ( Vector<String>a, int l , int r ) {
        int i = l;
        int j = r;
        String piv = a.elementAt( (l+r) / 2 ) ;
        do {
            while ( a.elementAt(i).compareToIgnoreCase(piv) < 0 )
                i++;
            while ( piv.compareToIgnoreCase(a.elementAt(j)) < 0 )
                j--;
            if ( i <= j ) {
                String temp = a.elementAt(i);
                a.setElementAt(a.elementAt(j),i);
                a.setElementAt(temp,j);
                i++;
                j--;
            }

        }while(i<j);
        if ( i < r )
            qSortIgnoreCase(a,i,r);
        if ( l < j )
            qSortIgnoreCase(a,l,j);
    }
}
