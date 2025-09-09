import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class PV {
    
    private Node root;
    private int size;
    
    public PV () {
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

    public void bestRotation(Node node) {
        if(node.isRightPending())
            if(node.right.right != null) rotationLeft(node);
            else {
                rotationRight(node.right);
                rotationLeft(node);
            }
        else {
            if(node.left.left != null) rotationRight(node);
            else {
                rotationLeft(node.left);
                rotationRight(node);
            }
        }
        
    }

    public void rotationLeft(Node node) {
        Node prevLeft = node.right.left;
        node.right.left = node;

        if(root == node) 
            root = node.right;
        else if (node.parent.value > node.value)
             node.parent.left = node.right;
        else 
            node.parent.right = node.right;
        
        if (prevLeft != null)
            prevLeft.parent = node;

        node.right.parent = node.parent;
        node.parent = node.right;
        node.right = prevLeft;
        
    }

    public void rotationRight(Node node) {
        Node newRoot = node.left;
        node.left = newRoot.right;

        if(node.left != null)
            node.left.parent = node;

        if(root == node)
            root = newRoot;
        else if(node.parent.value > node.value) 
            node.parent.left = newRoot;
        else 
            node.parent.right = newRoot;
        
        newRoot.parent = node.parent;
        newRoot.right = node;
        node.parent = newRoot;
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
        return this.root;
    }
}

