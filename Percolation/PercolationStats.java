public class PercolationStats {
    private double[] fractions;

// perform T independent computational experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) throw new IllegalArgumentException();
        fractions = new double[T];
        for (int k = 0; k < T; k++) {
            Percolation p = new Percolation(N);
            int count = 0;
            while (!p.percolates()) {
                int i = StdRandom.uniform(1, N + 1);
                int j = StdRandom.uniform(1, N + 1);
                if (p.isOpen(i, j)) continue;
                p.open(i, j);
                count++;
            }
            fractions[k] = (double) count / (N * N);    
        }       
    }
    
    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(fractions);
    }
    
    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(fractions);
    }
    
    // returns lower bound of the 95% confidence interval
    public double confidenceLo() {
        double m = mean();
        double s = stddev();
        return m - 1.96 * s / Math.sqrt(fractions.length);
    }
    
    // returns upper bound of the 95% confidence interval
    public double confidenceHi() {
        double m = mean();
        double s = stddev();
        return m + 1.96 * s / Math.sqrt(fractions.length);
    }
    
    // test client, described below
    public static void main(String[] args)   {
        if (args.length != 2) {
            StdOut.println("Wrong parameters, expected N T");
            return;
        }
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(N, T);
        StdOut.print("mean                    = ");
        StdOut.println(stats.mean());
        StdOut.print("stddev                  = ");
        StdOut.println(stats.stddev());
        StdOut.print("95% confidence interval = ");
        StdOut.print(stats.confidenceLo());
        StdOut.print(", ");
        StdOut.println(stats.confidenceHi());
    }
        
}