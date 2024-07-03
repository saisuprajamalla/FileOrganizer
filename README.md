# Directory Tree Processor Application

## Overview

This application allows users to create, move, delete, and list directories in a simulated directory tree structure. The commands are processed from an input file or standard input.

## Requirements

 For running the application: Java Runtime Environment (JRE) 11 or higher
  1. check if JRE is installed using "java -version".
  2. if not installed
     follow https://medium.com/@kirebyte/using-homebrew-to-install-java-jdk11-on-macos-2021-4a90aa276f1c and install it for MAC.
     or follow https://www.geeksforgeeks.org/how-to-download-and-install-java-for-64-bit-machine/ for windows.

### Note:
- Compiled code is already uploaded.
- "junit-platform-console-standalone-1.8.1.jar" is added to the repo to run unit tests from terminal.

### Key Considerations

- Case Sensitivity: Directory names are case-sensitive, following typical file system behavior. This decision ensures that directories with the same name but different cases are treated as distinct entities.

- Indentation: The LIST command outputs the directory structure with proper indentation to represent the hierarchy. The root directory is printed without indentation, while subsequent levels are indented for clarity.

## Instructions

### Running the Application with Compiled Classes (Needs JRE)

## Run the Application from Terminal:
1. Clone the repo from terminal. "git clone https://github.com/saisuprajamalla/FileOrganizer.git"
2. cd FileOrganizer/src
3. Run "java -jar DirectoryTreeApp.jar input.txt" command
4. You will see the output displayed as shown below.

<img width="591" alt="Screenshot 2024-07-03 at 2 01 45 AM" src="https://github.com/saisuprajamalla/FileOrganizer/assets/32082372/2fb9c20b-c95c-4d45-b2cd-ff962dbe6226">

## Run Unit tests
1. go back to FileOrganizer folder ( command: cd ..)
2. Run "java -jar junit-platform-console-standalone-1.8.1.jar --class-path out/production/FileOrganizer --scan-class-path"
3. You should see output as shown below

<img width="1207" alt="Screenshot 2024-07-03 at 2 00 41 AM" src="https://github.com/saisuprajamalla/FileOrganizer/assets/32082372/02cce2e2-f0be-4e53-8186-905cbee5958f">


## Decisions Made and Reasoning

### Language and Libraries

- Java: Chosen for its robustness and widespread use in enterprise environments.
- JUnit 5: Selected for unit testing due to its modern features and comprehensive testing capabilities.

### Design Decisions

1. DirectoryNode Class: Represents a node in the directory tree. Each node has a name and a map of children nodes. This design allows for efficient traversal and manipulation of the directory structure.

2. DirectoryTree Class: Manages the overall directory structure and provides methods to create, move, delete, and list directories. This class encapsulates the core logic and ensures a clear separation of concerns.

3. DirectoryCommandProcessor Class: This class is responsible for processing user commands and interacting with the DirectoryTree. It reads commands from an input file or standard input and executes them accordingly.
   
4. Main Class: Acts as the entry point of the application. It initializes the DirectoryCommandProcessor and processes the commands provided in the input file or standard input.
   
5. Error Handling: The implementation includes basic error handling for invalid commands and non-existent directories. Custom error messages are provided to help users understand what went wrong.


