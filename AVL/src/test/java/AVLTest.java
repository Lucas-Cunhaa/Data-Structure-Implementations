import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

public class AVLTest {

    @Test
    public void testAddSimple() {
        AVL avl = new AVL();

        avl.add(10);
        avl.add(20);
        avl.add(5);

        ArrayList<Integer> expectedBFS = new ArrayList<>(Arrays.asList(10, 5, 20));
        assertEquals(expectedBFS, avl.bfs());
    }

    @Test
    public void testAddMultiple() {
        AVL avl = new AVL();

        avl.add(30);
        avl.add(20);
        avl.add(40);
        avl.add(10);
        avl.add(25);

        ArrayList<Integer> expectedBFS = new ArrayList<>(Arrays.asList(30, 20, 40, 10, 25));
        assertEquals(expectedBFS, avl.bfs());
    }

    @Test
    public void testRotationLeft() {
        AVL avl = new AVL();

        avl.add(30);
        avl.add(40);
        avl.add(50);

        avl.rotationLeft(avl.getRoot());

        ArrayList<Integer> expectedBFS = new ArrayList<>(Arrays.asList(40, 30, 50));
        assertEquals(expectedBFS, avl.bfs());

        assertEquals(0, avl.getRoot().getBalance());
        assertEquals(0, avl.getRoot().getBalance());

        assertTrue(avl.getRoot().isBalanced());
    }

    @Test
    public void testRotationRightSimple() {
        AVL avl = new AVL();

        avl.add(30);
        avl.add(20);
        avl.add(10); // Desbalanceia para a esquerda

        avl.rotationRight(avl.getRoot());

        ArrayList<Integer> expectedBFS = new ArrayList<>(Arrays.asList(20, 10, 30));
        assertEquals(expectedBFS, avl.bfs());

        assertEquals(0, avl.getRoot().getBalance());
        assertTrue(avl.getRoot().isBalanced());
    }

    @Test
    public void testRotationRightWithSubtree() {
        AVL avl = new AVL();

        avl.add(50);
        avl.add(30);
        avl.add(20);
        avl.add(40);

        avl.rotationRight(avl.getRoot());

        ArrayList<Integer> expectedBFS = new ArrayList<>(Arrays.asList(30, 20, 50, 40));
        assertEquals(expectedBFS, avl.bfs());

        assertTrue(avl.getRoot().isBalanced());
    }

    @Test
    public void testRotationRightNotRoot() {
        AVL avl = new AVL();

        avl.add(60);
        avl.add(40);
        avl.add(20);
        avl.add(10);

        avl.rotationRight(avl.getRoot().left); // Rotaciona 40

        ArrayList<Integer> expectedBFS = new ArrayList<>(Arrays.asList(60, 20, 10, 40));
        assertEquals(expectedBFS, avl.bfs());
    }

    @Test
    public void testRotationRightComplex() {
        AVL avl = new AVL();

        avl.add(100);
        avl.add(80);
        avl.add(120);
        avl.add(70);
        avl.add(90);
        avl.add(60);

        avl.rotationRight(avl.getRoot().left); // Rotaciona 80

        ArrayList<Integer> expectedBFS = new ArrayList<>(Arrays.asList(100, 70, 120, 60, 80, 90));
        assertEquals(expectedBFS, avl.bfs());
    }

    @Test
    public void testZigZagRotation() {
        AVL avl = new AVL();

        // Insere de forma a causar rotação Zig-Zag
        avl.add(50);
        avl.add(30);
        avl.add(40); // esse nó força rotação dupla

        // O 40 deve virar a raiz após a rotação
        avl.bestRotation(avl.getRoot());

        assertEquals(40, avl.getRoot().value);

        // Estrutura esperada:
        // 40
        // / \
        // 30 50
        assertEquals(30, avl.getRoot().left.value);
        assertEquals(50, avl.getRoot().right.value);
    }

    @Test
    public void testZagZigRotation() {
        AVL avl = new AVL();

        // Insere de forma a causar rotação Zag-Zig
        avl.add(30);
        avl.add(50);
        avl.add(40); // esse nó força rotação dupla

        avl.bestRotation(avl.getRoot());
        // O 40 deve virar a raiz após a rotação
        assertEquals(40, avl.getRoot().value);

        // Estrutura esperada:
        // 40
        // / \
        // 30 50
        assertEquals(30, avl.getRoot().left.value);
        assertEquals(50, avl.getRoot().right.value);
    }

      @Test
    public void testEmptyTreeIsAvl() {
        AVL avl = new AVL();
        assertTrue(avl.isAvl(), "Árvore vazia deve ser considerada AVL");
    }

    @Test
    public void testSingleNodeIsAvl() {
        AVL avl = new AVL();
        avl.add(10);
        assertTrue(avl.isAvl(), "Árvore com um nó deve ser AVL");
    }

    @Test
    public void testBalancedTreeIsAvl() {
        AVL avl = new AVL();
        avl.add(20);
        avl.add(10);
        avl.add(30);

        assertTrue(avl.isAvl(), "Árvore perfeitamente balanceada deve ser AVL");
    }

    @Test
    public void testUnbalancedTreeIsNotAvl() {
        AVL avl = new AVL();

        // Inserção sequencial sem rotações
        avl.add(10);
        avl.add(20);
        avl.add(30);

        // Se as rotações não forem chamadas automaticamente,
        // a árvore ficará desbalanceada
        assertFalse(avl.isAvl(), "Árvore degenerada (linha) não deve ser AVL");
    }

    @Test
    public void testTreeAfterRotationsIsAvl() {
        AVL avl = new AVL();

        avl.add(30);
        avl.add(50);
        avl.add(40); // força rotação

        avl.bestRotation(avl.getRoot());

        assertTrue(avl.isAvl(), "Após rotação, a árvore deve ser AVL");
    }

    @Test
    public void testComplexBalancedTree() {
        AVL avl = new AVL();

        avl.add(50);
        avl.add(30);
        avl.add(70);
        avl.add(20);
        avl.add(40);
        avl.add(60);
        avl.add(80);

        assertTrue(avl.isAvl(), "Árvore completa deve ser AVL");
    }

    

}