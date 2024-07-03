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

    public DirectoryCommandProcessor(DirectoryTree tree) {
        this.tree = tree;
    }

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
                System.out.println("LIST");
                tree.list();
                break;
            default:
                System.out.println("Error: Unknown command");
                break;
        }
    }

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
