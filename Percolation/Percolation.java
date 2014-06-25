public class Percolation {
    private boolean[][] grid;    
    
    private WeightedQuickUnionUF unionFind;
    private WeightedQuickUnionUF unionFindNB;
    
    private int size;

    // create N-by-N grid, with all sites blocked    
    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException();
        size = N;
        grid = new boolean[N + 1][N + 1]; //Array already set to 0        
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                grid[i][j] = false;
            }
        }
        unionFind = new WeightedQuickUnionUF(N * N + 2);
        unionFindNB = new WeightedQuickUnionUF(N * N + 1);
        for (int i = 1; i <= N; i++) {
            if (grid[1][i]) {
                unionFind.union(i - 1, N * N);
                unionFindNB.union(i - 1, N * N);
            }
            if (grid[N][i]) {
                unionFind.union(N * (N - 1) + i - 1, N * N + 1);
            }
        }
    }
    
    private int xyToOffset(int i, int j) {
        return (i - 1) * size + j - 1;
    }
    
    private void checkBounds(int i, int j)
    {
        if (i <= 0 || i > size || j <= 0 || j > size) {
            throw new IndexOutOfBoundsException();
        }
    }
    
    
    private void openUF(int i, int j, WeightedQuickUnionUF uf) {
        if (i == 1) {
            uf.union(size * size, xyToOffset(i, j));
        }
        if (i > 1 && grid[i - 1][j]) {
            uf.union(xyToOffset(i - 1, j), xyToOffset(i, j));
        }
        if (i < size && grid[i + 1][j]) {
            uf.union(xyToOffset(i + 1, j), xyToOffset(i, j));
        }
        if (j > 1 && grid[i][j - 1]) {
            uf.union(xyToOffset(i, j - 1), xyToOffset(i, j));
        }
        if (j < size && grid[i][j + 1]) {
            uf.union(xyToOffset(i, j + 1), xyToOffset(i, j));
        }
    }
    
    // open site (row i, column j) if it is not already
    public void open(int i, int j) {
        checkBounds(i, j);
        if (!grid[i][j]) {
            grid[i][j] = true;
            openUF(i, j, unionFind);
            openUF(i, j, unionFindNB);            
            if (i == size) {
                unionFind.union(size * size + 1, xyToOffset(i, j));
            }           
        }
    }
    
    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        checkBounds(i, j);
        return grid[i][j];
    }
    
    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {       
        if (!isOpen(i, j)) return false;
        return unionFindNB.connected(xyToOffset(i, j), size * size);
    }
    
    // does the system percolate?
    public boolean percolates() {
        return unionFind.connected(size * size, size * size + 1);
    }
    
    public static void main(String[] args) {
        Percolation p = new Percolation(1);
        StdOut.println(p.isFull(1, 1));
        StdOut.println(p.percolates());
        p.open(1, 1);
        StdOut.println(p.isFull(1, 1));
        StdOut.println(p.percolates());
    }
}