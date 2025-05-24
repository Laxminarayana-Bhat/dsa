package binarysearchtree;

public class Main {
    public static void main(String[] args){
        BinarySearchTree bst=new BinarySearchTree();
        bst.rInsert(47);
        bst.rInsert(21);
        bst.rInsert(76);
        bst.rInsert(18);
        bst.rInsert(27);
        bst.rInsert(52);
        bst.rInsert(82);
        System.out.println(bst.bfs());
    }
}
