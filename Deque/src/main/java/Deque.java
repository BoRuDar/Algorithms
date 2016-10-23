import java.util.Iterator;

/**
 * Created by Bogdan Daragan on 24.10.16.
 */
public class Deque<Item> implements Iterable<Item> {

    private Node first = null;
    private Node last = null;
    private int size = 0;

    private class Node{
        private Item item;
        private Node next = null;
        private Node prev = null;
        Node(Item i, Node p, Node n){
            item = i;
            next = n;
            prev = p;
        }
    }

    public Deque() {
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size()   {
        return size;
    }

    public void addFirst(Item item) {
        Node oldFist = first;
        Node newFirst = new Node(item, null, oldFist);
        first = newFirst;

        if(oldFist == null){
            last = newFirst;
        }else {
            oldFist.prev = first;
        }
        size++;
    }

    public void addLast(Item item) {
        Node oldLast = last;
        Node newLast = new Node(item, oldLast, null);
        last = newLast;

        if(oldLast == null){
            first = newLast;
        }else {
            oldLast.next = last;
        }
        size++;
    }

    public Item removeFirst() {
        Item item = first.item;
        first.item = null;
        Node next = first.next;
        first.next = null;
        first = next;

        if(next == null){
            last = null;
        }else {
            next.prev = null;
        }
        size--;
        return item;
    }

    public Item removeLast() {
        Item item = last.item;
        last.item = null;
        Node prev = last.prev;
        last.prev = null;
        last = prev;

        if(prev == null){
            first = null;
        }else {
            prev.next = null;
        }
        size--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node items = first;

            @Override
            public boolean hasNext() {
                if(items != null) return true;
                return false;
            }

            @Override
            public Item next() {
                if(hasNext()){
                    Item item = items.item;
                    items = items.next;
                    return item;
                }
                return null;
            }
        };
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(3);
        deque.addLast(4);
        for (Integer i : deque) System.out.println(i);
    }
}