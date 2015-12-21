/*
 * TextUICarRadio.java
 *
 * Version:
 *  $Id: TextUICarRadio.java,v 1.1 2014/02/11 02:56:20 txw6529 Exp $
 * Revisions:
 *  $Log: TextUICarRadio.java,v $
 *  Revision 1.1  2014/02/11 02:56:20  txw6529
 *  CarRadio Project
 *
 *
 */

import java.util.List;
import java.util.Scanner;

/**
 * Provides a text user-interface to a single instance of a 
 * {@link CarRadio CarRadio}.
 *
 * @author Matthew Fluet (mtf)
 */
public class TextUICarRadio {

    /** The main method.
     *
     * @param args The command line arguments (unused)
     */
    public static void main(String[] args) {
	Boolean comment = false ;
        Scanner sc = new Scanner(System.in);

        StationData sd = null;
        do {
            System.out.println();
            System.out.println("Valid locations are: BostonMA DeathValleyCA NewYorkNY RochesterNY");
            System.out.print("location: ");
            if ( sc.hasNext() ) {
                String loc = sc.next();
                System.out.println(loc);
                if (loc.compareTo("BostonMA") == 0) {
                    sd = StationData.BostonMA;
                } else if (loc.compareTo("DeathValleyCA") == 0) {
                    sd = StationData.DeathValleyCA;
                } else if (loc.compareTo("NewYorkNY") == 0) {
                    sd = StationData.NewYorkNY;
                } else if (loc.compareTo("RochesterNY") == 0) {
                    sd = StationData.RochesterNY;
                } else {
                    System.out.println("Unknown location: \"" + loc + "\"");
                }
                sc.nextLine();
            } else {
                return;
            }
        } while (sd == null);

        CarRadio radio = new CarRadio(sd);
        boolean quit = false;
        do {
	    if (!comment)
	    {
                System.out.println();
                List<String> output = radio.display();
                for (String s : output) {
                    System.out.println(s);
                }
            System.out.println();
	    }
	    else
	    {
	        comment = false ;
	    }
            System.out.print("command: ");
            if ( sc.hasNext() ) {
                String cmd = sc.next();
                System.out.println(cmd);
                if (cmd.compareTo("quit") == 0) {
                    quit = true;
                } else if (cmd.compareTo("power") == 0) {
                    radio.powerBtn();
                } else if (cmd.compareTo("volumeUp") == 0) {
                    radio.volumeUpBtn();
                } else if (cmd.compareTo("volumeDown") == 0) {
                    radio.volumeDownBtn();
                } else if (cmd.compareTo("mute") == 0) {
                    radio.muteBtn();
                } else if (cmd.compareTo("amfm") == 0) {
                    radio.amfmBtn();
                } else if (cmd.compareTo("tuneUp") == 0) {
                    radio.tuneUpBtn();
                } else if (cmd.compareTo("tuneDown") == 0) {
                    radio.tuneDownBtn();
                } else if (cmd.compareTo("seekUp") == 0) {
                    radio.seekUpBtn();
                } else if (cmd.compareTo("seekDown") == 0) {
                    radio.seekDownBtn();
                } else if (cmd.compareTo("set") == 0) {
                    radio.setBtn();
                } else if (cmd.compareTo("preset1") == 0) {
                    radio.preset1Btn();
                } else if (cmd.compareTo("preset2") == 0) {
                    radio.preset2Btn();
                } else if (cmd.compareTo("preset3") == 0) {
                    radio.preset3Btn();
                } else if (cmd.compareTo("preset4") == 0) {
                    radio.preset4Btn();
                } else if (cmd.compareTo("preset5") == 0) {
                    radio.preset5Btn();
		} else if (cmd.substring(0,1).equals("#")) {
		    comment = true ;
                } else {
                    System.out.println("Unknown command: \"" + cmd + "\"");
                    System.out.println("Valid commands are: quit power volumeUp volumeDown mute amfm");
                    System.out.println("                    tuneUp tuneDown seekUp seekDown set");
                    System.out.println("                    preset1 preset2 preset3 preset4 preset5");
                }
                sc.nextLine();
            } else {
                System.out.println();
                quit = true;
            }
        } while (! quit);
    }

}
