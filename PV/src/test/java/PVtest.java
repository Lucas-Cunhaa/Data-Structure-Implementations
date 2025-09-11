import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PVtest {

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

    @Test
    public void testCase1_RootRedBecomesBlack() {
        PV tree = new PV(null);
        tree.addValue(10);

        assertTrue(tree.root.isBlack(), "Raiz vermelha deve ser pintada de preto");
    }

    @Test
    public void testCase2_ParentBlackNoChange() {
        PV tree = new PV(null);
        tree.addValue(10); // raiz preta
        tree.addValue(5); // filho vermelho, pai preto

        assertTrue(tree.root.isBlack());
        assertTrue(tree.root.left.isRed(), "Filho continua vermelho pois o pai é preto");
    }

    @Test
    public void testCase3_RecolorWhenUncleRed() {
        PV tree = new PV(null);
        tree.addValue(10); // raiz
        tree.addValue(5); // filho esquerdo
        tree.addValue(15); // tio
         Node root = tree.root;

        assertTrue(root.isBlack(), "Raiz deve permanecer preta");
        assertFalse(root.left.isBlack(), "Pai deve ser recolorido para preto");
        assertFalse(root.right.isBlack(), "Tio deve ser recolorido para preto");

        tree.addValue(1); // insere causando conflito

        root = tree.root;
        assertTrue(root.isBlack(), "Raiz deve permanecer preta");
        assertTrue(root.left.isBlack(), "Pai deve ser recolorido para preto");
        assertTrue(root.right.isBlack(), "Tio deve ser recolorido para preto");
    }

    @Test
    public void testCase4_ZigZagRotation() {
        PV tree = new PV(null);
        tree.addValue(10);
        tree.addValue(15);
        tree.addValue(12); // cria zig-zag (esquerda-direita)

        assertEquals(12, tree.root.value, "Zig-zag: 12 deve ser a nova raiz após rotação");
        assertEquals(10, tree.root.left.value);
        assertEquals(15, tree.root.right.value);
    }

    @Test
    public void testCase5_ZigZigRotationLeft() {
        PV tree = new PV(null);
        tree.addValue(10);
        tree.addValue(15);
        tree.addValue(20); // cria zig-zig (direita-direita)

        assertEquals(15, tree.root.value, "Após rotação, 15 deve ser a raiz");
        assertEquals(10, tree.root.left.value);
        assertEquals(20, tree.root.right.value);
    }

    @Test
    public void testCase5_ZigZigRotationRight() {
        PV tree = new PV(null);
        tree.addValue(10);
        tree.addValue(5);
        tree.addValue(2); // cria zig-zig (esquerda-esquerda)

        assertEquals(5, tree.root.value, "Após rotação, 5 deve ser a raiz");
        assertEquals(2, tree.root.left.value);
        assertEquals(10, tree.root.right.value);
    }

    @Test
public void test_ZigZagRotation_RightLeft() {
    PV tree = new PV(null);
    tree.addValue(20);
    tree.addValue(10);
    tree.addValue(13);

    assertEquals(13, tree.root.value, "Após rotação, 13 deve ser a nova raiz");
    assertEquals(10, tree.root.left.value);
    assertEquals(20, tree.root.right.value);
    assertTrue(tree.root.isBlack(), "A nova raiz deve ser preta");
    assertTrue(tree.root.left.isRed(), "O filho esquerdo deve ser vermelho");
    assertTrue(tree.root.right.isRed(), "O filho direito deve ser vermelho");
}

@Test
public void test_MultiLevelInsertion() {
    PV tree = new PV(null);
    tree.addValue(10);
    tree.addValue(5);
    tree.addValue(15);
    tree.addValue(2);
    tree.addValue(7);
    tree.addValue(12);
    tree.addValue(18);

    assertTrue(tree.root.isBlack(), "A raiz deve ser preta");
    assertTrue(tree.root.left.isBlack(), "O filho esquerdo da raiz deve ser preto");
    assertTrue(tree.root.right.isBlack(), "O filho direito da raiz deve ser preto");
    assertTrue(tree.root.left.left.isRed(), "O nó 2 deve ser vermelho");
    assertTrue(tree.root.left.right.isRed(), "O nó 7 deve ser vermelho");
    assertTrue(tree.root.right.left.isRed(), "O nó 12 deve ser vermelho");
    assertTrue(tree.root.right.right.isRed(), "O nó 18 deve ser vermelho");
}

 @Test
    public void testEmptyTree() {
        PV tree = new PV(null);
        assertTrue(tree.isPv(null), "Árvore vazia deve ser válida");
    }

    @Test
    public void testSingleBlackNode() {
        Node root = new Node(10);
        root.color = Color.BLACK;
        PV tree = new PV(root);

        assertTrue(tree.isPv(root), "Raiz preta sozinha deve ser válida");
    }

    @Test
    public void testSingleRedNode() {
        Node root = new Node(10);
        root.color = Color.BLACK;
        PV tree = new PV(root);

        assertTrue(tree.isPv(root), "Um único nó vermelho isolado também é válido");
    }

    @Test
    public void testRedParentWithRedLeftChild() {
        Node root = new Node(10);
        root.color = Color.RED;
        root.left = new Node(5);
        root.left.color = Color.RED;

        PV tree = new PV(root);

        assertFalse(tree.isPv(root), "Pai vermelho com filho esquerdo vermelho deve ser inválido");
    }

    @Test
    public void testRedParentWithRedRightChild() {
        Node root = new Node(10);
        root.color = Color.RED;
        root.right = new Node(15);
        root.right.color = Color.RED;

        PV tree = new PV(root);

        assertFalse(tree.isPv(root), "Pai vermelho com filho direito vermelho deve ser inválido");
    }

    @Test
    public void testBlackParentWithRedChildren() {
        Node root = new Node(10);
        root.color = Color.BLACK;
        root.left = new Node(5);
        root.right = new Node(15);
        root.left.color = Color.RED;
        root.right.color = Color.RED;

        PV tree = new PV(root);

        assertTrue(tree.isPv(root), "Pai preto pode ter filhos vermelhos");
    }

    @Test
    public void testNestedRedNodesDeepInTree() {
        Node root = new Node(10);
        root.color = Color.BLACK;

        Node left = new Node(5);
        left.color = Color.RED;

        Node leftLeft = new Node(3);
        leftLeft.color = Color.RED;

        root.left = left;
        left.left = leftLeft;

        PV tree = new PV(root);

        assertFalse(tree.isPv(root), "Vermelho com neto vermelho deve ser inválido");
    }

}
