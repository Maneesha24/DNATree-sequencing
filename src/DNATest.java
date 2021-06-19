import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import student.TestCase;

/**
 * This DNATreeTest class implements a “DNATreeTest” for testing
 * variables and methods
 * 
 * @author maneeshavenigalla maneesha24@vt.edu
 * @version 1.0
 *
 */
public class DNATest extends TestCase {

    /**
     * returns true if the condition passes
     */
    public void testTrimSpacesMethod() {
        String processedLine = DNAtree.trimSpaces("     insert      ACGT     ");
        assertEquals(processedLine, "insert ACGT");
    }


    /**
     * @throws IOException
     *             returns true if the condition passes
     */
    public void testExecuteSimpleInsert() throws IOException {

        DNAtree testTree = new DNAtree();

        final ByteArrayOutputStream outputStreamCaptor =
            new ByteArrayOutputStream();

        System.setOut(new PrintStream(outputStreamCaptor));

        DNAtree.executeCommands("insert AAAA", testTree);
        DNAtree.executeCommands("insert TGCAAA", testTree);
        DNAtree.executeCommands("insert GTCA", testTree);
        assertEquals("sequence AAAA inserted at level 0\n"
            + "sequence TGCAAA inserted at level 1\n"
            + "sequence GTCA inserted at level 1", outputStreamCaptor.toString()
                .trim());
    }


    /**
     * @throws IOException
     *             returns true if the condition passes
     */
    public void testExecuteComplexInsert() throws IOException {
        DNAtree testTree = new DNAtree();

        final ByteArrayOutputStream outputStreamCaptor =
            new ByteArrayOutputStream();

        System.setOut(new PrintStream(outputStreamCaptor));

        DNAtree.executeCommands("insert CAT", testTree);
        DNAtree.executeCommands("insert ACGTACGT", testTree);
        DNAtree.executeCommands("insert ACGTACGTT", testTree);
        assertEquals("sequence CAT inserted at level 0\n"
            + "sequence ACGTACGT inserted at level 1\n"
            + "sequence ACGTACGTT inserted at level 9", outputStreamCaptor
                .toString().trim());
    }


    /**
     * @throws IOException
     *             returns true if the condition passes
     */
    public void testExecuteInsertExists() throws IOException {

        DNAtree testTree = new DNAtree();

        final ByteArrayOutputStream outputStreamCaptor =
            new ByteArrayOutputStream();

        System.setOut(new PrintStream(outputStreamCaptor));

        DNAtree.executeCommands("insert CAT", testTree);
        DNAtree.executeCommands("insert ACGTACGT", testTree);
        DNAtree.executeCommands("insert ACGTACGTT", testTree);
        DNAtree.executeCommands("insert CAT", testTree);
        assertEquals("sequence CAT inserted at level 0\n"
            + "sequence ACGTACGT inserted at level 1\n"
            + "sequence ACGTACGTT inserted at level 9\n"
            + "sequence CAT already exists", outputStreamCaptor.toString()
                .trim());
    }


    /**
     * @throws IOException
     *             returns true if the condition passes
     */
    public void testExecuteInsertLeaf() throws IOException {

        DNAtree testTree = new DNAtree();

        final ByteArrayOutputStream outputStreamCaptor =
            new ByteArrayOutputStream();

        System.setOut(new PrintStream(outputStreamCaptor));

        DNAtree.executeCommands("insert CAT", testTree);
        DNAtree.executeCommands("insert ACGTACGT", testTree);
        DNAtree.executeCommands("insert ACGTACGTT", testTree);
        DNAtree.executeCommands("insert ACG", testTree);
        assertEquals("sequence CAT inserted at level 0\n"
            + "sequence ACGTACGT inserted at level 1\n"
            + "sequence ACGTACGTT inserted at level 9\n"
            + "sequence ACG inserted at level 4", outputStreamCaptor.toString()
                .trim());
    }


    /**
     * @throws IOException
     *             returns true if the condition passes
     */
    public void testExecuteAlphabetExists() throws IOException {

        DNAtree testTree = new DNAtree();

        final ByteArrayOutputStream outputStreamCaptor =
            new ByteArrayOutputStream();

        System.setOut(new PrintStream(outputStreamCaptor));

        DNAtree.executeCommands("insert CAT", testTree);
        DNAtree.executeCommands("insert ACGTACGT", testTree);
        DNAtree.executeCommands("insert ACGTACGTT", testTree);
        DNAtree.executeCommands("insert CAT", testTree);
        DNAtree.executeCommands("insert A", testTree);
        assertEquals("sequence CAT inserted at level 0\n"
            + "sequence ACGTACGT inserted at level 1\n"
            + "sequence ACGTACGTT inserted at level 9\n"
            + "sequence CAT already exists\n"
            + "sequence A inserted at level 2", outputStreamCaptor.toString()
                .trim());
    }


