/**
 * Created by Bogdan Daragan on 28.09.16.
 */
public class QuickFindUF {

    private int[] id;

    public QuickFindUF(int N){
        id = new int[N];

        for(int i=0; i<N; i++){
            id[i] = i;
        }
    }

    public boolean connected(int p, int q){
        return id[p] == id[q];
    }

    public void union(int p, int q){
        int pid = id[p];
        int qid = id[q];

        for (int i=0; i < id.length; i++){
            if(id[i] == pid) id[i] = qid;
        }
    }

    public void show(){
        for (int i=0; i < id.length; i++){
            System.out.println(id[i]);
        }
    }

    public static void main(String[] args) {

        QuickFindUF uf = new QuickFindUF(10);

        uf.union(5,6);
        uf.union(1,2);
        uf.union(3,4);
        uf.union(3,8);
        uf.union(3,9);

        uf.show();
    }
}
