import java.util.ArrayList;

/**
 * This UtilsFunc class implements a “UtilsFunc” for
 * searching for matching
 * sequences in a sequence database
 * 
 * @author maneeshavenigalla maneesha24@vt.edu
 * @version 1.0
 */
public class UtilsFunc {

    private int nodesVisited;

    private ArrayList<char[]> results;

    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     *         sets initial nodes visited value to zero
     *         and initialised matches as array list
     * 
     */
    public UtilsFunc() {
        nodesVisited = 0;
        results = new ArrayList<char[]>();
    }


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     *         returns the matches found as arrayList
     * @return returns the arrayList of matches
     */
    public ArrayList<char[]> getResults() {
        return results;
    }


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     *         increments the number of nodes visited
     */
    public void incrementNodesVisited() {
        nodesVisited++;
    }


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     *         returns the number of nodes visited
     * @return the value of nodes visited
     */
    public int getNodesVisited() {
        return nodesVisited;
    }


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     *         adds the sequence to the matches arrayList
     * @param sequence
     *            value of sequence to matched arraylist
     */
    public void resultAdd(char[] sequence) {
        results.add(sequence);
    }
}
