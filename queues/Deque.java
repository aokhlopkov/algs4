import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * 
 */
public class Deque<Item> implements Iterable<Item> {
    private class Node {
        private Node prev;
        private Node next;
        
        private Item item;
        
        public Node(Node p, Node n, Item i) {
            if (i == null) {
                throw new NullPointerException("Item can't be null");
            }
            prev = p;
            next = n;
            item = i;
        }        
        
        public Item getItem() {
            return item;
        }
        
        public Node getPrev() {
            return prev;
        }
        
        public void setPrev(Node n) {
            prev = n;
        }
        
        public Node getNext() {
            return next;
        }
        
        public void setNext(Node n) {
            next = n;
        }
    }
    
    private class NodeIterator implements Iterator<Item> {
        private Node node;
        
        public NodeIterator(Node n) {
            node = n;
        }
        
        public boolean hasNext() {
            return node != null;
        }
        
        public Item next() {
            if (node == null) {
                throw new NoSuchElementException("Iterator reached last element");
            }
            Item t = node.getItem();
            node = node.getNext();
            return t;
        }
        
        public void remove() {
            throw new UnsupportedOperationException("Iterator remove not supported");
        }
    }
    
    private Node first;
    private Node last;
    private int size;
    // construct an empty deque
    public Deque() {
        size = 0;
        first = null;
        last = null;
    }
    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }
    
// return the number of items on the deque
    public int size() {
        return size;
    }
// insert the item at the front
    public void addFirst(Item item) {        
        if (item == null) {
            throw new NullPointerException("Can't add null to deque");
        }
        Node node = new Node(null, first, item);
        if (first != null) {
            first.setPrev(node);
        } else {
            last = node;
        }
        first = node;
        size++;
    }

// insert the item at the end
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException("Can't add null to deque");
        }
        Node node = new Node(last, null, item);
        if (last != null) {
            last.setNext(node);
        } else {
            first = node;
        }
        last = node;
        size++;
    }
    
    // delete and return the item at the front
    public Item removeFirst() {
        if (first == null) {
            throw new NoSuchElementException("Deque is empty");
        }
        Item t = first.getItem();
        first = first.getNext();
        if (first != null) {
            first.setPrev(null);
        } else {
            last = null;
        }
        size--;
        return t;
    }
        
    // delete and return the item at the end
    public Item removeLast() {
        if (last == null) {
            throw new NoSuchElementException("Deque is empty");
        }
        Item t = last.getItem();
        last = last.getPrev();
        if (last != null) {
            last.setNext(null);
        } else {
            first = null;
        }
        size--;
        return t;
    }
    
    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new NodeIterator(first);
    }
    
     // unit testing
    public static void main(String[] args) {
        Deque<Integer> d = new Deque<Integer>();
        d.addFirst(4);
        d.addFirst(5);
        d.addLast(6);
        StdOut.println(d.removeFirst());
    }
}