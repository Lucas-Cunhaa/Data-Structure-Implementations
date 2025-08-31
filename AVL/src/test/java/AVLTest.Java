import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

public class AVLTest {

    @Test
    public void testAddIterative() {
        AVL avl = new AVL();

        avl.add(50);
        ArrayList<Integer> expectedBFSList = new ArrayList<>(Arrays.asList(50));
        assertEquals(expectedBFSList, avl.bfs());

        avl.add(44);
        avl.add(30);
        avl.add(60);
        avl.add(55);
        avl.add(20);
        avl.add(25);
        avl.add(64);
        avl.add(72);
        expectedBFSList = new ArrayList<>(Arrays.asList(50, 44, 60, 30, 55, 64, 20, 72, 25));
        //expectedBFSList = new ArrayList<>(Arrays.asList(44, 25, 55, 20, 30, 50, 64, 60, 72));
        assertEquals(expectedBFSList, avl.bfs());

        // avl.add(99);
        // avl.add(100);

        // expectedBFSList = new ArrayList<>(Arrays.asList(44, 25, 64, 20, 30, 55, 99, 50, 60, 72, 100));
        // assertEquals(expectedBFSList, avl.bfs());
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
    public void testRotationRight() {
        AVL avl = new AVL();

        avl.add(30);
        avl.add(40);
        avl.add(50);

        avl.rotationRight(avl.getRoot());

        ArrayList<Integer> expectedBFS = new ArrayList<>(Arrays.asList(30, 40, 50));
        assertEquals(expectedBFS, avl.bfs());

        assertTrue(avl.getRoot().isBalanced());
    }
}