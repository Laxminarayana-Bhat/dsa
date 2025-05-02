package hashtable;

public class HashTable {
    //linear probing & separate chaining are the 2 techniques to handle collision, here we are using 2nd one with LLs

    private Node[] dataMap;

    class Node {
        String key;
        int value;
        Node next;

        Node(String k, int v) {
            this.value = v;
            this.key = k;
        }
    }

    public HashTable() {
        dataMap = new Node[7];
    }

    public void printTable() {
        for (int i = 0; i < dataMap.length; i++) {
            System.out.println(i + "-");
            Node temp = dataMap[i];
            while (temp != null) {
                System.out.println(" { " + temp.key + " : " + temp.value);
                temp = temp.next;
            }
        }
    }
}
