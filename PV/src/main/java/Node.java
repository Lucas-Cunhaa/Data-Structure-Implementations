public class Node {
    
    int value;
    int height;
    Color color;
    Node uncle;
    Node left;
    Node right;
    Node parent;
    
    Node(int v) {
        this.value = v;
        this.height = 0;
        this.color = Color.RED;
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

         return 1 + Math.max(this.left.getHeight(), this.right.getHeight());
    }

    public int getBlackHeight() {
        return getBlackHeight(this);
    }

    private int getBlackHeight(Node node) {
        if (node == null) return 1;

        int sum = node.isBlack() ? 1 : 0;

        return sum + (node.left == null ? getBlackHeight(node.right) : getBlackHeight(node.left));
    }   
    
    public boolean isRed() {
        return this.color == Color.RED;
    }

    public boolean isBlack() {
        return this.color == Color.BLACK;
    }

    public boolean isZigZag() {
        if(parent == null || isFolha()) return false; 

        return parent.value > value && hasOnlyRigth();
    }

    public boolean isZagZig() {
        if(parent == null || isFolha()) return false; 

        return parent.value < value && hasOnlyLeft();
    }
}