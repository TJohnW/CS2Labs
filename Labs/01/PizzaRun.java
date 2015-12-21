/**
 * PizzaRun.java
 * 
 * @author TJohnW
 *
 */
public class PizzaRun {

	private static int SLICE_PER_PIE = 8;
	
	public static void main(String[] args) {
		float price = Float.parseFloat(args[0]);
		int slices = 0;
		
		for(int i = 1; i < args.length; i++) {
			slices += Integer.parseInt(args[i]);
		}
		
		int pizzas = calcWholePies(slices);
		int remainder = SLICE_PER_PIE - (slices % SLICE_PER_PIE);
		float cost = price*pizzas;
		
		String pizzaString = (pizzas == 1) ? "pizza" : "pizzas";
		String sliceString = (remainder == 1) ? "slice" : "slices";
		
		System.out.println("Buy " + pizzas + " " + pizzaString + " for $" + cost);
		System.out.println("There will be " + remainder + " extra " + sliceString);
	}
	
	private static int calcWholePies(int nSlices) {
		int wholePizzas = (int) nSlices/SLICE_PER_PIE;
		int remainder = nSlices % SLICE_PER_PIE;
		if(remainder > 0) {
			wholePizzas++;
		}
		return wholePizzas;	
	}
	
}
