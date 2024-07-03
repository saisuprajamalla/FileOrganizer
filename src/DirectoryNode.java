import java.util.Map;
import java.util.TreeMap;

/**
 * Represents a node in the directory tree.
 */
public class DirectoryNode {
    String name;
    Map<String, DirectoryNode> children;

    /**
     * Constructs a DirectoryNode with the specified name.
     *
     * @param name the name of the directory
     */
    DirectoryNode(String name) {
        this.name = name;
        this.children = new TreeMap<>();
    }
}
