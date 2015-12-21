import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.File;

/**
 * This class represents a single Matchmaker puzzle.
 * Note that only the given content of the puzzle is here, not the solution.
 *
 * @author Zack Butler
 */
public class Puzzle {
    private String title;
    private int ncolors;
    private int size;
    private ArrayList<ArrayList<Integer>> givens;

    /**
     *  Constructor. This reads from the given file and expects the
     * following file format:
     * <ul>
     * <li> First line: name of puzzle on a line by itself, can have spaces
     * <li> Next line: number-of-colors size-of-puzzle [all puzzles are square]
     * <li> Remaining lines contain size * size values representing the given
     * color (if any) in each square.  Zero represents an empty square, numbers
     * greater than zero represent each color in turn.
     * </ul>
     * @param filename Name of file containing puzzle data.
     */
    public Puzzle(String filename) {
        try {
            Scanner in = new Scanner(new File(filename));
            title = in.nextLine();
            ncolors = in.nextInt();
            size = in.nextInt();
            givens = new ArrayList<ArrayList<Integer>>();
            for (int i = 0; i < size; i++) {
                givens.add(new ArrayList<Integer>());
            }
            for (int loc = 0; loc < size*size; loc++) {
                int val = in.nextInt();
                if (val < 0 || val > ncolors)
                    throw new InputMismatchException("Incorrect color value");
                if (val > 0)
                    givens.get(val-1).add(loc);
            }
        } catch (IOException e) {
            System.err.println(e);
        } catch (InputMismatchException e) {
            System.err.println("Invalid puzzle file: " + e);
        }
    }

    public String getTitle() {
        return title;
    }

    public int getSize() {
        return size;
    }

    public int numColors() {
        return ncolors;
    }

    public ArrayList<ArrayList<Integer>> getInit() {
        return givens;
    }

}
