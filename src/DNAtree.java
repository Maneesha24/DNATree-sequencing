import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * This DNAtree class implements a “DNAtree” for searching for matching
 * sequences in a sequence database
 * 
 * @author maneeshavenigalla maneesha24@vt.edu
 * @version 1.0
 */
public class DNAtree {

    private static CustomTreeNode root;

    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     *         assigns the flyweightnode instance
     *         to the root
     */
    public DNAtree() {
        root = FlyweightNode.getFlyweightInstance();
    }


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     * @param args
     *            containing file name
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {

        DNAtree customTree = new DNAtree();
        Scanner scannedFile = null;

        // Step 1 - read the input file
        scannedFile = readFile(args[0]);
        while (scannedFile.hasNextLine()) {
            String line = scannedFile.nextLine();
            if (!line.isEmpty() && line.length() > 2) {
                // Step 2 - remove any spaces
                String trimmedLine = trimSpaces(line);
                if (trimmedLine.length() > 1) {
                    executeCommands(trimmedLine, customTree);
                }
            }
        }

    }


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     *         executes the commands from the
     *         file
     * @param line
     *            line to be read
     * @param customTree
     *            the above initialized tree
     */
    static void executeCommands(String line, DNAtree customTree) {
        // Step 3 - split the string into command and sequence
        String[] commands = line.split(" ");
        String commandValue = commands[0].trim();
        if (commandValue.toLowerCase().contentEquals("insert")) {
            customTree.insertNode(commands[1]);
        }
        else if (commandValue.toLowerCase().contentEquals("remove")) {
            customTree.removeNode(commands[1]);
        }
        else if (commandValue.toLowerCase().contentEquals("print")
            && commands.length == 1) {
            System.out.println("tree dump:");
            root.printNode(0);
        }
        else if (commandValue.toLowerCase().contentEquals("print")
            && commands[1].toLowerCase().contentEquals("lengths")) {
            System.out.println("tree dump:");
            root.printLengths(0);
        }
        else if (commands[1].toLowerCase().contentEquals("stats")) {
            System.out.println("tree dump:");
            root.printStats(0);
        }
        else {
            customTree.searchNode(commands[1]);
        }
    }


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     *         takes the sequence to be searched in the
     *         tree
     * @param sequence
     *            sequence value to be removed from tree
     */
    public void searchNode(String sequence) {
        boolean exact = sequence.endsWith("$");
        if (exact) {
            sequence = sequence.substring(0, sequence.length() - 1);
        }
        UtilsFunc results = new UtilsFunc();
        char[] sequenceData = sequence.toCharArray();
        root.search(0, sequenceData, exact, results);
        System.out.println("# of nodes visited: " + results.getNodesVisited());
        if (results.getResults().size() == 0) {
            System.out.println("no sequence found");
        }
        else {
            for (char[] seq : results.getResults()) {
                System.out.println("sequence: " + String.valueOf(seq));
            }
        }
    }


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     *         takes the sequence to be removed at the
     *         insertion level
     * @param sequence
     *            sequence value to be removed from tree
     */
    public void removeNode(String sequence) {

        LeafNode nodevalue = new LeafNode(sequence.toCharArray());

        if (root instanceof FlyweightNode) {
            System.out.println("sequence " + sequence + " does not exist");
        }

        else if (root instanceof LeafNode) {
            LeafNode leafNode = (LeafNode)root;
            if (leafNode.getStringVal().equals(sequence)) {
                root = FlyweightNode.getFlyweightInstance();
                System.out.println("sequence " + sequence + " removed");
            }

            else {
                System.out.println("sequence " + sequence + " does not exist");
            }
        }

        else {
            InternalNode internalNode = (InternalNode)root;
            CustomTreeNode nodeVal = internalNode.remove(0, nodevalue);
            if (nodeVal != null) {
                root = nodeVal;
            }
        }
    }


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     *         takes the sequence to be inserted at the
     *         insertion level
     * @param sequence
     *            sequence value to be added to node
     */
    public void insertNode(String sequence) {

        LeafNode newnode = new LeafNode(sequence.toCharArray());

        if (root instanceof FlyweightNode) {
            root = newnode;
            System.out.println("sequence " + sequence + " inserted at level "
                + 0);
        }

        else if (root instanceof LeafNode) {
            LeafNode leafRoot = (LeafNode)root;
            if (leafRoot.ifContainsSequence(newnode)) {
                System.out.println("sequence " + sequence + " already exists");
                return;
            }
            root = new InternalNode();
            InternalNode internalLeafNode = (InternalNode)root;
            internalLeafNode.insert(0, leafRoot, false);
            internalLeafNode.insert(0, newnode, true);
        }

        else {
            InternalNode internalLeafNode = (InternalNode)root;
            internalLeafNode.insert(0, newnode, true);
        }
    }


    /**
     * @author maneeshavenigalla maneesha24@vt.edu Scans the file to be read
     * @param filename
     *            name of the file to be read
     * @throws FileNotFoundException
     *             if file not found
     * @return scanned file as a scanner
     */
    public static Scanner readFile(String filename)
        throws FileNotFoundException {
        FileReader file = new FileReader(filename);
        return new Scanner(file);
    }


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     * @param line
     *            individual line from the scanned file
     * @return the trimmed line as a string
     */
    public static String trimSpaces(String line) {
        String processedLine = line.trim();
        processedLine = processedLine.replaceAll("\\s+", " ");
        return processedLine;
    }

}
