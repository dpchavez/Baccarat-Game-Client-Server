import java.util.ArrayList;

public class BaccaratGame
	{
		//These are the members...
		
		ArrayList<Card> playerHand;
		ArrayList<Card> bankerHand;
		BaccaratDealer theDealer;
		double currentBet = 0;
		double totalWinnings = 0;
		BaccaratInfo info = new BaccaratInfo();
		
		/**************************************  M E T H O D  ******************************************/
		
		/*This method will determine if the user won or lost their bet and return the amount won or
		lost based on the value in currentBet*/
		public double evaluateWinnings() 
		{
			//TODO
			BaccaratGameLogic game = new BaccaratGameLogic();
			
			 String theWinner = game.whoWon(playerHand, bankerHand);
			 
			 if (theWinner == info.who && theWinner == "Player") 
			 {
				totalWinnings += currentBet;
//				System.out.println("bruh1");
				return totalWinnings;}
			 
			 else if (theWinner == info.who && theWinner == "Banker")
			{
				totalWinnings += (currentBet - (currentBet * 0.05)); //Taking 5% off
//				System.out.println("bruh2");
				return totalWinnings;
			}
			 
			 else if (theWinner == info.who && theWinner == "Draw")
				 //bet multiplies by 8
			{
				totalWinnings += (currentBet * 8);
//				System.out.println("bruh3");
				return totalWinnings;
			}
			 
			 else
			{
//				System.out.println("bruh4");
				totalWinnings -= currentBet; 
			 	return totalWinnings;
			 	
			}
			
//			return 0;
		}
		
	}