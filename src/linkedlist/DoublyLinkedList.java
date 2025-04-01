package linkedlist;

public class DoublyLinkedList {
    public Node head;
    public Node tail;
    public int length;

    public class Node {
        int value;
        Node next;
        Node previous;

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
}
