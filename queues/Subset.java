public class Subset {
    public static void main(String[] args) {
        if (args.length != 1) {
            StdOut.println("One parameter expected");
            return;
        }
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {            
            queue.enqueue(StdIn.readString());
        }
        for (int i = 0; i < k; i++) {
            StdOut.println(queue.dequeue());
        }            
    }
}