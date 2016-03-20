import java.util.ArrayList;
import java.util.Vector;

public class Main {
    static final long n = 1000000 ; // 10^6

    public static boolean isPrime ( int x  ) {
        int n =(int) Math.sqrt(x)+1;
        for ( int i = 3 ; i < n; i+=2 ) {

            if ( x % i == 0 ) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPrimeTh ( int x ) {
        final int thCount = 4;

        Thread myThreads[] = new Thread[thCount];
        ArrayList<ThreadRessult> divisible = new ArrayList<>();

        for ( int i = 0 ; i < thCount ; i++ )
            divisible.add ( new ThreadRessult(false));

        int l = 3;
        int n = (int)Math.sqrt(x)+1;

        int step = (n-l) / thCount;

        for ( int i = 0 ; i < thCount ; i++ ) {

            TryDivisors temp = new TryDivisors();

            if ( i == thCount-1 )
                temp.SetParams(l,n,x,divisible.get(i));
            else
                temp.SetParams(l,l+step,x,divisible.get(i));

            l += step;

            myThreads[i] = new Thread(temp);

            myThreads[i].start();

            try {
                myThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for ( int i = 0 ; i < thCount ; i++ )
            if ( divisible.get(i).getValue() )
                return false;







        return true;
    }

    public static void FindPrimes ( Vector<Integer> Primes ) {
        Primes.add ( 2 ) ;
        for ( int i = 3 ; i <= n ; i+=2 ) {
            if ( isPrime ( i ) )
                Primes.add ( i ) ;
        }
    }
    public static void FindPrimesTh ( Vector<Integer> Primes ) {
        Primes.add ( 2 ) ;
        Primes.add ( 3 ) ;
        for ( int i = 3 ; i <= n ; i+=2 ) {
            if ( isPrimeTh(i ) )
                Primes.add ( i ) ;
        }
    }
    public static void main(String[] args) {

        Vector<Integer> Primes = new Vector<>();



        long startTime = System.currentTimeMillis();

        //FindPrimes(Primes) ;
        System.out.println( isPrime(1000000007));

        long endTime = System.currentTimeMillis();

        System.out.println(Primes.size());
        System.out.println(endTime-startTime +" ms");

        /*for ( int i = 0 ; i < Primes.size() ; i++ )
            System.out.print(Primes.get(i) + " " );
        System.out.println();
        */
        Primes = new Vector<>();



        startTime = System.currentTimeMillis();

        //FindPrimesTh(Primes) ;
        System.out.println(isPrimeTh(1000000007));

        endTime = System.currentTimeMillis();

        System.out.println(Primes.size());
        System.out.println(endTime-startTime +" ms");
        /*
        for ( int i = 0 ; i < Primes.size() ; i++ )
            System.out.print(Primes.get(i) + " " );
        System.out.println();
        */


    }
}

class TryDivisors implements Runnable{
    private int L;
    private int R;
    private int x;
    private ThreadRessult res;
    public void SetParams ( int L , int R , int x , ThreadRessult res ) {
        this.L = L;
        this.R = R;
        this.x = x;
        this.res = res;
        //System.out.println(L+":"+R);
    }

    @Override
    public void run() {
        res.setValue(false);
        if ( L % 2 == 0 )
            L++;

        for ( int i = L ; i <= R ; i+=2 ) {

            if ( x % i == 0 ) {
                res.setValue(true);
                return;
            }

        }


    }
}

class ThreadRessult  {
    boolean value;
    ThreadRessult (boolean one ) {
        value = one;
    }
    boolean getValue() {return value;}
    void setValue(boolean x) {value = x; }
}
