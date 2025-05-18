package binarysearchtree;

public class BinarySearchTree {
    //cant have duplicates
    public Node root;

    public class Node {
        public int value;
        public Node left;
        public Node right;

        Node(int val) {
            this.value = val;
        }
    }

    public boolean insert(int value) {
        Node node = new Node(value);
        if (root == null) {
            root = node;
            return true;
        }
        Node temp = root;
        while (true) {
            if (node.value == temp.value) return false;
            if (node.value < temp.value) {
                if (temp.left == null) {
                    temp.left = node;
                    return true;
                }
                temp = temp.left;
            } else {
                if (temp.right == null) {
                    temp.right = node;
                    return true;
                }
                temp = temp.right;
            }
        }
    }

    public boolean contains(int value) {
        Node temp = root;
        while (temp != null) {
            if (value < temp.value) {
                temp = temp.left;
            } else if (value > temp.value) {
                temp = temp.right;
            } else return true;
        }
        return false;
    }

    public boolean rContains(int value) {
        return rContains(root, value);
    }

    private boolean rContains(Node currentNode, int value) {
        if (currentNode == null) return false;
        if (currentNode.value == value) return true;
        if (value < currentNode.value) {
            return rContains(currentNode.left, value);
        } else {
            return rContains(currentNode.right, value);
        }
    }

    public void rInsert(int value) {
        root = rInsert(root, value);
    }

    private Node rInsert(Node currentNode, int value) {
        if (currentNode == null) {
            return new Node(value);
        }
        if (currentNode.value > value) {
            currentNode.left = rInsert(currentNode.left, value);
        } else if (currentNode.value < value) {
            currentNode.right = rInsert(currentNode.right, value);
        }
        return currentNode;
    }


}
