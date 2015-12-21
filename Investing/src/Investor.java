
/**
 * File:
 *   $Id$
 *   
 * Revisions:
 *   $Log$
 */

import java.util.*;

/**
 * Implementation of the Investor class
 * @author atd: Aaron T Deever
 *
 */
public class Investor {

    /* start the Investor out with $10,000 */
    private final int INVESTOR_MONEY = 10000;
    
    /* investor handles investments through a brokerage */
    private Brokerage brokerage;

    // process interactions
    private Scanner in = new Scanner(System.in);

    /**
     * Constructor
     */
    public Investor() { 
        brokerage = null;  // null until account is opened 
    }

    /**
     * Main method for Investor simulation
     * @param args not used
     */
    public static void main(String[] args) {

        if(args.length != 0) { 
            System.out.println("Usage: java Investor");
            return;
        }

        Investor investor = new Investor();
        investor.simulate();
    }

    /**
     * Method to simulate the interaction of an Investor
     * and a Brokerage to buy/sell stocks
     */
    public void simulate() { 

        boolean quit = false;

        do { 
            System.out.print("Options: (O)pen Account / (B)uy / (S)ell");
            System.out.println(
	           " / (P)ortfolio / (T)icker Update / (C)lose Account");
            System.out.print("Command: ");

            if(in.hasNext()) { 
                String cmd = in.next();
                switch(cmd.charAt(0)) { 
                case 'O':
                case 'o':
                    openAccount();
                    break;
                case 'B':
                case 'b':
                    buyStock();
                    break;
                case 'S':
                case 's':
                    sellStock();
                    break;
                case 'P':
                case 'p':
                    printPortfolio();
                    break;
                case 'T':
                case 't':
                    tickerUpdate();
                    break;
                case 'C':
                case 'c':
                    closeAccount();
                    quit = true;
                    break;
                default:
                    break;
                }
            }
            else { 
                return;
            }
            
            System.out.println();

        } while (!quit);
    }

    /**
     * Method to open an account for the Investor using the Brokerage.
     * All that is required to start the account is the Investor and
     * the initial monetary deposit.  Only open an account if one does
     * not exist yet for this Investor.
     */
    public void openAccount() { 
        
        if(brokerage != null) { 
            System.out.println("Investor has already opened an account.");
            return;
        }
        
        brokerage = new Brokerage(INVESTOR_MONEY);
        
        System.out.println(
	       "Account opened with $" + INVESTOR_MONEY);
        
        // display prices of stocks (this updates initial prices)
        System.out.println(brokerage.tickerUpdate());
        
    }

    /**
     * Method to simulate a purchase of shares of a stock using the Brokerage.
     * This method should prompt for two inputs:  the ticker symbol of 
     * the stock to purchase, and the integer number of shares 
     * to purchase.  The method will return a boolean indicating whether
     * or not the purchase was successful.
     */
    public void buyStock() { 
        
        if(brokerage == null) { 
            System.out.println("Must open account before buying stock.");
            return;
            
        }
        
        System.out.print("Input ticker symbol of stock to buy: ");
        String tickerSymbol = in.next();
        System.out.print("Input integer number of shares to buy: ");
        boolean success;
        if(in.hasNextInt()) { 
            int shares = in.nextInt();
            success = brokerage.increaseHolding(tickerSymbol, shares);
        }
        else { 
            in.next();  // discard non-integer token
            System.out.println("Number of shares to buy must be an integer.");
            success = false;
        }
        
        if(success) { 
            System.out.println("Transaction completed.");
        }
        else { 
            System.out.println("Unable to complete transaction.");
        }
    }
    
    /**
     * Method to simulate Investor selling a stock.
     * This method should prompt for two inputs:  the ticker symbol of 
     * the stock to sell, and the integer number of shares 
     * to sell.  The method will return a boolean indicating whether
     * or not the sale was successful.
     */
    public void sellStock() { 
        
        if(brokerage == null) { 
            System.out.println("Must open account before selling stock.");
            return;
            
        }
        
        System.out.print("Input ticker symbol of stock to sell: ");
        String tickerSymbol = in.next();
        System.out.print("Input integer number of shares to sell: ");
        boolean success;
        if(in.hasNextInt()) { 
            int shares = in.nextInt();
            success = brokerage.reduceHolding(tickerSymbol, shares);
        }
        else { 
            in.next();  // discard non-integer token
            System.out.println(
	           "Number of shares to sell must be an integer.");
            success = false;
        }
        
        if(success) { 
            System.out.println("Transaction completed.");
        }
        else { 
            System.out.println("Unable to complete transaction.");
        }
    }

    /**
     * Method to access and print the current portfolio 
     * of the Investor.  This method must prompt for input indicating 
     * whether the output portfolio should be presented sorted by name
     * or sorted by the value (largest to smallest) 
     * of each individual stock holding.  
     */
    public void printPortfolio() { 
        
        if(brokerage == null) { 
            System.out.println(
	           "Must open account before accessing portfolio.");
            return;
            
        }
        
        System.out.print("Print by (N)ame or (V)alue? ");
        String choice = in.next();
        String output;
        
        switch(choice.charAt(0)) { 
        case 'N':
        case 'n':
            output = brokerage.accessPortfolio("N");
            break;
        case 'V':
        case 'v':
            output = brokerage.accessPortfolio("V");
            break;
        default:
            output = "Unable to complete request.";
            break;
        }
        
        System.out.println(output);
        
    }

    /**
     * Method to update the price of each stock and output a ticker tape
     * indicating the updated values.
     */
    public void tickerUpdate() { 
        if(brokerage == null) { 
            System.out.println("Must open account before accessing ticker.");
            return;
            
        }
        System.out.println(brokerage.tickerUpdate());
    }
    
    /**
     * Method to close account and end the simulation.  This method should
     * automatically sell all remaining stocks and should output the
     * cash that the Investor ended up with.
     */
    
    public void closeAccount() { 
        if(brokerage == null) { 
            System.out.println("Simulation ended without account creation.");
            return;
            
        }
        int cash = brokerage.closeAccount();
        
        System.out.println(
	       "Investor closed account with $" + cash); 
    }
}
