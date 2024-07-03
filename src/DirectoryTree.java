/**
 * Represents a directory tree with operations to create, move, delete, and list directories.
 */
public class DirectoryTree {
    private final DirectoryNode root;

    /**
     * Constructs a DirectoryTree with a root node.
     */
    public DirectoryTree() {
        this.root = new DirectoryNode("");
    }

    /**
     * Creates a directory at the specified path.
     *
     * @param path the path where the directory should be created
     */
    public void create(String path) {
        String[] dirs = path.split("/");
        DirectoryNode current = root;
        for (String dir : dirs) {
            current.children.putIfAbsent(dir, new DirectoryNode(dir));
            current = current.children.get(dir);
        }
        System.out.println("CREATE " + path);
    }

    /**
     * Moves a directory from source path to destination path.
     *
     * @param src  the source path
     * @param dest the destination path
     */
    public void move(String src, String dest) {
        DirectoryNode srcNode = getNode(src);
        DirectoryNode destNode = getNode(dest);

        if (srcNode == null) {
            System.out.println("Error: Source directory does not exist");
            return;
        }

        if (destNode == null) {
            System.out.println("Error: Destination directory does not exist");
            return;
        }

        String srcDirName = src.substring(src.lastIndexOf("/") + 1);
        String parentPath = getParentPath(src);
        DirectoryNode parentNode = getNode(parentPath);
        if (parentNode == null || !parentNode.children.containsKey(srcDirName)) {
            System.out.println("Error: Source directory does not exist");
            return;
        }

        parentNode.children.remove(srcDirName);
        destNode.children.put(srcDirName, srcNode);
        System.out.println("MOVE " + src + " " + dest);
    }

    /**
     * Deletes the directory at the specified path.
     *
     * @param path the path of the directory to delete
     */
    public void delete(String path) {
        String dirName = path.substring(path.lastIndexOf("/") + 1);
        String parentPath = getParentPath(path);
        DirectoryNode parentNode = getNode(parentPath);
        if (parentNode == null || !parentNode.children.containsKey(dirName)) {
            System.out.println("DELETE " + path);
            System.out.println("Cannot delete " + path + " - " + parentPath + " does not exist");
            return;
        }

        parentNode.children.remove(dirName);
        System.out.println("DELETE " + path);
    }

    /**
     * Lists all directories in the tree.
     */
    public void list() {
        System.out.println("LIST");
        listHelper(root, 0);
    }

    /**
     * Helper method to list directories recursively.
     *
     * @param node  the current node
     * @param depth the current depth in the tree
     */
    private void listHelper(DirectoryNode node, int depth) {
        if (depth > 0) {  // Skip indentation for the root node
            for (int i = 0; i < depth - 1; i++) {
                System.out.print(" ");
            }
            System.out.println(node.name);
        } else {
            System.out.print(node.name);  // Root node printed without indentation
        }
        node.children.keySet().stream().sorted().forEach(key -> listHelper(node.children.get(key), depth + 1));
    }

    /**
     * Retrieves the DirectoryNode for the specified path.
     *
     * @param path the path to retrieve
     * @return the DirectoryNode at the specified path, or null if it does not exist
     */
    private DirectoryNode getNode(String path) {
        if (path.isEmpty()) {
            return root;
        }
        String[] dirs = path.split("/");
        DirectoryNode current = root;
        for (String dir : dirs) {
            if (!current.children.containsKey(dir)) {
                return null;
            }
            current = current.children.get(dir);
        }
        return current;
    }

    /**
     * Retrieves the parent path of the specified path.
     *
     * @param path the path to get the parent of
     * @return the parent path
     */
    private String getParentPath(String path) {
        int lastSlash = path.lastIndexOf("/");
        return (lastSlash == -1) ? "" : path.substring(0, lastSlash);
    }
}