package linkedlist;

public class DoublyLinkedList {
    public Node head;
    public Node tail;
    public int length;

    public static class Node {
        public int value;
        public Node next;
        public Node previous;

        public Node(int val) {
            this.value = val;
        }
    }

    public DoublyLinkedList(int value) {
        Node node = new Node(value);
        this.head = node;
        this.tail = node;
        length = 1;
    }

    public void printList() {
        Node current = head;
        System.out.println("---");
        while (current != null) {
            System.out.println(current.value);
            current = current.next;
        }
        System.out.println("---");
    }

    public void append(int val) {
        Node node = new Node(val);
        if (length == 0) {
            head = node;
        } else {
            tail.next = node;
            node.previous = tail;

        }
        tail = node;
        length++;
    }

    public Node removeLast() {
        if (length == 0) {
            return null;
        }
        Node temp = tail;
        tail = tail.previous;
        tail.next = null;
        temp.previous = null;
        length--;
        if (length == 0) {
            head = null;
            tail = null;
        }
        return temp;
    }

    public void prepend(int val) {
        Node newN = new Node(val);
        if (length == 0) {
            head = newN;
            tail = newN;
        } else {
            head.previous = newN;
            newN.next = head;
            head = newN;
        }
        length++;
    }

    public Node removeFirst() {
        if (length == 0) return null;
        Node temp = head;
        if (length == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.previous = null;
            temp.next = null;
        }
        length--;
        return temp;
    }

    public Node get(int index) {
        if (index < 0 || index >= length) return null;
        Node temp = head;
        if (index < length / 2) {
            for (int i = 0; i < length; i++) {
                temp = temp.next;
            }
        } else {
            temp = tail;
            for (int i = length - 1; i > index; i--) {
                temp = temp.previous;
            }
        }
        return temp;
    }

    public boolean set(int index, int val) {
        Node temp = get(index);
        if (temp != null) {
            temp.value = val;
            return true;
        }
        return false;
    }

    public boolean insert(int index, int value) {
        if (index < 0 || index > length) return true;
        if (index == 0) {
            prepend(value);
            return true;
        }
        if (index == length) {
            append(value);
            return true;
        }
        Node newN = new Node(value);
        Node bef = get(index - 1);
        Node aft = bef.next;
        newN.previous = bef;
        newN.next = aft;
        bef.next = newN;
        aft.previous = newN;
        length++;
        return true;
    }

    public Node remove(int index) {
        if (index < 0 || index >= length) return null;
        if (index == 0) return removeFirst();
        if (index == length - 1) return removeLast();

        Node temp = get(index);

        temp.next.previous = temp.previous;
        temp.previous.next = temp.next;
        temp.next = null;
        temp.previous = null;

        length--;
        return temp;
    }

    public void swapFirstLast() {
        if (length < 2) return;
        int temp = head.value;
        head.value = tail.value;
        tail.value = temp;
        //to swap first and last just swap their values as reference will be same for head and tail
    }


    //here we are just to swap prev and next pointers as they only matters, at last swap  head and tail pointers
    public void reverse() {
        // 'current' starts at the head of the list. This is the starting point
        // for the reversal process.
        Node current = head;

        // 'temp' is a temporary variable used for swapping nodes. It is initially
        // set to null since we haven't started the swapping process yet.
        Node temp = null;

        // We enter a loop that will continue as long as 'current' is not null.
        // This loop goes through each node in the list.
        while (current != null) {
            // Store the previous node of 'current' in 'temp'.
            // This is needed because we will be overwriting 'current.prev' next,
            // and we don't want to lose this reference.
            temp = current.previous;

            // The next two lines are where we swap the 'next' and 'prev' references
            // of the 'current' node. This effectively reverses the direction of the
            // links for 'current'.
            current.previous = current.next; // 'prev' now points to what used to be 'next'
            current.next = temp;         // 'next' now points to what used to be 'prev'

            // Move to the next node in the original list. After the swap, the original
            // 'next' node is now in 'current.prev', so we update 'current' to this node.
            current = current.previous;
        }

        // After the while loop, the list is reversed, but our 'head' and 'tail' pointers
        // are still pointing to the original head and tail. So we need to swap them.
        temp = head;   // Store the original head in 'temp'
        head = tail;   // Update head to be the original tail
        tail = temp;   // Update tail to be what was originally the head
    }
}
