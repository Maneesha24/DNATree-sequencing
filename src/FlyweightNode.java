/**
 * This FlyweightNode class implements a “FlyweightNode” which is
 * a single empty leaf node that is created one time and pointed
 * to whenever an empty child node is needed
 * 
 * @author maneeshavenigalla maneesha24@vt.edu
 * @version 1.0
 */
public class FlyweightNode extends CustomTreeNode {

    private static FlyweightNode flyweight = new FlyweightNode();

    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     *         returns the flyweight node
     * @return the flyweight node
     */
    public static FlyweightNode getFlyweightInstance() {
        return flyweight;
    }


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     *         executes the print command
     * @param level
     *            level value
     */
    public void printFunc(int level) {
        System.out.println("E");
    }


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     *         executes the print command
     * @param level
     *            level value
     */
    public void printLengthsFunc(int level) {
        System.out.println("E");
    }


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     *         executes the print command
     * @param level
     *            level value
     */
    public void printStatsFunc(int level) {
        System.out.println("E");
    }
}
