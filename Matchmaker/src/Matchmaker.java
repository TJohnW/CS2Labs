import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * Class to display Matchmaker puzzles for human solving.
 *
 * @author Zack Butler
 */
public class Matchmaker extends JFrame implements ActionListener {
    
    private final int NUM_PUZZLES = 3;
    private final int NUM_COLORS = 6;
    private final int MAX_PUZ_SIZE = 9;
    private final int BSIZE = 52;
    // icons for the meeples in the puzzles
    private ImageIcon[] meeples = new ImageIcon[NUM_COLORS+1];
    // we'll store the puzzle info here (hard-coded)
    private Puzzle[] puzzles;
    private int currPuz;
    private PuzzleVerifier verifier = new SolutionChecker();
    // this array contains the color currently displayed in 
    //  each button within the puzzle - 0 represents the first
    //  color in the array of given colors, and so on.
    private int values[];
    // grid is made of buttons, note just a 1-D array resized for
    // each puzzle (zero is upper left, goes across, continues on 
    // second row and so on).
    private GridButton[] grid;
    private ChoiceButton[] choices = new ChoiceButton[NUM_COLORS];
    private int currChoice = 1;
    // some more GUI components
    private JLabel choicelabel,titlelabel;
    private JPanel puzpanel, pcont;
    private JPanel titlepanel;
    private JButton next;
    private Font titlefont;
    private Insets margin = new Insets(0,0,0,0);

    private String[] colornames = {"Red", "Blue", "Green", "Orange", "Yellow", "Purple"};

    /**
     *  Constructor creates the GUI and sets up the hard-coded puzzle data
     */
    public Matchmaker() {
        // create the hard-coded puzzle data
        populate();
        currPuz = 0;

        // build the title
        titlefont = new Font("Serif",Font.BOLD,24);
        titlepanel = new JPanel();
        titlelabel = new JLabel(puzzles[0].getTitle());
        titlelabel.setFont(titlefont);
        titlepanel.add(titlelabel);
        add(titlepanel,BorderLayout.NORTH);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // load images
        // color choices along the right
        JPanel choicepanel = new CPanel();
        meeples[0] = new ImageIcon("white.png");
        try {
            for (int c = 1; c <= NUM_COLORS; c++) {
                meeples[c] = new ImageIcon("meepleicon"+c+".png");
                choices[c-1] = new ChoiceButton(meeples[c],c);
                choices[c-1].addActionListener(this);
                choicepanel.add(choices[c-1]);
            }
        } catch (Exception e) {}
        choicepanel.add(new JLabel("Active color:"));
        choicelabel = new JLabel(meeples[1]);
        choicepanel.add(choicelabel);
        add(choicepanel,BorderLayout.EAST);

        // grid layout to hold the puzzles themselves
        pcont = new BufferPanel();
        pcont.setBackground(Color.BLACK);
        add(pcont,BorderLayout.CENTER);
        puzpanel = initPuz(puzzles[0]);
        pcont.add(puzpanel,BorderLayout.CENTER);
        // control buttons across the bottom
        JPanel extras = new JPanel();
        JButton howto = new JButton("Howto");
        howto.addActionListener(this);
        JButton check = new JButton("Check");
        check.addActionListener(this);
        JButton restart = new JButton("Reset");
        restart.addActionListener(this);

        // can't go to the next puzzle until you solve the current one!
        next = new JButton("Next ->");
        next.addActionListener(this);
        next.setEnabled(false);
        extras.add(howto);
        extras.add(check);
        extras.add(restart);
        extras.add(next);
        add(extras,BorderLayout.SOUTH);

    }

    /**
     *  Puts the puzzle data into the array for use by the GUI.
     *  Modify this function to modify or create puzzles!
     */
    private void populate() { 
        // Puzzles are created based on only the given colors (no solutions),
        // so that solvers can't cheat by reading the source code!
        puzzles = new Puzzle[NUM_PUZZLES];
        for (int puz = 1; puz <= NUM_PUZZLES; puz++)
            puzzles[puz-1] = new Puzzle("puz"+puz);
    }

    /**
     *  Reset the GUI with the given puzzle data.
     * 
     * @param puz The puzzle data to use
     */
    private JPanel initPuz(Puzzle puz) {
        int psize = puz.getSize();
        values = new int[psize*psize];
        grid = new GridButton[psize*psize];
        JPanel mypan = new JPanel();
        mypan.setLayout(new GridLayout(psize,psize+1,2,2));
        mypan.setBackground(Color.BLACK);
        mypan.setForeground(Color.WHITE);
        for (int b = 0; b < psize*psize; b++) {
            grid[b] = new GridButton(meeples[0],b);
            grid[b].addActionListener(this);
            mypan.add(grid[b]);
            values[b] = 0;
        }
        // set givens
        for (int col = 0; col < puz.getInit().size(); col++) {
            for (Integer loc : puz.getInit().get(col)) {
                grid[loc].setIcon(meeples[col+1]);
                grid[loc].given = true;
                values[loc] = col+1;
            }
        }
        // activate needed colors
        int ch;
        for (ch = 0; ch < puz.numColors(); ch++) {
            choices[ch].setEnabled(true);
            choices[ch].setIcon(meeples[ch+1]);
        }
        // deactivate unneeded colors
        for (; ch < NUM_COLORS; ch++) {
            choices[ch].setEnabled(false);
            choices[ch].setIcon(meeples[0]);
        }
        return mypan;
    }

