package binarysearchtree;

public class Main {
    public static void main(String[] args){
        BinarySearchTree bst=new BinarySearchTree();
        bst.rInsert(1);
        bst.rInsert(3);
        bst.rInsert(5);
        bst.rInsert(0);
        System.out.println(bst.root.left.value);
    }
}
