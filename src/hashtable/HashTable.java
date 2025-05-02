package hashtable;

import java.util.ArrayList;

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
                System.out.println(" { " + temp.key + " : " + temp.value + " }");
                temp = temp.next;
            }
        }
    }

    private int hash(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash = (hash + key.charAt(i) * 23) % key.length();
        }
        return hash;
    }
//    Arithmetic operation - char	Becomes int
//    Concatenated with string - char	Remains char
//    Printed directly - char	Remains char
//    Passed to numeric context - char Converted to int

    public void set(String key, int value) {
        int index = hash(key);
        Node node = new Node(key, value);
        if (dataMap[index] == null) {
            dataMap[index] = node;
        } else {
            Node temp = dataMap[index];
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = node;
        }
    }

    public int get(String key) {
        int index = hash(key);
        Node temp = dataMap[index];
        while (temp != null) {
            if (temp.key == key) {
                return temp.value;
            }
            temp = temp.next;
        }
        return -1;
    }

    public ArrayList<?> keys() {
        ArrayList<Integer> list = new ArrayList<>();
        for (Node node : dataMap) {
            Node temp = node;
            while (temp != null) {
                list.add(temp.value);
                temp = temp.next;
            }
        }
        return list;
    }
}