    /**
     * @throws IOException
     *             returns true if the condition passes
     */
    public void testExecuteAlphabetRemoveDouble() throws IOException {

        DNAtree testTree = new DNAtree();

        final ByteArrayOutputStream outputStreamCaptor =
            new ByteArrayOutputStream();

        System.setOut(new PrintStream(outputStreamCaptor));

        DNAtree.executeCommands("insert CAT", testTree);
        DNAtree.executeCommands("insert ACGTACGT", testTree);
        DNAtree.executeCommands("insert ACGTACGTT", testTree);
        DNAtree.executeCommands("insert CAT", testTree);
        DNAtree.executeCommands("insert A", testTree);
        DNAtree.executeCommands("remove A", testTree);
        assertEquals("sequence CAT inserted at level 0\n"
            + "sequence ACGTACGT inserted at level 1\n"
            + "sequence ACGTACGTT inserted at level 9\n"
            + "sequence CAT already exists\n"
            + "sequence A inserted at level 2\n" + "sequence A removed",
            outputStreamCaptor.toString().trim());
    }


    /**
     * @throws IOException
     *             returns true if the condition passes
     */
    public void testExecuteAlphabetSearch() throws IOException {

        DNAtree testTree = new DNAtree();

        final ByteArrayOutputStream outputStreamCaptor =
            new ByteArrayOutputStream();

        System.setOut(new PrintStream(outputStreamCaptor));

        DNAtree.executeCommands("insert CAT", testTree);
        DNAtree.executeCommands("insert ACGTACGT", testTree);
        DNAtree.executeCommands("insert ACGTACGTT", testTree);
        DNAtree.executeCommands("insert CAT", testTree);
        DNAtree.executeCommands("insert A", testTree);
        DNAtree.executeCommands("remove A", testTree);
        DNAtree.executeCommands("search ACGTACGTT$", testTree);
        assertEquals("sequence CAT inserted at level 0\n"
            + "sequence ACGTACGT inserted at level 1\n"
            + "sequence ACGTACGTT inserted at level 9\n"
            + "sequence CAT already exists\n"
            + "sequence A inserted at level 2\n" + "sequence A removed\n"
            + "# of nodes visited: 10\n" + "sequence: ACGTACGTT",
            outputStreamCaptor.toString().trim());
    }


    /**
     * @throws IOException
     *             returns true if the condition passes
     */
    public void testExecuteSimpleRemove() throws IOException {

        DNAtree testTree = new DNAtree();

        final ByteArrayOutputStream outputStreamCaptor =
            new ByteArrayOutputStream();

        System.setOut(new PrintStream(outputStreamCaptor));

        DNAtree.executeCommands("insert AAAA", testTree);
        DNAtree.executeCommands("insert TGCAAA", testTree);
        DNAtree.executeCommands("remove AAAA", testTree);
        assertEquals("sequence AAAA inserted at level 0\n"
            + "sequence TGCAAA inserted at level 1\n" + "sequence AAAA removed",
            outputStreamCaptor.toString().trim());
    }
    
    /**
     * @throws IOException
     *             returns true if the condition passes
     */
    public void testExecuteSimpleLeafNode() throws IOException {

        DNAtree testTree = new DNAtree();

        final ByteArrayOutputStream outputStreamCaptor =
            new ByteArrayOutputStream();

        System.setOut(new PrintStream(outputStreamCaptor));

        DNAtree.executeCommands("insert ACGTTACGATCTAGG", testTree);
        DNAtree.executeCommands("insert ACGT", testTree);
        DNAtree.executeCommands("insert ACGA", testTree);
        DNAtree.executeCommands("insert GAT", testTree);
        DNAtree.executeCommands("insert CG", testTree);
        DNAtree.executeCommands("insert GATA", testTree);
        DNAtree.executeCommands("insert ACG", testTree);
        DNAtree.executeCommands("insert ACG", testTree);
        DNAtree.executeCommands("insert ACGTTACGATCTAGG", testTree);
        DNAtree.executeCommands("search GA", testTree);
        DNAtree.executeCommands("search ACGA$", testTree);
        DNAtree.executeCommands("insert GAT", testTree);
        DNAtree.executeCommands("search ACG", testTree);
        DNAtree.executeCommands("search A", testTree);
        DNAtree.executeCommands("search A$", testTree);
        assertEquals("sequence ACGTTACGATCTAGG inserted at level 0\n"
            + "sequence ACGT inserted at level 5\n"
            + "sequence ACGA inserted at level 4\n"
            + "sequence GAT inserted at level 1\n"
            + "sequence CG inserted at level 1\n"
            + "sequence GATA inserted at level 4\n"
            + "sequence ACG inserted at level 4\n"
            + "sequence ACG already exists\n"
            + "sequence ACGTTACGATCTAGG already exists\n"
            + "# of nodes visited: 13\n"
            + "sequence: GATA\n"
            + "sequence: GAT\n"
            + "# of nodes visited: 5\n"
            + "sequence: ACGA\n"
            + "sequence GAT already exists\n"
            + "# of nodes visited: 14\n"
            + "sequence: ACGA\n"
            + "sequence: ACGTTACGATCTAGG\n"
            + "sequence: ACGT\n"
            + "sequence: ACG\n"
            + "# of nodes visited: 22\n"
            + "sequence: ACGA\n"
            + "sequence: ACGTTACGATCTAGG\n"
            + "sequence: ACGT\n"
            + "sequence: ACG\n"
            + "# of nodes visited: 3\n"
            + "no sequence found",
            outputStreamCaptor.toString().trim());
    }


