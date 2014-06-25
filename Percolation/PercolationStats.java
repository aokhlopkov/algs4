public class PercolationStats {
    private double[] fractions;

// perform T independent computational experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) throw new IllegalArgumentException();
        double[] fractions = new double[N];
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
            fractions[k] = (double)count / (N * N);    
        }
        
        
    }
    
    // sample mean of percolation threshold
    public double mean() {
        double result = 0;
        for (double f: fractions) {
            result += f;
        }
        return result / fractions.length;
    }
    
    // sample standard deviation of percolation threshold
    public double stddev() {
        int N = fractions.length;
        if (N == 1) return Double.NaN;
        double m = mean();
        double result = 0;
        for (double f: fractions) {
            result += (f - m) * (f - m);
        }
        return Math.sqrt(result / (N - 1));
    }
    
    // returns lower bound of the 95% confidence interval
    public double confidenceLo() {
        return 0.0; 
    }
    
    // returns upper bound of the 95% confidence interval
    public double confidenceHi() {
        return 0.0;
    }
    
    // test client, described below
    public static void main(String[] args)   {
    }
        
}