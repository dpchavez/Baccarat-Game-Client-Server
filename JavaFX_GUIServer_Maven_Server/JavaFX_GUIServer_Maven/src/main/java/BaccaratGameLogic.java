import java.util.ArrayList;

//This is the Baccarat Game Logic class...
	public class BaccaratGameLogic
	{
		//THIS CLASS HAS NO MEMBERS....
		
		/************************************** M E T H O D S ******************************************/
		
		/*The method whoWon will evaluate two hands at the end of the game and return a string
		depending on the winner: “Player”, “Banker”, “Draw”*/
		public String whoWon(ArrayList<Card> hand1, ArrayList<Card>hand2) 
		{
			int playerPoints = handTotal(hand1);
			int bankerPoints = handTotal(hand2);
			
			//This will evaluate a natural win...
			if((playerPoints == 8 || playerPoints == 9) && (playerPoints != bankerPoints)) 
				return "Player";
			
			//This will evaluate if the banker won
			else if ((bankerPoints == 8 || bankerPoints == 9) && (bankerPoints != playerPoints))
				return "Banker";
			
			//This will evaluate if the Player wins...
			else if (playerPoints > bankerPoints)
				return "Player";
			
			//Evaluating if banker won
			else if (bankerPoints > playerPoints)
				return "Banker";
			
			//both player and banker have equal points
			else
				return "Draw";
			
		}
		//--------------------------------------------------------------------------------------------
		/*The method handTotal will take a hand and return how many points that hand is worth.*/
		public static int handTotal(ArrayList<Card> hand) 
		{
			//TODO 
			int handPoints = 0;
			
			for( int i = 0; i < hand.size(); i++) 
			{
				if (hand.get(i).value < 10)
					handPoints += hand.get(i).value;
				
				//As states in the rules, the player or banker can only reach to a max of 9 points in 
				//order to win, so we have to readjust the point system
				if (handPoints > 9)
					handPoints = handPoints - 10;
				
			}
						
			return handPoints;
		}
		
		//--------------------------------------------------------------------------------------------
		
		/* The methods evaluateBankerDraw and evaluatePlayerDraw will return true if either one should
		 * be dealt a third card, otherwise return false. */
		
		public static boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard) 
		{
			//TODO
			int total = handTotal(hand); //Will store the total points od banker...
			
			
			//If The Banker’s cards total 2 or less, The Banker gets one additional card (RETURN TRUE)
			if (total <= 2)
				return true;
			
			// if the bankers first two cards total 7 or more, no more cards are dealt.
			else if (total >= 7)
				return false;
			
			/*  If The Bankers first two cards total 3, 4, 5, or 6, it depends on if The Player drew another
				card and if so, the value of that card to determine if The Banker receives another card*/
			
			else if (total == 6) 
			{
				if (playerCard.value == 6 || playerCard.value == 7)
					return true;
				return false;
			}
			//---------------------------------------------------------------------
			else if (total == 5) 
			{
				if (playerCard == null || playerCard.value == 4 || playerCard.value == 5 
						||playerCard.value == 6 || playerCard.value == 7 )
					return true;
				
				return false;
			}
			//---------------------------------------------------------------------
			else if (total == 4) 
			{
				if (playerCard == null || playerCard.value == 2 || playerCard.value == 3 
						||playerCard.value == 4 || playerCard.value == 5 || playerCard.value == 6
						|| playerCard.value == 7)
					return true;
				
				return false;
			}
			//---------------------------------------------------------------------
			else if (total == 3) 
			{
				
				if (playerCard == null || playerCard.value == 0 || playerCard.value == 1 
						||playerCard.value == 2 || playerCard.value == 3 || playerCard.value == 4
						|| playerCard.value == 5 || playerCard.value == 6 || playerCard.value == 7
						|| playerCard.value == 9)
					return true;
				
				return false;
				
			}
			//---------------------------------------------------------------------
			return false; //IGNORE THIS
		}
		
		//----------------------------------------------------------------------------------------------
		public static boolean evaluatePlayerDraw(ArrayList<Card> hand) 
		{
			//TODO
			int total = handTotal(hand); //This is taking the number of points the player currently has
			
			//If the player has 5 or less points... then a third card must be drawn..
			if(total <= 5)
				return true;

			return false;
			
		}
	}
	
	/****************************************************************************************************/