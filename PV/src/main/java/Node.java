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
        if(isFolha()) return 1;

        if(this.right == null) 
            return this.left.isBlack() ?  1 + this.left.getBlackHeight() : 0 + this.left.getBlackHeight();

        if(this.left == null)
            return this.right.isBlack() ?  1 + this.right.getBlackHeight() : 0 + this.right.getBlackHeight();

        return (this.right.isBlack() ? 1 : this.left.isBlack() ? 1 : 0) + Math.max(this.right.getBlackHeight(), this.left.getBlackHeight());
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