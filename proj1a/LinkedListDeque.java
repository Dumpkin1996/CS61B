public class LinkedListDeque <Item> {

    public class ItemNode {
        public Item item;
        public ItemNode next;
        public ItemNode prev;

        public ItemNode (Item x, ItemNode n1, ItemNode n2) {
            item = x;
            next = n1;
            prev = n2;
        }
    }

    private ItemNode sentinel;

    private int size;

    public LinkedListDeque () {
        sentinel = new ItemNode (null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public LinkedListDeque (Item x) {
        ItemNode p = new ItemNode (x, sentinel, sentinel);
        sentinel = new ItemNode (null, p, p);
        size = 1;
    }

    public void addFirst (Item x) {
        ItemNode first = new ItemNode (x, sentinel.next, sentinel);
        sentinel.next = first;
        first.next.prev = first;
        size += 1;
    }

    public void addLast (Item x) {
        ItemNode last = new ItemNode (x, sentinel, sentinel.prev);
        sentinel.prev = last;
        last.prev.next = last;
        size += 1;
    }

    public boolean isEmpty () {
        return size == 0;
    }

    public Item removeFirst () {
        if (sentinel.next == sentinel)
            return null;
        Item removed = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return removed;
    }

    public Item removeLast () {
        if (sentinel.next == sentinel)
            return null;
        Item removed = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return removed;
    }

    public Item get (int index) {
        ItemNode p = sentinel;
        while (index >= 0) {
            index --;
            p = p.next;
        }
        return p.item;
    }

    private Item getRecursive (ItemNode p, int index) {
        if (index == 0)
            return p.item;
        else
            return getRecursive(p.next, index - 1);
    }


    public Item getRecursive (int index) { return getRecursive(sentinel.next, index); }

    public int size () {
        return size;
    }

    public void printDeque () {
        ItemNode p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item);
            System.out.print(" ");
            p = p.next;
        }
        System.out.print("\n");
    }

}
