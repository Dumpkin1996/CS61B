public class ArrayDeque <Item> {

    private int size;

    private int capacity;

    private int nextFirst;

    private int nextLast;

    private Item [] items;

    public ArrayDeque () {
        size = 0;
        capacity = 8;
        nextFirst = 3;
        nextLast = 4;
        items = (Item []) new Object [8];
    }

    public void addFirst (Item x) {
        if (capacity - size == 1)
            enlarge();
        size += 1;
        items[nextFirst] = x;
        nextFirst = (nextFirst - 1 + capacity) % capacity;
    }

    public void addLast (Item x) {
        if (capacity - size == 1)
            enlarge();
        size += 1;
        items[nextLast] = x;
        nextLast = (nextLast + 1) % capacity;
    }

    public boolean isEmpty () {
        return (size == 0);
    }

    public int size () {
        return size;
    }

    public void printDeque () {
        if (nextFirst < nextLast) {
            for (int i = nextFirst + 1; i < nextLast; i++) {
                System.out.print(items[i]);
                System.out.print(" ");
            }
        }
        else {
            for (int i = nextFirst + 1; i < capacity; i++) {
                System.out.print(items[i]);
                System.out.print(" ");
            }
            for (int i = 0; i < nextLast; i++) {
                System.out.print(items[i]);
                System.out.print(" ");
            }
        }
    }

    public void enlarge () {
        Item [] newitems = (Item []) new Object [capacity * 2];
        System.arraycopy(items, nextFirst + 1, newitems, 4, size - nextFirst);
        System.arraycopy(items, 0, newitems,  3 + size - nextFirst, nextLast);
        capacity *= 2;
        nextFirst = 3;
        nextLast = 4 + size;
        items = newitems;
    }

    public Item removeFirst () {
        size -= 1;
        int first = (nextFirst + 1) % capacity;
        Item removed = items[first];
        items[first] = null;
        nextFirst = first;
        return removed;
    }

    public Item removeLast () {
        size -= 1;
        int last = (nextLast - 1 + capacity) % capacity;
        Item removed = items[last];
        items[last] = null;
        nextLast = last;
        return removed;
    }

    public Item get (int index ) {
        return items[ (nextFirst + index + 1) % capacity ];
    }








}
