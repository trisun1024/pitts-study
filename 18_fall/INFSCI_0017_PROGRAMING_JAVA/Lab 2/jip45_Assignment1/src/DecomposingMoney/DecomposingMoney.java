package DecomposingMoney;

import javax.swing.JOptionPane;

public class DecomposingMoney {

	public static void main(String[] args) {
		
		// Input a mount of money
		String MoneyStr = JOptionPane.showInputDialog("Please Input a mount of money from 1 dollar to 9999 dollars");
		
		// Convert to integer
		int Money = Integer.parseInt(MoneyStr);
		
		// Output numbers in bucks, sawbucks, benjamins and grands
		final int divNum = 10;
		int bucks = Money % divNum; 
		int sawbucks = (int) (Money/divNum) % divNum;
		int benjamins = (int) (Money/(divNum*divNum)) % divNum;
		int grands = (int) (Money/(divNum*divNum*divNum));
		
		// Output results
		JOptionPane.showMessageDialog(null, "" + grands + " Grands\n" + benjamins + " Benjamins\n" + sawbucks + " Sawbucks\n" + bucks + " Bucks\n");

	}

}
