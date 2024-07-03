import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * DirectoryCommandProcessor handles the processing of directory commands.
 */
public class DirectoryCommandProcessor {
    private static final Logger LOGGER = Logger.getLogger(DirectoryCommandProcessor.class.getName());
    private final DirectoryTree tree;

    /**
     * Constructs a DirectoryCommandProcessor with the specified DirectoryTree.
     *
     * @param tree the DirectoryTree to operate on
     */
    public DirectoryCommandProcessor(DirectoryTree tree) {
        this.tree = tree;
    }

    /**
     * Processes commands from the specified input file or standard input.
     *
     * @param inputFilePath the path to the input file containing commands, or null to read from standard input
     */
    public void processCommands(String inputFilePath) {
        BufferedReader reader = null;
        try {
            if (inputFilePath != null) {
                reader = new BufferedReader(new FileReader(inputFilePath));
            } else {
                reader = new BufferedReader(new InputStreamReader(System.in));
            }

            String line;
            while ((line = reader.readLine()) != null) {
                processCommand(line);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An IO exception occurred", e);
        } finally {
            closeReader(reader);
        }
    }

    /**
     * Processes a single command line.
     *
     * @param commandLine the command line to process
     */
    private void processCommand(String commandLine) {
        String[] commandParts = commandLine.split(" ");
        String command = commandParts[0];
        switch (command) {
            case "CREATE":
                tree.create(commandParts[1]);
                break;
            case "MOVE":
                tree.move(commandParts[1], commandParts[2]);
                break;
            case "DELETE":
                tree.delete(commandParts[1]);
                break;
            case "LIST":
                tree.list();
                break;
            default:
                System.out.println("Error: Unknown command");
                break;
        }
    }

    /**
     * Closes the BufferedReader, logging any IOException that occurs.
     *
     * @param reader the BufferedReader to close
     */
    private void closeReader(BufferedReader reader) {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to close reader", e);
        }
    }
}