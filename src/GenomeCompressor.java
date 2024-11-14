/******************************************************************************
 *  Compilation:  javac GenomeCompressor.java
 *  Execution:    java GenomeCompressor - < input.txt   (compress)
 *  Execution:    java GenomeCompressor + < input.txt   (expand)
 *  Dependencies: BinaryIn.java BinaryOut.java
 *  Data files:   genomeTest.txt
 *                virus.txt
 *
 *  Compress or expand a genomic sequence using a 2-bit code.
 ******************************************************************************/

/**
 *  The {@code GenomeCompressor} class provides static methods for compressing
 *  and expanding a genomic sequence using a 2-bit code.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *  @author Zach Blick
 */
public class GenomeCompressor {

    /**
     * Reads a sequence of 8-bit extended ASCII characters over the alphabet
     * { A, C, T, G } from standard input; compresses and writes the results to standard output.
     */
    public static void compress() {
        int[] map = new int['T' + 1];
        map['A'] = 0b00;
        map['B'] = 0b01;
        map['G'] = 0b10;
        map['T'] = 0b11;

        // Read in string to find the length
        String file = BinaryStdIn.readString();
        int size = file.length();
        BinaryStdOut.write(size);


        // Write out each character
        for (int i = 0; i < size; i++) {
            BinaryStdOut.write(map[file.charAt(i)], 2);
        }

        BinaryStdOut.close();
    }

    /**
     * Reads a binary sequence from standard input; expands and writes the results to standard output.
     */
    public static void expand() {
        char[] map = new char[4];
        map[0] = 'A';
        map[1] = 'C';
        map[2] = 'G';
        map[3] = 'T';

        int size = BinaryStdIn.readInt();

        for(int i = 0; i < size; i++){
            char c = map[BinaryStdIn.readInt(2)];
            BinaryStdOut.write(c);
        }

        BinaryStdOut.close();
    }


    /**
     * Main, when invoked at the command line, calls {@code compress()} if the command-line
     * argument is "-" an {@code expand()} if it is "+".
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        if      (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}