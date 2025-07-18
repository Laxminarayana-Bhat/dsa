package binarysearchtree;

import java.util.*;

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

    private Node rInvertTree(Node node) {
        // Check if the current node is null. This condition is crucial because it
        // serves as the termination condition for the recursion. When the method
        // encounters a null, it means it has reached beyond the leaf nodes of the
        // tree (i.e., it's at a position where a leaf node would have a child if
        // leaf nodes could have children). Since there's nothing to invert at this
        // point, the method simply returns null without making any changes.
        if (node == null) return null;

        // Temporarily store the current node's left child in a variable named 'temp'.
        // This step is necessary because the inversion process will overwrite the
        // 'left' pointer of the current node with the value of its 'right' pointer.
        // Storing the original left child allows us to access it later when we need
        // to set the current node's right child. Without this temporary storage, the
        // original left child would be lost after setting the current node's left child
        // to the inverted right subtree.
        Node temp = node.left;

        // Recursively invert the right subtree. The method calls itself with the
        // current node's right child as the argument. This recursive call will
        // continue down the right subtree, inverting each node's children along the
        // way. Once the inversion of the right subtree is complete, the method
        // sets the current node's left child to the result. This effectively moves
        // the entire right subtree to the left side of the current node. The
        // recursion ensures that every node in the right subtree is processed
        // and inverted in a bottom-up manner, starting from the rightmost leaf nodes.
        node.left = invertTree(node.right);

        // Recursively invert the original left subtree, which is now stored in 'temp'.
        // This step is similar to the previous one but operates on the original left
        // subtree of the current node. By calling 'invertTree' with 'temp' (the
        // original left child) as the argument, the method inverts the left subtree
        // and sets the result as the current node's right child. This moves the entire
        // left subtree, now inverted, to the right side of the current node. As with
        // the right subtree, the inversion is performed recursively in a bottom-up
        // fashion, ensuring that the structure of the subtree is correctly mirrored.
        node.right = invertTree(temp);

        // Return the current node. By this point in the method, the current node's
        // left and right children have been swapped, and the subtrees rooted at
        // those children have been inverted. Returning the current node allows the
        // parent call (one level up in the recursion stack) to receive the inverted
        // subtree and continue the inversion process up the tree. This step is
        // repeated until the recursion unwinds back to the root of the tree,
        // at which point the entire tree has been inverted.
        return node;
    }

    private Node invertTree(Node node) {
        if (node == null) return null;
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            Node temp = stack.pop();
            Node t = temp.left;
            temp.left = temp.right;
            temp.right = t;
            if (temp.left != null) stack.push(temp.left);
            if (temp.right != null) stack.push(temp.right);
        }
        return node;
    }

    private Node RinvertTree(Node node) {
        if (node == null) return null;
        Node temp = node.left;
        node.left = node.right;
        node.right = temp;
        invertTree(node.left);
        invertTree(node.right);
        return node;
    }

    private Node sortedArrayToBST(int[] nums, int left, int right) {
        // Base case: if the left index exceeds the right,
        // it means the sub-array is empty. Return null
        // to indicate no node should be created at this level.
        if (left > right) return null;

        // Find the middle element of the current sub-array.
        // This element will be the root of the subtree formed
        // by this sub-array. This choice ensures the resulting
        // BST is balanced. The middle element is chosen by
        // calculating the average of the left and right indices,
        // adjusting for integer division. This also ensures that
        // in cases where the sub-array has an even number of elements,
        // the left subtree will not have fewer nodes than the right subtree.
        int mid = left + (right - left) / 2;

        // Create a new node with the value of the middle element.
        // This node will be the root of the subtree for the current
        // recursive call.
        Node node = new Node(nums[mid]);

        // Recursively build the left subtree by using the portion
        // of the array that's to the left of the middle element.
        // The current middle element's index is adjusted by one to the left
        // to exclude it from the left sub-array. This recursive call
        // will continue until the base case is reached for the left side.
        node.left = sortedArrayToBST(nums, left, mid - 1);

        // Similarly, recursively build the right subtree by using
        // the portion of the array that's to the right of the middle element.
        // The current middle element's index is adjusted by one to the right
        // to exclude it from the right sub-array. This recursive call
        // will continue until the base case is reached for the right side.
        node.right = sortedArrayToBST(nums, mid + 1, right);

        // Return the node. At the end of recursion, this node will be
        // the root of a balanced binary search tree constructed from
        // the input array.
        return node;
    }

    public List<Integer> bfs() {
        Node current = root;
        Queue<Node> q = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        if (current == null) return res;
        q.add(current);
        while (!q.isEmpty()) {
            current = q.remove();
            res.add(current.value);
            if (current.left != null) {
                q.add(current.left);
            }
            if (current.right != null) {
                q.add(current.right);
            }
        }
        return res;
    }

    public List<Integer> dfsPreOrder() { //pre-order- root,left,right
        List<Integer> result = new ArrayList<>();
        class Traverse {
            public Traverse(Node node) {
                result.add(node.value);
                if (node.left != null) {
                    new Traverse(node.left);
                }
                if (node.right != null) {
                    new Traverse(node.right);
                }
            }
        }
        new Traverse(root);
        return result;
    }

    public List<Integer> dfsPreOrder2() {
        List<Integer> result = new ArrayList<>();
        preOrderHelper(root, result);
        return result;
    }

    private void preOrderHelper(Node node, List<Integer> result) {
        if (node == null) return;
        result.add(node.value);
        preOrderHelper(node.left, result);
        preOrderHelper(node.right, result);
    }

    public List<Integer> dfsPostOrder2() {
        List<Integer> result = new ArrayList<>();
        postOrderHelper(root, result);
        return result;
    }

    private void postOrderHelper(Node node, List<Integer> result) {
        if (node == null) return;
        postOrderHelper(node.left, result);
        postOrderHelper(node.right, result);
        result.add(node.value);
    }

    public List<Integer> dfsPostOrder() { //pre-order- root,left,right
        List<Integer> result = new ArrayList<>();
        class Traverse {
            public Traverse(Node node) {
                if (node.left != null) {
                    new Traverse(node.left);
                }
                if (node.right != null) {
                    new Traverse(node.right);
                }
                result.add(node.value);
            }
        }
        new Traverse(root);
        return result;
    }

    public List<Integer> dfsInOrder2() {
        List<Integer> result = new ArrayList<>();
        inOrderHelper(root, result);
        return result;
    }

    private void inOrderHelper(Node node, List<Integer> result) {
        if (node == null) return;
        postOrderHelper(node.left, result);
        result.add(node.value);
        postOrderHelper(node.right, result);
    }

    public List<Integer> dfsInOrder() { //pre-order- root,left,right
        List<Integer> result = new ArrayList<>();
        class Traverse {
            public Traverse(Node node) {
                if (node.left != null) {
                    new Traverse(node.left);
                }
                result.add(node.value);
                if (node.right != null) {
                    new Traverse(node.right);
                }

            }
        }
        new Traverse(root);
        return result;
    }

    public boolean isValidBST() {//in inorder traversal have elements in ascending order, so just check the order and BINGO
        List<Integer> ans = dfsInOrder();
        for (int i = 0; i < ans.size() - 1; i++) {
            if (ans.get(i) >= ans.get(i + 1)) return false;
        }
        return true;
    }

    public List<List<Integer>> levelOrder(Node root) {//bfs level by level adding
        Queue<Node> q = new LinkedList<>();
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;
        q.offer(root);
        int lvl = 0;
        while (q.size() != 0) {
            int len = q.size();
            list.add(new ArrayList<>());
            for (int i = 0; i < len; i++) {
                Node node = q.poll();
                list.get(lvl).add(node.value);
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            lvl++;
        }
        return list;
    }

    public List<List<Integer>> rLevelOrder(Node root) {
        List<List<Integer>> list = new ArrayList<>();
        level(root, 0, list);
        return list;
    }

    public void level(Node node, int lvl, List<List<Integer>> list) {
        if (node == null) return;
        if (list.size() <= lvl) {
            list.add(new ArrayList<>());
        }
        list.get(lvl).add(node.value);
        level(node.left, lvl + 1, list);
        level(node.right, lvl + 1, list);
    }

    public List<Integer> rightSideView(Node root) {
        Queue<Node> q = new LinkedList<>();
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        q.offer(root);
        while (!q.isEmpty()) {
            int val = q.size();
            for (int i = 0; i < val; i++) {
                Node node = q.poll();
                if (i == val - 1)
                    ans.add(node.value);
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
        }
        return ans;
    }

    public List<Integer> rRightSideView(Node root) {
        List<Integer> ans = new ArrayList<>();
        helper(root, 0, ans);
        return ans;
    }

    public void helper(Node node, int lvl, List<Integer> ans) {
        if (node == null) return;
        if (lvl == ans.size()) {
            ans.add(node.value);
        }
        helper(node.right, lvl + 1, ans);
        helper(node.left, lvl + 1, ans);
    }

    int count = 0;

    public int goodNodes(Node root) {
        helper(root, root.value);
        return count;
    }

    public void helper(Node node, int num) {
        if (node == null) return;
        if (node.value >= num) {
            count++;
        }
        num = Math.max(num, node.value);
        helper(node.left, num);
        helper(node.right, num);
    }

    public Node lowestCommonAncestor(Node root, Node p, Node q) {
        while (root != null) {
            if (root.value > p.value && root.value > q.value) {
                root = root.left;
            } else if (root.value < p.value && root.value < q.value) {
                root = root.right;
            } else
                return root;
        }
        return null;
    }

    public Node rLowestCommonAncestor(Node root, Node p, Node q) {
        if (root == null) return null;
        if (root.value > p.value && root.value > q.value) {
            return rLowestCommonAncestor(root.left, p, q);
        } else if (root.value < p.value && root.value < q.value) {
            return rLowestCommonAncestor(root.right, p, q);
        } else
            return root;
    }

//    🧠 Hint:
//    The first element of the preorder array is always the root of the tree.
//    In the inorder array, elements to the left of the root belong to the left subtree, and elements to the right belong to the right subtree.
//    Use recursion to construct the left and right subtrees.

    Map<Integer, Integer> map = new HashMap<>();
    int preidx = 0;

    public Node buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return helper(preorder, 0, preorder.length - 1);
    }

    public Node helper(int[] preorder, int left, int right) {
        if (left > right) return null;
        int rootval = preorder[preidx++];//just to get the value using index
        Node root = new Node(rootval);
        int index = map.get(rootval);
        root.left = helper(preorder, left, index - 1);
        root.right = helper(preorder, index + 1, right);
        return root;
    }

    int dia = 0;

    public int diameterOfBinaryTree(Node root) {
        helper(root);
        return dia;
    }

    public int helper(Node node) {
        if (node == null) return 0;
        int left = helper(node.left);
        int right = helper(node.right);
        dia = Math.max(left + right, dia);
        return 1 + Math.max(left, right);
    }


    /*
      The path sum of a path is the sum of the node's values in the path.

         A
        / \
       B   C
     If you're at A:

     You can take the full path B → A → C (this is allowed once).

     But when passing a value up to A’s parent, you can only choose one path: either A → B or A → C (whichever is higher).
     */
    int sum = Integer.MIN_VALUE;

    public int maxPathSum(Node rooot) {
        rhelper(root);
        return sum;
    }

    public int rhelper(Node node) {
        if (node == null) return 0;
        int left = Math.max(0, rhelper(node.left));
        int right = Math.max(0, rhelper(node.right));
        int cur = node.value + left + right;
        sum = Math.max(sum, cur);
        return node.value + Math.max(left, right);
    }

    //keep finding the height, if left-right >1 return -1 at any level
    public boolean isBalanced(Node root) {
        return rrhelper(root)!=-1;
    }
    public int rrhelper(Node root){
        if(root==null)return 0;//height helper

        int left=helper(root.left);
        if(left==-1)return -1;
        int right=helper(root.right);
        if(right==-1)return -1;

        //rule to check balanced or not
        if(Math.abs(left-right)>1)return -1;

        //height
        return Math.max(left,right)+1;
    }

    public boolean isSubtree(Node root, Node subRoot) {
        if(root==null)return false;
        if(subhelper(root,subRoot))return true;
        return isSubtree(root.left,subRoot)||isSubtree(root.right,subRoot);
    }

    //code to check whether 2 tree are same
    public boolean subhelper(Node root,Node sub){
        if(root==null&&sub==null)return true;
        if(root==null||sub==null)return false;
        if(root.value!=sub.value)return false;
        return subhelper(root.left,sub.left)&&subhelper(root.right,sub.right);
    }

}
