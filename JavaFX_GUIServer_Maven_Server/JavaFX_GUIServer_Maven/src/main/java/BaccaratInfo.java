import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class BaccaratInfo implements Serializable
{
	int clientNumber;
	int bid; //how much client bets
	String who;//who they bet on
	String winner; //who won hand
	double howMuch$; //how much money they won or lost
	double total$;
	ArrayList<String> playerHand; 
	ArrayList<String> dealerHand;
	Boolean natural;
	Boolean again; //if they play another round
	String thirdPlayerCard;//the card they draw
//	int thirdPlayerCardNum;
	String thirdBankerCard;
	
	
	
//	Boolean ifDrawPlayer;//if the player draws
//	Boolean ifDrawBank; //if dealer draws
	
	
	
	BaccaratInfo()//sets up a object with dummy values 
	{
		clientNumber = 0;
		bid = 0;
		who = "";
		winner = "";
		howMuch$ = 0;
		playerHand = new ArrayList<String>();
		dealerHand = new ArrayList<String>();
		total$ = 0;
		natural = false;
		again = false;
		thirdPlayerCard = "No card was drawn";
		thirdBankerCard = "No card was drawn"; 
//		ifDrawPlayer = false;
//		ifDrawBank = false;
	}
	
	
}