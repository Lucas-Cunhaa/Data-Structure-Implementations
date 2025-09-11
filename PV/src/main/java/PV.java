public class PV {
    public Node root;

    public PV(Node root) {
        this.root = root;
    }

    public void addValue(int value) {
        Node newNode = new Node(value);

        if(this.root == null) root = newNode;
        else {
            Node aux = root;

            while(aux != null) {
                if(aux.value > value) {
                    if(aux.left == null) {
                        if(aux.parent != null)
                            if(aux.parent.value > aux.value)
                                newNode.uncle = aux.parent.right;
                            else 
                                newNode.uncle = aux.left;
                        aux.left = newNode;
                        break;
                    }
                    aux = aux.left;

                } else  {
                    if(aux.right == null) {
                         if(aux.parent != null)
                            if(aux.parent.value > aux.value)
                                newNode.uncle = aux.parent.right;
                            else 
                                newNode.uncle = aux.parent.left;
                        aux.right = newNode;
                        break;
                    }
                    aux = aux.right;
                }
            }
            
            newNode.parent = aux;
        }

        checkNode(newNode);
    }

    public void checkNode(Node node) {
        if(node == root && node.isRed()) {
            node.color = Color.BLACK;
            return;
        }

        if(node.parent.isBlack()) return;

        if(node.isRed() && node.parent.isRed() && (node.uncle != null && node.uncle.isRed())) {
            node.parent.parent.color = Color.RED;

            node.parent.color = Color.BLACK;
            node.uncle.color = Color.BLACK;
            checkNode(node.parent.parent);
        } 

        if(node.isRed() && node.parent.isRed() && (node.uncle == null || node.uncle.isBlack())) {
            if(node.parent.isZigZag()) {
                rotationLeft(node.parent);
                node = node.left;
            }
            if(node.parent.isZagZig()) {
                rotationRight(node.parent);
                node = node.right;
            } 
        }

        if(node.isRed() && node.parent.isRed()) {
            node.parent.color = Color.BLACK;
            node.parent.parent.color = Color.RED;

            if(node.parent.hasOnlyLeft()) rotationRight(node.parent.parent);
            else rotationLeft(node.parent.parent);
        }
    }

    public void rotationRight (Node node) {
        Node newRoot = node.left;
        node.left = newRoot.right;

        if(node.parent == null)
            this.root = newRoot;
        else if(node.parent.value > node.value)
            node.parent.left = newRoot;
        else 
            node.parent.right = newRoot;
        
        newRoot.parent = node.parent;
        node.parent = newRoot;
        newRoot.right = node;

        if(node.left != null) node.left.parent = node;
    }

    public void rotationLeft (Node node) {
        Node newRoot = node.right;
        node.right = newRoot.left;

        if(node.parent == null)
            this.root = newRoot;
        else if(node.parent.value > node.value)
            node.parent.left = newRoot;
        else 
            node.parent.right = newRoot;
        
        newRoot.parent = node.parent;
        node.parent = newRoot;
        newRoot.left = node;

        if(node.right != null) 
            node.right.parent = node;
    }

    public boolean isPv(Node root) {
       if(root == null) return true;

       if(root.isRed()) return false;

       return checkIsPvByColor(root) && (checkPVByHeigth(root) != -1);
    }
    
    private boolean checkIsPvByColor(Node current) {
        if(current == null) return true; 

        if(current.isRed()) {
            if((current.left != null && current.left.isRed()) || current.right != null && current.right.isRed()) return false;
        }

        return checkIsPvByColor(current.right) && checkIsPvByColor(current.left);
    }

    private int checkPVByHeigth(Node current) {
        if (current == null) return 1;

        int leftHeigth = checkPVByHeigth(current.left);
        int rigthHeigth = checkPVByHeigth(current.right);

        if(leftHeigth == -1 || rigthHeigth == -1 || leftHeigth != rigthHeigth) return -1;

        return leftHeigth + (current.isBlack() ? 1 : 0);
    }

    public static void main(String[] args) {
        PV tree = new PV(null);
        tree.addValue(10); // raiz
        tree.addValue(15); // filho esquerdo
        tree.addValue(12); // tio
    }


}