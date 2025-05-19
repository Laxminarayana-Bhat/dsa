package binarysearchtree;

public class Main {
    public static void main(String[] args){
        BinarySearchTree bst=new BinarySearchTree();
        bst.rInsert(1);
        bst.rInsert(3);
        bst.rInsert(7);
        System.out.println(bst.root.right.right.value);
        bst.deleteNode(7);
        bst.rInsert(5);
        System.out.println(bst.root.right.right.value);
    }
}
