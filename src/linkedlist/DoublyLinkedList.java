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

    //used 2 pointer approach l[0] == r[len-1] l++ r--
    public boolean isPalindrome() {
        if (length == 0 || length == 1) return true;
        Node l = head;
        Node r = tail;
        while (l != r) {
            if (l.value == r.value) {
                l = l.next;
                r = r.previous;
            } else {
                return false;
            }
        }
        return true;
    }

    public void swapNodePairs() {
        // Create a placeholder (dummyNode) node to simplify swapping.
        Node dummyNode = new Node(0);

        // Link the dummyNode node to the start of our list.
        dummyNode.next = head;

        // Initialize 'previousNode' to 'dummyNode' to remember the node
        // before each pair we're swapping.
        Node previousNode = dummyNode;

        // Continue as long as there's a pair of nodes to swap.
        while (head != null && head.next != null) {

            // Identify the first node of the pair to swap.
            Node firstNode = head;

            // Identify the second node of the pair to swap.
            Node secondNode = head.next;

            // Connect the previousNode's 'next' pointer to secondNode,
            // essentially skipping over firstNode.
            previousNode.next = secondNode;

            // Connect firstNode to the node after secondNode.
            // This ensures we don't lose the rest of the list.
            firstNode.next = secondNode.next;

            // Connect secondNode back to firstNode,
            // completing the swap.
            secondNode.next = firstNode;

            // Adjust 'prev' pointers for our doubly linked list.
            // Set secondNode's 'prev' to the node before current pair.
            secondNode.previous = previousNode;

            // Set firstNode's 'prev' to secondNode as they've been swapped.
            firstNode.previous = secondNode;

            // If there's a node after our current pair, set its 'prev' to firstNode.
            if (firstNode.next != null) {
                firstNode.next.previous = firstNode;
            }

            // Move the head pointer to the node after the current pair.
            head = firstNode.next;

            // Update 'previousNode' for the next pair of nodes.
            previousNode = firstNode;
        }

        // After swapping all pairs, update our list's head to
        // start at the node after dummyNode.
        head = dummyNode.next;

        // Ensure the new head's 'prev' is null, indicating the start of list.
        if (head != null) head.previous = null;
    }
}