    /**
     * @throws IOException
     *             returns true if the condition passes
     */
    public void testExecuteEmptyRemove() throws IOException {

        DNAtree testTree = new DNAtree();

        final ByteArrayOutputStream outputStreamCaptor =
            new ByteArrayOutputStream();

        System.setOut(new PrintStream(outputStreamCaptor));

        DNAtree.executeCommands("remove AAAA", testTree);
        assertEquals("sequence AAAA does not exist", outputStreamCaptor
            .toString().trim());
    }


    /**
     * @throws IOException
     *             returns true if the condition passes
     */
    public void testExecuteRepeatedRemove() throws IOException {

        DNAtree testTree = new DNAtree();

        final ByteArrayOutputStream outputStreamCaptor =
            new ByteArrayOutputStream();

        System.setOut(new PrintStream(outputStreamCaptor));

        DNAtree.executeCommands("insert AAAA", testTree);
        DNAtree.executeCommands("insert TGCAAA", testTree);
        DNAtree.executeCommands("remove AAAA", testTree);
        DNAtree.executeCommands("remove AAAA", testTree);
        assertEquals("sequence AAAA inserted at level 0\n"
            + "sequence TGCAAA inserted at level 1\n"
            + "sequence AAAA removed\n" + "sequence AAAA does not exist",
            outputStreamCaptor.toString().trim());
    }


    /**
     * @throws IOException
     *             returns true if the condition passes
     */
    public void testExecuteRemoveExists() throws IOException {

        DNAtree testTree = new DNAtree();

        final ByteArrayOutputStream outputStreamCaptor =
            new ByteArrayOutputStream();

        System.setOut(new PrintStream(outputStreamCaptor));

        DNAtree.executeCommands("insert AAAA", testTree);
        DNAtree.executeCommands("insert TGCAAA", testTree);
        DNAtree.executeCommands("remove CTGC", testTree);
        assertEquals("sequence AAAA inserted at level 0\n"
            + "sequence TGCAAA inserted at level 1\n"
            + "sequence CTGC does not exist", outputStreamCaptor.toString()
                .trim());
    }


    /**
     * @throws IOException
     *             returns true if the condition passes
     */
    public void testExecuteRemovePrint() throws IOException {

        DNAtree testTree = new DNAtree();

        final ByteArrayOutputStream outputStreamCaptor =
            new ByteArrayOutputStream();

        System.setOut(new PrintStream(outputStreamCaptor));

        DNAtree.executeCommands("insert AAAA", testTree);
        DNAtree.executeCommands("insert TGCAAA", testTree);
        DNAtree.executeCommands("print", testTree);
        DNAtree.executeCommands("remove AAAA", testTree);
        DNAtree.executeCommands("print", testTree);
        assertEquals("sequence AAAA inserted at level 0\n"
            + "sequence TGCAAA inserted at level 1\n" + "tree dump:\n" + "I\n"
            + "  AAAA\n" + "  E\n" + "  E\n" + "  TGCAAA\n" + "  E\n"
            + "sequence AAAA removed\n" + "tree dump:\n" + "TGCAAA",
            outputStreamCaptor.toString().trim());
    }


