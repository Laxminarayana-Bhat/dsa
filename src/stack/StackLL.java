package stack;

public class StackLL {
    int size;
    Node top;

    class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public StackLL(int val) {
        top = new Node(val);
        size = 1;
    }

    public void printStack() {
        Node temp = top;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    public void push(int val) {
        Node node = new Node(val);
        if (size == 0) {
            top = node;
        } else {
            node.next = top;
            top = node;
        }
        size++;
    }

    public Node pop(){
        if (size==0){
            return null;
        }
        Node temp=top;
        top=temp.next;
        temp.next=null;//otherwise next nodes will be there
        size--;
    return temp;
    }
}
