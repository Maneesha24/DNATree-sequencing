/**
 * This InternalNode class implements a “InternalNode”
 * stores the value of the internal node
 * 
 * @author maneeshavenigalla maneesha24@vt.edu
 * @version 1.0
 */
public class InternalNode extends CustomTreeNode {

    private CustomTreeNode[] childNodes = new CustomTreeNode[5];

    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     *         sets the A,C,G and T protein values
     */
    public InternalNode() {
        for (int i = 0; i < childNodes.length; i++) {
            childNodes[i] = FlyweightNode.getFlyweightInstance();
        }
    }


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     * @param level
     *            value where the node to be inserted
     * @param newnode
     *            value to be inserted to the node
     * @param isNode
     *            value of the flag
     */
    public void insert(int level, LeafNode newnode, boolean isNode) {
        char characterVal = newnode.getCharacterValue(level);

        int levelValue = 0;

        switch (characterVal) {
            case 'A':
                levelValue = 0;
                break;
            case 'C':
                levelValue = 1;
                break;
            case 'G':
                levelValue = 2;
                break;
            case 'T':
                levelValue = 3;
                break;
            case 0:
                levelValue = 4;
                break;
            default:
                break;
        }

        CustomTreeNode child = childNodes[levelValue];
        if (child instanceof FlyweightNode) {
            childNodes[levelValue] = newnode;
            if (isNode) {
                System.out.println("sequence " + newnode + " inserted at level "
                    + (level + 1));
            }
            return;
        }
        if (child instanceof LeafNode) {
            if (levelValue == 4) {
                System.out.println("sequence " + newnode + " already exists");
                return;
            }

            LeafNode leafNode = (LeafNode)child;
            if (leafNode.ifContainsSequence(newnode)) {
                System.out.println("sequence " + newnode + " already exists");
                return;
            }

            InternalNode internalNode = new InternalNode();

            childNodes[levelValue] = internalNode;

            internalNode.insert(level + 1, leafNode, false);
            internalNode.insert(level + 1, newnode, true);
            return;
        }
        InternalNode toInsertNode = (InternalNode)child;
        toInsertNode.insert(level + 1, newnode, true);
    }


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     * @param level
     *            value where the node to be inserted
     * @param nodeRemove
     *            value to be inserted to the node
     * @return returns the removed node
     */
    public CustomTreeNode remove(int level, LeafNode nodeRemove) {

        char characterVal = nodeRemove.getCharacterValue(level);

        int levelValue = 0;

        switch (characterVal) {
            case 'A':
                levelValue = 0;
                break;
            case 'C':
                levelValue = 1;
                break;
            case 'G':
                levelValue = 2;
                break;
            case 'T':
                levelValue = 3;
                break;
            case 0:
                levelValue = 4;
                break;
            default:
                break;
        }

        CustomTreeNode child = childNodes[levelValue];

        if (child instanceof FlyweightNode) {
            System.out.println("sequence " + nodeRemove + " does not exist");
            return null;
        }

        if (child instanceof LeafNode) {
            LeafNode leafNode = (LeafNode)child;
            if (leafNode.toString().equals(nodeRemove.toString())) {
                childNodes[levelValue] = FlyweightNode.getFlyweightInstance();
                System.out.println("sequence " + nodeRemove + " removed");
                return getNodeVal();
            }
            else {
                System.out.println("sequence " + nodeRemove
                    + " does not exist");
                return null;
            }
        }

        CustomTreeNode internalNode = child;
        CustomTreeNode nodeVal = ((InternalNode)internalNode).remove(level + 1,
            nodeRemove);

        if (nodeVal != null) {
            childNodes[levelValue] = nodeVal;
            nodeVal = getNodeVal();
        }
        return nodeVal;

    }


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     * @param level
     *            prints the node at the level
     */
    public void printFunc(int level) {
        System.out.println("I");
        for (CustomTreeNode child : childNodes) {
            child.printNode(level + 1);
        }
    }


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     * @param level
     *            prints the node at the level
     */
    public void printLengthsFunc(int level) {
        System.out.println("I");
        for (CustomTreeNode child : childNodes) {
            child.printLengths(level + 1);
        }
    }


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     * @param level
     *            prints the node at the level
     */
    public void printStatsFunc(int level) {
        System.out.println("I");
        for (CustomTreeNode child : childNodes) {
            child.printStats(level + 1);
        }
    }


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     * @return CustomTreeNode
     *         returns the node value
     */
    public CustomTreeNode getNodeVal() {
        int value = 0;
        CustomTreeNode returnedNode = null;
        for (CustomTreeNode node : childNodes) {
            if (!(node instanceof FlyweightNode)) {
                value += 1;
                if (value > 1) {
                    return null;
                }
                returnedNode = node;
            }
        }
        if (returnedNode instanceof InternalNode) {
            return null;
        }
        return returnedNode;
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
        for (CustomTreeNode child : childNodes) {
            child.searchAll(results);
        }
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
        char ch = 0;
        if (level >= 0 && level < sequence.length) {
            ch = sequence[level];
        }

        if (ch == 0 && !exact) {
            searchAll(results);
            return;
        }

        results.incrementNodesVisited();
        CustomTreeNode child = null;
        switch (ch) {
            case 'A':
                child = childNodes[0];
                break;
            case 'C':
                child = childNodes[1];
                break;
            case 'G':
                child = childNodes[2];
                break;
            case 'T':
                child = childNodes[3];
                break;
            case 0:
                child = childNodes[4];
                break;
            default:
                break;
        }

        if (ch == 0 && exact) {
            results.incrementNodesVisited();
            if (child instanceof LeafNode) {
                LeafNode lNode = (LeafNode)child;
                results.addMatch(lNode.toString().toCharArray());
            }
            return;
        }

        if (child instanceof FlyweightNode) {
            results.incrementNodesVisited();
            return;
        }

        if (child instanceof LeafNode) {
            results.incrementNodesVisited();
            LeafNode lnode = (LeafNode)child;
            if (exact) {
                if (lnode.toString().equals(String.valueOf(sequence))) {
                    results.addMatch(sequence);
                }
            }
            else {
                if (lnode.toString().startsWith(String.valueOf(sequence))) {
                    results.addMatch(lnode.toString().toCharArray());
                }
            }
            return;
        }

        child.search(level + 1, sequence, exact, results);
    }

}
