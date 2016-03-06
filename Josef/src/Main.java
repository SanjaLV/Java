import java.lang.ref.Reference;
import java.util.ArrayList;



public class Main {

    public static void main(String[] args) {
        Treap T = new Treap();
        //System.out.println(T.root);
        T.root = T.NewTreap(1);
        for ( int i = 2  ; i < 10 ; i++ ) {
            T.root = T.Merge(T.root, T.NewTreap(i));
        }
        System.out.println(T);

        Reference<Integer> a = null;
        Reference<Integer> b = null;
        a.set(1);
        b.set(1);
        T.Split(0,a,b);
        System.out.println(a+" "+b);
    }
}

class Treap {
    private class TNode {
        int LSon;
        int RSon;
        double Key;
        int Value;
        TNode(){
            LSon = 0;
            RSon = 0;
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
            A.RSon = Merge(A.RSon , b );
            return a;
        }
        else {
            B.LSon = Merge(a , B.LSon );
            return b;
        }
    }
    void Split(int r, Reference<Integer> a , Reference<Integer> b ) {
        if ( r == 0 ){
            a.set(0);
            b.set(0);

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
        recursiveToString(curent.LSon , t);
        t.append("{").append(curent.Value).append("} ");
        recursiveToString(curent.RSon , t);
    }
    @Override
    public String toString() {
        StringBuilder t = new StringBuilder();
        recursiveToString(root,t);
        return t.toString();
    }
}