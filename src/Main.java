import linkedlist.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList linkedList=new LinkedList(1);
        linkedList.printList();//printing the list
        linkedList.append(4);
        linkedList.append(5);
        linkedList.removeLast();
        linkedList.prepend(10);
        linkedList.printList();
    }
}