/**
 * Main class to initialize and run the DirectoryCommandProcessor.
 */
public class Main {
    public static void main(String[] args) {
        DirectoryTree tree = new DirectoryTree();
        DirectoryCommandProcessor processor = new DirectoryCommandProcessor(tree);

        String inputFilePath = args.length > 0 ? args[0] : null;
        processor.processCommands(inputFilePath);
    }
}
