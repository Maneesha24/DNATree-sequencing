/**
 * This LeafNode class implements a “LeafNode”
 * stores the value of the leaf node
 * 
 * @author maneeshavenigalla maneesha24@vt.edu
 * @version 1.0
 */
public class LeafNode extends CustomTreeNode {

    private final char[] dnaSequence;

    public LeafNode(char[] sequenceVal) {
        dnaSequence = sequenceVal;
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
            }
        }
        String stats = String.format("A:%.2f C:%.2f G:%.2f T:%.2f", a / n, c
            / n, g / n, t / n);
        System.out.println(String.valueOf(dnaSequence) + " " + stats);
    }

}
