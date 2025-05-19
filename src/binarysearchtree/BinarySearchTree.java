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

    public void deleteNode(int value) {
        deleteNode(root, value);
    }

    private Node deleteNode(Node currentNode, int value) {
        if (currentNode == null) return null;
        if (currentNode.value < value) {
            currentNode.right = deleteNode(currentNode.right, value);
        } else if (currentNode.value > value) {
            currentNode.left = deleteNode(currentNode.left, value);
        } else {//4 scenario if to be deleted node has->1. no node below,2. node on left,3. node on right,4. node on both side
            if (currentNode.left == null && currentNode.right == null) {
                return null;//null will be set to previous rec call
            } else if (currentNode.left == null) {
                currentNode = currentNode.right;//3-right node will be set and to be delete node is out of pointer
            } else if (currentNode.right == null) {
                currentNode = currentNode.left;//2-left node will be set and to be delete node is out of pointer
            } else {//swap with the smallest in the subtree then set it to current value and then delete it on the "right" it will and must be in right
                int smallest = minValue(currentNode.right);
                currentNode.value = smallest;
                currentNode.right = deleteNode(currentNode.right, smallest);
            }
        }
        return currentNode;
    }

    private int minValue(Node currentNode) {
        while (currentNode.left != null) {
            currentNode = currentNode.left;
        }
        return currentNode.value;
    }
}
