

public class MyPhilosopher<T extends Fork> extends Philosopher {
    final int NEED_EAT_COUNT = 1337;
    public MyPhilosopher(int position, Fork left, Fork right) {
        super(position, left, right);
    }
    @Override
    protected void act() {
        boolean eated_somthing = false;

        if ( this.position % 2 == 0 ) {
            synchronized ( left ) {

                synchronized ( right ) {
                    eat();
                    eated_somthing = true;
                }

            }
            if ( getEatCount() == NEED_EAT_COUNT )
                this.stop();

        }
        else {
            synchronized ( right ) {
                synchronized ( left ) {
                    eat();
                    eated_somthing = true;
                }
            }
            if ( getEatCount() == NEED_EAT_COUNT )
                this.stop();

        }

        if ( eated_somthing )
            this.think();
    }
}
