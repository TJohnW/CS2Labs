/**
 * File:
 *   $Id$
 *   
 * Revisions:
 *   $Log$
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Implementation of the Brokerage class.  In this simplified simulation
 * the brokerage will manage a single client's investments.  It will
 * also track the movement of the market as a whole.
 * 
 * @author atd: Aaron T Deever
 * @author ADD YOUR NAME HERE
 *
 */
public class Brokerage {
	
	private int investment;
    
    /* Map containing stocks available and their current price per share.
     */
    private Map<String, Integer> market = new HashMap<String, Integer>();
    
    private Map<String, Stock> holding = new HashMap<String, Stock>();
    
    private class Stock {
      	
    	private String name;
    	
    	private int shares; // amt of shares
    	private int price; // price of each share
    	private int total; // total monetary value of stock
    	
    	private Stock(String name, int price, int shares) {
    		this.name = name;
    		this.shares = shares;
    		this.price = price;
    		this.total = shares*price;
    	}
    	
    	// Error checking is done elsewhere. //
    	
    	private void buy(int shares) {
    		this.shares += shares;
    		this.total += shares*this.price;
    	}
    	
    	private void sell(int shares) {
    		this.shares -= shares;
    		this.total -= shares*this.price;
    	}
    	
    	private void update(int newPrice) {
    		this.price = newPrice;
    		this.total = this.price*this.shares;
    	}	
    }
    
    /**
     * Constructor.  Initializes the investor and the market as a whole.
     * In this simplified simulation there is just a single investor and the
     * whole market is tracked by the brokerage.
     * @param initialInvestment initial investment
     */
    public Brokerage(int initialInvestment) {
    	
        investment = initialInvestment;
        /* initialize the market */
        market.put("GOOG", 1183);
        market.put("AMZN", 360);
        market.put("AAPL", 532);
        market.put("YHOO", 38);
        market.put("MSFT", 40);
        market.put("EBAY", 57);
        
    }
    
    /**
     * Add to Investor's holding.  This function should error-check to 
     * ensure the ticker symbol exists, the number of shares requested
     * is a positive value, and that the client has sufficient funds.
     * @param tickerSymbol the particular stock to buy
     * @param shares the number of shares requested
     * @return true if transaction is completed.  False otherwise.
     */
    public boolean increaseHolding(String tickerSymbol, int shares) { 
        if(!market.containsKey(tickerSymbol) || shares <= 0 ||
        	investment < shares*market.get(tickerSymbol))
        		return false;
        
        investment -= shares*market.get(tickerSymbol);
        if(!holding.containsKey(tickerSymbol)) {
        	holding.put(tickerSymbol, new Stock(tickerSymbol, market.get(tickerSymbol), shares));
        } else {
        	holding.get(tickerSymbol).buy(shares);
        }
        return true;
    }
    
    /**
     * Reduce Investor's holding.  This function should error-check to 
     * ensure the ticker symbol exists, and the number of shares to reduce
     * is a positive value no greater than the number currently held.
     * @param tickerSymbol the particular stock to sell
     * @param shares the number of shares to sell
     * @return true if transaction is completed.  False otherwise.
     */
    public boolean reduceHolding(String tickerSymbol, int shares) { 
    	if(!market.containsKey(tickerSymbol) || !holding.containsKey(tickerSymbol) ||
    		shares > holding.get(tickerSymbol).shares)
            	return false;
            
            investment += shares*market.get(tickerSymbol);
            holding.get(tickerSymbol).sell(shares);
            return true;
    }
    
    /**
     * Generates a string to represent the investor's portfolio.  Can be
     * requested in alphabetical order, or in decreasing order of the
     * value of the holdings (shares * price per share).
     * @param choice "N" for by name, "V" for by value
     * @return String representing the portfolio.  This string must
     * include the name, number of shares, price per share, and total 
     * value for each stock in the portfolio.  The entries must be
     * sorted according to the input request.
     */
    public String accessPortfolio(String choice) {
    	List<Stock> holdings = new ArrayList<Stock>(holding.values());
    	
    	switch(choice) {
			case "N":
				Collections.sort(holdings, new Comparator<Stock>() {
					public int compare(Stock o1, Stock o2) {
						return o1.name.compareTo(o2.name);
					}
		    	});
				break;
			case "V":
				Collections.sort(holdings, new Comparator<Stock>() {
					public int compare(Stock o1, Stock o2) {
						return o2.total - o1.total;
					}
		    	});
				break;
			default:
				return "Invalid sort choice.";
		}
    	
    	return printPortfolio(holdings);
    }
    
    private String printPortfolio(List<Stock> holdings) {
    	String out = "CURRENT PORTFOLIO\n";
    	out += "Cash Available: " + this.investment + "\n";
    	out += "SYMBOL SHARES PRICE TOTAL VALUE\n" +
    	"===============================\n";
    	for(Stock s: holdings) {
    		out += String.format("%6s%6d%6d%12d", s.name.toUpperCase(), s.shares, s.price, s.total);
    		out += "\n";
    	}
    	return out;
    }
    
    /**
     * Update the price per share of each stock using a random value to
     * determine the change.  A multiplier is applied to the stock price and
     * the result is rounded to the nearest integer.  A minimum price of $1 is
     * required. (For the given inputs, this constraint will always hold
     * without checking). This method can also be used to update the price of
     * a stock inside any stock object that contains that information.
     * @return A string "ticker" that indicates
     *         the ticker symbols and their prices.
     */
    public String tickerUpdate() { 
        
        String output = "";
        
        for(String str : market.keySet()) { 
            int currVal = market.get(str);
            int num = (int)(Math.random() * 5);
            int newVal;
            switch(num) { 
            case 0:
                newVal = (int)(currVal * .9 + 0.5);
                break;
            case 1:
                newVal = (int)(currVal * .95 + 0.5);
                break;
            case 2:
                newVal = currVal;
                break;
            case 3:
                newVal = (int)(currVal * 1.1 + 0.5);
                break;
            case 4:
            default:
                newVal = (int)(currVal * 1.2 + 0.5);
                break;
            }
            
            if(this.holding.containsKey(str))
            	holding.get(str).update(newVal);
            
            market.put(str,  newVal);
            output += str + " " + newVal + "      ";
        }
    
        return output;
    }
    
    /**
     * Sell all remaining stocks in the portfolio.
     * @return the cash value of the portfolio.
     */
    public int closeAccount() { 
        for(Stock stk: holding.values()) {
        	investment += stk.total;
        }
        holding = new HashMap<String, Stock>();
        return investment;
    }
}