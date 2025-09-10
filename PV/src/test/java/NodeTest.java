import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class NodeTest {

    @Test
    public void testIsFolha() {
        Node leaf = new Node(10);
        assertTrue(leaf.isFolha());

        Node withLeft = new Node(20);
        withLeft.left = new Node(10);
        assertFalse(withLeft.isFolha());
    }

    @Test
    public void testHasOnlyLeft() {
        Node node = new Node(20);
        node.left = new Node(10);

        assertTrue(node.hasOnlyLeft());
        assertFalse(node.hasOnlyRigth());
    }

    @Test
    public void testHasOnlyRight() {
        Node node = new Node(20);
        node.right = new Node(30);

        assertTrue(node.hasOnlyRigth());
        assertFalse(node.hasOnlyLeft());
    }

    @Test
    public void testGetHeight() {
        Node root = new Node(10);
        Node left = new Node(5);
        Node right = new Node(15);
        Node rightRight = new Node(20);

        root.left = left;
        root.right = right;
        right.right = rightRight;

        assertEquals(2, root.getHeight());
        assertEquals(0, left.getHeight());
        assertEquals(1, right.getHeight());
        assertEquals(0, rightRight.getHeight());
    }

    @Test
    public void testIsRedAndBlack() {
        Node redNode = new Node(5);
        redNode.color = Color.RED;

        Node blackNode = new Node(10);
        blackNode.color = Color.BLACK;

        assertTrue(redNode.isRed());
        assertFalse(redNode.isBlack());

        assertTrue(blackNode.isBlack());
        assertFalse(blackNode.isRed());
    }

    @Test
    public void testGetBlackHeight_Simple() {
        Node blackRoot = new Node(10);
        blackRoot.color = Color.BLACK;

        Node blackLeft = new Node(5);
        blackLeft.color = Color.BLACK;

        Node redRight = new Node(15);
        redRight.color = Color.RED;

        blackRoot.left = blackLeft;
        blackRoot.right = redRight;

        assertEquals(2, blackRoot.getBlackHeight());
    }

    @Test
    public void testGetBlackHeight_Unbalanced() {
        Node root = new Node(10);
        root.color = Color.BLACK;

        Node left = new Node(5);
        left.color = Color.RED;

        Node leftLeft = new Node(3);
        leftLeft.color = Color.BLACK;

        root.left = left;
        left.left = leftLeft;

        assertEquals(2, root.getBlackHeight());
    }

    @Test
public void testHeight_Folha() {
    Node leaf = new Node(10);
    assertEquals(0, leaf.getHeight());
}

@Test
public void testHeight_OneLeftChild() {
    Node root = new Node(10);
    root.left = new Node(5);
    assertEquals(1, root.getHeight());
}

@Test
public void testHeight_OneRightChild() {
    Node root = new Node(10);
    root.right = new Node(15);
    assertEquals(1, root.getHeight());
}

@Test
public void testHeight_TwoChildrenSameLevel() {
    Node root = new Node(10);
    root.left = new Node(5);
    root.right = new Node(15);
    assertEquals(1, root.getHeight());
}

@Test
public void testHeight_LeftDeeper() {
    Node root = new Node(10);
    Node left = new Node(5);
    Node leftLeft = new Node(2);

    root.left = left;
    left.left = leftLeft;

    assertEquals(2, root.getHeight());
    assertEquals(1, left.getHeight());
    assertEquals(0, leftLeft.getHeight());
}

@Test
public void testHeight_RightDeeper() {
    Node root = new Node(10);
    Node right = new Node(15);
    Node rightRight = new Node(20);

    root.right = right;
    right.right = rightRight;

    assertEquals(2, root.getHeight());
    assertEquals(1, right.getHeight());
    assertEquals(0, rightRight.getHeight());
}

@Test
public void testHeight_BalancedThreeLevels() {
    Node root = new Node(10);
    root.left = new Node(5);
    root.right = new Node(15);
    root.left.left = new Node(2);
    root.right.right = new Node(20);

    assertEquals(2, root.getHeight());
}

@Test
public void testHeight_UnbalancedLeftOnly() {
    Node root = new Node(10);
    root.left = new Node(9);
    root.left.left = new Node(8);
    root.left.left.left = new Node(7);
    root.left.left.left.left = new Node(6);

    assertEquals(4, root.getHeight());
}

}
