import java.util.Random;
import java.util.concurrent.TimeUnit;



public abstract class Philosopher<T extends Fork> implements Runnable{
    protected static final boolean LOG = true;

    protected final int position;
    protected final T left;
    protected final T right;
    protected final Random rand = new Random();

    protected int eatCount = 0;
    protected long waitTime = 0;
    protected long startWait;

    protected volatile boolean stopFlag = false;

    public Philosopher(int position, T left, T right) {
        this.position = position;
        this.left = left;
        this.right = right;
    }

    public final void think() {
        log("is thinking.");  //$NON-NLS-1$
        //try {
        //    TimeUnit.MILLISECONDS.sleep(rand.nextInt(100));
        //} catch (InterruptedException e) { throw new RuntimeException(e); }
        log("is hungry.");  //$NON-NLS-1$
        startWait = System.nanoTime();
    }

    public final void eat() {
        waitTime += System.nanoTime() - startWait;
        log("is eating.");  //$NON-NLS-1$
        //try {
        //    TimeUnit.MILLISECONDS.sleep(rand.nextInt(100));
        //} catch (InterruptedException e) { throw new RuntimeException(e); }
        eatCount++;
        log("finished eating.");  //$NON-NLS-1$
    }

    public void stop() {
            stopFlag = true;
    }

    public void run() {
        while (!stopFlag) {
            act();
        }
        log("stopped."); //$NON-NLS-1$
    }

    protected abstract void act();

    @Override
    public String toString() {
        return "[Philosopher " + position + "]";  //$NON-NLS-1$//$NON-NLS-2$
    }

    public void log(String msg) {
        if (LOG) {
            System.out.println(this.toString() + " " + msg); //$NON-NLS-1$
        }
    }

    public int getEatCount() {
        return eatCount;
    }

    public long getWaitTime(TimeUnit tu) {
        return tu.convert(waitTime, TimeUnit.NANOSECONDS);
    }

    public int getPosition() {
            return position;
    }
}