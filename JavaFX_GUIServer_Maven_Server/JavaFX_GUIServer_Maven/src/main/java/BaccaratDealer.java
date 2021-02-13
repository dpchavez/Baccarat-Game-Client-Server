import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class BaccaratDealer
	{
		//This is it's member...
		ArrayList<Card>deck = new ArrayList<Card>(52); //This array list will hold the data of the deck...
		//WAS STATIC BEFORE

		/************************************** M E T H O D S ******************************************/
		
		/*generateDeck will generate a new standard 52 card deck where each card is an
		instance of the Card class in the ArrayList<Card> deck.*/
		public void generateDeck() //WAS STATIC BEFORE NOW NOT
		{
			//Creating a vector that will be holding the suits...
			Vector <String> suits = new Vector <String>(4);
			suits.add("Spades");
			suits.add("Hearts");
			suits.add("Diamonds");
			suits.add("Clubs");
			
			//System.out.print(suits);
			
			
			//Creating a vector that will store the ranks...
			 Vector<Integer> ranks = new Vector <Integer>(13);
			
			//Adding values to the cards....
			for (int i = 1; i < 14; i++) 
			{
				ranks.add(i);
				//System.out.print(ranks);
			}
			
			//System.out.print(ranks);
			
			
			//Now adding elements into the deck...
			for (int i = 0; i < suits.size(); i++) 
			{
				for(int j = 0; j < ranks.size(); j++){
					
					deck.add(new Card(suits.get(i), ranks.get(j)));
				}
				
			}
			
			
		}
		
		//--------------------------------------------------------------------------------------------
		

		//dealHand will deal two cards and return them in an ArrayList<Card>.
		public ArrayList<Card>dealHand()
		{
			
			ArrayList <Card> dummy = new ArrayList <Card> (); //IGNORE THIS FOR NOW
			
			//TODO
			//Gettong the first and second card of the deck...
			Card firstCard = deck.get(0);
			//Getting the second card...
			Card secondCard = deck.get(1);
			
			//Now adding the cards to a new array list...
			dummy.add(firstCard);
			dummy.add(secondCard);
			
			//Now we have to delete the cards that were picked from the deck...
			deck.remove(0); //deleting first element...
			deck.remove(0); //after deleting the first element, the second becomes the head of the list...
			
			//Now we return the array list that contains the first 2 cards of the deck...
			return dummy;
		}
		//--------------------------------------------------------------------------------------------
		
		//drawOne will deal a single card and return it
		public Card drawOne() 
		{
			Card card = deck.get(0); //Getting the first card of the deck...
			
			//Now deleting the first item from the array list
			deck.remove(0);
			
			return card; //IGNORE THIS FOR NOW...
		}
		
		//--------------------------------------------------------------------------------------------
		
		/*shuffleDeck will create a new deck of 52 cards and “shuffle”; randomize the cards in that
		  ArrayList<Card>*/
		public void shuffleDeck() 
		{
			Collections.shuffle(deck);
			
			
		}
		
		//--------------------------------------------------------------------------------------------
		
		//deckSize will just return how many cards are in this.deck at any given time
		public int deckSize() 
		{
		
			return deck.size();
		}
		//---------------------------------------------------------------------------------------------
		
		//these functions make it able for client to read cards
		//converts card to string
		public String cToS(Card theCard)
		{
			String suite = theCard.suite;
			return suite;
		}
		//converts card to Int
		public int cToI(Card theCard)
		{
			int number = theCard.value;
			return number;
		}
		//-----------------------------------------------------------------------------------------------
		/***********************************************************************************************/
		
	}