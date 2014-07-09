import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private class RQIterator implements Iterator<Item> {
        private Item[] items;
        private int pos;
        
        public RQIterator(Item[] queueItems, int size) {
            items = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                items[i] = queueItems[i];
            }
            StdRandom.shuffle(items);
            pos = 0;
        }
        
        public boolean hasNext() {
            return pos < items.length;
        }
        
        public Item next() {
            if (pos >= items.length) {
                throw new NoSuchElementException("Iterator reached end");
            }
            pos++;
            return items[pos - 1];
        }
        
        public void remove() {
            throw new UnsupportedOperationException("Remove not supported");
        }
    }
            
    
    private Item[] items;
    private int size;
// construct an empty randomized queue
    public RandomizedQueue() {
        size = 0;
        items = (Item[]) new Object[1];
    }
    
// is the queue empty?
    public boolean isEmpty() {
        return size == 0;
    }
    
    // return the number of items on the queue
    public int size() {
        return size;
    }
    
    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException("Item can't be null");
        }           
        if (size == items.length) {            
            Item[] newItems = (Item[]) new Object[size * 2];
            for (int i = 0; i < size; i++) {
                newItems[i] = items[i];
            }
            items = newItems;
        }
        items[size] = item;
        size++;
    }
    
   // delete and return a random item
    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty");
        }                                                 
        int index = StdRandom.uniform(size);
        Item t = items[index];
        size--;
        items[index] = items[size];
        items[size] = null;
        
        if (size == items.length / 4 && size != 0) {
            Item[] newItems = (Item[]) new Object[size * 2];
            for (int i = 0; i < size; i++) {
                newItems[i] = items[i];
            }
            items = newItems;
        }
        return t;
    }
        
// return (but do not delete) a random item
    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty");
        }                                                 
        int index = StdRandom.uniform(size);
        return items[index];
    }
    
    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RQIterator(items, size);
    }
    
    // unit testing
    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);
        q.enqueue(6);
        q.enqueue(7);
        q.enqueue(8);
        q.enqueue(9);
        for (int i1: q) {
            for (int i2: q) {
                StdOut.print(i1);
                StdOut.print(" ");
                StdOut.println(i2);
            }
        }
        StdOut.println(q.dequeue());
        StdOut.println(q.dequeue());
        StdOut.println(q.dequeue());
        StdOut.println(q.dequeue());
        StdOut.println(q.dequeue());
        StdOut.println(q.dequeue());
        StdOut.println(q.dequeue());
        StdOut.println(q.dequeue());
        StdOut.println(q.dequeue());
        q.enqueue(10);
    }
    
    
}