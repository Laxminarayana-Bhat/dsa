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

    public Node get(int index){
        if (index<0||index>=length) return null;
        Node temp=head;
        if (index<length/2){
            for (int i=0;i<length;i++){
                temp=temp.next;
            }
        }else{
            temp=tail;
            for (int i=length-1;i>index;i--){
                temp=temp.previous;
            }
        }
        return temp;
    }
}
