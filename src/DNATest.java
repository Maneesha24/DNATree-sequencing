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

    DNAtree testTree = new DNAtree();

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
    public void testExecuteSimpleRemove() throws IOException {
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
    public void testExecuteRemoveExists() throws IOException {
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
    public void testExecuteSimplePrint() throws IOException {
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

}
