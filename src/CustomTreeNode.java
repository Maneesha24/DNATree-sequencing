/**
 * This CustomTreeNode class implements a “CustomTreeNode”
 * for denoting node in a tree
 * 
 * @author maneeshavenigalla maneesha24@vt.edu
 * @version 1.0
 *
 */
public abstract class CustomTreeNode {
    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     *         gives indentation spaces
     * @param level
     *            level value
     */
    public void printNode(int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
        printFunc(level);
    }


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     *         calls the print function for printing
     *         nodes
     * @param results
     *            search all nodes visited
     */
    public void searchAll(UtilsFunc results) {
        results.incrementNodesVisited();
    }


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     *         calls the print function for printing
     *         nodes
     * @param level
     *            level value
     * @param sequence
     *            sequence that needs to be checked
     * @param exact
     *            returns true if it is a exact match
     * @param results
     *            results from search function
     */
    public void search(
        int level,
        char[] sequence,
        boolean exact,
        UtilsFunc results) {
        results.incrementNodesVisited();
    }


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     *         gives indentation spaces
     * @param level
     *            level value
     */
    public void printLengths(int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
        printLengthsFunc(level);
    }


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     *         gives indentation spaces
     * @param level
     *            level value
     */
    public void printStats(int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
        printStatsFunc(level);
    }


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     *         calls the print function for printing
     *         nodes
     * @param level
     *            level value
     */
    public abstract void printFunc(int level);


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     *         calls the print function for printing
     *         nodes with their respective lengths
     * @param level
     *            level value
     */
    public abstract void printLengthsFunc(int level);


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     *         calls the print function for printing
     *         nodes with their respective lengths
     * @param level
     *            level value
     */
    public abstract void printStatsFunc(int level);
}
