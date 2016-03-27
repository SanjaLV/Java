import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.abs;
import static java.lang.Math.max;

public class Main{
  private static final int SECOND = 1000;
  private static final int RUN_TIME = 25*SECOND;
  private static final int PHILS_NUMBER = 0;

  public static void main(String ... args) throws Exception {
    List<Philosopher<?>> phils = new ArrayList<Philosopher<?>>(PHILS_NUMBER);

    Fork left = new Fork();
    Fork first = left;

    for (int i = 0; i < PHILS_NUMBER; i++) {
    	Fork right = new Fork();
    	if (i == PHILS_NUMBER-1) {
    		right = first;
      }
    	Philosopher<? extends Fork> p = new MyPhilosopher(i, left, right);
      phils.add(p);
      left = right;
    }

    Thread[] threads = new Thread[phils.size()];
    for (int i = 0; i < phils.size(); i++) {
        threads[i] = new Thread(phils.get(i));
        threads[i].start();
    }

    /*
    TimeUnit.MILLISECONDS.sleep(RUN_TIME);

    for (Philosopher<?> phil : phils) {
        phil.stop();
    }
    */
    for (Thread thread : threads) {
        thread.join();
    }


    ArrayList<Long> times = new ArrayList<>();
    long MaxDist = 0;
    long sumTime = 0, sumEatCount = 0;
    for (Philosopher<?> phil : phils) {
        long w8time = phil.getWaitTime(TimeUnit.MILLISECONDS);
        times.add(w8time);
    	sumTime += w8time;
    	sumEatCount += phil.getEatCount();
        String temp = phil.toString() + "ate " + String.valueOf(phil.getEatCount()) + " times and waited " + String.valueOf(phil.getWaitTime(TimeUnit.MILLISECONDS)) + " ms";
        System.out.println(temp);

    }
    long avgTime = 0;
    long avgEat = 0;
    try {
      avgTime = sumTime / phils.size();
      avgEat = sumEatCount / phils.size();
    }catch ( ArithmeticException e) {
      e.printStackTrace();
    }

    for ( Long x : times ) {
      MaxDist = max ( MaxDist , abs(x - avgTime)) ;
    }

    System.out.println("Average times ate: "  +avgEat );
    System.out.println("Average wait time: " + avgTime + " ms");
    System.out.println("Max distance to avg : " + MaxDist);

  }
}