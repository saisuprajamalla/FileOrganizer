import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class DirectoryTreeTest {

    private DirectoryTree tree;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        tree = new DirectoryTree();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testCreate() {
        tree.create("fruits");
        DirectoryNode node = getNode(tree, "fruits");
        assertNotNull(node);
        assertEquals("fruits", node.name);
    }

    @Test
    public void testMove() {
        tree.create("fruits");
        tree.create("vegetables");
        tree.move("fruits", "vegetables");
        DirectoryNode node = getNode(tree, "vegetables/fruits");
        assertNotNull(node);
        assertEquals("fruits", node.name);
    }

    @Test
    public void testDelete() {
        tree.create("fruits");
        tree.delete("fruits");
        DirectoryNode node = getNode(tree, "fruits");
        assertNull(node);
    }

    @Test
    public void testList() {
        tree.create("fruits");
        tree.create("vegetables");
        tree.create("grains");
        tree.create("fruits/apples");
        tree.create("fruits/apples/fuji");

        outContent.reset();
        tree.list();
        String expectedOutput = "LIST\n" +
                "fruits\n" +
                " apples\n" +
                "  fuji\n" +
                "grains\n" +
                "vegetables\n";
        assertEquals(expectedOutput, outContent.toString().trim() + "\n");
    }

    // Helper method to access the private getNode method in DirectoryTree
    private DirectoryNode getNode(DirectoryTree tree, String path) {
        try {
            java.lang.reflect.Method method = DirectoryTree.class.getDeclaredMethod("getNode", String.class);
            method.setAccessible(true);
            return (DirectoryNode) method.invoke(tree, path);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
