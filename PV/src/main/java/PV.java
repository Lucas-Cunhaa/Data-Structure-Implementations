public class PV {
    Node root;

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
                    if(aux.left == null)
                        aux.left = newNode;
                    else 
                        aux = aux.left;
                } else  {
                    if(aux.right == null)
                        aux.right = newNode;
                    else 
                        aux = aux.right;
                }
            }

            newNode.parent = aux;
            newNode.uncle = aux.parent.value > aux.value ? aux.parent.right : aux.parent.left;
        }
    }

    public void checkNode(Node node) {
        if(node.parent == null && node.isRed()) node.color = Color.BLACK;
        else if(node.parent.isBlack()) return;

        if(node.isRed() && node.parent.isRed() && (node.uncle != null && node.uncle.isRed())) {
            node.parent.parent.color = Color.RED;

            node.parent.color = Color.BLACK;
            node.uncle.color = Color.BLACK;
            checkNode(node.parent.parent);

        } else if (node.isRed() && node.parent.isRed() && (node.uncle == null || node.uncle.isBlack())) {

        }

    }

    public void rotationRight (Node node) {
        Node nextRoot = node.left;
        node.left = nextRoot.right;

        

    }

    public void rotationLeft (Node node) {

    }


}