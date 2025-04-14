package queue;

public class QueueLL {
    Node first;
    Node last;
    int length;

    public class Node {
        public int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public QueueLL(int value) {
        Node node = new Node(value);
        this.first = node;
        this.last = node;
        length = 1;
    }

    public void enQueue(int val) {
        Node node = new Node(val);
        if (length == 0) {
            first = node;
            last = node;
        } else {
            last.next = node;
            last = node;
        }
        length++;
    }

    public Node deQueue() {
        if (length == 0) {
            return null;
        }
        Node temp = first;
        first = first.next;
        length--;
        if (length == 0) {
            first = null;
            last = null;
        }
        temp.next = null;
        return temp;
    }

    public void printList() {
        Node temp = first;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }
}
