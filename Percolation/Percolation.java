public class Percolation {
    private int[][] grid;    
    
    private WeightedQuickUnionUF unionFind;
    
    private int size;

    // create N-by-N grid, with all sites blocked    
    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException();
        size = N;
        grid = new int[N][N]; //Array already set to 0        
        unionFind = new WeightedQuickUnionUF(N * N + 2);
        for (int i = 0; i < size; i++) {
            union(0, i, N, 0);
            union(N - 1, i, N, 1);
        }
    }
    
    private void union(int i1, int j1, int i2, int j2) {
        unionFind.union(i1 * size + j1, i2 * size + j2);
    }
    
    private void checkBounds(int i, int j)
    {
        if (i < 0 || i >= size || j < 0 || j >= size) {
            throw new IndexOutOfBoundsException();
        }
    }
    
    
    // open site (row i, column j) if it is not already
    public void open(int i, int j) {
        i--;
        j--;
        checkBounds(i, j);
        if (grid[i][j] == 0) {
            grid[i][j] = 1;
            if (i > 0 && grid[i - 1][j] == 1) {
                union(i - 1, j, i, j);
            }
            if (i < size - 1 && grid[i + 1][j] == 1) {
                union(i + 1, j, i, j);
            }
            if (j > 0 && grid[i][j - 1] == 1) {
                union(i, j - 1, i, j);
            }
            if (j < size - 1 && grid[i][j + 1] == 1) {
                union(i, j + 1, i, j);
            }
        }
    }
    
    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        i--;
        j--;
        checkBounds(i, j);
        return grid[i][j] != 0;
    }
    
    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {       
        i--;
        j--;
        checkBounds(i, j);
        int index = i * size + j;
        return unionFind.connected(index, size * size);
    }
    
    // does the system percolate?
    public boolean percolates() {
        return unionFind.connected(size * size, size * size + 1);
    }
    
    
    public static void main(String[] args)   {
        Percolation p = new Percolation(2);
        p.open(2,1);
        StdOut.println(p.percolates());
        p.open(1,1);
        StdOut.println(p.percolates());
        PercolationVisualizer.draw(p, 2);
    }
    
    
       
}