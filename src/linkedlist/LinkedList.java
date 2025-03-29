package linkedlist;

public class LinkedList {

    private Node head;
    private Node tail;
    public int length;

    public class Node {
        public int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public LinkedList(int value) {
        Node node = new Node(value);
        this.head = node;
        this.tail = node;
        this.length = 1;
    }


    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public int getLength() {
        return length;
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    public void printAll() {
        if (length == 0) {
            System.out.println("Head: null");
            System.out.println("Tail: null");
        } else {
            System.out.println("Head: " + head.value);
            System.out.println("Tail: " + tail.value);
        }
        System.out.println("Length:" + length);
        System.out.println("\nLinked List:");
        if (length == 0) {
            System.out.println("empty");
        } else {
            printList();
        }
    }

    public void append(int value) {
        // Create a new node with the given value
        Node newNode = new Node(value);
        // If the list is empty, have both head and tail
        // point at the new node.
        // The LL could be come empty if the original node was removed.
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            // If the list is not empty, set the next node of the
            // current tail to the new node
            // and update the tail to be the new node
            tail.next = newNode;
            tail = newNode;
        }
        // Increment the length of the list
        length++;
    }

//    public Node removeLast() {
//        if (length == 0) return null;
//
//        Node pre = head;
//        Node temp = head;
//        while (temp.next != null) {
//            pre = temp;
//            temp = temp.next;
//        }
//        tail = pre;
//        tail.next = null;
//        length--;
//
//        if (length == 0) {
//            head = null;
//            tail = null;
//        }
//        return temp;
//    }

    public Node removeLast() {
        if (length == 0) return null;
        if (length == 1) {
            Node temp = head;
            head = null;
            tail = null;
            length--;
            return temp;
        } else {
            Node temp = head;
            while (temp.next.next != null) {
                temp = temp.next;
                System.out.println(temp.value + "val");
            }
            Node lastNode = temp.next;
            System.out.println(lastNode.value + "lasst");
            temp.next = null;
            tail = temp;
            length--;
            return lastNode;
        }
    }

    public void prepend(int elem) {
        Node node = new Node(elem);
        if (length == 0) {
            head = node;
            tail = node;
            return;
        }
        node.next = head;
        head = node;
        length++;
    }

    public Node removeFirst() {
        if (length == 0) return null;
        Node temp = head;
        head = head.next;
        temp.next = null;
        length--;
        if (length == 0) tail = null;
        return temp;
    }

    public Node get(int index) {
        if (index < 0 || index > length - 1) return null;
//        int count=0;
        Node temp = head;
        long initial = System.currentTimeMillis();
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        System.out.println("total - " + (System.currentTimeMillis() - initial));
//        if (count==index){
//            return temp.value;
//        }
//        while(count!=index && head.next!=null){
//            temp=temp.next;
//            count++;
//        }
        return temp;
    }

    public boolean set(int index, int value) {
        Node temp = get(index);
        if (temp != null) {
            temp.value = value;
            return true;
        }
        return false;
    }

    public boolean insert(int index, int value) {
        if (index > length || index < 0) return false;
        if (index == 0) {
            prepend(value);
            return true;
        }
        if (index == length) {
            append(value);
            return true;
        }
        Node node = new Node(value);
        Node temp = get(index - 1);
        node.next = temp.next;
        temp.next = node;
        length++;
        return true;
    }

    public Node remove(int index) {
        if (index < 0 || index > length - 1) return null;
        if (index == 0) return removeFirst();
        if (index == length - 1) return removeLast();

        Node prev=get(index-1);
        Node removable=prev.next;
        prev.next=removable.next;
        removable.next=null;
        length--;

        return removable;


    }

}

