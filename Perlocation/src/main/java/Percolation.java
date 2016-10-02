import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by Bogdan Daragan on 01.10.16.
 */
public class Percolation {

    private int [][][] square;
    private static int N;
    private WeightedQuickUnionUF union;
    private int sites = 0;

    public int getSites() {
        return sites;
    }

    public Percolation(int n)   {
        N = n;
        square = new int[n+1][n][2];
        int counter = 0;

        for (int i=0; i<n ; i++){
            for (int j=0; j<n; j++){
                square[i][j][0] = counter++; //ID
                square[i][j][1] = 0; //Open/Close
            }
        }

        union = new WeightedQuickUnionUF(n*n+2);

        square[n][0][0] = 100; //TOP
        square[n][1][0] = 101; //BOTTOM
        square[n][0][1] = 1;
        square[n][1][1] = 1;
    }            // create n-by-n grid, with all sites blocked

    public void open(int i, int j)  {
        //if( isOpen(i,j) ) return;
        square[i][j][1] = 1; //open current site
        this.sites++;

        if(i == 0){
            union.union(square[N][0][0], square[i][j][0]); //with virtual top
        }
        if(i == N-1){
            union.union(square[N][1][0], square[i][j][0]); //with virtual bottom
        }

        if( (i+1) <=N && isOpen(i+1,j) ){
            union.union(square[i+1][j][0], square[i][j][0]); //bottom
        }
        if( (i-1) > 0 && isOpen(i-1,j) ){
            union.union(square[i-1][j][0], square[i][j][0]); //top
        }
        if( (j+1) <N && isOpen(i, j+1) ){
            union.union(square[i][j+1][0], square[i][j][0]); //right
        }
        if( (j-1) > 0 && isOpen(i, j-1) ){
            union.union(square[i][j-1][0], square[i][j][0]); //left
        }
    }

    public boolean isOpen(int i, int j) {
        if(square[i][j][1] == 1) return true;
        return false;
    }

    public boolean isFull(int i, int j)  {
        if(union.connected(square[i][j][0], square[N][0][0])) return true;
        return false;
    }

    public boolean percolates() {
        return union.connected(square[N][0][0], square[N][1][0]);
    }         // does the system percolate?

    public int exec(String [] a){
        int n = Integer.parseInt(a[0]);

        for(int t = 0; ; t++){
            int i = StdRandom.uniform(n);
            int j = StdRandom.uniform(n);
            if( this.isOpen(i,j) ) continue;
            this.open(i,j);

            if (this.percolates() ) {
                System.out.println("Percolation on " + this.getSites() + " site");
                break;
            }
        }

        return this.getSites();
    }

    public static void main(String[] args) {
        long t0 = System.currentTimeMillis();
        int n = Integer.parseInt(args[0]);

        Percolation p = new Percolation(n);

        long t1 = System.currentTimeMillis();
        System.out.println(t1-t0);
    }
}