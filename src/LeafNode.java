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
     *         converts string value
     * @return converts the value to string
     */
    public String getStringVal() {
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
        double n = (dnaSequence.length / 100.);
        int a = 0;
        int c = 0;
        int g = 0;
        int t = 0;
        for (char characterVal : dnaSequence) {
            switch (characterVal) {
                case 'A':
                    a += 1;
                    break;
                case 'C':
                    c += 1;
                    break;
                case 'G':
                    g += 1;
                    break;
                case 'T':
                    t += 1;
                    break;
                default:
                    break;
            }
        }

        String resultA = String.format("A:%.2f", a / n);
        String resultC = String.format("C:%.2f", c / n);
        String resultG = String.format("G:%.2f", g / n);
        String resultT = String.format("T:%.2f", t / n);

        String stats = "";
        stats += resultA;
        stats += " " + resultC;
        stats += " " + resultG;
        stats += " " + resultT;
        System.out.println(String.valueOf(dnaSequence) + " " + stats);
    }


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     *         calls the print function for printing
     *         nodes
     * @param output
     *            search all nodes visited
     */
    public void searchAll(UtilsFunc output) {
        output.incrementNodesVisited();
        output.resultAdd(dnaSequence);
    }


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     *         calls the print function for printing
     *         nodes
     * @param level
     *            level value
     * @param searchSequence
     *            sequence that needs to be checked
     * @param match
     *            returns true if it is a exact match
     * @param output
     *            results from search function
     */
    public void search(
        int level,
        char[] searchSequence,
        boolean match,
        UtilsFunc output) {
        output.incrementNodesVisited();
        if (match && getStringVal().equals(String.valueOf(searchSequence))) {
            output.resultAdd(searchSequence);
        }
        else if (getStringVal().startsWith(String.valueOf(searchSequence))) {
            output.resultAdd(this.dnaSequence);
        }
    }

}