    /**
     * @throws IOException
     *             returns true if the condition passes
     */
    public void testExecuteSearch() throws IOException {

        DNAtree testTree = new DNAtree();

        final ByteArrayOutputStream outputStreamCaptor =
            new ByteArrayOutputStream();

        System.setOut(new PrintStream(outputStreamCaptor));

        DNAtree.executeCommands("insert AAAA", testTree);
        DNAtree.executeCommands("insert TGCAAA", testTree);
        DNAtree.executeCommands("print", testTree);
        DNAtree.executeCommands("remove AAAA", testTree);
        DNAtree.executeCommands("print", testTree);
        DNAtree.executeCommands("search TGCAAA", testTree);
        assertEquals("sequence AAAA inserted at level 0\n"
            + "sequence TGCAAA inserted at level 1\n" + "tree dump:\n" + "I\n"
            + "  AAAA\n" + "  E\n" + "  E\n" + "  TGCAAA\n" + "  E\n"
            + "sequence AAAA removed\n" + "tree dump:\n" + "TGCAAA\n"
            + "# of nodes visited: 1\n" + "sequence: TGCAAA", outputStreamCaptor
                .toString().trim());
    }


    /**
     * @throws IOException
     *             returns true if the condition passes
     */
    public void testExecuteSearchExact() throws IOException {

        DNAtree testTree = new DNAtree();

        final ByteArrayOutputStream outputStreamCaptor =
            new ByteArrayOutputStream();

        System.setOut(new PrintStream(outputStreamCaptor));

        DNAtree.executeCommands("insert AAAA", testTree);
        DNAtree.executeCommands("insert TGCAAA", testTree);
        DNAtree.executeCommands("print", testTree);
        DNAtree.executeCommands("remove AAAA", testTree);
        DNAtree.executeCommands("print", testTree);
        DNAtree.executeCommands("search TGCAAA", testTree);
        DNAtree.executeCommands("insert A", testTree);
        DNAtree.executeCommands("insert GCA", testTree);
        DNAtree.executeCommands("search A", testTree);
        DNAtree.executeCommands("search GCA$", testTree);
        assertEquals("sequence AAAA inserted at level 0\n"
            + "sequence TGCAAA inserted at level 1\n" + "tree dump:\n" + "I\n"
            + "  AAAA\n" + "  E\n" + "  E\n" + "  TGCAAA\n" + "  E\n"
            + "sequence AAAA removed\n" + "tree dump:\n" + "TGCAAA\n"
            + "# of nodes visited: 1\n" + "sequence: TGCAAA\n"
            + "sequence A inserted at level 1\n"
            + "sequence GCA inserted at level 1\n" + "# of nodes visited: 2\n"
            + "sequence: A\n" + "# of nodes visited: 2\n" + "sequence: GCA",
            outputStreamCaptor.toString().trim());
    }


    /**
     * @throws IOException
     *             returns true if the condition passes
     */
    public void testExecuteComplexRemove() throws IOException {

        DNAtree testTree = new DNAtree();

        final ByteArrayOutputStream outputStreamCaptor =
            new ByteArrayOutputStream();

        System.setOut(new PrintStream(outputStreamCaptor));

        DNAtree.executeCommands("insert ACG", testTree);
        DNAtree.executeCommands("insert GAT", testTree);
        DNAtree.executeCommands("insert GCAT", testTree);
        DNAtree.executeCommands("insert GTA", testTree);
        DNAtree.executeCommands("print", testTree);
        DNAtree.executeCommands("remove GAT", testTree);
        DNAtree.executeCommands("remove GTA", testTree);
        DNAtree.executeCommands("print", testTree);
        assertEquals("sequence ACG inserted at level 0\n"
            + "sequence GAT inserted at level 1\n"
            + "sequence GCAT inserted at level 2\n"
            + "sequence GTA inserted at level 2\n" + "tree dump:\n" + "I\n"
            + "  ACG\n" + "  E\n" + "  I\n" + "    GAT\n" + "    GCAT\n"
            + "    E\n" + "    GTA\n" + "    E\n" + "  E\n" + "  E\n"
            + "sequence GAT removed\n" + "sequence GTA removed\n"
            + "tree dump:\n" + "I\n" + "  ACG\n" + "  E\n" + "  GCAT\n"
            + "  E\n" + "  E", outputStreamCaptor.toString().trim());
    }


