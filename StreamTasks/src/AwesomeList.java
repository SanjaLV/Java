import java.util.ArrayList;

public class AwesomeList<P> extends ArrayList<P> {

    public  AwesomeList<P> chain_add ( P o ) {
        super.add(o);
        return this;
    }

}
