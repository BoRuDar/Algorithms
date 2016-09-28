/**
 * Created by Bogdan Daragan on 28.09.16.
 */
public class WQUPC {
    //Weighted quick union with path compression

    private final int[] id;
    private int[] sz; //how many modes

    public WQUPC(int N){
        id = new int[N];
        sz = new int[N];

        for(int i=0; i<N; i++){
            id[i] = i;
            sz[i] = 1; //initial size
        }
    }

    private int root(int i){
        while (i != id[i]) {
            id[i] = id[id[i]]; //put current node to grandparent
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q){
        return root(p) == root(q);
    }

    public void union(int p, int q){
        int pr = root(p);
        int qr = root(q);
        if (pr == qr) return;

        if (sz[pr] > sz[qr]){ //which tree is bigger?
            id[qr] = pr; //if pr -> add to it the qr
            sz[pr] += sz[qr];
        } else {
            id[pr] = qr;
            sz[qr] += sz[pr];
        }
    }

    public static void main(String[] args) {

        WQUPC uf = new WQUPC(10);

        uf.union(5,6);
        uf.union(1,2);
        uf.union(3,4);
        uf.union(3,8);
        uf.union(3,9);

        System.out.println(uf.connected(4,8));
    }
}