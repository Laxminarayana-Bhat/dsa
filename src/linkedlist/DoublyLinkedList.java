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
}