    /**
     * @throws IOException
     *             returns true if the condition passes
     */
    public void testExecuteComplexRemove2() throws IOException {

        DNAtree testTree = new DNAtree();

        final ByteArrayOutputStream outputStreamCaptor =
            new ByteArrayOutputStream();

        System.setOut(new PrintStream(outputStreamCaptor));

        DNAtree.executeCommands("insert ACCTT", testTree);
        DNAtree.executeCommands("insert ACTGGGAA", testTree);
        DNAtree.executeCommands("insert ACTTA", testTree);
        DNAtree.executeCommands("remove ACCTT", testTree);
        DNAtree.executeCommands("print", testTree);
        DNAtree.executeCommands("remove ACTGGGAA", testTree);
        DNAtree.executeCommands("print", testTree);
        assertEquals("sequence ACCTT inserted at level 0\n"
            + "sequence ACTGGGAA inserted at level 3\n"
            + "sequence ACTTA inserted at level 4\n"
            + "sequence ACCTT removed\n" + "tree dump:\n" + "I\n" + "  I\n"
            + "    E\n" + "    I\n" + "      E\n" + "      E\n" + "      E\n"
            + "      I\n" + "        E\n" + "        E\n" + "        ACTGGGAA\n"
            + "        ACTTA\n" + "        E\n" + "      E\n" + "    E\n"
            + "    E\n" + "    E\n" + "  E\n" + "  E\n" + "  E\n" + "  E\n"
            + "sequence ACTGGGAA removed\n" + "tree dump:\n" + "ACTTA" + "",
            outputStreamCaptor.toString().trim());
    }


    /**
     * @throws IOException
     *             returns true if the condition passes
     */
    public void testExecuteSimplePrint() throws IOException {

        DNAtree testTree = new DNAtree();

        final ByteArrayOutputStream outputStreamCaptor =
            new ByteArrayOutputStream();

        System.setOut(new PrintStream(outputStreamCaptor));

        DNAtree.executeCommands("insert ACGT", testTree);
        DNAtree.executeCommands("insert AAAA", testTree);
        DNAtree.executeCommands("insert AA", testTree);
        DNAtree.executeCommands("insert AAACCCCGGTGAAAACGTA", testTree);
        DNAtree.executeCommands("insert ACTGGGAA", testTree);
        DNAtree.executeCommands("remove ACGT", testTree);
        DNAtree.executeCommands("insert ACCTT", testTree);
        DNAtree.executeCommands("insert ACTTA", testTree);
        DNAtree.executeCommands("print", testTree);
        DNAtree.executeCommands("insert TATA", testTree);
        DNAtree.executeCommands("insert TCG", testTree);
        assertEquals("sequence ACGT inserted at level 0\n"
            + "sequence AAAA inserted at level 2\n"
            + "sequence AA inserted at level 3\n"
            + "sequence AAACCCCGGTGAAAACGTA inserted at level 4\n"
            + "sequence ACTGGGAA inserted at level 3\n"
            + "sequence ACGT removed\n" + "sequence ACCTT inserted at level 3\n"
            + "sequence ACTTA inserted at level 4\n" + "tree dump:\n" + "I\n"
            + "  I\n" + "    I\n" + "      I\n" + "        AAAA\n"
            + "        AAACCCCGGTGAAAACGTA\n" + "        E\n" + "        E\n"
            + "        E\n" + "      E\n" + "      E\n" + "      E\n"
            + "      AA\n" + "    I\n" + "      E\n" + "      ACCTT\n"
            + "      E\n" + "      I\n" + "        E\n" + "        E\n"
            + "        ACTGGGAA\n" + "        ACTTA\n" + "        E\n"
            + "      E\n" + "    E\n" + "    E\n" + "    E\n" + "  E\n"
            + "  E\n" + "  E\n" + "  E\n"
            + "sequence TATA inserted at level 1\n"
            + "sequence TCG inserted at level 2", outputStreamCaptor.toString()
                .trim());
    }


