/**
 * This LeafNode class implements a “LeafNode”
 * stores the value of the leaf node
 * 
 * @author maneeshavenigalla maneesha24@vt.edu
 * @version 1.0
 */
public class LeafNode extends CustomTreeNode {

    private final char[] dnaSequence;

    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     *         sets the sequence value to dnaSequence
     * @param sequenceVal
     *            value to be assigned
     */
    public LeafNode(char[] sequenceVal) {
        dnaSequence = sequenceVal;
    }


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     *         returns the dna sequence value
     * @return dnasequence
     */
    public char[] getSequence() {
        return dnaSequence;
    }


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     *         takes the sequence to be inserted at the
     *         insertion level
     * @param nodeValue
     *            sequence value to be checked against
     * @return returns true if the strings are equal
     */
    public boolean ifContainsSequence(LeafNode nodeValue) {
        return String.valueOf(this.dnaSequence).equals(String.valueOf(
            nodeValue.dnaSequence));
    }


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     *         takes the sequence to be inserted at the
     *         insertion level
     * @param level
     *            level to get the value against
     * @return character at that node level
     */
    public char getCharacterValue(int level) {
        if (level < dnaSequence.length) {
            return dnaSequence[level];
        }
        return 0;
    }


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     *         converts string value
     * @return converts the value to string
     */
    public String toString() {
        return String.valueOf(dnaSequence);
    }


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     *         called when print command
     *         is called
     * @param level
     *            level to get the value against
     */
    public void printFunc(int level) {
        System.out.println(String.valueOf(dnaSequence));
    }


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     *         called when print lengths command
     *         is called
     * @param level
     *            level to get the value against
     */
    public void printLengthsFunc(int level) {
        System.out.println(String.valueOf(dnaSequence) + " "
            + dnaSequence.length);
    }


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     *         called when print stats command
     *         is called
     * @param level
     *            level to get the value against
     */
    public void printStatsFunc(int level) {
        double n = dnaSequence.length / 100.;
        double a = 0;
        double c = 0;
        double g = 0;
        double t = 0;
        for (char ch : dnaSequence) {
            switch (ch) {
                case 'A':
                    a++;
                    break;
                case 'C':
                    c++;
                    break;
                case 'G':
                    g++;
                    break;
                case 'T':
                    t++;
                    break;
                default:
                    break;
            }
        }
        String stats = String.format("A:%.2f C:%.2f G:%.2f T:%.2f", a / n, c
            / n, g / n, t / n);
        System.out.println(String.valueOf(dnaSequence) + " " + stats);
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
        results.addMatch(dnaSequence);
    }


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     *         calls the print function for printing
     *         nodes
     * @param level
     *            level value
     * @param searchSequence
     *            sequence that needs to be checked
     * @param exact
     *            returns true if it is a exact match
     * @param results
     *            results from search function
     */
    public void search(
        int level,
        char[] searchSequence,
        boolean exact,
        UtilsFunc results) {
        results.incrementNodesVisited();
        if (exact) {
            if (toString().equals(String.valueOf(searchSequence))) {
                results.addMatch(searchSequence);
            }
        }
        else {
            if (toString().startsWith(String.valueOf(searchSequence))) {
                results.addMatch(this.dnaSequence);
            }
        }
    }

}
