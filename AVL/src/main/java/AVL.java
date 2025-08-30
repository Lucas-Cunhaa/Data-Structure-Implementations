import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class AVL {

    private Node root;
    private int size;
    
    public AVL () {
        this.size = -1;
    }

    public void add(int value) {
        Node newNode = new Node(value);

        if(root == null) root = newNode;
        else {
            Node aux = root;

            while(aux != null) {
                if(value > aux.value) {
                    if(aux.right == null) {
                        aux.right = newNode;
                        newNode.parent = aux;
                        break;
                    }
                    aux = aux.right;
                }

                else {
                    if(aux.left == null) {
                        aux.left = newNode;
                        newNode.parent = aux;
                        break;
                    }
                    aux = aux.left;
                }
            }
        }
    }

    public void rotationRight(Node node) {
        Node prevRight = node.right.right;
        node.right.right = node;

        if(root == node) 
            root = node.right;
        else if (node.parent.value > node.value)
             node.parent.left = node.right;
        else 
            node.parent.right = node.right;
        
        node.right.parent = node.parent;
        node.parent = node.right;
        node.right = prevRight;
    }

    public ArrayList<Integer> bfs() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Queue<Node> queue = new LinkedList<Node>();

        if(root == null) return result;

        queue.add(root);
        while(!queue.isEmpty()) {
            Node current = queue.poll();

            result.add(current.value);

            if(current.left != null)
                queue.add(current.left);
            if(current.right != null)
                queue.add(current.right);
        }

        return result;
    }

    public Node getRoot() {
        return root;
    }
}

class Node {
    
    int value;
    int height;
    Node left;
    Node right;
    Node parent;
    
    Node(int v) {
        this.value = v;
        this.height = 0;
    }

    public boolean isFolha() {
        return left == null && right == null;
    }

    public boolean hasOnlyLeft() {
        return left != null && right == null;
    }

    public boolean hasOnlyRigth() {
        return left == null && right != null;
    }

    public int getHeight() {
         if(isFolha()) return 0;

         if(this.right == null) return 1 + this.left.getHeight();
         
         else if(this.left == null) return 1 + this.right.getHeight();

         return Math.max(this.left.getHeight(), this.right.getHeight());
    }

    public int getBalance() {
        int leftHeigth = this.left == null ? -1 : this.left.getHeight();
        int rightHeigth = this.right == null ? -1 : this.right.getHeight();

        return leftHeigth - rightHeigth;
    }

    public boolean isLeftPending() {
        return getBalance() == 1;
    }

    public boolean isRightPending() {
        return getBalance() == -1;
    }

    public boolean isBalanced() {
        return Math.abs(getBalance()) <= 1;
    }

    
}