    /**
     * @throws IOException
     *             returns true if the condition passes
     */
    public void testExecuteSimplePrintLengths() throws IOException {

        DNAtree testTree = new DNAtree();

        final ByteArrayOutputStream outputStreamCaptor =
            new ByteArrayOutputStream();

        System.setOut(new PrintStream(outputStreamCaptor));

        DNAtree.executeCommands("insert ACGT", testTree);
        DNAtree.executeCommands("insert AAAA", testTree);
        DNAtree.executeCommands("insert AA", testTree);
        DNAtree.executeCommands("insert AAACCCCGGTGAAAACGTA", testTree);
        DNAtree.executeCommands("insert ACTGGGAA", testTree);
        DNAtree.executeCommands("remove ACGT", testTree);
        DNAtree.executeCommands("insert ACCTT", testTree);
        DNAtree.executeCommands("insert ACTTA", testTree);
        DNAtree.executeCommands("print", testTree);
        DNAtree.executeCommands("insert TATA", testTree);
        DNAtree.executeCommands("insert TCG", testTree);
        DNAtree.executeCommands("print lengths", testTree);
        assertEquals("sequence ACGT inserted at level 0\n"
            + "sequence AAAA inserted at level 2\n"
            + "sequence AA inserted at level 3\n"
            + "sequence AAACCCCGGTGAAAACGTA inserted at level 4\n"
            + "sequence ACTGGGAA inserted at level 3\n"
            + "sequence ACGT removed\n" + "sequence ACCTT inserted at level 3\n"
            + "sequence ACTTA inserted at level 4\n" + "tree dump:\n" + "I\n"
            + "  I\n" + "    I\n" + "      I\n" + "        AAAA\n"
            + "        AAACCCCGGTGAAAACGTA\n" + "        E\n" + "        E\n"
            + "        E\n" + "      E\n" + "      E\n" + "      E\n"
            + "      AA\n" + "    I\n" + "      E\n" + "      ACCTT\n"
            + "      E\n" + "      I\n" + "        E\n" + "        E\n"
            + "        ACTGGGAA\n" + "        ACTTA\n" + "        E\n"
            + "      E\n" + "    E\n" + "    E\n" + "    E\n" + "  E\n"
            + "  E\n" + "  E\n" + "  E\n"
            + "sequence TATA inserted at level 1\n"
            + "sequence TCG inserted at level 2\n" + "tree dump:\n" + "I\n"
            + "  I\n" + "    I\n" + "      I\n" + "        AAAA 4\n"
            + "        AAACCCCGGTGAAAACGTA 19\n" + "        E\n" + "        E\n"
            + "        E\n" + "      E\n" + "      E\n" + "      E\n"
            + "      AA 2\n" + "    I\n" + "      E\n" + "      ACCTT 5\n"
            + "      E\n" + "      I\n" + "        E\n" + "        E\n"
            + "        ACTGGGAA 8\n" + "        ACTTA 5\n" + "        E\n"
            + "      E\n" + "    E\n" + "    E\n" + "    E\n" + "  E\n"
            + "  E\n" + "  I\n" + "    TATA 4\n" + "    TCG 3\n" + "    E\n"
            + "    E\n" + "    E\n" + "  E", outputStreamCaptor.toString()
                .trim());
    }


    /**
     * @throws IOException
     *             returns true if the condition passes
     */
    public void testExecuteSimplePrintStats() throws IOException {

        DNAtree testTree = new DNAtree();

        final ByteArrayOutputStream outputStreamCaptor =
            new ByteArrayOutputStream();

        System.setOut(new PrintStream(outputStreamCaptor));

        DNAtree.executeCommands("insert ACGT", testTree);
        DNAtree.executeCommands("insert AAAA", testTree);
        DNAtree.executeCommands("insert AA", testTree);
        DNAtree.executeCommands("insert AAACCCCGGTGAAAACGTA", testTree);
        DNAtree.executeCommands("insert ACTGGGAA", testTree);
        DNAtree.executeCommands("remove ACGT", testTree);
        DNAtree.executeCommands("insert ACCTT", testTree);
        DNAtree.executeCommands("insert ACTTA", testTree);
        DNAtree.executeCommands("print", testTree);
        DNAtree.executeCommands("insert TATA", testTree);
        DNAtree.executeCommands("insert TCG", testTree);
        DNAtree.executeCommands("print lengths", testTree);
        DNAtree.executeCommands("print stats", testTree);
        assertEquals("sequence ACGT inserted at level 0\n"
            + "sequence AAAA inserted at level 2\n"
            + "sequence AA inserted at level 3\n"
            + "sequence AAACCCCGGTGAAAACGTA inserted at level 4\n"
            + "sequence ACTGGGAA inserted at level 3\n"
            + "sequence ACGT removed\n" + "sequence ACCTT inserted at level 3\n"
            + "sequence ACTTA inserted at level 4\n" + "tree dump:\n" + "I\n"
            + "  I\n" + "    I\n" + "      I\n" + "        AAAA\n"
            + "        AAACCCCGGTGAAAACGTA\n" + "        E\n" + "        E\n"
            + "        E\n" + "      E\n" + "      E\n" + "      E\n"
            + "      AA\n" + "    I\n" + "      E\n" + "      ACCTT\n"
            + "      E\n" + "      I\n" + "        E\n" + "        E\n"
            + "        ACTGGGAA\n" + "        ACTTA\n" + "        E\n"
            + "      E\n" + "    E\n" + "    E\n" + "    E\n" + "  E\n"
            + "  E\n" + "  E\n" + "  E\n"
            + "sequence TATA inserted at level 1\n"
            + "sequence TCG inserted at level 2\n" + "tree dump:\n" + "I\n"
            + "  I\n" + "    I\n" + "      I\n" + "        AAAA 4\n"
            + "        AAACCCCGGTGAAAACGTA 19\n" + "        E\n" + "        E\n"
            + "        E\n" + "      E\n" + "      E\n" + "      E\n"
            + "      AA 2\n" + "    I\n" + "      E\n" + "      ACCTT 5\n"
            + "      E\n" + "      I\n" + "        E\n" + "        E\n"
            + "        ACTGGGAA 8\n" + "        ACTTA 5\n" + "        E\n"
            + "      E\n" + "    E\n" + "    E\n" + "    E\n" + "  E\n"
            + "  E\n" + "  I\n" + "    TATA 4\n" + "    TCG 3\n" + "    E\n"
            + "    E\n" + "    E\n" + "  E\n" + "tree dump:\n" + "I\n" + "  I\n"
            + "    I\n" + "      I\n"
            + "        AAAA A:100.00 C:0.00 G:0.00 T:0.00\n"
            + "        AAACCCCGGTGAAAACGTA A:42.11 C:26.32 G:21.05 T:10.53\n"
            + "        E\n" + "        E\n" + "        E\n" + "      E\n"
            + "      E\n" + "      E\n"
            + "      AA A:100.00 C:0.00 G:0.00 T:0.00\n" + "    I\n"
            + "      E\n" + "      ACCTT A:20.00 C:40.00 G:0.00 T:40.00\n"
            + "      E\n" + "      I\n" + "        E\n" + "        E\n"
            + "        ACTGGGAA A:37.50 C:12.50 G:37.50 T:12.50\n"
            + "        ACTTA A:40.00 C:20.00 G:0.00 T:40.00\n" + "        E\n"
            + "      E\n" + "    E\n" + "    E\n" + "    E\n" + "  E\n"
            + "  E\n" + "  I\n" + "    TATA A:50.00 C:0.00 G:0.00 T:50.00\n"
            + "    TCG A:0.00 C:33.33 G:33.33 T:33.33\n" + "    E\n" + "    E\n"
            + "    E\n" + "  E", outputStreamCaptor.toString().trim());
    }


