import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

public class AVLTest {

    @Test
    public void testAddIterative() {
        AVL avl = new AVL();

        avl.add(50);
        avl.add(44);
        avl.add(30);
        avl.add(60);
        avl.add(55);
        avl.add(20);
        avl.add(25);
        avl.add(64);
        avl.add(72);

        // ArrayList<Integer> expectedBFSList = new ArrayList<>(Arrays.asList(44, 25,
        // 55, 20, 30, 50, 64, 60, 72));
        ArrayList<Integer> expectedBFSList = new ArrayList<>(Arrays.asList(50, 44, 60, 30, 55, 64, 20, 72, 25));

        assertEquals(expectedBFSList, avl.bfs());

        avl.add(99);
        avl.add(100);

        expectedBFSList = new ArrayList<>(Arrays.asList(44, 25, 64, 20, 30, 55, 99, 50, 60, 72, 100));
        assertEquals(expectedBFSList, avl.bfs());
    }

    @Test
    public void testLinearAdd() {
        AVL avl = new AVL();

        avl.add(1);
        avl.add(2);
        avl.add(3);
        avl.add(4);
        avl.add(5);
        avl.add(6);
        avl.add(7);

        ArrayList<Integer> expectedBFSList = new ArrayList<>(Arrays.asList(4, 2, 6, 1, 3, 5, 7));

        assertEquals(expectedBFSList, avl.bfs());
    }

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

}