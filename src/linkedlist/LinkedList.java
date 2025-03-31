package linkedlist;

import java.util.HashSet;
import java.util.Set;

/*
 *
 * SINGLY LL
 *
 * */
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

        Node prev = get(index - 1);
        Node removable = prev.next;
        prev.next = removable.next;
        removable.next = null;
        length--;

        return removable;


    }

    public void reverse() {
        Node temp = head;
        head = tail;
        tail = temp;
        Node before = null;
        Node after;
        for (int i = 0; i < length; i++) {
            after = temp.next;
            temp.next = before;
            before = temp;
            temp = after;
        }
    }

    public boolean hasLoop() {
        //You are required to use Floyd's cycle-finding algorithm (also known as the "tortoise and the hare" algorithm) to detect the loop
        // Initialize slow pointer to the head of the linked list
        Node slow = head;

        // Initialize fast pointer to the head of the linked list
        Node fast = head;

        // Traverse the linked list with two pointers: slow and fast
        // slow moves one node at a time, while fast moves two nodes at a time
        while (fast != null && fast.next != null) {
            // Move slow pointer to the next node
            slow = slow.next;

            // Move fast pointer to the next two nodes
            fast = fast.next.next;

            // If slow pointer meets fast pointer, then there is a loop in the linked list
            if (slow == fast) {
                return true;
            }
        }

        // If the loop has not been detected after the traversal, then there is no loop in the linked list
        return false;
    }

    public Node findMiddleNodeFloydAlgo() {
        // Initialize slow pointer to the head of the linked list
        Node slow = head;

        // Initialize fast pointer to the head of the linked list
        Node fast = head;

        // Traverse the linked list with two pointers: slow and fast
        // slow moves one node at a time, while fast moves two nodes at a time
        while (fast != null && fast.next != null) {
            // Move slow pointer to the next node
            slow = slow.next;

            // Move fast pointer to the next two nodes
            fast = fast.next.next;
        }

        // Return the Node object representing the middle node of the linked list
        return slow;
    }

    public Node findMiddleNodeMine() {
        Node current = head;
        int len = 0;
        while (current != null) {
            len++;
            current = current.next;
        }
        if (len == 0) return null;
        if (len == 1) return head;

        if (len % 2 == 0) {
            len = (len / 2);
        } else {
            len = len / 2;
        }
        current = head;
        for (int i = 0; i < len; i++) {
            current = current.next;
        }
        return current;
    }

    public Node findKthFromEndMine(int k) {
        Node slow = head;
        Node fast = head;

        int length = 0;
        while (length != k) {
            if (fast == null) return null;
            fast = fast.next;
            length++;
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public Node findKthFromEndAlgo(int k) {
        Node slow = head;
        Node fast = head;

        for (int i = 0; i < k; i++) {
            if (fast == null) return null;
            fast = fast.next;
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public void removeDuplicatesMine() {
        if (head == null || head.next == null) return; // Handle empty or single-element list

        Set<Integer> set = new HashSet<>();
        Node current = head;
        set.add(current.value); // Add the first node's value to the set

        while (current.next != null) {
            if (set.contains(current.next.value)) {
                // Duplicate found, skip the next node
                current.next = current.next.next;
                length--; // Decrease length
            } else {
                // No duplicate, add to the set
                set.add(current.next.value);
                current = current.next; // Move to the next node
            }
        }
    }

    public void removeDuplicates() {
        Set<Integer> values = new HashSet<>();
        Node previous = null;
        Node current = head;
        while (current != null) {
            if (values.contains(current.value)) {
                previous.next = current.next;
                length--;
            } else {
                values.add(current.value);
                previous = current;
            }
            current = current.next;
        }
    }

    public int binaryToDecimal() {
        Node current = head;
        int sum = 0;
        while (current != null) {
            sum = sum * 2 + current.value;
            current = current.next;
        }
        return sum;
    }

    public void reverseBetweenMine(int m, int n) {
        if (head == null || m >= n) return; // Edge case checks

        Node dummy = new Node(0); // Dummy node to handle head reversal
        dummy.next = head;
        Node before = dummy;

        // Move 'before' to the node just before position m
        for (int i = 0; i < m; i++) {
            before = before.next;
        }

        Node temp = before.next;  // First node to be reversed
        Node prev = null;         // Will become the tail of the reversed sublist
        Node after = null;        // To keep track of the next node

        // Reverse the sublist from m to n
        for (int i = m; i <= n; i++) {
            after = temp.next;    // Save the next node
            temp.next = prev;     // Reverse the current node's pointer
            prev = temp;          // Move prev to current
            temp = after;         // Move to the next node
        }

        // Reconnect the reversed sublist back to the list
        assert before.next != null;
        before.next.next = temp;  // Connect the old 'm' node (now tail) to the remaining list
        before.next = prev;       // Connect 'before' to the new head of the reversed sublist

        // Update head if m == 1
        head = dummy.next;
    }

    public void reverseBetween(int m, int n) {
        if (head == null) return;
        Node dummy = new Node(0);
        dummy.next = head;
        Node before = dummy;
        for (int i = 0; i < m; i++) {
            before = before.next;
        }
        Node temp = before.next;
        Node after = null;
        for (int i = 0; i < n - m; i++) {
            after = temp.next;
            temp.next = after.next;
            after.next = before.next;
            before.next = after;
        }

        head = dummy.next;
    }

    public void partitionList(int x) {
        // Step 1: Check for an empty list.
        // If the list is empty, there is nothing
        // to partition, so we exit the method.
        if (head == null) return;

        // Step 2: Create two dummy nodes.
        // These dummy nodes act as placeholders
        // to simplify list manipulation.
        Node dummy1 = new Node(0);
        Node dummy2 = new Node(0);

        // Step 3: Initialize pointers for new lists.
        // 'prev1' and 'prev2' will track the end nodes of
        // the two lists that are being created.
        Node prev1 = dummy1;
        Node prev2 = dummy2;

        // Step 4: Start with the head of the original list.
        Node current = head;

        // Step 5: Iterate through the original list.
        while (current != null) {

            // Step 6: Compare current node value with 'x'.
            // Nodes are partitioned based on their value
            // being less than or greater than/equal to 'x'.

            // Step 6.1: If value is less than 'x',
            // add node to the first list.
            if (current.value < x) {
                prev1.next = current;  // Link node to the end of the first list.
                prev1 = current;       // Update the end pointer of the first list.
            } else {
                // Step 6.2: If value is greater or equal,
                // add node to the second list.
                prev2.next = current;  // Link node to the end of the second list.
                prev2 = current;       // Update the end pointer of the second list.
            }

            // Move to the next node in the original list.
            current = current.next;
        }

        // Step 7: Terminate the second list.
        // This prevents cycles in the list.
        prev2.next = null;

        // Step 8: Connect the two lists.
        // The first list is followed by the second list.
        prev1.next = dummy2.next;

        // Step 9: Update the head of the original list.
        // The head now points to the start of the first list.
        head = dummy1.next;
    }


}