    /**
     * @throws IOException
     *             returns true if the condition passes
     */
    public void testSampleFile() throws IOException {

        final ByteArrayOutputStream outputStreamCaptor =
            new ByteArrayOutputStream();

        System.setOut(new PrintStream(outputStreamCaptor));

        DNAtree.main(new String[] { "SampleInput.txt" });
        assertEquals("sequence ACGT inserted at level 0\n"
            + "sequence AAAA inserted at level 2\n"
            + "sequence AA inserted at level 3\n"
            + "sequence AAACCCCGGTGAAAACGTA inserted at level 4\n"
            + "sequence ACTGGGAA inserted at level 3\n"
            + "sequence ACGT removed\n" + "sequence ACCTT inserted at level 3\n"
            + "sequence ACTTA inserted at level 4\n" + "tree dump:\n" + "I\n"
            + "  I\n" + "    I\n" + "      I\n" + "        AAAA\n"
            + "        AAACCCCGGTGAAAACGTA\n" + "        E\n" + "        E\n"
            + "        E\n" + "      E\n" + "      E\n" + "      E\n"
            + "      AA\n" + "    I\n" + "      E\n" + "      ACCTT\n"
            + "      E\n" + "      I\n" + "        E\n" + "        E\n"
            + "        ACTGGGAA\n" + "        ACTTA\n" + "        E\n"
            + "      E\n" + "    E\n" + "    E\n" + "    E\n" + "  E\n"
            + "  E\n" + "  E\n" + "  E\n"
            + "sequence TATA inserted at level 1\n"
            + "sequence TCG inserted at level 2\n" + "tree dump:\n" + "I\n"
            + "  I\n" + "    I\n" + "      I\n" + "        AAAA 4\n"
            + "        AAACCCCGGTGAAAACGTA 19\n" + "        E\n" + "        E\n"
            + "        E\n" + "      E\n" + "      E\n" + "      E\n"
            + "      AA 2\n" + "    I\n" + "      E\n" + "      ACCTT 5\n"
            + "      E\n" + "      I\n" + "        E\n" + "        E\n"
            + "        ACTGGGAA 8\n" + "        ACTTA 5\n" + "        E\n"
            + "      E\n" + "    E\n" + "    E\n" + "    E\n" + "  E\n"
            + "  E\n" + "  I\n" + "    TATA 4\n" + "    TCG 3\n" + "    E\n"
            + "    E\n" + "    E\n" + "  E\n" + "tree dump:\n" + "I\n" + "  I\n"
            + "    I\n" + "      I\n"
            + "        AAAA A:100.00 C:0.00 G:0.00 T:0.00\n"
            + "        AAACCCCGGTGAAAACGTA A:42.11 C:26.32 G:21.05 T:10.53\n"
            + "        E\n" + "        E\n" + "        E\n" + "      E\n"
            + "      E\n" + "      E\n"
            + "      AA A:100.00 C:0.00 G:0.00 T:0.00\n" + "    I\n"
            + "      E\n" + "      ACCTT A:20.00 C:40.00 G:0.00 T:40.00\n"
            + "      E\n" + "      I\n" + "        E\n" + "        E\n"
            + "        ACTGGGAA A:37.50 C:12.50 G:37.50 T:12.50\n"
            + "        ACTTA A:40.00 C:20.00 G:0.00 T:40.00\n" + "        E\n"
            + "      E\n" + "    E\n" + "    E\n" + "    E\n" + "  E\n"
            + "  E\n" + "  I\n" + "    TATA A:50.00 C:0.00 G:0.00 T:50.00\n"
            + "    TCG A:0.00 C:33.33 G:33.33 T:33.33\n" + "    E\n" + "    E\n"
            + "    E\n" + "  E\n" + "# of nodes visited: 5\n"
            + "sequence: AAAA\n" + "# of nodes visited: 13\n"
            + "sequence: AAAA\n" + "sequence: AAACCCCGGTGAAAACGTA\n"
            + "sequence: AA\n" + "# of nodes visited: 4\n"
            + "no sequence found", outputStreamCaptor.toString().trim());
    }


