/**
 * Created by Bogdan Daragan on 28.09.16.
 */
public class QuickUnionUF {

    private final int[] id;

    public QuickUnionUF(int N){
        id = new int[N];

        for(int i=0; i<N; i++){
            id[i] = i;
        }
    }

    private int root(int i){
        while (i != id[i]) i = id[i];
        return i;
    }

    public boolean connected(int p, int q){
        return root(p) == root(q);
    }

    public void union(int p, int q){
        int proot = root(p);
        int qroot = root(q);
        id[proot] = qroot;
    }

    public static void main(String[] args) {

        QuickUnionUF uf = new QuickUnionUF(10);

        uf.union(5,6);
        uf.union(1,2);
        uf.union(3,4);
        uf.union(3,8);
        uf.union(3,9);

        System.out.println(uf.connected(4,5));
    }
}