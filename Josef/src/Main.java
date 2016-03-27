
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {

        Treap T = new Treap();

        //System.out.println(T.root);
        T.root = T.NewTreap(1);
        for ( int i = 2  ; i <= 10 ; i++ ) {
            T.root = T.Merge(T.root, T.NewTreap(i));
        }
        System.out.println(T);



        MutableInt a = new MutableInt(1);
        MutableInt b = new MutableInt(1);

        T.Split(0,a,b);
        System.out.println(a+" "+b);
    }
}

class MutableInt {
    private int val;
    MutableInt ( int x ) {
        val = x;
    }
    public void setVal ( int v ) {
        val = v;
    }
    public int getVal ( ) {
        return val;
    }
    public String toString(){
        return (""+val);
    }
}

class Treap {
    private class TNode {
        MutableInt LSon;
        MutableInt RSon;
        double Key;
        int Value;
        TNode(){
            LSon = new MutableInt(0);
            RSon = new MutableInt(0);
            Key  = Math.random();
        }
    }
    private ArrayList<TNode> Tree = new ArrayList<>();
    private int writer = 0;
    public int root;

    Treap(){
        root = 0;
        Tree.add(new TNode() );
    }
    int NewTreap ( int val ) {
        writer++;
        TNode v = new TNode();
        v.Value = val;
        Tree.add(v);
        return writer;
    }

    int Merge ( int a , int b ) {
        if ( a == 0 || b == 0 )
            return a+b;

        TNode A = Tree.get(a);
        TNode B = Tree.get(b);

        if ( A.Key > B.Key ) {
            A.RSon.setVal(Merge(A.RSon.getVal(), b)) ;
            return a;
        }
        else {
            B.LSon.setVal( Merge(a , B.LSon.getVal() ) ) ;
            return b;
        }
    }

    void Split(int r, MutableInt a , MutableInt b ) {
        if ( r == 0 ){
            a.setVal(0);
            b.setVal(0);

            System.out.println("hello");
            return;
        }

    }
    void Delete(int key) {

    }


    public void recursiveToString(int v , StringBuilder t){
        if ( v == 0 )
            return;
        TNode curent = Tree.get(v);
        recursiveToString(curent.LSon.getVal() , t);
        t.append("{").append(curent.Value).append("} ");
        recursiveToString(curent.RSon.getVal() , t);
    }
    @Override
    public String toString() {
        StringBuilder t = new StringBuilder();
        recursiveToString(root,t);
        return t.toString();
    }


}