    /**
     * @throws IOException
     *             returns true if the condition passes
     */
    public void testExecuteSearch2() throws IOException {

        DNAtree testTree = new DNAtree();

        final ByteArrayOutputStream outputStreamCaptor =
            new ByteArrayOutputStream();

        System.setOut(new PrintStream(outputStreamCaptor));

        DNAtree.executeCommands("insert CAT", testTree);
        DNAtree.executeCommands("insert ACGTACGT", testTree);
        DNAtree.executeCommands("insert ACGTACGTT", testTree);
        DNAtree.executeCommands("insert A", testTree);
        DNAtree.executeCommands("search A", testTree);
        assertEquals("sequence CAT inserted at level 0\n"
            + "sequence ACGTACGT inserted at level 1\n"
            + "sequence ACGTACGTT inserted at level 9\n"
            + "sequence A inserted at level 2\n" + "# of nodes visited: 42\n"
            + "sequence: ACGTACGTT\n" + "sequence: ACGTACGT\n" + "sequence: A",
            outputStreamCaptor.toString().trim());
    }


    /**
     * @throws IOException
     *             returns true if the condition passes
     */
    public void testExecuteInsert2() throws IOException {

        DNAtree testTree = new DNAtree();

        final ByteArrayOutputStream outputStreamCaptor =
            new ByteArrayOutputStream();

        System.setOut(new PrintStream(outputStreamCaptor));

        DNAtree.executeCommands("insert CAT", testTree);
        DNAtree.executeCommands("insert ACGTACGT", testTree);
        DNAtree.executeCommands("insert ACGTACGTT", testTree);
        DNAtree.executeCommands("insert CAT", testTree);
        assertEquals("sequence CAT inserted at level 0\n"
            + "sequence ACGTACGT inserted at level 1\n"
            + "sequence ACGTACGTT inserted at level 9\n"
            + "sequence CAT already exists", outputStreamCaptor.toString()
                .trim());
    }


    /**
     * @throws IOException
     *             returns true if the condition passes
     */
    public void testExecuteRemove() throws IOException {

        DNAtree testTree = new DNAtree();

        final ByteArrayOutputStream outputStreamCaptor =
            new ByteArrayOutputStream();

        System.setOut(new PrintStream(outputStreamCaptor));

        DNAtree.executeCommands("insert CAT", testTree);
        DNAtree.executeCommands("insert ACGTACGT", testTree);
        DNAtree.executeCommands("insert ACGTACGTT", testTree);
        DNAtree.executeCommands("insert CAT", testTree);
        DNAtree.executeCommands("remove CAT", testTree);
        DNAtree.executeCommands("remove ACGTACGTT", testTree);
        DNAtree.executeCommands("remove ACGTACGT", testTree);
        assertEquals("sequence CAT inserted at level 0\n"
            + "sequence ACGTACGT inserted at level 1\n"
            + "sequence ACGTACGTT inserted at level 9\n"
            + "sequence CAT already exists\n" + "sequence CAT removed\n"
            + "sequence ACGTACGTT removed\n" + "sequence ACGTACGT removed",
            outputStreamCaptor.toString().trim());
    }

}
