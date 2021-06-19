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
        
//        To create one child node for each protein in DNA
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

        int levelValue = switchCaseFunc(characterVal);

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

        int levelValue = switchCaseFunc(characterVal);

        CustomTreeNode child = childNodes[levelValue];

        if (child instanceof FlyweightNode) {
            System.out.println("sequence " + nodeRemove + " does not exist");
            return null;
        }

        if (child instanceof LeafNode) {
            LeafNode leafNode = (LeafNode)child;
            if (leafNode.getStringVal().equals(nodeRemove.getStringVal())) {
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
        for (CustomTreeNode indv : childNodes) {
            // Insert the level by 1
            indv.printNode(level + 1);
        }
    }


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     * @param level
     *            prints the node at the level
     */
    public void printLengthsFunc(int level) {
        System.out.println("I");
        for (CustomTreeNode indv : childNodes) {
            // Insert the level by 1
            indv.printLengths(level + 1);
        }
    }


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     * @param level
     *            prints the node at the level
     */
    public void printStatsFunc(int level) {
        System.out.println("I");
        for (CustomTreeNode indv : childNodes) {
            // Insert the level by 1
            indv.printStats(level + 1);
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
     * @param match
     *            returns true if it is a exact match
     * @param output
     *            results from search function
     */
    public void search(
        int level,
        char[] sequence,
        boolean match,
        UtilsFunc output) {
        char characterVal = 0;
        if (level < sequence.length) {
            characterVal = sequence[level];
        }

        if (characterVal == 0 && !match) {
            searchAll(output);
            return;
        }

        output.incrementNodesVisited();
        CustomTreeNode child = null;
        switch (characterVal) {
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

        if (child instanceof FlyweightNode) {
            output.incrementNodesVisited();
            return;
        }

        if (child instanceof LeafNode) {
            output.incrementNodesVisited();
            LeafNode lnode = (LeafNode)child;
            if (match) {
                if (lnode.getStringVal().equals(String.valueOf(sequence))) {
                    output.resultAdd(sequence);
                }
            }
            else {
                if (lnode.getStringVal().startsWith(String.valueOf(sequence))) {
                    output.resultAdd(lnode.getStringVal().toCharArray());
                }
            }
            return;
        }

        child.search(level + 1, sequence, match, output);
    }


    /**
     * @author maneeshavenigalla maneesha24@vt.edu
     *         calls the switch case to check if it
     *         is A,C,G and T case.
     * @param value
     *            value of the characters
     * @return the level value
     */
    public int switchCaseFunc(char value) {

        int levelValue = 0;

        switch (value) {
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

        return levelValue;
    }

}