    /**
     *  Handle all buttons here.
     * @param e Event triggering the listener
     */
    public void actionPerformed(ActionEvent e) {
        // Handle grid buttons
        if (e.getSource() instanceof GridButton) {
            GridButton pushed = (GridButton)(e.getSource());
            // if grid button and not a given, note active color
            if (!grid[pushed.pos].given) {
                int col = values[pushed.pos];
                //   if already that color, turn off
                if (col == currChoice)
                    values[pushed.pos] = 0;                
                //   else turn to that color
                else
                    values[pushed.pos] = currChoice;
                grid[pushed.pos].setIcon(meeples[values[pushed.pos]]);
            }
        }
        // if choice button, highlight that button and change current choice
        else if (e.getSource() instanceof ChoiceButton) {
            currChoice = ((ChoiceButton)(e.getSource())).col;
            choicelabel.setIcon(meeples[currChoice]);
        }
        // handle control buttons
        else {
            String cmd = e.getActionCommand();
            if (cmd.equals("Howto"))
                JOptionPane.showMessageDialog(null,"Click in the grid to add or remove \n" + 
                                              "a meeple of the active color. \n" +
                                              "Given meeples may not be changed or removed.\n" +
                                              "Use the buttons to the right to change \n"+
                                              "the active color.  When every color is in \n" + 
                                              "one contiguous region, click \"Check\" to verify\n" +
                                              "and get the next puzzle.", "Instructions", 
                                              JOptionPane.INFORMATION_MESSAGE);

            else if (cmd.equals("Reset")) {
                pcont.remove(puzpanel);
                puzpanel = initPuz(puzzles[currPuz]);
                pcont.add(puzpanel,BorderLayout.CENTER);
                pcont.validate();
            }
            else if (cmd.equals("Check")) {
                // if check button, check and respond with dialog box
                int result = verifier.checkSolution(values, puzzles[currPuz].numColors());
                if (result == -1) { // success!
                    if (currPuz < puzzles.length-1) {
                        // turn on the "Next" button
                        next.setEnabled(true);
                        JOptionPane.showMessageDialog(null,"Congratulations!", 
                                                      "Congratulations!",
                                                      JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Congratulations! \n" +
                                                       "You have solved all the puzzles!",
                                                      "Congratulations!",
                                                      JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null,"That is not correct.\n" + 
                                                  colornames[result-1] + " is not contiguous.", 
                                                  "Sorry.",
                                                  JOptionPane.ERROR_MESSAGE);
                }
            }
            else if (cmd.equals("Next ->")) {
                // if next button, load next puzzle
                currPuz++;
                pcont.removeAll();
                pcont.validate();
                puzpanel = initPuz(puzzles[currPuz]);
                pcont.add(puzpanel,BorderLayout.CENTER);
                pcont.validate();
                // set title
                titlelabel.setText(puzzles[currPuz].getTitle());
                titlepanel.validate();
                next.setEnabled(false);
                // seems to make sense to reset this
                currChoice = 1;
                choicelabel.setIcon(meeples[currChoice]);
            }
        }
    }

    

    /**
     * Main method simply creates and displays the GUI. 
     * @param args Command-line arguments (ignored)
     */
     public static void main(String[] args) {
         Matchmaker i = new Matchmaker();
         i.pack();
         i.setResizable(false);
         i.setVisible(true);
     }

    class BufferPanel extends JPanel {

        Dimension dim = new Dimension(MAX_PUZ_SIZE*(BSIZE+2)+10,
                                      MAX_PUZ_SIZE*(BSIZE+2)+10);
    
        public Dimension getPreferredSize() {
            return dim;
        }
    }

    
    class CPanel extends JPanel {
        public Dimension getPreferredSize() { return new Dimension
                (BSIZE+30,BSIZE*MAX_PUZ_SIZE+10); } }

    /**
     * This class is used for the buttons on the right that select
     * the active color.
     */
    class ChoiceButton extends JButton {
        public ChoiceButton(Icon i, int c) {
            super(i);
            col = c;
            setBackground(Color.WHITE);
            setForeground(Color.WHITE);
            setFocusPainted(false);
            setMargin(margin);
            setContentAreaFilled(false);
            setOpaque(true);

        }
        public Dimension getPreferredSize() {
            return mySize;
        }
        public int col;
        public Dimension mySize = new Dimension(BSIZE+6,BSIZE+6);
    }

    /**
     * This class is used for the buttons that make up the puzzle itself.
     */
    class GridButton extends JButton {
        public GridButton(Icon i, int which) {
            super(i);
            pos = which;
            given = false;
            setBorderPainted(false);
            setBackground(Color.WHITE);
            setForeground(Color.WHITE);
            setFocusPainted(false);
            setMargin(margin);
            setContentAreaFilled(false);
            setOpaque(true);
        }
        public int pos;
        public boolean given;
        public Dimension mySize = new Dimension(BSIZE,BSIZE);
        public Dimension getPreferredSize() {
            return mySize;
        }
    }

}
